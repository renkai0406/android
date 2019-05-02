package com.axone.vsmusic.transmodel;

import java.io.Serializable;

import com.axone.vsmusic.translation.Translation;
import com.renkai.translation.Type;

public class LoginModel extends Translation implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8460831953846312179L;
	private String username;
	private String password;
	
	public LoginModel(String username, String password) {
		this.type = Type.LOGIN;
		this.username = username;
		this.password = password;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
