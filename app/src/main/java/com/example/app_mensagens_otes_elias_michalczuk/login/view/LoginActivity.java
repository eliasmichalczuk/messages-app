package com.example.app_mensagens_otes_elias_michalczuk.login.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.app_mensagens_otes_elias_michalczuk.R;
import com.example.app_mensagens_otes_elias_michalczuk.chat.presenter.ChatPresenter;
import com.example.app_mensagens_otes_elias_michalczuk.connection.LoginAndUpdateOnlineUsers;
import com.example.app_mensagens_otes_elias_michalczuk.login.services.LoginService;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.model.User;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.view.OnlineUsersActivity;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends Activity implements View.OnClickListener {
    public static final String ARG_ITEM_ID = "login";

    private ChatPresenter presenter;
    private Button sendButton;
    private EditText editText;
    private LoginService service;
    private ProgressDialog dialog;

    public LoginActivity() {
        this.service = new LoginService(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.sendButton = (Button) findViewById(R.id.btn_confirm);
        this.editText = findViewById(R.id.text_send);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void bindViews() {

//        sendButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String usernameChosen = this.editText.getText().toString();
//        ProgressBar loading = findViewById(R.id.progressBar_cyclic);
//        if (usernameChosen == null || usernameChosen.length() < 2) {
//            Toast.makeText(v.getContext(), "Choose valid username", Toast.LENGTH_SHORT).show();
//            return;
//        }
        Toast toast = Toast.makeText(v.getContext(), "Longing with " + usernameChosen, Toast.LENGTH_SHORT);
        toast.show();
        User.getInstance().setUsername(usernameChosen);

            dialog = new ProgressDialog(LoginActivity.this);
            dialog.setMessage("Longing in...");
            dialog.show();
            service.login();
    }

    public void finishedLoggingIn() {
        dialog.dismiss();
        Intent intent = new Intent(LoginActivity.this, OnlineUsersActivity.class);
        LoginActivity.this.startActivity(intent);
        LoginActivity.this.finish();
    }

    public void errorOnLoggingIn(String error) {
        Toast toast = Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }


//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        View view = this.getView();
//        this.bindViews(view);
//        this.sendButton.setOnClickListener(this);
//    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.activity_login, container, false);
//        return rootView;
//    }
}
