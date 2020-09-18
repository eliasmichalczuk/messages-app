package com.example.app_mensagens_otes_elias_michalczuk.chat.model;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.app_mensagens_otes_elias_michalczuk.connection.Ping;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.model.OnlineUsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Chat {

    private static MutableLiveData<List<Message>> messages = new MutableLiveData<List<Message>>();

    public static void update(Context context, JSONObject json, String status, boolean error) throws JSONException {
        JSONObject jsonMsg = json.getJSONObject("message");
        Message message = null;
        try {
            message = new Message(jsonMsg.get("content").toString(),
                    jsonMsg.get("sender").toString(),
                    jsonMsg.get("receiver").toString(),
                    jsonMsg.get("address").toString(), status, error);
        } catch (JSONException e) {
            Log.i("MessageJSONPARSER", "ERR " + e.getMessage());
        }
        List<Message> msgs = getMessages().getValue();

        if (msgs == null) {
            msgs = new ArrayList<>();
            msgs.add(message);
            messages.postValue(msgs);
        } else {
            msgs.add(message);
            messages.postValue(msgs);
        }
    }

    public static MutableLiveData<List<Message>> getMessages() {
        return messages;
    }
}
