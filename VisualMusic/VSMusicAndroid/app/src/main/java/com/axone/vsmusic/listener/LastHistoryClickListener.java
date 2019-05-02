package com.axone.vsmusic.listener;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.axone.vsmusic.activity.PlayingCreatedActivity;
import com.axone.vsmusic.task.GetSongTask;
import com.axone.vsmusic.util.MessagePool;

/**
 * Created by 秋水 on 2017/11/1.
 */

public class LastHistoryClickListener implements View.OnClickListener {

    private Activity activity;
    private String songName;
    private String songLocation;

    public LastHistoryClickListener(Activity activity, String songName, String songLocation) {
        this.activity = activity;
        this.songName = songName;
        this.songLocation = songLocation;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClass(activity, PlayingCreatedActivity.class);
        GetSongTask gfTask = new GetSongTask();
        MessagePool.pTask = gfTask;
        gfTask.execute(songName, songLocation);
        activity.startActivity(intent);
    }
}
