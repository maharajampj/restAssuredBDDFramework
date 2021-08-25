package com.qa.stepDefinitions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.hamcrest.collection.HasItemInArray;

import com.qa.objects.users;
import com.qa.utility.dateFunctions;
import com.qa.utility.fileFunctions;
import com.qa.utility.geoFunctions;
import com.qa.utility.jsonFunctions;
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
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;

public class api_users 
{
	Properties prop=setUp.envSetUp();
	String baseUri=prop.getProperty("baseUri");
	String userPath=prop.getProperty("userPath");
	RequestSpecification req=null;
	Response res=null;	
	jsonFunctions jsonFun=new jsonFunctions();
	geoFunctions geoFun=new geoFunctions();
	dateFunctions dateFun=new dateFunctions();
	fileFunctions fileFun=new fileFunctions();
	
	String userSchemaPath="//src//main//resources//schema//users.json";
	
	
	
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
	
	@When("^I send GET request$")
	public void sendRequest()
	{
		res=req.get(userPath);
	}
	
	@Then("^I validate the response code$")
	public void validateResponseCode()
	{
		
		//System.out.println(res.getBody().jsonPath().prettify());
		List<String> names=new ArrayList<String>();
		names.add("George");
		names.add("Janet");
		names.add("Emma");
		jsonFun.ValidateHasItems(res, "data.first_name", names);
		jsonFun.ValidateKeyNumber(res, "total_pages", 2);
		jsonFun.ValidateKeyText(res, "data[0].first_name", "George");
		
	
	}
	@Then("^I validate the last name$")
	public void validateLastNameCode()
	{
		System.out.println(jsonFun.returnListOfString(res, "data.avatar"));
		System.out.println(jsonFun.returnInt(res, "data[0].id"));
		System.out.println(jsonFun.returnFloat(res, "data[0].id"));
	
	}
	@Then("^I validate the created time$")
	public void validateCreatedTime()
	{

		System.out.println(dateFun.dateDifference("2020-12-27T17:51:39.718Z", "2020-10-26T17:51:39.718Z"));
		System.out.println(dateFun.isLeapYear("100-12-27T17:51:39.718Z"));
	}
	@Then("I validate the distance from {double} and {double}")
	public void validateDistance(double lat1,double lon1)
	{
		List<String> lat2=jsonFun.returnListOfString(res, "address.geo.lat");
		List<String> lon2=jsonFun.returnListOfString(res, "address.geo.lng");
		for(int i=0;i<lat2.size();i++)
		{
			
		System.out.println(geoFun.ditanceFinder(lat1, lon1, Double.parseDouble(lat2.get(i)), Double.parseDouble(lon2.get(i))));
		}
		jsonFun.ValidateStatuCode(res,200);
		jsonFun.ValidateHeader(req, res);
	
	}
	@Then("I validate the schema")
	public void validateSchema()
	{
		File schema = fileFun.readFile(userSchemaPath);
		res.then().body(matchesJsonSchema(schema));
	}
	
}
