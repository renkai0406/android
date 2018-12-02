package com.axone.vsmusic.transmodel;

import java.io.Serializable;

import com.axone.vsmusic.translation.Translation;

public class CreateModel extends Translation implements Serializable{
	private String songname;
	private boolean isFavour;
	private String songLocation;
	private String lyricLocation;
	
	public CreateModel(String songname, boolean isFavour, String songLocation, String lyricLocation) {
		super();
		this.songname = songname;
		this.isFavour = isFavour;
		this.songLocation = songLocation;
		this.lyricLocation = lyricLocation;
	}

	public String getSongname() {
		return songname;
	}

	public void setSongname(String songname) {
		this.songname = songname;
	}

	public boolean isFavour() {
		return isFavour;
	}

	public void setFavour(boolean isFavour) {
		this.isFavour = isFavour;
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
