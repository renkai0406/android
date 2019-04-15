package com.renkai.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.renkai.R;
import com.renkai.vmodel.DetailedMsgModel;
import com.renkai.vmodel.IViewModel;

public class DetailedMsgView extends LinearLayout implements IView {

    private TextView detailMsg;
    private ImageView detailHead;
    private ImageView detailBg;

    public DetailedMsgView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_detailed_msg, this);

        this.detailMsg = findViewById(R.id.detailmsg_msg);
        this.detailHead = findViewById(R.id.detailmsg_head);
    }

    @Override
    public void setModel(IViewModel model) {
        DetailedMsgModel dm = (DetailedMsgModel)model;
        this.detailMsg.setText(dm.getMsg());
        this.detailHead.setImageBitmap(dm.getHead());
    }
}
