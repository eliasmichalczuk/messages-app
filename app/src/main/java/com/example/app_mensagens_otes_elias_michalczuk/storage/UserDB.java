package com.example.app_mensagens_otes_elias_michalczuk.storage;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.app_mensagens_otes_elias_michalczuk.online_users.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserDB {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String login;
    private String password;

    public static UserDB from(String login, String password) {
        return new UserDB(0, login, password);
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
