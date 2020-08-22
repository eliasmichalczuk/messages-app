package com.example.app_mensagens_otes_elias_michalczuk.online_users.presenter;

import com.example.app_mensagens_otes_elias_michalczuk.BasePresenter;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.view.OnlineUsersActivity;

public class OnlineUsersPresenter implements BasePresenter {
    private OnlineUsersActivity view;
    public OnlineUsersPresenter(OnlineUsersActivity view) {
        this.view = view;
    }
    @Override
    public void start() {
//        new UsersUpdater().execute();
    }
}
