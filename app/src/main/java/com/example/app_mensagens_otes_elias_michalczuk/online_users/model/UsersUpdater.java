package com.example.app_mensagens_otes_elias_michalczuk.online_users.model;

import android.os.AsyncTask;
import android.util.Log;

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

public class UsersUpdater extends AsyncTask<Object[], Object[], List<String>> {

    private static String IP = "192.168.0.1";
    private static int PORT = 4500;
    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private OnlineUsersActivity osa;

    public UsersUpdater(OnlineUsersActivity osa){
        this.osa = osa;
    }

    @Override
    protected void onPostExecute(List<String> users) {
//        this.osa.showUsers();
    }

    @Override
    protected List<String> doInBackground(Object[]... objects) {
        List onlineUsers1 = new ArrayList<String>();
        OnlineUsers.onlineUsers.add("user 123");
        OnlineUsers.onlineUsers.add("user 456");
        OnlineUsers.onlineUsers.add("user 789");
//        OnlineUsers.setOnlineUsers(onlineUsers1);
        return null;

//        try {
//            socket = new Socket(IP, PORT);
//            out = new PrintWriter(socket.getOutputStream(), true);
//            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            try {
//                JSONObject json = new JSONObject(in.readLine());
//                //Log.d("otes", "jsonObject: " + json.toString());
//                if(json.has("online-users")) {
//                    Log.d("USERS deserialized", " Mensagem de usuarios online");
//                    JSONArray users = json.getJSONArray("online-users");
//                    List onlineUsers = new ArrayList<String>();
//                    int index = 0;
//                    while (users.getString(index) != null) {
//                        onlineUsers.add(users.getString(index));
//                        index++;
//                    }
////                    Log.d("USERS deserialized", " array de usuarios: " + users.toString());
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