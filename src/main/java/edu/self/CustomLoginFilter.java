package edu.self;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Date;

import javax.servlet.ServletException;

public class CustomLoginFilter extends OncePerRequestFilter {

    public CustomLoginFilter(String url, AuthenticationManager authenticationManager) {
//        super(new AntPathRequestMatcher(url, HttpMethod.GET.name()));
    }

//    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        return null;
    }

//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse) res;
//        String token = request.getHeader("SECURITY_TOKEN_KEY");
//        if (token != null) {
//            Authentication authResult;
//            try {
//                authResult = attemptAuthentication(request, response);
//                if (authResult == null) {
//                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                    return;
//                }
//            } catch (AuthenticationException failed) {
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                return;
//            }
//
//            try {
//                SecurityContextHolder.getContext().setAuthentication(authResult);
//            } catch (Exception e) {
//                logger.error(e.getMessage(), e);
//                if (e.getCause() instanceof AccessDeniedException) {
//                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                    return;
//                }
//            }
//        }
//        chain.doFilter(request, response);// return to others spring security filters
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        Authentication authResult;
        try {
            authResult = attemptAuthentication(request, response);
            if (authResult == null) {

                String error = "Expired or invalid JWT token";
                ApiError errorResponse = new ApiError("message");
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                response.getWriter().write(convertObjectToJson(errorResponse));
                return;
            }
        } catch (AuthenticationException failed) {
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error");
            return;
        }

        try {
            SecurityContextHolder.getContext().setAuthentication(authResult);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            if (e.getCause() instanceof AccessDeniedException) {
//                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error");
                return;
            }
        }


        filterChain.doFilter(request, response);// return to others spring security filters
    }

    private String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
