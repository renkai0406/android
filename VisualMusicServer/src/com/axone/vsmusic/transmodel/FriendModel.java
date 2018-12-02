package com.axone.vsmusic.transmodel;

import java.io.Serializable;

import com.axone.vsmusic.translation.Translation;

public class FriendModel extends Translation implements Serializable{

	private String memoname;
	private String tag;
	private String lastHistoryName;
	private int lastHistoryType;
	private boolean isFavour;
	private String lastHistorySong;
	private String lastHistoryLyric;
	public FriendModel() {
		// TODO Auto-generated constructor stub
	}
	public FriendModel(String memoname, String tag, String lastHistoryName, int lastHistoryType, boolean isFavour, 
			String lastHistorySong, String lastHistoryLyric) {
		super();
		this.memoname = memoname;
		this.tag = tag;
		this.lastHistoryName = lastHistoryName;
		this.lastHistoryType = lastHistoryType;
		this.isFavour = isFavour;
		this.lastHistorySong = lastHistorySong;
		this.lastHistoryLyric = lastHistoryLyric;
	}
	public String getMemoname() {
		return memoname;
	}
	public void setMemoname(String memoname) {
		this.memoname = memoname;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getLastHistoryName() {
		return lastHistoryName;
	}
	public void setLastHistoryName(String lastHistoryName) {
		this.lastHistoryName = lastHistoryName;
	}
	public int getLastHistoryType() {
		return lastHistoryType;
	}
	public void setLastHistoryType(int lastHistoryType) {
		this.lastHistoryType = lastHistoryType;
	}
	public boolean isFavour() {
		return isFavour;
	}
	public void setFavour(boolean isFavour) {
		this.isFavour = isFavour;
	}
	public String getLastHistorySong() {
		return lastHistorySong;
	}
	public void setLastHistorySong(String lastHistorySong) {
		this.lastHistorySong = lastHistorySong;
	}
	public String getLastHistoryLyric() {
		return lastHistoryLyric;
	}
	public void setLastHistoryLyric(String lastHistoryLyric) {
		this.lastHistoryLyric = lastHistoryLyric;
	}
	
}
