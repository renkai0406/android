package com.renkai.repertory;

public class LoginInfo{
	
	private int user_id;
	private String user_name;
	private String user_password;
	
	public LoginInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public LoginInfo(int user_id, String user_name, String user_password) {
		this.user_name = user_name;
		this.user_password = user_password;
	}
	
	public LoginInfo(String user_name, String user_password) {
		super();
		this.user_name = user_name;
		this.user_password = user_password;
	}

	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	
}
