package com.companyName.Testone.testrunner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.companyName.Testone.StepDef.TestListener;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(features = "src/test/resources/features", glue = {
		"com/companyName/Testone/StepDef" }, plugin = { "pretty", "html:target/site/cucumber-pretty",
				"json:target/cucumber.json" }, tags = { "@Test" }, monochrome = true)
@Listeners(TestListener.class)
public class TestRunner {
	private TestNGCucumberRunner testNGCucumberRunner;

	@BeforeClass
	public void setUPClass() {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@Test(groups ="cucumber", description = "Runs Cucumber Scenarios", dataProvider="scenarios")
	public void runScenario(PickleEventWrapper pickleWrapper, CucumberFeatureWrapper featureWrapper) throws Throwable{
		testNGCucumberRunner.runScenario(pickleWrapper.getPickleEvent());
	}
	@DataProvider
	public Object[][] scenarios(){
		if(testNGCucumberRunner == null){
		return new Object[0][0];
		}
		return testNGCucumberRunner.provideScenarios();
	}
	@AfterClass(alwaysRun=true)
	public void tearDown(){
		testNGCucumberRunner.finish();
	}
}
