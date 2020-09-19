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

public class Ping extends AsyncTask<Object[], Object[], List<String>> {

    private static String IP = "192.168.0.1";
    private static int PORT = 4500;
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

            try {
                socket = new Socket("192.168.100.6", 1408);
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

                        Chat.update(null, response, responseString.contains("error") ? "Error" : "Sent", responseString.contains("error"));
                    }

                } catch (JSONException e) {
                    Log.d("USERS", "parsing JSON: " + e.getMessage());
                }
            } catch (IOException e) {
                Log.d("DEBUG ERROR", e.toString());
            }
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