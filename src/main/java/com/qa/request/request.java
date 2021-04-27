package com.qa.request;

import java.util.Properties;

import com.qa.utility.setUp;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class request 
{
	Properties prop=setUp.envSetUp();
	public  String baseUri=prop.getProperty("petStoreUri");
	RequestSpecification req=RestAssured.given().baseUri(baseUri);
	
	
	public RequestSpecification getReq() {
		return req;
	}
	public void setReq(RequestSpecification req) {
		this.req = req;
	}
	public Response sendPostRequest(Headers headers,Object obj,String path)
	{
		
		req.headers(headers);
		req.body(obj);
		Response res=req.post(path);
		return res;
	}
	public Response sendGetRequest(Headers headers,String path)
	{
		req.headers(headers);
		Response res=req.get(path);
		return res;
	}
	public Response sendPutRequest(Headers headers,Object obj,String path)
	{
		req.headers(headers);
		Response res=req.put(path);
		return res;
	}
	public Response sendDeleteRequest(Headers headers,Object obj,String path)
	{
		req.headers(headers);
		Response res=req.delete(path);
		return res;
	}
}
