package com.axone.vsmusic.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.axone.vsmusic.R;


/**
 * Created by 秋水 on 2017/9/12.
 */

public class FavourView extends RelativeLayout {

    private ImageView songLogo;
    private TextView songName;
    private TextView songCreator;
    private TextView lyricCreator;
    private TextView favourNumber;
    private ImageButton isFavour;

    public FavourView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_favour, this);

        songLogo = (ImageView) findViewById(R.id.vw_song_logo);
        songName = (TextView) findViewById(R.id.vw_song_name);
        songCreator = (TextView) findViewById(R.id.vw_song_creator);
        lyricCreator = (TextView) findViewById(R.id.vw_lyric_creator);
        favourNumber = (TextView) findViewById(R.id.vw_favour_num);
        isFavour = (ImageButton) findViewById(R.id.vw_favour);
    }

    public void setFavourClickListener(OnClickListener click){
        isFavour.setOnClickListener(click);
    }

    public void setSongLogo(Bitmap img){
        songLogo.setImageBitmap(img);
    }

    public void setSongName(String name){
        songName.setText(name);
    }

    public void setSongCreator(String creator){
        songCreator.setText(creator);
    }

    public void setLyricCreator(String creator){
        lyricCreator.setText(creator);
    }

    public void setFavourNumber(int number){
        favourNumber.setText(number + "");
    }

    public  void setIsFavour(boolean favourite){
        isFavour.setImageResource(favourite?R.mipmap.like:R.mipmap.dislike);
    }
}
