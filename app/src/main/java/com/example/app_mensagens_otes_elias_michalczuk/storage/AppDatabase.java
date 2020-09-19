package com.example.app_mensagens_otes_elias_michalczuk.storage;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserDB.class, ChatDB.class, MessageDB.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract MessageDao messageDao();
    public abstract ChatDao chatDao();
}
