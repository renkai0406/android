package com.renkai.repertory;

public class FriendInfo{
	
	private int friend_id;
	private int user_id;
	private int friend_user_id;
	private String memo_name;

	public FriendInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public FriendInfo(int friend_id, int user_id, int friend_user_id, String memo_name) {
		this.friend_id = friend_id;
		this.user_id = user_id;
		this.friend_user_id = friend_user_id;
		this.memo_name = memo_name;
	}

	public int getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(int friend_id) {
		this.friend_id = friend_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getFriend_user_id() {
		return friend_user_id;
	}

	public void setFriend_user_id(int friend_user_id) {
		this.friend_user_id = friend_user_id;
	}
	
	public String getMemo_name() {
		return memo_name;
	}
	
	public void setMemo_name(String memo_name) {
		this.memo_name = memo_name;
	}
	
}
