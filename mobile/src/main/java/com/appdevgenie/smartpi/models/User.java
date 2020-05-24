package com.appdevgenie.smartpi.models;

public class User {

    private String user_id;
    private String token;

    public User() {
    }

    public User(String user_id, String token) {
        this.user_id = user_id;
        this.token = token;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
