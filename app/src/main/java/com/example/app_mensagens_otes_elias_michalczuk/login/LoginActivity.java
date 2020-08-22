package com.example.app_mensagens_otes_elias_michalczuk.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_mensagens_otes_elias_michalczuk.R;
import com.example.app_mensagens_otes_elias_michalczuk.chat.presenter.Chat;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.model.User;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.view.OnlineUsersActivity;
import com.example.app_mensagens_otes_elias_michalczuk.startup.SplashScreen;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String ARG_ITEM_ID = "login";

    private Chat presenter;
    private ImageButton sendButton;
    private EditText editText;

    public LoginActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void bindViews() {
        this.sendButton = findViewById(R.id.btn_send);
        this.editText = findViewById(R.id.text_send);
        sendButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String usernameChosen = this.editText.getText().toString();

        if (usernameChosen == null || usernameChosen.length() < 2) {
            Toast.makeText(v.getContext(), "Choose valid username", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(v.getContext(), "Entrando com " + usernameChosen, Toast.LENGTH_SHORT).show();
        User.getInstance().setUsername(usernameChosen);

        Intent mainIntent = null;
        mainIntent = new Intent(LoginActivity.this, OnlineUsersActivity.class);
        LoginActivity.this.startActivity(mainIntent);
        LoginActivity.this.finish();
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
