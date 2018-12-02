package com.axone.vsmusic.log;

/**
 * Created by 秋水 on 2017/11/9.
 */

public class AxoneLog {
    private String time;
    private int type;
    private String info;

    public AxoneLog(String time, int type, String info) {
        this.time = time;
        this.type = type;
        this.info = info;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
