package com.example.app_mensagens_otes_elias_michalczuk.chat.view;

import android.app.Activity;
import android.os.Bundle;

import com.example.app_mensagens_otes_elias_michalczuk.R;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.app_mensagens_otes_elias_michalczuk.chat.ChatContract;
import com.example.app_mensagens_otes_elias_michalczuk.chat.model.Message;
import com.example.app_mensagens_otes_elias_michalczuk.dummy.DummyContent;
import com.example.app_mensagens_otes_elias_michalczuk.chat.presenter.Chat;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.ItemDetailActivity;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.view.OnlineUsersActivity;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link OnlineUsersActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ChatConversationFragment extends Fragment implements View.OnClickListener, ChatContract.View {

    public static final String ARG_ITEM_ID = "item_id";

    private Chat presenter;
    private ImageButton sendButton;
    private EditText editText;
    private ListView listView;
    private ChatDisplayer displayer;

    public ChatConversationFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            this.presenter = new Chat(this);
            Activity activity = this.getActivity();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = this.getView();
        this.bindViews(view);
        this.sendButton.setOnClickListener(this);
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
        Toast.makeText(v.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bindViews(View view) {
        this.sendButton = view.findViewById(R.id.btn_send);
        this.editText = view.findViewById(R.id.text_send);
        this.listView = view.findViewById(R.id.list_view);
        this.displayer = new ChatDisplayer(view.getContext(), Message.mock());
        listView.setAdapter(this.displayer);
    }
}