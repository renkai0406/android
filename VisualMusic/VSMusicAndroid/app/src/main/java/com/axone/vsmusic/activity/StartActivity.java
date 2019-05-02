package com.axone.vsmusic.activity;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.axone.vsmusic.R;
import com.axone.vsmusic.util.Config;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //设置路径
        Config.SONG_LOCATION = getExternalFilesDir(Environment.DIRECTORY_MUSIC).getAbsolutePath();
        Config.TEXT_LOCATION = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
        Config.LYRIC_LOCATION = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
        Config.PICTURE_LOCATION = getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();


        Intent intent = new Intent();
        intent.setClass(StartActivity.this, SlideActivity.class);
        this.startActivity(intent);
        finish();
    }
}
