package com.example.app_mensagens_otes_elias_michalczuk.chat.model;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.json.JSONException;
import org.json.JSONObject;

public class Chat {

    private static MutableLiveData<Message> message = new MutableLiveData<Message>();

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
        Chat.message.postValue(message);
    }

    public static MutableLiveData<Message> getMessage() {
        return message;
    }
}
