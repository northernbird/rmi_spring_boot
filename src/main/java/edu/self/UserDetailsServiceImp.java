//package edu.self;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//
//@Service
//public class UserDetailsServiceImp implements UserDetailsService {
//
////    @Autowired
////    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if (username == null) {
//            throw new UsernameNotFoundException("empty");
//        }
//
//        // 本来ならDBアクセスしてパスワードを取得するところだが、サンプルなのでプログラム直書き
//        String password;
//        switch (username) {
//                case "user":
//                password = "pass";
//                break;
//            default:
//                throw new UsernameNotFoundException("not found");
//        }
//
//        return new User(username, password, Collections.emptySet());
//    }
//
//}
