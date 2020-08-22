package com.example.app_mensagens_otes_elias_michalczuk.online_users.model;

public class User {

    private static User instance = null;
    private String username;
    private String address;
    private boolean connected;

    private User() {
    }

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public String getAddress() {
        return address;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
