package com.axone.vsmusic.transmodel;

import java.util.LinkedList;

/**
 * Created by 秋水 on 2017/9/2.
 */

public class SongModels {
    private LinkedList<SongModel> songs = new LinkedList<>();

    public int getSize() {
        return songs.size();
    }

    public void add(SongModel sm) {
        songs.add(sm);
    }

    public SongModel getSong(int index){return songs.get(index);}
}
