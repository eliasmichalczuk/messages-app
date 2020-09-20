package com.example.app_mensagens_otes_elias_michalczuk.storage;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.app_mensagens_otes_elias_michalczuk.chat.model.Message;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.model.User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(foreignKeys = @ForeignKey(entity = ChatDB.class,
        parentColumns = "id",
        childColumns = "chat_id",
        onDelete = ForeignKey.CASCADE), tableName = "message")
public class MessageDB {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "chat_id")
    @NonNull
    private int chat_id;
    @ColumnInfo(name = "user_id")
    @NonNull
    private int userId;
    @ColumnInfo(name = "content")
    @NonNull
    private String content;
    @ColumnInfo(name = "sender")
    @NonNull
    private String sender;
    @ColumnInfo(name = "receiver")
    @NonNull
    private String receiver;
    @ColumnInfo(name = "address")
    @NonNull
    private String address;
    @ColumnInfo(name = "status")
    @NonNull
    private String status;
    @ColumnInfo(name = "error")
    @NonNull
    private boolean error;

    public static MessageDB toSaveMessageDBFrom(ChatDB chat, Message message) {
        return new MessageDB(0, chat.getId(),
                User.getInstance().getId(), message.content, message.sender, message.receiver, message.address, message.status, message.error);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
