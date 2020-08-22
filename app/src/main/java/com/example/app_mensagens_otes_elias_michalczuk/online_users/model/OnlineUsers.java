package com.example.app_mensagens_otes_elias_michalczuk.online_users.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class OnlineUsers {

    private static OnlineUsers instance = null;
    public List<String> onlineUsers;
    private OnlineUsers() {
        this.onlineUsers = new ArrayList<>();
    }
    public OnlineUsers getInstance() {
        if (instance == null) {
            this.instance = new OnlineUsers();
        }
        return instance;
    }

//    public static void setOnlineUsers(List<String> onlineUsers) {
//        OnlineUsers.onlineUsers = onlineUsers;
//    }

    public static List<String> mock() {
        ArrayList arr = new ArrayList();
        arr.add("user 123");
        arr.add("user 456");
        arr.add("user 786");
        return arr;
    }
}
