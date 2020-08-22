package com.example.app_mensagens_otes_elias_michalczuk.chat;

import com.example.app_mensagens_otes_elias_michalczuk.BasePresenter;
import com.example.app_mensagens_otes_elias_michalczuk.BaseView;
import com.example.app_mensagens_otes_elias_michalczuk.chat.presenter.ChatPresenter;

public interface ChatContract {
    interface View extends BaseView<ChatPresenter> {
    }

    interface Presenter extends BasePresenter
    {
    }

    interface Service {
        public void sendText(String msg, String user);
    }
}
