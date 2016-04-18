package com.aleksodem.tasklightitdemchenko.model.pojo;

public class AuthorizationRequest {

    private String username;
    private String password;

    public AuthorizationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
