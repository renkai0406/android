package com.renkai.chat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.renkai.R;
import com.renkai.view.DetailedMsgView;
import com.renkai.vmodel.DetailedMsgModel;

public class ChatActivity extends AppCompatActivity {

    private Button send;
    private EditText inputMsg;
    private LinearLayout msgScroll;

    private int friendHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        this.send = findViewById(R.id.chat_send);
        this.inputMsg = findViewById(R.id.chat_msg);
        this.msgScroll = findViewById(R.id.chat_msg_scroll);

        Intent intent = this.getIntent();
        String title = intent.getStringExtra("friend");
        this.friendHead = intent.getIntExtra("head", 0);
        setTitle(title);

        View msgCell1 = new DetailedMsgView(getContext(), null);
        ((DetailedMsgView) msgCell1).setModel(new DetailedMsgModel("好的，先到这儿", R.mipmap.headicon0));
        msgScroll.addView(msgCell1);

        View msgCell2 = new DetailedMsgView(getContext(), null);
        ((DetailedMsgView) msgCell2).setModel(new DetailedMsgModel("bye", friendHead));
        msgScroll.addView(msgCell2);

        View msgCell3 = new DetailedMsgView(getContext(), null);
        ((DetailedMsgView) msgCell3).setModel(new DetailedMsgModel("bye", R.mipmap.headicon0));
        msgScroll.addView(msgCell3);

        this.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = inputMsg.getText().toString();
                if(!TextUtils.isEmpty(msg)){
                    View msgCell = new DetailedMsgView(getContext(), null);
                    ((DetailedMsgView) msgCell).setModel(new DetailedMsgModel(msg, friendHead));
                    msgScroll.addView(msgCell);
                }
            }
        });
    }

    private Context getContext(){
        return this;
    }
}
