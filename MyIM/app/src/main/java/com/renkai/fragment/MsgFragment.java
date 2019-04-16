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
import com.renkai.view.BriefMsgView;
import com.renkai.vmodel.BriefMsgModel;

public class MsgFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_main_msg, container, false);
        InitMsgView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void InitMsgView(View view){
        LinearLayout scroll = view.findViewById(R.id.vp_main_msg_scroll);

        BriefMsgView child1 = new BriefMsgView(getContext(), null);
        child1.setModel(new BriefMsgModel(R.mipmap.headicon1, "张三", "hi", "13:31", true));
        scroll.addView(child1);

        BriefMsgView child2 = new BriefMsgView(getContext(), null);
        child2.setModel(new BriefMsgModel(R.mipmap.headicon2, "张二", "hi hi", "12:08", true));
        scroll.addView(child2);

        BriefMsgView child3 = new BriefMsgView(getContext(), null);
        child3.setModel(new BriefMsgModel(R.mipmap.headicon3, "王五", "hi hi hi", "昨天", false));
        scroll.addView(child3);
    }
}
