package com.onlinebookstore.entity;

public class AuthRequestResponse {

    private String userName;
    private String password;
    private String token;
    private String email;

    public AuthRequestResponse() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AuthRequestResponse(String userName, String password, String token, String email) {
        this.userName = userName;
        this.password = password;
        this.token = token;
        this.email = email;
    }
}
