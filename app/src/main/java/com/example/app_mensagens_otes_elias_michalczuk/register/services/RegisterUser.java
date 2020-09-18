package com.example.app_mensagens_otes_elias_michalczuk.register.services;

import android.os.AsyncTask;

import com.example.app_mensagens_otes_elias_michalczuk.online_users.model.User;
import com.example.app_mensagens_otes_elias_michalczuk.register.view.RegisterActivity;
import com.example.app_mensagens_otes_elias_michalczuk.storage.AppDatabase;
import com.example.app_mensagens_otes_elias_michalczuk.storage.UserDB;

import java.util.List;

public class RegisterUser extends AsyncTask<Object[], Object[], List<String>> {

    private AppDatabase db;
    private UserDB user;
    private RegisterActivity activity;
    public RegisterUser(AppDatabase db, UserDB user, RegisterActivity activity) {
        this.db = db;
        this.user = user;
        this.activity = activity;
    }

    @Override
    protected void onPostExecute(List<String> users) {
        this.activity.userRegistered();
    }

    @Override
    protected List<String> doInBackground(Object[]... objects) {
        db.userDao().addUser(user);
        UserDB registeredUser = db.userDao().findByLogin(user.getLogin());
        if (null == registeredUser) {
            throw new RuntimeException("CREATED USER NOT FOUND");
        }

        User globalUser = User.getInstance();
        globalUser.setLogin(registeredUser.getLogin());
        globalUser.setId(registeredUser.getId());
        return null;
    }

}