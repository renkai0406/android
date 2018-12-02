package com.renkai.repertory;

public class SongInfo{
	
	private int song_id;
	private int user_id;
	private String song_name;
	private String song_singer;
	private int song_type;
	private String song_location;
	private String lyric_location;

	public SongInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public SongInfo(int song_id, int user_id, String song_name, String song_singer, int song_type, String song_location,
			String lyric_location) {
		this.song_id = song_id;
		this.user_id = user_id;
		this.song_name = song_name;
		this.song_singer = song_singer;
		this.song_type = song_type;
		this.song_location = song_location;
		this.lyric_location = lyric_location;
	}
	
	public int getSong_id() {
		return song_id;
	}
	public void setSong_id(int song_id) {
		this.song_id = song_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getSong_name() {
		return song_name;
	}
	public void setSong_name(String song_name) {
		this.song_name = song_name;
	}
	public String getSong_singer() {
		return song_singer;
	}
	public void setSong_singer(String song_singer) {
		this.song_singer = song_singer;
	}
	public int getSong_type() {
		return song_type;
	}
	public void setSong_type(int song_type) {
		this.song_type = song_type;
	}
	public String getSong_location() {
		return song_location;
	}
	public void setSong_location(String song_location) {
		this.song_location = song_location;
	}
	public String getLyric_location() {
		return lyric_location;
	}
	public void setLyric_location(String lyric_location) {
		this.lyric_location = lyric_location;
	}
	
}
