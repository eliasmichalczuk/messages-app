package com.example.app_mensagens_otes_elias_michalczuk.connection.model;

import lombok.Getter;

@Getter
public class Connection {

    private static Connection instance;
    private String address = "192.168.100.6";
    private int port = 1408;
    private Connection() {}

    public static Connection getInstance() {
        if (instance == null) {
            instance = new Connection();
        }
        return instance;
    }

    public void makeItHappen(String address, int port) {
        this.address = address;
        this.port = port;
    }
}
