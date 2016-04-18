package com.aleksodem.tasklightitdemchenko.model.pojo;

public class AuthorizationResponse {

    private boolean success;
    private String token;

    public AuthorizationResponse(boolean success, String token) {
        this.success = success;
        this.token = token;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getToken() {
        return token;
    }
}
