package com.renkai.vmodel;

import android.graphics.Bitmap;

public class DetailedMsgModel implements IViewModel {

    private String msg;
    private Bitmap head;

    public DetailedMsgModel(String msg, Bitmap head){
        this.msg = msg;
        this.head = head;
    }

    public String getMsg(){
        return this.msg;
    }

    public Bitmap getHead(){
        return head;
    }
}
