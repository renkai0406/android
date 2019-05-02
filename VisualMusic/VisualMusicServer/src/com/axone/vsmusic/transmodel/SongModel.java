package com.axone.vsmusic.transmodel;

import java.io.Serializable;

import com.axone.vsmusic.translation.Translation;

public class SongModel extends Translation implements Serializable{

	private String songname;
	private String singer;
	private boolean isFavour;
	private int songType;
	private String songLocation;
	private String lyricLocation;
	public SongModel(String songname, String singer, boolean isFavour, int songType, String songLocation,
			String lyricLocation) {
		super();
		this.songname = songname;
		this.singer = singer;
		this.isFavour = isFavour;
		this.songType = songType;
		this.songLocation = songLocation;
		this.lyricLocation = lyricLocation;
	}
	public String getSongname() {
		return songname;
	}
	public void setSongname(String songname) {
		this.songname = songname;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public boolean isFavour() {
		return isFavour;
	}
	public void setFavour(boolean isFavour) {
		this.isFavour = isFavour;
	}
	public int getSongType() {
		return songType;
	}
	public void setSongType(int songType) {
		this.songType = songType;
	}
	public String getSongLocation() {
		return songLocation;
	}
	public void setSongLocation(String songLocation) {
		this.songLocation = songLocation;
	}
	public String getLyricLocation() {
		return lyricLocation;
	}
	public void setLyricLocation(String lyricLocation) {
		this.lyricLocation = lyricLocation;
	}
	
}
