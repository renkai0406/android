package com.axone.vsmusic.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.axone.vsmusic.R;
import com.axone.vsmusic.task.CreateTask;
import com.axone.vsmusic.util.MessagePool;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class PlayingCreatedActivity extends AppCompatActivity {

    private ImageButton bt_play;
    private MediaPlayer player;
    private ImageView iv_photo;
    private ProgressBar pb_loading;
    private SeekBar seekBar;
    private Intent inent;
    private Timer timer;
    private TimerTask timerTask;
    private boolean isChanging=false;//互斥变量，防止定时器与SeekBar拖动时进度冲突
    private boolean isPlaying = false;//是否正在播放中
    private boolean isFirst = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        MessagePool.pTask.setPCAcitivity(this);

        bt_play = (ImageButton) findViewById(R.id.playing_play);
        iv_photo = (ImageView) findViewById(R.id.playing_photo);
        pb_loading = (ProgressBar) findViewById(R.id.playing_loading);
        seekBar = (SeekBar) findViewById(R.id.playing_seekbar);
        inent = getIntent();

        bt_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path = getIntent().getStringExtra("path");
                if (isPlaying){
                    player.pause();
                    bt_play.setImageResource(R.mipmap.button_play);
                    isPlaying = false;
                }else{
                    player.start();
                    bt_play.setImageResource(R.mipmap.button_pause);
                    isPlaying = true;
                }

            }
        });
    }

    public void initPlay(){
        player = new MediaPlayer();
    }
    public void startPlay(String path){
        File file = new File(path);
        if(file.exists()){
            bt_play.setImageResource(R.mipmap.button_pause);
            try {
                System.out.println("即将播放歌曲：" + path);
                player.setDataSource(path);
                player.prepare();
                if (isNegative(getIntent().getStringExtra("lyric"))){
                    player.setPlaybackParams(player.getPlaybackParams().setSpeed(0.3f));
                }else{
                    player.setPlaybackParams(player.getPlaybackParams().setSpeed(1f));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            seekBar.setMax(player.getDuration());
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    if(isChanging){
                        return;
                    }
                    seekBar.setProgress(player.getCurrentPosition());
                }
            };
            timer.schedule(timerTask, 0, 10);
            pb_loading.setVisibility(View.GONE);
            iv_photo.setVisibility(View.VISIBLE);
            player.start();
            isPlaying = true;
        }
    }
    public void pause(){

    }
    public void continuePlay(){}
    public void stop(){}
    public void deal(){}

    private boolean isNegative(String lyric){
        if(lyric == null)
            return false;

        char ch[]={'愁','苦','悲','忧','哀','痛'};
        String arr[]={"伤心","差劲","消极","焦虑","糟糕","阴郁","烦闷" ,"郁闷","怅惘","失落","惆怅","无聊","酸楚","黯然","自卑","垂头丧气","懒散","消沉","内疚","萎靡","猥琐","绝望","失望","气馁","愤怒","憎恨","烦恼","寂寞","空虚","厌世","孤僻","伤心","严肃","脆弱","挫败","不幸","孤独","担心","惊慌","沮丧","恐怖","受惊","疑惧","吓呆","惊吓","恐吓","胆小","嫉妒","无助","冷漠","麻木","羞愧","窘迫","愚蠢","羞辱","卑鄙","堕落","可恶","邪恶","贪婪","可恨","可怕","懒惰","讨厌","腐败","吝啬","恶毒"};

        int len=arr.length;
        int s = lyric.length();
        for(int i=0;i<s;i++){
            for(int j=0;j<6;j++){
                if(lyric.charAt(i)==ch[j]){
                    return true;
                }
            }
        }
        for(int i=0;i<s-1;i++){
            for(int j=0;j<len;j++){
                if(arr[j]==lyric.substring(i,i+1)){
                    return true;
                }
            }
        }
        return false;

    }
}
