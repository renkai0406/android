package com.renkai.repertory;

import java.util.Date;

public class UserInfo {

	private int user_id;
	private int user_sex;
	private Date user_birth;
	private String user_tag;
	private String user_add;
	private int user_blood;
	private int user_age;
	
	public UserInfo() {
		// TODO Auto-generated constructor stub
	}
	public UserInfo(int user_sex, Date user_birth, String user_tag, String user_add, int user_blood, int user_age) {
		this.user_sex = user_sex;
		this.user_birth = user_birth;
		this.user_tag = user_tag;
		this.user_add = user_add;
		this.user_blood = user_blood;
		this.user_age = user_age;
	}
	
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(int user_sex) {
		this.user_sex = user_sex;
	}
	public Date getUser_birth() {
		return user_birth;
	}
	public void setUser_birth(Date user_birth) {
		this.user_birth = user_birth;
	}
	public String getUser_tag() {
		return user_tag;
	}
	public void setUser_tag(String user_tag) {
		this.user_tag = user_tag;
	}
	public String getUser_add() {
		return user_add;
	}
	public void setUser_add(String user_add) {
		this.user_add = user_add;
	}
	public int getUser_blood() {
		return user_blood;
	}
	public void setUser_blood(int user_blood) {
		this.user_blood = user_blood;
	}
	public int getUser_age() {
		return user_age;
	}
	public void setUser_age(int user_age) {
		this.user_age = user_age;
	}
	
}
