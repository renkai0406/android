package com.renkai.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.renkai.R;
import com.renkai.vmodel.BriefMsgModel;
import com.renkai.vmodel.IViewModel;

public class BriefMsgView extends LinearLayout implements IView {
    private ImageView briefHead;
    private TextView briefName;
    private TextView briefMsg;
    private TextView briefTime;
    private ImageView briefNotify;

    public BriefMsgView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_brief_msg, this);

        this.briefHead = findViewById(R.id.briefmsg_head);
        this.briefName = findViewById(R.id.briefmsg_name);
        this.briefMsg = findViewById(R.id.briefmsg_msg);
        this.briefTime = findViewById(R.id.briefmsg_time);
        this.briefNotify = findViewById(R.id.briefmsg_notify);
    }

    @Override
    public void setModel(IViewModel model) {
        BriefMsgModel bm = (BriefMsgModel)model;
        this.briefHead.setImageBitmap(bm.getHead());
        this.briefName.setText(bm.getName());
        this.briefMsg.setText(bm.getMsg());
        this.briefTime.setText(bm.getTime());
        this.briefNotify.setVisibility(((BriefMsgModel) model).isNotifing() ? VISIBLE : INVISIBLE);
    }
}
