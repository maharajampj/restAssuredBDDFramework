package com.qa.utility;

import static org.hamcrest.Matchers.hasItems;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.Matchers.*;
import org.hamcrest.collection.HasItemInArray;

import io.restassured.response.Response;

public class jsonMethods 
{

	public String printObject(Object obj) 
	{
        ObjectMapper objMap=new ObjectMapper();
		
		String body = null;
		try {
			body = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return  body;
	}
	public void ValidateHasItems(Response res,String path,List<String> list)
	{
		for(String name:list)
		{
			res.then().body(path,hasItems(name));
		}
		
	}
	public void ValidateKeyNumber(Response res,String path,int num)
	{
		
			res.then().body(path,equalTo(num));
		
		
	}
	public void ValidateKeyText(Response res,String path,String text)
	{
		
			res.then().body(path,equalTo(text));
		
		
	}
	
	public List<Integer> returnListOfNumbers(Response res,String path)
	{
		List<Integer> lists = res.jsonPath().getList(path);
		return lists;
	}
	public List<String> returnListOfString(Response res,String path)
	{
		List<String> lists = res.jsonPath().getList(path);
		return lists;
	}
	public String returnText(Response res,String path)
	{
		String text=res.jsonPath().getString(path);
		return text;
	}
	public int returnInt(Response res,String path)
	{
		int num=Integer.parseInt(res.jsonPath().getString(path));
		return num;
	}
	public float returnFloat(Response res,String path)
	{
		float num=Float.parseFloat(res.jsonPath().getString(path));
		return num;
	}
}
