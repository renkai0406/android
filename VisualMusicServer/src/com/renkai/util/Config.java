package com.renkai.util;

public interface Config {
	
	public static int PORT = 8087;
	public static int FILE_PORT = 8088;
	public static String SQL_USER = "";
	//∞¢¿Ô‘∆
	public static String COMMON_LOCATION = "/usr/java/vsmusic";
	//Œ‰¥Û‘∆
	//public static String COMMON_LOCATION = "/home/ubuntu/axone/bin";
	public static String SONG_LOCATION = "/song";
	public static String TEXT_LOCATION = "/text";
	public static String LYRIC_LOCATION = "/lyric";
    public static String LOG_LOCATION = "/log";
	public static String PICTURE_LOCATION = "/picture";
	public static String DELIVER_PREDICTION_BEFORE = "---------- Prediction for ";
	public static String DELIVER_PREDICTION_AFTER = " ----------";
	public static String PATTERN_QUOTES = "(name)\\\\s+\\\"(\\\\w+\\\\s+\\\\w+)\\\"";
	
	public static int FILE_BUFFER_SIZE = 1024;
}
