package com.example.app_mensagens_otes_elias_michalczuk.connection;

import android.os.AsyncTask;
import android.util.Log;

import com.example.app_mensagens_otes_elias_michalczuk.chat.model.Chat;
import com.example.app_mensagens_otes_elias_michalczuk.connection.model.Connection;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class SendMessage extends AsyncTask<Object[], Object[], List<String>> {

    String sender;
    String receiver;
    String msg;
    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;


    public SendMessage(String sender, String msg, String receiver) {
        this.sender = sender;
        this.msg = msg;
        this.receiver = receiver;
    }

    @Override
    protected void onPostExecute(List<String> users) {
    }

    @Override
    protected List<String> doInBackground(Object[]... objects) {
        JSONObject json = null;
        try {
            socket = new Socket(Connection.getInstance().getAddress(), Connection.getInstance().getPort());
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            try {
                json
                        = new JSONObject("" +
                        "{ \"message\": { \"sender\": \"" + this.sender + "\"," +
                        "\"receiver\":\"" + this.receiver + "\"," +
                        "\"address\" : \"" + Connection.getInstance().getAddress() + "\", \"content\" :\"" + this.msg + "\" } }");
                out.println(json.toString());
                User.getInstance().setConnected(true);


                String response = in.readLine();
                Chat.update(null, json, response.contains("error") ? "Not Sent" : "Sent", response.contains("error"));
            } catch (JSONException e) {
                Log.d("SEND MESSAGE ", "parsing JSON: " + e.getMessage());
            }
        } catch (IOException e) {
            try {
                Chat.update(null, json, "Not Sent", true);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            Log.d("DEBUG ERROR", e.toString());
        }
        return null;
    }
}