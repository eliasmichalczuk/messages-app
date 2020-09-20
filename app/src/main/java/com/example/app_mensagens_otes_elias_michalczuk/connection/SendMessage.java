package com.example.app_mensagens_otes_elias_michalczuk.connection;

import android.os.AsyncTask;
import android.util.Log;

import com.example.app_mensagens_otes_elias_michalczuk.chat.model.Chat;
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

    private static String IP = "192.168.0.1";
    private static int PORT = 4500;
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
//        this.osa.showUsers();
    }

    @Override
    protected List<String> doInBackground(Object[]... objects) {
//        try {
//            JSONObject json
//                    = new JSONObject("" +
//                    "{ \"message\": { \"sender\": \"" + this.sender + "\"," +
//                    "\"receiver\":\"" + this.receiver + "\"," +
//                    "\"address\" : \"192.168.7.2\", \"content\" :\"" + this.msg + "\" } }");
//            Chat.update(null, json);
//        } catch (Exception e) {
//            Log.e("SendMessage", "Error creating json mock " + e.getMessage());
//            e.printStackTrace();
//        }

        try {
            socket = new Socket("192.168.100.6", 1408);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            try {
                JSONObject json
                        = new JSONObject("" +
                        "{ \"message\": { \"sender\": \"" + this.sender + "\"," +
                        "\"receiver\":\"" + this.receiver + "\"," +
                        "\"address\" : \"192.168.100.6\", \"content\" :\"" + this.msg + "\" } }");
                out.println(json.toString());
//                String responseStatus = "";
//                String incoming  = in.readLine();
//                StringBuilder str = new StringBuilder(incoming);
//                str.replace(incoming.lastIndexOf("\""), incoming.lastIndexOf("”") + 1, "\\\"");
//                String response = in.readLine();
//                responseStatus = response.substring(20);
//                        .replaceAll("\"", "").replaceAll("}", "");
                User.getInstance().setConnected(true);
//                Chat.update(null, json, response.contains("error") ? "Error" : "Sent", response.contains("error"));
                Chat.update(null, json, "Sent", false);
            } catch (JSONException e) {
                Log.d("SEND MESSAGE", "parsing JSON: " + e.getMessage());
            }
        } catch (IOException e) {
            Log.d("DEBUG ERROR", e.toString());
        }
        return null;
    }
}