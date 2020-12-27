package com.qa.runner;

import java.net.ResponseCache;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.objects.users;
import com.qa.utility.jsonFunctions;

import io.restassured.RestAssured;
import io.restassured.response.Response;



public class Test {

	public static void main(String[] args) throws JsonProcessingException 
	{
       String date1="2020-12-27T17:51:39.718Z";
       String date2="2023-11-28T17:51:39.718Z";
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
       sdf.setTimeZone(TimeZone.getTimeZone("UTC"));     
       
      
       try {
    	   Date date3=sdf.parse(date1);
    	   Date date4=sdf.parse(date2);
System.out.println(date4.after(date3));
System.out.println((date4.getTime()-date3.getTime())/(1000*60*60*24));

Calendar c=Calendar.getInstance();
c.setTime(date4);
System.out.println(c.getWeekYear());
System.out.println(c.get(Calendar.DAY_OF_WEEK));




	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
	}

}
