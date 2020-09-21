package com.example.app_mensagens_otes_elias_michalczuk.connection;

import android.os.AsyncTask;
import android.util.Log;

import com.example.app_mensagens_otes_elias_michalczuk.connectToServer.services.ConnectToServerService;
import com.example.app_mensagens_otes_elias_michalczuk.connection.model.Connection;
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

public class LoginAndUpdateOnlineUsers extends AsyncTask<Object[], Object[], List<String>> {

    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private ConnectToServerService service;
    private String errorMessage;

    public LoginAndUpdateOnlineUsers(ConnectToServerService service) {
        this.service = service;
    }

    @Override
    protected void onPostExecute(List<String> users) {
        if (this.errorMessage != null) {
            this.service.errorOnLoggingIn(errorMessage);
            return;
        }
        this.service.finishedLoggingIn();
    }

    @Override
    protected List<String> doInBackground(Object[]... objects) {
        try {
            socket = new Socket(Connection.getInstance().getAddress(), Connection.getInstance().getPort());
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            try {

                JSONObject loginJson
                        = new JSONObject("{ \"login\": { \"user-id\":\"" + User.getInstance().getUsername() + "\" } }");
                out.println(loginJson.toString());

                JSONObject json = new JSONObject(in.readLine());
                if (!json.has("error")) {
                    User.getInstance().setConnected(true);
                    OnlineUsers.update(null, json);
                } else {
                    this.errorMessage = "username already in use";
                }


            } catch (JSONException e) {
                this.errorMessage = "JSON parse error";
                e.printStackTrace();
                Log.e("OnlineUsersUpdater", "Error creating json mock " + e.getMessage());
            }
        } catch (IOException e) {
            this.errorMessage = "Erro de servidor";
            e.printStackTrace();
        }
        return null;
    }
}