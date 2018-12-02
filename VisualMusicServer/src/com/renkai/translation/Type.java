package com.renkai.translation;

public interface Type {
	
	//传输协议定义
	public static final int REGISTER = 10;
	public static final int LOGIN = 13;
	public static final int REGISTER_CONFIRM = 14;
	public static final int REGISTER_SET = 15;
	//public static final int GET_CREATE_INFO = 2;
	
	public static final int UPDATE_USER_INFO = 22;
	public static final int GET_USER_INFO = 23;

	public static final int ADD_CREATE_INFO = 30;
	public static final int GET_CREATE_INFO = 33;
	public static final int GET_CREATES_INFO = 34;
	
	public static final int ADD_FAVOURITE_INFO = 40;
	public static final int DEL_FAVOURITE_INFO = 41;
	public static final int GET_FAVOURITE_INFO = 43;
	public static final int GET_FAVOURITES_INFO = 44;
	
	public static final int ADD_HISTORY_INFO = 50;
	public static final int UPDATE_HISTORY_INFO = 52;
	public static final int GET_HISTORY_INFO = 53;
	public static final int GET_LAST_HISTORY_INFO = 54;
	
	public static final int GET_SONG_INFO = 63;
	public static final int GET_SONGS_INFO = 64;
	
	public static final int ADD_FRIEND_INFO = 70;
	public static final int GET_FRIEND_INFO = 73;
	public static final int GET_FRIENDS_INFO = 74;
	public static final int GET_NEAR_FRIEND = 75;
	
	public static final int SET_LOCATION = 80;
	
	public static final int GET_FILE = 90;
	public static final int SET_FILE = 91;
    public static final int MATCH_MUSIC = 92;
    public static final int CREATE_SONG = 93;
	
	public static int SUCCESS = 0;
	public static int ERROR = 1;
	public static int EMPTY_USER = 2;
	public static int EXITED_USER = 3;
	public static int WRONG_PASSWORD = 4;
	public static int NUMBER_NULL = 0;
	
	//其他属性定义
    public static int TYPE_SONG = 1;
    public static int TYPE_TEXT = 2;
    public static int TYPE_LYRIC = 3;
    public static int TYPE_PICTURE = 4;
}
