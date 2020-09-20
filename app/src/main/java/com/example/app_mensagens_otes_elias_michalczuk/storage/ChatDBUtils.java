package com.example.app_mensagens_otes_elias_michalczuk.storage;

import com.example.app_mensagens_otes_elias_michalczuk.online_users.model.User;

public class ChatDBUtils {

    public static ChatDB findAndCreateIfNotFoundOrThrowError(AppDatabase database, String otherUserUsername) {
        ChatDB chat = database.chatDao().findByUserIdAndOtherUserUsername(User.getInstance().getId(), otherUserUsername);

        if (chat == null) {
            database.chatDao().addChat(new ChatDB(0, User.getInstance().getId(), otherUserUsername));
        }
        chat = database.chatDao()
                .findByUserIdAndOtherUserUsername(
                        User.getInstance().getId(), otherUserUsername);
        if (chat == null) {
            throw new RuntimeException("ERROR FINDING AND CREATING CHAT WITH USER ID AND RECEIVER " + User.getInstance().getId() + " " + otherUserUsername);
        }
        return chat;
    }
}
