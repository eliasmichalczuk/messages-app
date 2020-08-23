package com.example.app_mensagens_otes_elias_michalczuk.chat.view;

import android.app.Activity;
import android.os.Bundle;

import com.example.app_mensagens_otes_elias_michalczuk.R;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.app_mensagens_otes_elias_michalczuk.chat.ChatContract;
import com.example.app_mensagens_otes_elias_michalczuk.chat.model.Chat;
import com.example.app_mensagens_otes_elias_michalczuk.chat.model.Message;
import com.example.app_mensagens_otes_elias_michalczuk.chat.presenter.ChatPresenter;
import com.example.app_mensagens_otes_elias_michalczuk.chat.services.ChatService;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.model.User;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.view.OnlineUsersActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link OnlineUsersActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ChatConversationFragment extends Fragment implements View.OnClickListener, ChatContract.View {

    public static final String ARG_ITEM_ID = "SELECTED_USER_TO_CHAT";

    private ChatPresenter presenter;
    private ImageButton sendButton;
    private EditText editText;
    private ListView listView;
    private ChatDisplayer displayer;
    private ChatService service;
    private String selectedUserToChat;

    public ChatConversationFragment() {
        this.service = new ChatService();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            this.selectedUserToChat = getArguments().getString(ARG_ITEM_ID);
            this.presenter = new ChatPresenter(this);
            Activity activity = this.getActivity();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = this.getView();
        this.bindViews(view);
        this.sendButton.setOnClickListener(this);

        Chat.getMessages().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> msgs) {
                displayer.update(msgs);
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.chat_conversation, container, false);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        String text = this.editText.getText().toString();
        if (text.equals("")) return;
        this.service.sendText(User.getInstance().getUsername(), text, selectedUserToChat);
        this.editText.setText("");
    }

    @Override
    public void bindViews(View view) {
        this.sendButton = view.findViewById(R.id.btn_send);
        this.editText = view.findViewById(R.id.text_send);
        this.listView = view.findViewById(R.id.list_view);
        this.displayer = new ChatDisplayer(view.getContext(), new ArrayList<Message>());
        listView.setAdapter(this.displayer);
    }
}