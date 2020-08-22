package com.example.app_mensagens_otes_elias_michalczuk.startup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.app_mensagens_otes_elias_michalczuk.R;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.model.User;
import com.example.app_mensagens_otes_elias_michalczuk.login.LoginActivity;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.view.OnlineUsersActivity;

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

                Intent mainIntent = null;
//                if (user.isConnected()) {
                    mainIntent = new Intent(SplashScreen.this, OnlineUsersActivity.class);
//                } else {
//                    mainIntent = new Intent(SplashScreen.this, LoginActivity.class);
//                }
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}