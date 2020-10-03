package com.qa.runner;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.utility.setUp;

import io.cucumber.testng.CucumberFeatureWrapper;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.PickleEventWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

@CucumberOptions( features = "src/main/resources/com/features",monochrome = true,

glue = { "com.qa.stepDefinitions" },

tags = { "@sanity" },
plugin = {

		"pretty",
		})
public class testRunner
{
	public TestNGCucumberRunner testNGCucumberRunner;
	setUp set=new setUp();
	@BeforeSuite
	public void before()
	{
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        set.envSetUp();
	}
	@Test(dataProvider = "scenarios")
	public void scenario(PickleEventWrapper pickle,CucumberFeatureWrapper feature) throws Throwable 
	{
		testNGCucumberRunner.runScenario(pickle.getPickleEvent());
	}
	
	@DataProvider()
	public Object[][] scenarios() 
	{
		return testNGCucumberRunner.provideScenarios();
	}
	@AfterSuite
	public void after()
	{
		testNGCucumberRunner.finish();
	}
}
