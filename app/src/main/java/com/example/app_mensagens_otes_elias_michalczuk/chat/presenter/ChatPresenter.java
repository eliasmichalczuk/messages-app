package com.example.app_mensagens_otes_elias_michalczuk.chat.presenter;

import com.example.app_mensagens_otes_elias_michalczuk.chat.ChatContract;
import com.example.app_mensagens_otes_elias_michalczuk.chat.view.ChatConversationFragment;

public class ChatPresenter implements ChatContract.Presenter {
    private ChatConversationFragment view;
    public ChatPresenter(ChatConversationFragment view) {
        this.view = view;
    }

    @Override
    public void start() {

    }
}
