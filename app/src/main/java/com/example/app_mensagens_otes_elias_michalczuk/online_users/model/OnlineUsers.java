package com.example.app_mensagens_otes_elias_michalczuk.online_users.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class OnlineUsers {

    public static List<String> onlineUsers = new ArrayList<>();

    public OnlineUsers() {
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
