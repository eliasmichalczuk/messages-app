package com.example.app_mensagens_otes_elias_michalczuk.chat;

import com.example.app_mensagens_otes_elias_michalczuk.BasePresenter;
import com.example.app_mensagens_otes_elias_michalczuk.BaseView;
import com.example.app_mensagens_otes_elias_michalczuk.chat.presenter.Chat;

public interface ChatContract {
    interface View extends BaseView<Chat> {
    }

    interface Presenter extends BasePresenter
    {
    }

    interface Service {
        public void sendText(String msg, String user);
    }
}
