package com.renkai.repertory;

import java.io.Serializable;

import com.axone.vsmusic.translation.Translation;

public class FavouriteInfo extends Translation implements Serializable{
	
	private int favour_id;
	private int user_id;
	private int song_id;
	
	public FavouriteInfo(int favour_id, int user_id, int song_id) {
		this.favour_id = favour_id;
		this.user_id = user_id;
		this.song_id = song_id;
	}
	public int getFavour_id() {
		return favour_id;
	}
	public void setFavour_id(int favour_id) {
		this.favour_id = favour_id;
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
