package com.renkai.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
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
    private DetailedMsgModel model;

    public DetailedMsgView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_detailed_msg_item, this);

        this.detailMsg = findViewById(R.id.detailmsg_msg);
        this.detailHead = findViewById(R.id.detailmsg_head);
    }

    @Override
    public void setModel(IViewModel md) {
        this.model = (DetailedMsgModel)md;
        this.detailMsg.setText(this.model.getMsg());
        Drawable headPic = getResources().getDrawable(this.model.getHead());
        if(headPic != null)
            this.detailHead.setImageDrawable(headPic);
    }
}
