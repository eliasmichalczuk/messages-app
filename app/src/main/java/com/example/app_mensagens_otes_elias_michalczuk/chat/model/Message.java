package com.example.app_mensagens_otes_elias_michalczuk.chat.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public Message() {}

//    public static List<Message> mock() {
//        ArrayList arr = new ArrayList();
//        arr.add(new Message("insane message", "sender", "receiver", "123"));
//        arr.add(new Message("insane message", "sender", "receiver", "123"));
//        arr.add(new Message("insane message", "sender", "receiver", "123"));
//        arr.add(new Message("insane message", "sender", "receiver", "123"));
//        arr.add(new Message("insane message", "sender", "receiver", "123"));
//        arr.add(new Message("insane message", "sender", "receiver", "123"));
//        arr.add(new Message("a little bigger message !!!", "receiver", "sender", "124"));
//        arr.add(new Message("a little bigger message !!!", "receiver", "sender", "124"));
//        arr.add(new Message("a little bigger message !!!", "receiver", "sender", "124"));
//        arr.add(new Message("a little bigger message !!!", "receiver", "sender", "124"));
//        arr.add(new Message("a little bigger message !!!", "receiver", "sender", "124"));
//        arr.add(new Message("a little bigger message !!!", "receiver", "sender", "124"));
//        arr.add(new Message("a little bigger message !!!", "receiver", "sender", "124"));
//        arr.add(new Message("a little bigger message !!!", "receiver", "sender", "124"));
//        return arr;
//    }
}
