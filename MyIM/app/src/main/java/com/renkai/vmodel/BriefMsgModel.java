package com.renkai.vmodel;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class BriefMsgModel implements IViewModel {
    private int head;
    private String name;
    private String msg;
    private String time;
    private boolean notifing;

    public BriefMsgModel(int head, String name, String msg, String time, boolean notifing) {
        this.head = head;
        this.name = name;
        this.msg = msg;
        this.time = time;
        this.notifing = notifing;
    }

    public int getHead() {
        return head;
    }

    public String getName() {
        return name;
    }

    public String getMsg() {
        return msg;
    }

    public String getTime() {
        return time;
    }

    public boolean isNotifing() {
        return notifing;
    }
}
