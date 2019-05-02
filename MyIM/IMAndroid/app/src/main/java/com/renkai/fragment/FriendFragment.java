package com.renkai.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.renkai.R;
import com.renkai.view.FriendItemView;
import com.renkai.vmodel.FriendItemModel;

public class FriendFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_main_friend, container, false);
        initFriendView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initFriendView(View view){
        LinearLayout scroll = view.findViewById(R.id.vp_main_friend_scroll);

        FriendItemView friend1 = new FriendItemView(getContext(), null);
        friend1.setModel(new FriendItemModel(R.mipmap.headicon1, "张三"));
        scroll.addView(friend1);

        FriendItemView friend2 = new FriendItemView(getContext(), null);
        friend2.setModel(new FriendItemModel(R.mipmap.headicon2, "张二"));
        scroll.addView(friend2);

        FriendItemView friend3 = new FriendItemView(getContext(), null);
        friend3.setModel(new FriendItemModel(R.mipmap.headicon3, "王五"));
        scroll.addView(friend3);

    }
}
