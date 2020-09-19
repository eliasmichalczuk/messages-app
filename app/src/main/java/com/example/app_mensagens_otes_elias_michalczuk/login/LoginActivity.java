package com.example.app_mensagens_otes_elias_michalczuk.login;

import androidx.room.Room;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_mensagens_otes_elias_michalczuk.R;
import com.example.app_mensagens_otes_elias_michalczuk.chat.presenter.ChatPresenter;
import com.example.app_mensagens_otes_elias_michalczuk.connectToServer.services.ConnectToServerService;
import com.example.app_mensagens_otes_elias_michalczuk.connectToServer.view.ConnectToServerActivity;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.model.User;
import com.example.app_mensagens_otes_elias_michalczuk.register.view.RegisterActivity;
import com.example.app_mensagens_otes_elias_michalczuk.startup.SplashScreen;
import com.example.app_mensagens_otes_elias_michalczuk.storage.AppDatabase;
import com.example.app_mensagens_otes_elias_michalczuk.storage.UserDB;
import com.example.app_mensagens_otes_elias_michalczuk.storage.user.FindByLoginAsyncTask;

import java.util.Map;

public class LoginActivity extends Activity implements View.OnClickListener {
    public static final String ARG_ITEM_ID = "login";

    private Button sendButton;
    private EditText passText;
    private EditText loginText;
    private AppDatabase database;

    public LoginActivity() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_r);
        ((Button) findViewById(R.id.btn_register)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(mainIntent);
                LoginActivity.this.finish();
            }
        });
        sendButton = findViewById(R.id.btn_confirm_login);
        this.passText = findViewById(R.id.pass_text_login);
        this.loginText = findViewById(R.id.login_text_login);
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "messagesdatabase").build();
    }

    @Override
    public void onClick(View v) {
        String passvalue = passText.getText().toString();
        String loginvalue = loginText.getText().toString();
        if (passvalue.isEmpty() || loginvalue.isEmpty()) {
            showToast("Login and password cannot be empty");
        }

        new FindByLoginAsyncTask(this, loginvalue, passvalue).execute();

    }

    public void userFoundOnDB(UserDB user, String login) {
        User userInstance = User.getInstance();
        userInstance.setLogin(login);
        userInstance.setId(user.getId());
        saveSharedPreferences(userInstance);
        userLoggedIn();
    }

    private void saveSharedPreferences(User user) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("login_messagesapp", user.getLogin());
        editor.putInt("id_messagesapp", user.getId());
        editor.putString("username_messagesapp", user.getUsername());
        if(editor.commit()) {
            Toast.makeText(getBaseContext(), "Armazenado no SP", Toast.LENGTH_LONG).show();
        }
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
        return;
    }

    public void userLoggedIn() {
        Intent mainIntent = new Intent(LoginActivity.this, ConnectToServerActivity.class);
        LoginActivity.this.startActivity(mainIntent);
        LoginActivity.this.finish();
    }

}