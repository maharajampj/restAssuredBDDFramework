package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.utility.setUp;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class api_users 
{
	Properties prop=setUp.envSetUp();
	String baseUri=prop.getProperty("baseUri");
	String userPath=prop.getProperty("userPath");
	RequestSpecification req=null;
	Response res=null;	
	
	
	@Before()
	public void validEndPoint()
	{
		req=RestAssured.given().baseUri(baseUri);
	}

	@Given("^I have API endpoint$")
	public void endPoint()
	{
	}
	
	@When("^I send the request$")
	public void sendRequest()
	{
		res=req.when().get(userPath);
	}
	
	@Then("^I validate the response code$")
	public void validateResponseCode()
	{
		res.then().statusCode(200);
		System.out.println(res.getBody().jsonPath().prettify());

	}
	
}
