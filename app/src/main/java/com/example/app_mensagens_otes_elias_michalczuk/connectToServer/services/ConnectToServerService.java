package com.example.app_mensagens_otes_elias_michalczuk.connectToServer.services;

import com.example.app_mensagens_otes_elias_michalczuk.connection.LoginAndUpdateOnlineUsers;
import com.example.app_mensagens_otes_elias_michalczuk.connectToServer.view.ConnectToServerActivity;

public class ConnectToServerService {

    private final ConnectToServerActivity view;

    public ConnectToServerService(ConnectToServerActivity view) {
        this.view = view;
    }

    public void login() {
        new LoginAndUpdateOnlineUsers(this).execute();
    }

    public void finishedLoggingIn() {
        this.view.finishedLoggingIn();
    }

    public void errorOnLoggingIn(String error) {
        this.view.errorOnLoggingIn(error);
    }
}
