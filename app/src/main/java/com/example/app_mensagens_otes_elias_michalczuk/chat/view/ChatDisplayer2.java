package com.example.app_mensagens_otes_elias_michalczuk.chat.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_mensagens_otes_elias_michalczuk.R;
import com.example.app_mensagens_otes_elias_michalczuk.chat.model.Message;
import com.example.app_mensagens_otes_elias_michalczuk.dummy.DummyContent;

import java.util.List;

public class ChatDisplayer2  extends AppCompatActivity {

        /**
         * Whether or not the activity is in two-pane mode, i.e. running on a tablet
         * device.
         */
        private boolean mTwoPane;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.chat_list);

            View recyclerView = findViewById(R.id.item_list);
            assert recyclerView != null;
            setupRecyclerView((RecyclerView) recyclerView);
        }

        private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
            recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, Message.mock()));
        }

        public static class SimpleItemRecyclerViewAdapter
                extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

            private final ChatDisplayer2 mParentActivity;
            private final List<Message> mValues;
            private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();

                        Context context = view.getContext();
                        Intent intent = new Intent(context, ItemDetailActivity.class);
                        intent.putExtra(ChatConversationFragment.ARG_ITEM_ID, item.id);

                        context.startActivity(intent);
                }
            };

            SimpleItemRecyclerViewAdapter(ChatDisplayer2 parent,
                                          List<Message> items) {
                mValues = items;
                mParentActivity = parent;
            }

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(viewType == 1 ? R.layout.bubble_right : R.layout.bubble_left, parent, false);
                return new ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(final ViewHolder holder, int position) {
                holder.text.setText(mValues.get(position).content);
                if (this.getItemViewType(position) == 1) {
                    holder.username.setText(mValues.get(position).sender);
                }
            }

            @Override
            public int getItemCount() {
                return mValues.size();
            }

            @Override
            public int getItemViewType(int position) {
                //Implement your logic here
                Message vo = mValues.get(position);
                return vo.receiver == "receiver" ? 1 : 0;
            }

            class ViewHolder extends RecyclerView.ViewHolder {
                final TextView username;
                final TextView text;

                ViewHolder(View view) {
                    super(view);
                    username = (TextView) view.findViewById(R.id.username);
                    text = (TextView) view.findViewById(R.id.msg);
                }
            }
        }
}
