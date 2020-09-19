package com.example.app_mensagens_otes_elias_michalczuk.startup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.example.app_mensagens_otes_elias_michalczuk.R;
import com.example.app_mensagens_otes_elias_michalczuk.connectToServer.view.ConnectToServerActivity;
import com.example.app_mensagens_otes_elias_michalczuk.login.LoginActivity;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.model.ConnectToServer;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.model.User;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.view.OnlineUsersActivity;
import com.example.app_mensagens_otes_elias_michalczuk.register.view.RegisterActivity;

import java.util.Map;

public class SplashScreen extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                User user = User.getInstance();

                Map<String, ?> sharedPreferencesValue = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getAll();
                if (sharedPreferencesValue.get("login_messagesapp") != null && sharedPreferencesValue.get("id_messagesapp") != null) {
                    user.setLogin(sharedPreferencesValue.get("login_messagesapp").toString());
                    user.setUsername(sharedPreferencesValue.get("username_messagesapp") != null ? sharedPreferencesValue.get("username_messagesapp").toString() : "");
                    user.setId(Integer.valueOf(sharedPreferencesValue.get("id_messagesapp").toString()).intValue());
                }


                Intent mainIntent = null;
                if (user.getId() == 0) {
                    mainIntent = new Intent(SplashScreen.this, LoginActivity.class);
                } else {
                    mainIntent = new Intent(SplashScreen.this, ConnectToServerActivity.class);
                }
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}