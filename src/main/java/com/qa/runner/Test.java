package com.qa.runner;

import java.net.ResponseCache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.objects.users;
import com.qa.utility.jsonMethods;

import io.restassured.RestAssured;
import io.restassured.response.Response;



public class Test {

	public static void main(String[] args) throws JsonProcessingException 
	{
		users user=new users("Punitha","");
		jsonMethods jsonMeth=new jsonMethods();
		System.out.println(jsonMeth.printObject(user));
		
		Response response = RestAssured.given().header("Content-Type","application/json").
				body(user).post("https://reqres.in/api/users");
		
		System.out.println(response.getBody().jsonPath().prettify());
		
		
	}

}
