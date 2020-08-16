package com.example.app_mensagens_otes_elias_michalczuk.chat.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.example.app_mensagens_otes_elias_michalczuk.R;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.app_mensagens_otes_elias_michalczuk.chat.ChatContract;
import com.example.app_mensagens_otes_elias_michalczuk.chat.model.Message;
import com.example.app_mensagens_otes_elias_michalczuk.dummy.DummyContent;
import com.example.app_mensagens_otes_elias_michalczuk.chat.presenter.Chat;
import com.example.app_mensagens_otes_elias_michalczuk.view.ItemDetailActivity;
import com.example.app_mensagens_otes_elias_michalczuk.view.ItemListActivity;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment implements View.OnClickListener, ChatContract.View {

    public static final String ARG_ITEM_ID = "item_id";

    private DummyContent.DummyItem mItem;
    private Chat presenter;
    private ImageButton sendButton;
    private EditText editText;
    private ListView listView;
    private ChatDisplayer displayer;

    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
            this.presenter = new Chat(this);
            Activity activity = this.getActivity();

//            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
//            if (appBarLayout != null) {
//                appBarLayout.setTitle(mItem.content);
//            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = this.getView();
        this.bindViews(view);
        this.sendButton.setOnClickListener(this);


//        ListView item = (ListView) view.findViewById(R.id.linear);
//        item.addView();
//        View child = getLayoutInflater().inflate(R.layout.bubble_right, null);
//        item.addView(child);
//        item.addView(getLayoutInflater().inflate(R.layout.bubble_left, null));
//        item.addView(getLayoutInflater().inflate(R.layout.bubble_left, null));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);
        // Show the dummy content as text in a TextView.
        if (mItem != null) {
//            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.details);
        }

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