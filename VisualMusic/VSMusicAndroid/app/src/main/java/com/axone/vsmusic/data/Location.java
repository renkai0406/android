package com.axone.vsmusic.data;

/**
 * Created by 秋水 on 2017/9/6.
 */

public class Location {
    private float x;
    private float y;

    public Location(){

    }

    public Location(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
