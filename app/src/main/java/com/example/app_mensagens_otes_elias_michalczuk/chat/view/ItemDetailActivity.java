package com.example.app_mensagens_otes_elias_michalczuk.chat.view;

import android.content.Intent;
import android.os.Bundle;

import com.example.app_mensagens_otes_elias_michalczuk.R;
import com.example.app_mensagens_otes_elias_michalczuk.online_users.view.OnlineUsersActivity;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;

import android.view.MenuItem;

public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        setTitle(getIntent().getStringExtra(ChatConversationFragment.ARG_ITEM_ID));
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(ChatConversationFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(ChatConversationFragment.ARG_ITEM_ID));
            ChatConversationFragment fragment = new ChatConversationFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, OnlineUsersActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}