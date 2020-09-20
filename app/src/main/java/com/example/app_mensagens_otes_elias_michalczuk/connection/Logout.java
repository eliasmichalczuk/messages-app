package com.example.app_mensagens_otes_elias_michalczuk.connection;

import android.os.AsyncTask;
import android.util.Log;

import com.example.app_mensagens_otes_elias_michalczuk.chat.model.Chat;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.model.OnlineUsers;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class Logout extends AsyncTask<Object[], Object[], List<String>> {

    private static String IP = "192.168.0.1";
    private static int PORT = 4500;
    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private String username;

    public Logout(String username){
        this.username = username;
    }

    @Override
    protected void onPostExecute(List<String> users) {
    }

    @Override
    protected List<String> doInBackground(Object[]... objects) {

        try {
            socket = new Socket("192.168.100.6", 1408);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            try {

                JSONObject loginJson
                        = new JSONObject("{ \"logout\": { \"user-id\":\"" + username + "\" } }");
                out.println(loginJson.toString());
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("LOGOUT ERROR ", e.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}