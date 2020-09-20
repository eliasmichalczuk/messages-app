package com.example.app_mensagens_otes_elias_michalczuk.connection;

import android.os.AsyncTask;
import android.util.Log;

import com.example.app_mensagens_otes_elias_michalczuk.connection.model.Connection;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class Logout extends AsyncTask<Object[], Object[], List<String>> {

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
            socket = new Socket(Connection.getInstance().getAddress(), Connection.getInstance().getPort());
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