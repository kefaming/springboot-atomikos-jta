package com.atomikos.jta.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public final static String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss.SSS";
	
	public static void main(String[] args) {
		Date dateValue = new Date();
		String s = yestoday();
		System.out.println(s);
	}
	
	public static final String yestoday() {
		Calendar cal = Calendar.getInstance();
		cal.set(11, 0);
		cal.set(12, 0);
		cal.set(13, 0);
		cal.set(14, 0);
		cal.add(5, -1);
		Date date = cal.getTime();
		return format(date,"yyyy-MM-dd");
	}
	
	public static final String today() {
		Calendar cal = Calendar.getInstance();
		cal.set(11, 0);
		cal.set(12, 0);
		cal.set(13, 0);
		cal.set(14, 0);
		Date date = cal.getTime();
		return format(date,"yyyy-MM-dd");
	}

	public static final String tomorrow() {
		Calendar cal = Calendar.getInstance();
		cal.set(11, 0);
		cal.set(12, 0);
		cal.set(13, 0);
		cal.set(14, 0);
		cal.add(5, 1);
		Date date = cal.getTime();
		return format(date,"yyyy-MM-dd");
	}

	public static final Date thisMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(11, 0);
		cal.set(12, 0);
		cal.set(13, 0);
		cal.set(14, 0);
		cal.set(5, 1);
		return cal.getTime();
	}

	public static final Date nextMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(11, 0);
		cal.set(12, 0);
		cal.set(13, 0);
		cal.set(14, 0);
		cal.set(5, 1);
		cal.add(2, 1);
		return cal.getTime();
	}

	public static final Date parse(String dateString) {
		DateFormat formator = new SimpleDateFormat("yyyy-MM-dd");
		return parse(dateString, formator);
	}

	public static final Date parse(String dateString, DateFormat formator) {
		if ((dateString == null) || (dateString.length() <= 0)) {
			return null;
		}
		if (formator == null)
			throw new IllegalArgumentException("Argument 'formator' is null");
		try {
			return formator.parse(dateString);
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

//	public static String format(Date dateValue) {
//		DateFormat sfdate = new SimpleDateFormat(FORMAT_DATETIME);
//		return format(dateValue,sfdate);
//	}
	
	public static String format(Date dateValue) {
		DateFormat sfdate = new SimpleDateFormat(FORMAT_DATETIME);
		return sfdate.format(dateValue);
	}
	
	public static String format(Date dateValue,String pattern) {
		DateFormat sfdate = new SimpleDateFormat(pattern);
		return sfdate.format(dateValue);
	}
}