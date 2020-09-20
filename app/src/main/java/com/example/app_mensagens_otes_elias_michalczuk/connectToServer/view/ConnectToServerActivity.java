package com.example.app_mensagens_otes_elias_michalczuk.connectToServer.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_mensagens_otes_elias_michalczuk.R;
import com.example.app_mensagens_otes_elias_michalczuk.chat.presenter.ChatPresenter;
import com.example.app_mensagens_otes_elias_michalczuk.connectToServer.services.ConnectToServerService;
import com.example.app_mensagens_otes_elias_michalczuk.connection.Logout;
import com.example.app_mensagens_otes_elias_michalczuk.connection.model.Connection;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.model.User;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.view.OnlineUsersActivity;

public class ConnectToServerActivity extends Activity implements View.OnClickListener {
    public static final String ARG_ITEM_ID = "login";

    private ChatPresenter presenter;
    private Button sendButton;
    private EditText editText;
    private EditText serverAddress;
    private EditText serverPort;
    private ConnectToServerService service;
    private ProgressDialog dialog;

    public ConnectToServerActivity() {
        this.service = new ConnectToServerService(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connectotoserver);
        bindViews();
        logoutUser();
    }

    public void logoutUser() {
        String logoutIntent = getIntent().getStringExtra("logout");
        if (logoutIntent != null && !logoutIntent.equals("")) {
            new Logout(logoutIntent).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void bindViews() {
        this.sendButton = (Button) findViewById(R.id.btn_confirm);
        this.editText = findViewById(R.id.text_send);
        this.serverAddress = findViewById(R.id.server_address_text);
        this.serverPort = findViewById(R.id.server_port_text);
        this.editText.setText(lastUsedUsername());
        setValues();
    }

    public void setValues() {
        Connection conect = Connection.getInstance();
        serverAddress.setText(conect.getAddress());
        serverPort.setText(String.valueOf(conect.getPort()));
    }

    @Override
    public void onClick(View v) {
        String usernameChosen = editText.getText().toString();
        if (usernameChosen == null || usernameChosen.equals("")) {
            Toast.makeText(v.getContext(), "Choose valid username", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast toast = Toast.makeText(v.getContext(), "Longing with " + usernameChosen, Toast.LENGTH_SHORT);
        toast.show();
        User.getInstance().setUsername(usernameChosen);

//        if (!this.checkWifiOnAndConnected()) {
//            this.wifiNotConnected(); return;
//        }
        saveServerConnectionInfo();
        saveSharedPreferences(usernameChosen);
        dialog = new ProgressDialog(ConnectToServerActivity.this);
        dialog.setMessage("Longing in...");
        dialog.show();
        service.login();
    }

    private void saveServerConnectionInfo() {
        Connection.getInstance().makeItHappen(serverAddress.getText().toString(), Integer.parseInt(serverPort.getText().toString()));

    }

    private void saveSharedPreferences(String username) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username_messagesapp", username);
        editor.commit();
    }

    private String lastUsedUsername() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        return sharedPref.getString("username_messagesapp", "");
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
