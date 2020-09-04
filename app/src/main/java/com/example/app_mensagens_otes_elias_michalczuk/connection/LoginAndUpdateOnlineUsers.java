package com.example.app_mensagens_otes_elias_michalczuk.connection;

import android.os.AsyncTask;
import android.util.Log;

import com.example.app_mensagens_otes_elias_michalczuk.login.services.LoginService;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.model.OnlineUsers;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.CharBuffer;
import java.util.List;

public class LoginAndUpdateOnlineUsers extends AsyncTask<Object[], Object[], List<String>> {

    private static String IP = "192.168.0.1";
    private static int PORT = 4500;
    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private LoginService service;
    private boolean error = false;

    public LoginAndUpdateOnlineUsers(LoginService service) {
        this.service = service;
    }

    @Override
    protected void onPostExecute(List<String> users) {
        if (this.error) {
            this.service.errorOnLoggingIn();
            return;
        }
        this.service.finishedLoggingIn();
    }

    @Override
    protected List<String> doInBackground(Object[]... objects) {

//        try {
//            JSONObject loginJson
//                    = new JSONObject("{ \"logout\": { \"user-id\":\"" + User.getInstance().getUsername() + "\" } }");
//            User.getInstance().setConnected(true);
//
//            JSONObject json = new JSONObject("{ \"online-users\": [ \"broadcast\", \"o.professor\", \"o.aluno\", \"outro.aluno\", \"mais.um.aluno\" ] }");
//            OnlineUsers.update(null, json);
//        } catch (Exception e) {
//            Log.e("OnlineUsersUpdater", "Error creating json mock " + e.getMessage());
//            e.printStackTrace();
//        }

//        return null;

        try {
            socket = new Socket("192.168.100.6", 1408);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            try {

                JSONObject loginJson
                        = new JSONObject("{ \"login\": { \"user-id\":\"" + User.getInstance().getUsername() + "\" } }");
                out.println(loginJson.toString());

                JSONObject json = new JSONObject(in.readLine());
                if (json.has("online-users")) {
                }
                User.getInstance().setConnected(true);
                OnlineUsers.update(null, json);

            } catch (JSONException e) {
                this.error = true;
                e.printStackTrace();
                Log.e("OnlineUsersUpdater", "Error creating json mock " + e.getMessage());
            }
        } catch (IOException e) {
            this.error = true;
            e.printStackTrace();
//            Log.d("CONNECTION ERROR", e.toString());
        }
        return null;
    }


    public boolean sendMessage(String message) {
        out.println(message);
        return true;
    }

    private void closeAll() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}