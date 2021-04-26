package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.request.request;
import com.qa.utility.jsonFunctions;
import com.qa.utility.listFunctions;
import com.qa.utility.setUp;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class unitTests 
{
	Properties prop=setUp.envSetUp();
	String posts=prop.getProperty("posts");
	String comments=prop.getProperty("comments");
	request request=new request();
	jsonFunctions jFun= new jsonFunctions();
	listFunctions lFun=new listFunctions();
	@Test
	public void ValidatePosts()
	{
		List<Header> list = new ArrayList<Header>();
		list.add(new Header("Content-Type", "application/json"));
		Headers headers=new Headers(list);
		Response res=request.sendGetRequest(headers, posts);
		int userId=jFun.returnInt(res, "userId[0]");
		Assert.assertEquals(userId, 1);
	}
	@Test
	public void ValidateTitle()
	{
		List<Header> list = new ArrayList<Header>();
		list.add(new Header("Content-Type", "application/json"));
		Headers headers=new Headers(list);
		Response res=request.sendGetRequest(headers, posts);
		List<String> title=jFun.returnListOfString(res, "title");
		Assert.assertEquals(lFun.listHasAnyItem(title, "sunt123s"), false);
		
	}
	@Test
	public void ValidateFirst_LastTitle()
	{
		List<Header> list = new ArrayList<Header>();
		list.add(new Header("Content-Type", "application/json"));
		Headers headers=new Headers(list);
		Response res=request.sendGetRequest(headers, posts);
		List<String> title=jFun.returnListOfString(res, "title");
		Assert.assertEquals(lFun.listMaxText(title), "voluptatum itaque dolores nisi et quasi");
		Assert.assertEquals(lFun.listMinText(title), "a quo magni similique perferendis");
		
		
	}
	@Test
	public void ValidateEmail()
	{
		List<Header> list = new ArrayList<Header>();
		list.add(new Header("Content-Type", "application/json"));
		Headers headers=new Headers(list);
		Response res=request.sendGetRequest(headers, comments);
		List<String> emails=jFun.returnListOfString(res, "email");
		Assert.assertEquals(lFun.listHasProperEmailFormat(emails), true);
}
}
