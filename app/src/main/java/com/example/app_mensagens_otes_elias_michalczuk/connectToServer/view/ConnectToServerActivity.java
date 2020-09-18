package com.example.app_mensagens_otes_elias_michalczuk.connectToServer.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_mensagens_otes_elias_michalczuk.R;
import com.example.app_mensagens_otes_elias_michalczuk.chat.presenter.ChatPresenter;
import com.example.app_mensagens_otes_elias_michalczuk.connectToServer.services.ConnectToServerService;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.model.User;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.view.OnlineUsersActivity;

public class ConnectToServerActivity extends Activity implements View.OnClickListener {
    public static final String ARG_ITEM_ID = "login";

    private ChatPresenter presenter;
    private Button sendButton;
    private EditText editText;
    private ConnectToServerService service;
    private ProgressDialog dialog;

    public ConnectToServerActivity() {
        this.service = new ConnectToServerService(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connectotoserver);
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

//        if (!this.checkWifiOnAndConnected()) {
//            this.wifiNotConnected(); return;
//        }

        dialog = new ProgressDialog(ConnectToServerActivity.this);
        dialog.setMessage("Longing in...");
        dialog.show();
        service.login();
    }

    public void finishedLoggingIn() {
        dialog.dismiss();
        Intent intent = new Intent(ConnectToServerActivity.this, OnlineUsersActivity.class);
        ConnectToServerActivity.this.startActivity(intent);
        ConnectToServerActivity.this.finish();
    }

    public void errorOnLoggingIn(String error) {
        dialog.dismiss();
        Toast toast = Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }

    public void wifiNotConnected() {
        Toast toast = Toast.makeText(getApplicationContext(), "You're not connected to a network. Try again", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }

    private boolean checkWifiOnAndConnected() {
        if (this.isProbablyAnEmulator()) {
            return false;
        }
        WifiManager wifiMgr = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiMgr.isWifiEnabled()) {
            WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
            if (wifiInfo.getNetworkId() == -1) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean isProbablyAnEmulator() {
        return true;
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
//        View rootView = inflater.inflate(R.layout.activity_connectotoserver, container, false);
//        return rootView;
//    }
}
