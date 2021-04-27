package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.qa.objects.users;
import com.qa.request.request;
import com.qa.utility.jsonFunctions;
import com.qa.utility.listFunctions;
import com.qa.utility.setUp;

import io.restassured.config.ParamConfig;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class unitTests 
{
	Properties prop=setUp.envSetUp();
	String posts=prop.getProperty("posts");
	String comments=prop.getProperty("comments");
	String storeInventory=prop.getProperty("storeInventory");
	String createUser=prop.getProperty("createUser");
	request request=new request();
	jsonFunctions jFun= new jsonFunctions();
	listFunctions lFun=new listFunctions();
	Faker faker=new Faker();
	
	
	
	@Test(enabled = false)
	public void ValidatePosts()
	{
		List<Header> list = new ArrayList<Header>();
		list.add(new Header("Content-Type", "application/json"));
		Headers headers=new Headers(list);
		Response res=request.sendGetRequest(headers, posts);
		int userId=jFun.returnInt(res, "userId[0]");
		Assert.assertEquals(userId, 1);
	}
	@Test(enabled = false)
	public void ValidateTitle()
	{
		List<Header> list = new ArrayList<Header>();
		list.add(new Header("Content-Type", "application/json"));
		Headers headers=new Headers(list);
		Response res=request.sendGetRequest(headers, posts);
		List<String> title=jFun.returnListOfString(res, "title");
		Assert.assertEquals(lFun.listHasAnyItem(title, "sunt123s"), false);
		
	}
	@Test(enabled = false)
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
	@Test(enabled = false)
	public void ValidateEmail()
	{
		List<Header> list = new ArrayList<Header>();
		list.add(new Header("Content-Type", "application/json"));
		Headers headers=new Headers(list);
		Response res=request.sendGetRequest(headers, comments);
		List<String> emails=jFun.returnListOfString(res, "email");
		Assert.assertEquals(lFun.listHasProperEmailFormat(emails), true);
}
	@Test(enabled=false)
	public void ValidatePetInventory()
	{
	List<Header> list = new ArrayList<Header>();
	list.add(new Header("Content-Type", "application/json"));
	Headers headers=new Headers(list);
	Response res=request.sendGetRequest(headers, storeInventory);
	int availablePet=jFun.returnInt(res, "Available");
	Assert.assertEquals(availablePet, 12);
		
	}
	@Test(enabled=false)
	public void ValidateCreatUser()
	{
	List<Header> list = new ArrayList<Header>();
	list.add(new Header("Content-Type", "application/json"));
	Headers headers=new Headers(list);
	users user=new users(1,faker.name().username(),faker.name().firstName(),faker.name().lastName(),"kim@mail.com",faker.company().buzzword(),faker.phoneNumber().toString(),2);
	Response res=request.sendPostRequest(headers, user, createUser);
	Assert.assertEquals(res.statusCode(), 200);
	Assert.assertEquals(jFun.returnInt(res, "code"), 200);
	}
	@Test(enabled=true)
	public void ValidateCreatedUser()
	{
	List<Header> list = new ArrayList<Header>();
	list.add(new Header("Content-Type", "application/json"));
	Headers headers=new Headers(list);
	Response res=request.getReq().get(createUser+"/Maha1234");
	jFun.ValidateStatuCode(res, 200);
	String email=jFun.returnText(res, "email");
	Assert.assertEquals(email, "user1@mail.com");
	
	}
	
}
