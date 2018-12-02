package com.renkai.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	
	public static String getCurTime() {
		Date date = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateString = formatter.format(date);
	    return dateString;
	}

}
