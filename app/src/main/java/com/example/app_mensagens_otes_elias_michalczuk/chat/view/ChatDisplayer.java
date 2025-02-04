package com.example.app_mensagens_otes_elias_michalczuk.chat.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.app_mensagens_otes_elias_michalczuk.R;
import com.example.app_mensagens_otes_elias_michalczuk.chat.model.Message;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.model.User;

import java.util.List;

public class ChatDisplayer extends BaseAdapter {

    Context context;
    List<Message> data;
    String selectedUserToChat;
    private static LayoutInflater inflater = null;
    private User user = User.getInstance();

    public ChatDisplayer(Context context, List<Message> data, String selectedUserToChat) {
        this.context = context;
        this.data = data;
        this.selectedUserToChat = selectedUserToChat;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.item_list, null);
//        context.setContentView(view);

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        Message msg = data.get(position);
        if (selectedUserToChat.equals(msg.sender)) {
            vi = inflater.inflate(R.layout.bubble_left, null);
            ((TextView) vi.findViewById(R.id.username)).setText(msg.sender);
        } else {
            vi = inflater.inflate(R.layout.bubble_right, null);
            ((TextView) vi.findViewById(R.id.status)).setText(msg.status);
        }
        ((TextView) vi.findViewById(R.id.msg)).setText(msg.content);

        return vi;
    }

    public void update(List<Message> msgs) {
        data.clear();
        data.addAll(msgs);
        this.notifyDataSetChanged();
    }

    public void update(Message msg) {
        data.add(msg);
        this.notifyDataSetChanged();
    }

}
