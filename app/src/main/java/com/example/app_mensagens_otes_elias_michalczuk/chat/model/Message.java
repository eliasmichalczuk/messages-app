package com.example.app_mensagens_otes_elias_michalczuk.chat.model;

import com.example.app_mensagens_otes_elias_michalczuk.storage.MessageDB;

public class Message {
    public String content;
    public String sender;
    public String receiver;
    public String address;
    public String status;
    public boolean error;

    public Message(String content, String sender, String receiver, String address, String status, boolean error) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.address = address;
        this.status = status;
        this.error = error;
    }

    private Message() {
    }

    public static Message fromMessageDB(MessageDB mdb) {
        return new Message(mdb.getContent(), mdb.getSender(), mdb.getReceiver(), mdb.getAddress(), mdb.getStatus(), mdb.isError());
    }
}
