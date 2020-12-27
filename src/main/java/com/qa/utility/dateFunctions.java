package com.qa.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class dateFunctions {

	public Date setAsStandardDate(String value) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date = null;
		try {
			date = sdf.parse(value);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return date;

	}
	
	public long dateDifference(String value1,String value2)
	{
		Date d1=setAsStandardDate(value1);
		Date d2=setAsStandardDate(value2);
        long diff=(d1.getTime()-d2.getTime())/(1000*60*60*24);
        return diff;
	}
	
	public boolean isLeapYear(String value)
	{
		Date d1=setAsStandardDate(value);
		Calendar c=Calendar.getInstance();
		c.setTime(d1);
		boolean flag=false;
		int year=c.get(Calendar.YEAR);
		if(((year % 4 == 0) && (year % 100!= 0)) || (year%400 == 0))
		{
			flag=true;
		}
		return flag;
	}
}
