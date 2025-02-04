package com.example.app_mensagens_otes_elias_michalczuk.online_users.model;

public class User {

    private static User instance = null;
    private String username;
    private String address;
    private boolean connected;
    private String login;
    private int id = 0;

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

    public void setLogin(String login) {
        this.login = login;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public void logout() {
        login = null;
        id = 0;
        username = null;
    }

    public void login(String login, int id) {
        this.login = login;
        this.id = id;
    }
}
