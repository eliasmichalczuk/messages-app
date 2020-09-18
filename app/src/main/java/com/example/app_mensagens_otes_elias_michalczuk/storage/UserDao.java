package com.example.app_mensagens_otes_elias_michalczuk.storage;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
import java.util.Optional;

@Dao
public interface UserDao {
    @Query("select * from UserDB where login=:login")
    UserDB findByLogin(String login);
    @Insert
    void addUser(UserDB userDB);
}
