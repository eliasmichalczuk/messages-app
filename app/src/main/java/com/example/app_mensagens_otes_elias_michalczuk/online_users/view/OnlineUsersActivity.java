package com.example.app_mensagens_otes_elias_michalczuk.online_users.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.example.app_mensagens_otes_elias_michalczuk.BaseView;
import com.example.app_mensagens_otes_elias_michalczuk.R;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app_mensagens_otes_elias_michalczuk.chat.view.ChatConversationFragment;
import com.example.app_mensagens_otes_elias_michalczuk.chat.view.ItemDetailActivity;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.model.OnlineUsers;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.presenter.OnlineUsersPresenter;

import java.util.ArrayList;
import java.util.List;

public class OnlineUsersActivity extends AppCompatActivity implements BaseView<OnlineUsersPresenter> {

    private boolean deviceIsTablet;
    private OnlineUsersPresenter presenter;
    private View recyclerView;
    private List<String> users = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_users);
        Log.i("OUA", "iniciado");

//        if (!User.getInstance().isConnected()) {
//            Intent intent = new Intent(getApplicationContext(), ItemDetailActivity.class);
//            intent.putExtra(LoginFragment.ARG_ITEM_ID, "123");
//            getApplicationContext().startActivity(intent);
//        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());


        if (findViewById(R.id.item_detail_container) != null) {
            deviceIsTablet = true;
        }

        this.recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        OnlineUsers.getUsers().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> users) {
                RecyclerView.Adapter adapter = ((RecyclerView) recyclerView).getAdapter();
                ((SimpleItemRecyclerViewAdapter) adapter).update(users);
            }
        });
    }

    public void showUsers() {
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(
                new SimpleItemRecyclerViewAdapter(this, users, deviceIsTablet));
    }

    @Override
    public void bindViews(View view) {

    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final OnlineUsersActivity parentActivity;
        private final List<String> usernames;
        private final boolean deviceIsTablet;
        private final View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedOnlineUserToChat = (String) view.getTag();
                if (deviceIsTablet) {
                    Bundle arguments = new Bundle();
                    arguments.putString(ChatConversationFragment.ARG_ITEM_ID, selectedOnlineUserToChat);
                    ChatConversationFragment fragment = new ChatConversationFragment();
                    fragment.setArguments(arguments);
                    parentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra(ChatConversationFragment.ARG_ITEM_ID, selectedOnlineUserToChat);

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(OnlineUsersActivity parent,
                                      List<String> items,
                                      boolean twoPane) {
            usernames = items;
            parentActivity = parent;
            deviceIsTablet = twoPane;
        }

        public void update(List<String> users) {
            usernames.clear();
            usernames.addAll(users);
            this.notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_online_user, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.username.setText(usernames.get(position));
            holder.itemView.setTag(usernames.get(position));
            holder.itemView.setOnClickListener(onClickListener);
        }

        @Override
        public int getItemCount() {
            return usernames == null ? 0 : usernames.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView username;

            ViewHolder(View view) {
                super(view);
                username = (TextView) view.findViewById(R.id.username);
            }
        }
    }
}