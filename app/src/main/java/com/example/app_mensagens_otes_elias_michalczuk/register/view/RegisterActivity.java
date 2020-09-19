package com.example.app_mensagens_otes_elias_michalczuk.register.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.app_mensagens_otes_elias_michalczuk.chat.presenter.ChatPresenter;
import com.example.app_mensagens_otes_elias_michalczuk.connectToServer.services.ConnectToServerService;
import com.example.app_mensagens_otes_elias_michalczuk.connectToServer.view.ConnectToServerActivity;
import com.example.app_mensagens_otes_elias_michalczuk.login.LoginActivity;
import com.example.app_mensagens_otes_elias_michalczuk.register.services.RegisterUser;
import com.example.app_mensagens_otes_elias_michalczuk.storage.AppDatabase;
import com.example.app_mensagens_otes_elias_michalczuk.storage.UserDB;

import androidx.room.Room;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_mensagens_otes_elias_michalczuk.R;

public class RegisterActivity extends Activity implements View.OnClickListener {
    public static final String ARG_ITEM_ID = "login";

    private ChatPresenter presenter;
    private Button sendButton;
    private EditText passText;
    private EditText loginText;
    private ConnectToServerService service;
    private ProgressDialog dialog;
    private AppDatabase database;

    public RegisterActivity() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.sendButton = (Button) findViewById(R.id.btn_confirm);
        this.passText = findViewById(R.id.pass_text);
        this.loginText = findViewById(R.id.login_text);
        ((Button) findViewById(R.id.btn_login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                RegisterActivity.this.startActivity(mainIntent);
                RegisterActivity.this.finish();
            }
        });
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "messagesdatabase").build();
    }

    @Override
    public void onClick(View v) {
        String passvalue = passText.getText().toString();
        String loginvalue = loginText.getText().toString();
        if (passvalue.isEmpty() || loginvalue.isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(), "Login and password cannot be empty", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
            return;
        }
        this.save(UserDB.from(loginvalue, passvalue));
    }

    private void save(final UserDB user) {
        new RegisterUser(database, user, this).execute();
    }

    public void userRegistered() {
        Intent mainIntent = new Intent(RegisterActivity.this, ConnectToServerActivity.class);
        RegisterActivity.this.startActivity(mainIntent);
        RegisterActivity.this.finish();
    }
}