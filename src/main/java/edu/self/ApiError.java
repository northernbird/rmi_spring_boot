package edu.self;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ApiError {
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    private String error;
    public ApiError(String error) {
        this.error = error;
    }

}
