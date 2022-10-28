package com.companyName.Testone.StepDef;

import com.companyName.Testone.Pages.LoginPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class LoginStepDef extends LoginPage {

	@Given("^user launch the application (.*)$")
	public void user_launch_the_application(String url) {
		startDriver();
		driver.get(DefaultStepDefinition.currentIterationMap.get(url));
	}

	@And("user validate new browser window button")
	public void validate_new_browser_window_button() {
		openNewBrowser();
	}

	@Then("user validate new browser tab button")
	public void user_validate_new_browser_tab_button() {
		openNewBrowserTab();
	}
	@Then("user validate alert")
	public void user_validate_alert(){
		clickonAlert();
	}
	@Then("^user validate feedback form with (.*) (.*) (.*) (.*) (.*) (.*)$")
	public void user_validate_feedback_form(String name,String email, String phone, String country, String company, String mess){
		fillForm(name, email, phone, country, company, mess);
	}

}
