package com.renkai.vmodel;

import android.graphics.Bitmap;

public class BriefMsgModel implements IViewModel {
    private Bitmap head;
    private String name;
    private String msg;
    private String time;
    private boolean notifing;

    public BriefMsgModel(Bitmap head, String name, String msg, String time, boolean notifing) {
        this.head = head;
        this.name = name;
        this.msg = msg;
        this.time = time;
        this.notifing = notifing;
    }

    public Bitmap getHead() {
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
