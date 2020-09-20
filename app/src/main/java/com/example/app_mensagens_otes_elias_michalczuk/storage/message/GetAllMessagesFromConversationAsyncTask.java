package com.example.app_mensagens_otes_elias_michalczuk.storage.message;

import android.os.AsyncTask;

import androidx.room.Room;

import com.example.app_mensagens_otes_elias_michalczuk.chat.view.ChatConversationFragment;
import com.example.app_mensagens_otes_elias_michalczuk.storage.AppDatabase;
import com.example.app_mensagens_otes_elias_michalczuk.storage.ChatDB;
import com.example.app_mensagens_otes_elias_michalczuk.storage.ChatDBUtils;
import com.example.app_mensagens_otes_elias_michalczuk.storage.MessageDB;

import java.lang.ref.WeakReference;
import java.util.List;

public class GetAllMessagesFromConversationAsyncTask extends AsyncTask<Void, Void, List<MessageDB>> {

    private WeakReference<ChatConversationFragment> weakActivity;
    private String selectedUserToChat;

    public GetAllMessagesFromConversationAsyncTask(ChatConversationFragment frag, String selectedUserToChat) {
        weakActivity = new WeakReference<>(frag);
        this.selectedUserToChat = selectedUserToChat;
    }

    @Override
    protected List<MessageDB> doInBackground(Void... params) {
        AppDatabase database = Room.databaseBuilder(weakActivity.get().getContext().getApplicationContext(),
                AppDatabase.class, "messagesdatabase").build();

        ChatDB chat = ChatDBUtils.findAndCreateIfNotFoundOrThrowError(database, selectedUserToChat);
        return database.messageDao().findAllByChatId(chat.getId());
    }

    @Override
    protected void onPostExecute(List<MessageDB> messages) {
        weakActivity.get().messagesForThisConversationFound(messages);
    }
}
