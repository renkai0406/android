package com.axone.vsmusic.transmodel;

import com.axone.vsmusic.translation.Translation;

import java.io.Serializable;

/**
 * Created by 秋水 on 2017/9/1.
 */

public class FavouriteModel extends Translation implements Serializable {
    private static final long serialVersionUID = -5837215755038124838L;
    private String songname;
    private String singer;
    private int songType;
    private String songLocation;
    private String lyricLocation;
    public FavouriteModel(String songname, String singer, int songType, String songLocation, String lyricLocation) {
        this.songname = songname;
        this.singer = singer;
        this.songType = songType;
        this.songLocation = songLocation;
        this.lyricLocation = lyricLocation;
    }

    public String getSongname() {
        return songname;
    }
    public void setSongname(String songname) {
        this.songname = songname;
    }
    public String getSinger() {
        return singer;
    }
    public void setSinger(String singer) {
        this.singer = singer;
    }
    public int getSongType() {
        return songType;
    }
    public void setSongType(int songType) {
        this.songType = songType;
    }
    public String getSongLocation() {
        return songLocation;
    }
    public void setSongLocation(String songLocation) {
        this.songLocation = songLocation;
    }
    public String getLyricLocation() {
        return lyricLocation;
    }
    public void setLyricLocation(String lyricLocation) {
        this.lyricLocation = lyricLocation;
    }
}
