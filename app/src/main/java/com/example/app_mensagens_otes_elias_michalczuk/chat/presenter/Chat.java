package com.example.app_mensagens_otes_elias_michalczuk.chat.presenter;

import com.example.app_mensagens_otes_elias_michalczuk.chat.ChatContract;
import com.example.app_mensagens_otes_elias_michalczuk.chat.view.ItemDetailFragment;

public class Chat implements ChatContract.Presenter {
    private ItemDetailFragment view;
    public Chat(ItemDetailFragment view) {
        this.view = view;
    }

    @Override
    public void start() {

    }
}
