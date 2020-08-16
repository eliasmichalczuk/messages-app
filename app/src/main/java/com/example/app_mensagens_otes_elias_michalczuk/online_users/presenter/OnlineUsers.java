package com.example.app_mensagens_otes_elias_michalczuk.online_users.presenter;

import android.util.Log;

import com.example.app_mensagens_otes_elias_michalczuk.BasePresenter;
import com.example.app_mensagens_otes_elias_michalczuk.chat.view.ChatConversationFragment;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.model.UsersUpdater;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.view.OnlineUsersActivity;

public class OnlineUsers implements BasePresenter {
    private OnlineUsersActivity view;
    public OnlineUsers(OnlineUsersActivity view) {
        this.view = view;
    }
    @Override
    public void start() {
//        new UsersUpdater().execute();
    }
}
