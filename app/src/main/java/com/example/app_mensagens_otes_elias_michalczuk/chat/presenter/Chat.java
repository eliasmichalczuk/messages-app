package com.example.app_mensagens_otes_elias_michalczuk.chat.presenter;

import com.example.app_mensagens_otes_elias_michalczuk.chat.ChatContract;
import com.example.app_mensagens_otes_elias_michalczuk.chat.view.ChatConversationFragment;

public class Chat implements ChatContract.Presenter {
    private ChatConversationFragment view;
    public Chat(ChatConversationFragment view) {
        this.view = view;
    }

    @Override
    public void start() {

    }
}
