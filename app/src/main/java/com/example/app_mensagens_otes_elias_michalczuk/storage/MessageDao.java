package com.example.app_mensagens_otes_elias_michalczuk.storage;

import androidx.annotation.Nullable;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MessageDao {
    @Query("select * from message where message.chat_id =:chatId")
    @Nullable
    List<MessageDB> findAllByChatId(int chatId);
    @Query("select * from message")
    @Nullable
    List<MessageDB> findAll();
    @Insert
    void addMessage(MessageDB message);
}
