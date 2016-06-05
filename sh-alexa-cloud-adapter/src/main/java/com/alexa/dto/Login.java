package com.alexa.dto;


public class Login {
    private String username;
    private String password;
    private String state;

    public Login() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Login(String state) {
        this.state = state;
    }
}
