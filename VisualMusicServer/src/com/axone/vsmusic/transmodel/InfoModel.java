package com.axone.vsmusic.transmodel;

import com.axone.vsmusic.translation.Translation;

import java.io.Serializable;

/**
 * Created by ÇïË® on 2017/9/14.
 */

public class InfoModel extends Translation implements Serializable{

    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

