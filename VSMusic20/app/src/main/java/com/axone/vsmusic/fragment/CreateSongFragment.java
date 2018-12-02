package com.axone.vsmusic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.axone.vsmusic.R;
import com.axone.vsmusic.activity.PlayingCreatedActivity;
import com.axone.vsmusic.task.CreateTask;
import com.axone.vsmusic.util.MessagePool;

/**
 * Created by 秋水 on 2017/9/15.
 */

public class CreateSongFragment extends Fragment {

    private EditText et_lyric;
    private EditText et_title;
    private ImageButton bt_create;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view = inflater.inflate(R.layout.viewpager_createsong, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        et_lyric = (EditText) view.findViewById(R.id.lyric_lyric);
        et_title = (EditText) view.findViewById(R.id.lyric_title);
        bt_create = (ImageButton) view.findViewById(R.id.lyric_create);

        bt_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lyric = et_lyric.getText().toString();
                String title = et_title.getText().toString();
                String toast_info = null;

                if(title == null && lyric == null){
                    toast_info = "歌名和歌词都不能为空!";
                } else if(title == null && lyric != null){
                    toast_info = "歌名不能为空!";
                }else if(title != null && lyric == null){
                    toast_info = "歌词不能为空!";
                }

                if(toast_info != null){
                    Toast toast = Toast.makeText(getActivity(), toast_info, Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }


                Intent intent = new Intent();
                intent.setClass(getActivity(), PlayingCreatedActivity.class);
                intent.putExtra("lyric", lyric);
                CreateTask ct = new CreateTask();
                MessagePool.pTask = ct;
                ct.execute(title, lyric);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
