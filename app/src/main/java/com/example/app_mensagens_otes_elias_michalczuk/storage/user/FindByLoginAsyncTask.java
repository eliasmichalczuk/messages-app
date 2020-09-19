package com.example.app_mensagens_otes_elias_michalczuk.storage.user;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.Toast;

import androidx.room.Room;

import com.example.app_mensagens_otes_elias_michalczuk.login.LoginActivity;
import com.example.app_mensagens_otes_elias_michalczuk.storage.AppDatabase;
import com.example.app_mensagens_otes_elias_michalczuk.storage.UserDB;

import java.lang.ref.WeakReference;

public class FindByLoginAsyncTask  extends AsyncTask<Void, Void, UserDB> {

    //Prevent leak
    private WeakReference<LoginActivity> weakActivity;
    private String login;
    private String passvalue;

    public FindByLoginAsyncTask(LoginActivity activity, String login, String passvalue) {
        weakActivity = new WeakReference<>(activity);
        this.login = login;
        this.passvalue = passvalue;
    }

    @Override
    protected UserDB doInBackground(Void... params) {
        AppDatabase database = Room.databaseBuilder(weakActivity.get().getApplicationContext(),
                AppDatabase.class, "messagesdatabase").build();
        return database.userDao().findByLogin(login);
    }

    @Override
    protected void onPostExecute(UserDB user) {
        if (null == user) {
            showToast("User with login not found");
            return;
        }

        if (!user.getPassword().equals(passvalue)) {
            showToast("Passwords don't match");
            return;
        }
        weakActivity.get().userFoundOnDB(user, login);
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(weakActivity.get().getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
        return;
    }
}
