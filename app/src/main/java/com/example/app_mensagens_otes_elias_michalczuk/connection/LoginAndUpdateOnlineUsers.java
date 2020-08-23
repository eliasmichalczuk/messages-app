package com.example.app_mensagens_otes_elias_michalczuk.connection;

import android.os.AsyncTask;
import android.util.Log;

import com.example.app_mensagens_otes_elias_michalczuk.online_users.model.OnlineUsers;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.model.User;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.view.OnlineUsersActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LoginAndUpdateOnlineUsers extends AsyncTask<Object[], Object[], List<String>> {

    private static String IP = "192.168.0.1";
    private static int PORT = 4500;
    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;

    public LoginAndUpdateOnlineUsers(){
    }

    @Override
    protected void onPostExecute(List<String> users) {
//        this.osa.showUsers();
    }

    @Override
    protected List<String> doInBackground(Object[]... objects) {

        try {
            JSONObject loginJson
                    = new JSONObject("{ \"logout\": { \"user-id\":\"" + User.getInstance().getUsername() + "\" } }");
            User.getInstance().setConnected(true);

            JSONObject json = new JSONObject("{ \"online-users\": [ \"broadcast\", \"o.professor\", \"o.aluno\", \"outro.aluno\", \"mais.um.aluno\" ] }");
            OnlineUsers.update(null, json);
        } catch (Exception e) {
            Log.e("OnlineUsersUpdater", "Error creating json mock " + e.getMessage());
            e.printStackTrace();
        }

        return null;

//        try {
//            socket = new Socket(IP, PORT);
//            out = new PrintWriter(socket.getOutputStream(), true);
//            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            try {
//                JSONObject json = new JSONObject(in.readLine());
//                //Log.d("otes", "jsonObject: " + json.toString());
//                if(json.has("online-users")) {
//                    OnlineUsers.setOnlineUsers(onlineUsers);
//                }
//            }
//            catch(JSONException e) {
//                Log.d("USERS", "parsing JSON: " + e.getMessage());
//            }
//        }
//        catch (IOException e) {
//            Log.d("DEBUG ERROR", e.toString());
//        }
//        return null;
    }

    public boolean sendMessage(String message){
        out.println(message);
        return true;
    }

    private void closeAll(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}