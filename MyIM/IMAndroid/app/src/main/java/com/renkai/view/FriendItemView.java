package com.renkai.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.renkai.R;
import com.renkai.vmodel.FriendItemModel;
import com.renkai.vmodel.IViewModel;

public class FriendItemView extends LinearLayout implements IView {

    private ImageView friendHead;
    private TextView friendName;

    private FriendItemModel model;

    public FriendItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_friend_item, this);

        this.friendHead = findViewById(R.id.friend_item_head);
        this.friendName = findViewById(R.id.friend_item_name);
    }

    @Override
    public void setModel(IViewModel md) {
        this.model = (FriendItemModel) md;

        Drawable headPic = getResources().getDrawable(this.model.getHead());
        if(headPic != null)
            this.friendHead.setImageDrawable(headPic);
        this.friendName.setText(this.model.getName());
    }
}
