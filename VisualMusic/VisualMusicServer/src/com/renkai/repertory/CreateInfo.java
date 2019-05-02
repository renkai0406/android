package com.renkai.repertory;

public class CreateInfo {
	private int create_id;
	private int user_id;
	private int song_id;
	
	public CreateInfo() {
		// TODO Auto-generated constructor stub
	}

	public CreateInfo(int create_id, int user_id, int song_id) {
		this.create_id = create_id;
		this.user_id = user_id;
		this.song_id = song_id;
	}

	public int getCreate_id() {
		return create_id;
	}

	public void setCreate_id(int create_id) {
		this.create_id = create_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getSong_id() {
		return song_id;
	}

	public void setSong_id(int song_id) {
		this.song_id = song_id;
	}
}
