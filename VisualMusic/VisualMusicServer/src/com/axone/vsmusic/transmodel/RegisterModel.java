package com.axone.vsmusic.transmodel;

import java.io.Serializable;
import java.util.Date;

import com.axone.vsmusic.translation.Translation;
import com.renkai.translation.Type;

public class RegisterModel extends Translation implements Serializable{
	
	private String username;
	private String password;
	private int sex;
	private Date birthday;
	private String tag;
	private String address;
	private int blood;
	private int age;
	
	public RegisterModel() {
	}
	
	public RegisterModel(String username, String password, int sex, Date birthday, String tag, String address, int blood, int age) {
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.birthday = birthday;
		this.tag = tag;
		this.address = address;
		this.blood = blood;
		this.age = age;
	}



	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getBlood() {
		return blood;
	}
	public void setBlood(int blood) {
		this.blood = blood;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
