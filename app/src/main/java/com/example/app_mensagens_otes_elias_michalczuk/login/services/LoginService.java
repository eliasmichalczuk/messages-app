package com.example.app_mensagens_otes_elias_michalczuk.login.services;

import com.example.app_mensagens_otes_elias_michalczuk.connection.LoginAndUpdateOnlineUsers;
import com.example.app_mensagens_otes_elias_michalczuk.login.view.LoginActivity;

public class LoginService {

    private final LoginActivity view;

    public LoginService(LoginActivity view) {
        this.view = view;
    }

    public void login() {
        new LoginAndUpdateOnlineUsers(this).execute();
    }

    public void finishedLoggingIn() {
        this.view.finishedLoggingIn();
    }

    public void errorOnLoggingIn() {
        this.view.errorOnLoggingIn("ERROR");
    }
}
