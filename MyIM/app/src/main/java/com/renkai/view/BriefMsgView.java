package com.renkai.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.renkai.R;
import com.renkai.chat.ChatActivity;
import com.renkai.vmodel.BriefMsgModel;
import com.renkai.vmodel.IViewModel;

public class BriefMsgView extends LinearLayout implements IView {
    private ImageView briefHead;
    private TextView briefName;
    private TextView briefMsg;
    private TextView briefTime;
    private ImageView briefNotify;

    private Context parent;

    private BriefMsgModel model;

    public BriefMsgView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_brief_msg_item, this);

        this.parent = context;

        this.briefHead = findViewById(R.id.briefmsg_head);
        this.briefName = findViewById(R.id.briefmsg_name);
        this.briefMsg = findViewById(R.id.briefmsg_msg);
        this.briefTime = findViewById(R.id.briefmsg_time);
        this.briefNotify = findViewById(R.id.briefmsg_notify);


    }

    @Override
    public void setModel(IViewModel md) {
        this.model = (BriefMsgModel)md;
        Drawable headPic = getResources().getDrawable(this.model.getHead());
        if(headPic != null)
            this.briefHead.setImageDrawable(headPic);
        this.briefName.setText(this.model.getName());
        this.briefMsg.setText(this.model.getMsg());
        this.briefTime.setText(this.model.getTime());
        this.briefNotify.setVisibility(((BriefMsgModel) model).isNotifing() ? VISIBLE : INVISIBLE);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jumpToDetial = new Intent(parent, ChatActivity.class);
                jumpToDetial.putExtra("friend", model.getName());
                jumpToDetial.putExtra("head", model.getHead());
                parent.startActivity(jumpToDetial);
            }
        });
    }
}
