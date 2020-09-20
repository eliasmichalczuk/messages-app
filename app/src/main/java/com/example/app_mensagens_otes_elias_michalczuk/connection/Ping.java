package com.example.app_mensagens_otes_elias_michalczuk.connection;

import android.os.AsyncTask;
import android.util.Log;

import com.example.app_mensagens_otes_elias_michalczuk.chat.model.Chat;
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

public class Ping extends AsyncTask<Object[], Object[], List<String>> {

    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;


    public Ping() {
    }

    @Override
    protected void onPostExecute(List<String> users) {
//        this.osa.showUsers();
    }

    @Override
    protected List<String> doInBackground(Object[]... objects) {

        boolean tryAgain = true;
        while (tryAgain) {
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (User.getInstance().getUsername() != null) {
                try {
                    socket = new Socket(Connection.getInstance().getAddress(), Connection.getInstance().getPort());
                    out = new PrintWriter(socket.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    try {
                        JSONObject json
                                = new JSONObject("{ \"ping\": { \"user-id\":\"" + User.getInstance().getUsername() + "\" } }");
                        out.println(json.toString());
                        String responseString = in.readLine();
                        JSONObject response = new JSONObject(responseString);
                        if (response.has("online-users")) {
                            User.getInstance().setConnected(true);
                            OnlineUsers.update(null, response);
                        }
                        if (response.has("message")) {
                            Chat.update(null, response, "Received", responseString.contains("error"));
                        }

                    } catch (JSONException e) {
                        Log.d("USERS", "parsing JSON: " + e.getMessage());
                    }
                } catch (IOException e) {
                    Log.d("DEBUG ERROR", e.toString());
                }
            }
        }
        return null;
    }
}