package com.wdxxl.wechat.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String formatDate(Date date)throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SZ");
       return sdf.format(date);
    }
	
	public static void main(String[]args) throws ParseException{
		System.out.println(DateUtil.formatDate(new Date()));
	}
}
