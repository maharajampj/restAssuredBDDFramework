package com.qa.runner;

import java.net.ResponseCache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.objects.users;

import io.restassured.RestAssured;
import io.restassured.response.Response;



public class Test {

	public static void main(String[] args) throws JsonProcessingException 
	{
		users user=new users("Punitha","");
		ObjectMapper objMap=new ObjectMapper();
		
		String body=objMap.writerWithDefaultPrettyPrinter().writeValueAsString(user);
		
		System.out.println(body);
		
		Response response = RestAssured.given().header("Content-Type","application/json").
				body(user).post("https://reqres.in/api/users");
		
		System.out.println(response.getBody().jsonPath().prettify());
		
		
	}

}
