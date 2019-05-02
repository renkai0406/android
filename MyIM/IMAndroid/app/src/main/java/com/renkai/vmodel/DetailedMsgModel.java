package com.renkai.vmodel;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class DetailedMsgModel implements IViewModel {

    private String msg;
    private int head;

    public DetailedMsgModel(String msg, int head){
        this.msg = msg;
        this.head = head;
    }

    public String getMsg(){
        return this.msg;
    }

    public int getHead(){
        return head;
    }
}
