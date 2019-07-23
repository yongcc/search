package com.search.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static String getCurrentDatetime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return formatter.format(new Date());
    }
	
	public static String convertDateString(String input) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+HH:mm");
		Date date = null;
		try {
			date = formatter.parse(input);
		} catch (ParseException e) {
			return null;
		}
		SimpleDateFormat formatter1 = new SimpleDateFormat("yy년 MM월 dd일");
		return formatter1.format(date);
	}
}
