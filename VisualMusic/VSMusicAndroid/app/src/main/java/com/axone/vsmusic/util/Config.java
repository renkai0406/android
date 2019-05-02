package com.axone.vsmusic.util;

/**
 * Created by 秋水 on 2017/8/25.
 */

public class Config {
    public static final int SERVER_PORT =  8087;
    public static final int SERVER_PORT_FILE = 8088;
    public static final String SERVER_URL = "119.23.104.68";

    public static final String EMPTY_USER_MESSAGE = "该用户名不存在！";
    public static final String WRONG_PASSWORD_MESSAGE = "密码错误！";
    public static final String SECRET = "保密";

    public static String COMMON_LOCATION = "/storage/emulated/0/Android/data/com.axone.vsmusic/files";
    public static String SONG_LOCATION = "/Music";
    public static String TEXT_LOCATION = "/Documents";
    public static String LYRIC_LOCATION = "/Lyric";
    public static String PICTURE_LOCATION = "/Pictures";
    public static String LOG_LOCATION = "/Logs";
    public static String POSTFIX_SONG = ".mid";
    public static String POSTFIX_TEXT = ".txt";

    public static String HTML_INDEX_URL = "file:///android_asset/web/index.html";


    public static final int FILE_BUFFER_SIZE = 1024;
}
