package com.example.app_mensagens_otes_elias_michalczuk.storage;

import androidx.annotation.Nullable;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ChatDao {
    @Query("select * from chat where chat.user_id=:id and chat.other_user_username=:otherUserUsername")
    @Nullable
    ChatDB findByUserIdAndOtherUserUsername(int id, String otherUserUsername);
    @Insert
    public void addChat(ChatDB chat);
}
