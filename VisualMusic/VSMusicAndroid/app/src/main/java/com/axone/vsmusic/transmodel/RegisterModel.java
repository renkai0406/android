package com.axone.vsmusic.transmodel;

import com.axone.vsmusic.translation.Translation;
import com.axone.vsmusic.translation.Type;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 秋水 on 2017/8/28.
 */

public class RegisterModel extends Translation implements Serializable {

    private String username;
    private String password;
    private int sex;
    private Date birthday;
    private String tag;
    private String address;
    private int blood;

    public RegisterModel(String username, String password, int sex, Date birthday, String tag, String address, int blood) {
        type = Type.REGISTER;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.birthday = birthday;
        this.tag = tag;
        this.address = address;
        this.blood = blood;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getSex() {
        return sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getTag() {
        return tag;
    }

    public String getAddress() {
        return address;
    }

    public int getBlood() { return blood; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }
}
