package com.qa.stepDefinitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.hamcrest.collection.HasItemInArray;

import com.qa.utility.jsonMethods;
import com.qa.utility.setUp;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.*;

public class api_users 
{
	Properties prop=setUp.envSetUp();
	String baseUri=prop.getProperty("baseUri");
	String userPath=prop.getProperty("userPath");
	RequestSpecification req=null;
	Response res=null;	
	jsonMethods jsonMeth=new jsonMethods();
	
	
	
	@Before()
	public void validEndPoint()
	{
		req=RestAssured.given().baseUri(baseUri);
		RestAssured.defaultParser=Parser.JSON;
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
		
		//System.out.println(res.getBody().jsonPath().prettify());
		List<String> names=new ArrayList<String>();
		names.add("George");
		names.add("Janet");
		names.add("Emma");
		//jsonMeth.ValidateHasItems(res, "data.first_name", names);
		jsonMeth.ValidateKeyNumber(res, "total_pages", 2);
		jsonMeth.ValidateKeyText(res, "data[0].first_name", "George");
		
	
	}
	@Then("^I validate the last name$")
	public void validateLastNameCode()
	{
		System.out.println(jsonMeth.returnListOfString(res, "data.avatar"));
		System.out.println(jsonMeth.returnInt(res, "data[0].id"));
		System.out.println(jsonMeth.returnFloat(res, "data[0].id"));
	
	}
	
}
