package com.example.app_mensagens_otes_elias_michalczuk.storage;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity(tableName = "chat")
public class ChatDB {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @ColumnInfo(name = "user_id")
    private int userId;
    @ColumnInfo(name = "other_user_username")
    private String otherUserUsername;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOtherUserUsername() {
        return otherUserUsername;
    }

    public void setOtherUserUsername(String otherUserUsername) {
        this.otherUserUsername = otherUserUsername;
    }
}
