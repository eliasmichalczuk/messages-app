package com.example.app_mensagens_otes_elias_michalczuk.login;

import androidx.room.Room;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.app_mensagens_otes_elias_michalczuk.storage.AppDatabase;
import com.example.app_mensagens_otes_elias_michalczuk.storage.UserDB;

import java.util.List;

public class LoginActivity extends Activity implements View.OnClickListener {
    public static final String ARG_ITEM_ID = "login";

    private ChatPresenter presenter;
    private Button sendButton;
    private EditText passText;
    private EditText loginText;
    private ConnectToServerService service;
    private ProgressDialog dialog;
    private AppDatabase database;

    public LoginActivity() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.sendButton = (Button) findViewById(R.id.btn_confirm);
        this.passText = findViewById(R.id.pass_text);
        this.loginText = findViewById(R.id.login_text);
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

        UserDB registeredUser = database.userDao().findByLogin(loginvalue);
        if (null == registeredUser) {
            showToast("User with login not found");
        }

        if (registeredUser.getPassword() != passvalue) {
            showToast("Passwords don't match");
        }

        User user = User.getInstance();
        user.setLogin(loginvalue);
        user.setId(registeredUser.getId());
        userLoggedIn();
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