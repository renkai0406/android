package com.axone.vsmusic.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.axone.vsmusic.R;

/**
 * Created by 秋水 on 2017/9/12.
 */

public class FriendView extends LinearLayout {

    private ImageView friendLogo;
    private TextView friendName;
    private TextView friendTag;
    private TextView lastHistory;
    private ImageButton more;

    public FriendView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_friend, this);

        friendLogo = (ImageView) findViewById(R.id.friend_logo);
        friendName = (TextView) findViewById(R.id.friend_name);
        friendTag = (TextView) findViewById(R.id.friend_tag);
        lastHistory = (TextView) findViewById(R.id.friend_last);
        more = (ImageButton) findViewById(R.id.friend_more);
    }

    public void setFriendMoreListener(OnClickListener listener){
        more.setOnClickListener(listener);
    }

    public void setLastHistoryListener(OnClickListener listener){
        lastHistory.setOnClickListener(listener);
    }

    public void setFriendLogo(Bitmap bitmap){
        friendLogo.setImageBitmap(bitmap);
    }

    public void setFriendName(String name){
        friendName.setText(name);
    }

    public void setFriendTag(String tag){
        friendTag.setText(tag);
    }

    public void setTagTextColor(int color){ friendTag.setTextColor(color);}

    public void setTagBGColor(int color){friendTag.setBackgroundColor(color);}

    public void setLastHistory(String history){
        lastHistory.setText(history);
    }

}
