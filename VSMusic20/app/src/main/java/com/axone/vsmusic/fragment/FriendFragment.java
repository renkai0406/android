package com.axone.vsmusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.axone.vsmusic.R;
import com.axone.vsmusic.listener.FriendMoreClickListener;
import com.axone.vsmusic.listener.LastHistoryClickListener;
import com.axone.vsmusic.transmodel.FriendModel;
import com.axone.vsmusic.transmodel.FriendModels;
import com.axone.vsmusic.util.Config;
import com.axone.vsmusic.view.FriendView;

/**
 * Created by 秋水 on 2017/9/12.
 */

public class FriendFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("我的好友界面创建");
         View view = inflater.inflate(R.layout.viewpager_myfriends, container, false);
        return view;
    }

    public void setFriends(FriendModels friendModels, View view){
        LinearLayout vp_friends = (LinearLayout) view.findViewById(R.id.vp_friends);
        for(int i = 0; i < friendModels.getSize(); i ++){
            FriendModel friendModel = friendModels.getFriend(i);
            FriendView friendView = new FriendView(getContext(), null);
            friendView.setFriendName(friendModel.getMemoname());
            friendView.setFriendTag(friendModel.getTag());
            String lastHistoryName = friendModel.getLastHistoryName();
            if(lastHistoryName == null){
                friendView.setLastHistory(Config.SECRET);
            }else{
                friendView.setLastHistory(friendModel.getLastHistoryName());
                //设置“最近在听”监听
                friendView.setLastHistoryListener(new LastHistoryClickListener(getActivity(), lastHistoryName, friendModel.getLastHistorySong()));
            }
            //设置“更多信息”监听
            friendView.setFriendMoreListener(new FriendMoreClickListener());
            vp_friends.addView(friendView);
        }
    }
}
