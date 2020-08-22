package com.example.app_mensagens_otes_elias_michalczuk.online_users.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.example.app_mensagens_otes_elias_michalczuk.BaseView;
import com.example.app_mensagens_otes_elias_michalczuk.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app_mensagens_otes_elias_michalczuk.chat.view.ChatConversationFragment;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.ItemDetailActivity;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.model.User;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.presenter.OnlineUsers;

import java.util.List;

public class OnlineUsersActivity extends AppCompatActivity implements BaseView<OnlineUsers> {

    private boolean mTwoPane;
    private OnlineUsers presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_users);

//        if (!User.getInstance().isConnected()) {
//            Intent intent = new Intent(getApplicationContext(), ItemDetailActivity.class);
//            intent.putExtra(LoginFragment.ARG_ITEM_ID, "123");
//            getApplicationContext().startActivity(intent);
//        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());


        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);


    }

    public void showUsers() {

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, com.example.app_mensagens_otes_elias_michalczuk.online_users.model.OnlineUsers.mock(), mTwoPane));
    }

    @Override
    public void bindViews(View view) {

    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final OnlineUsersActivity mParentActivity;
        private final List<String> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = (String) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(ChatConversationFragment.ARG_ITEM_ID, item);
                    ChatConversationFragment fragment = new ChatConversationFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra(ChatConversationFragment.ARG_ITEM_ID, item);

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(OnlineUsersActivity parent,
                                      List<String> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_online_user, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
//            holder.mIdView.setText("123");
            holder.username.setText(mValues.get(position));

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
//            final TextView mIdView;
            final TextView username;

            ViewHolder(View view) {
                super(view);
//                mIdView = (TextView) view.findViewById(R.id.id_text);
                username = (TextView) view.findViewById(R.id.content);
            }
        }
    }
}