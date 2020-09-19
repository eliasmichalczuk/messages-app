package com.example.app_mensagens_otes_elias_michalczuk.online_users.model;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OnlineUsers {

    private static MutableLiveData<List<String>> users = new MutableLiveData<List<String>>();
    private static OnlineUsers instance = null;

    private OnlineUsers() {
    }

    public static void update(Context context, JSONObject json) throws JSONException {
        JSONArray users = json.getJSONArray("online-users");
        List onlineUsers = new ArrayList<String>();
        int index = 0;
        try {
            while (true) {
                users.getString(index);
                onlineUsers.add(users.getString(index));
                index++;
            }
        } catch (JSONException e) {
//            Log.i("OnlineUsers", "Json array out of bounds, OK");
        }
//        Log.d("USERS deserialized", " array de usuarios: " + users.toString());
        OnlineUsers.users.postValue(onlineUsers);
    }

    public static List<String> mock() {
        ArrayList arr = new ArrayList();
        arr.add("user 123");
        arr.add("user 456");
        arr.add("user 786");
        return arr;
    }

//    public static void setOnlineUsers(List<String> onlineUsers) {
//        OnlineUsers.onlineUsers = onlineUsers;
//    }

    public static MutableLiveData<List<String>> getUsers() {
        return OnlineUsers.users;
    }

    public OnlineUsers getInstance() {
        if (instance == null) {
            this.instance = new OnlineUsers();
        }
        return instance;
    }
}
