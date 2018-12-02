package com.renkai.repertory;

public class LastHistoryInfo {

	private int last_id;
	private int user_id;
	private int song_id;
	private boolean is_other_see;
	
	public LastHistoryInfo() {
		// TODO Auto-generated constructor stub
	}

	public LastHistoryInfo(int last_id, int user_id, int song_id, boolean is_other_see) {
		super();
		this.last_id = last_id;
		this.user_id = user_id;
		this.song_id = song_id;
		this.is_other_see = is_other_see;
	}

	public int getLast_id() {
		return last_id;
	}

	public void setLast_id(int last_id) {
		this.last_id = last_id;
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

	public boolean isOther_see() {
		return is_other_see;
	}

	public void setOther_see(boolean is_other_see) {
		this.is_other_see = is_other_see;
	}

}
