package com.example.app_mensagens_otes_elias_michalczuk.chat.services;

import com.example.app_mensagens_otes_elias_michalczuk.chat.ChatContract;
import com.example.app_mensagens_otes_elias_michalczuk.connection.SendMessage;

public class ChatService {

    public void sendText(String sender, String msg, String receiver){
        new SendMessage(sender, msg, receiver).execute();
    }
}
