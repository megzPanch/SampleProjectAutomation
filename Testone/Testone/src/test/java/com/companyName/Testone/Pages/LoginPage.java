package com.companyName.Testone.Pages;

import org.openqa.selenium.UnhandledAlertException;

import com.companyName.Testone.CommonUtils.BasePageObject;
import com.companyName.Testone.StepDef.ConfigProvider;

public class LoginPage extends BasePageObject{
	private String newbrowserBtn = "//button[text()='New Browser Window']";
	private String newBrowserTab = "//button[text()='New Browser Tab']";
	private String sampleTxt = "//strong[contains(text(),'Agile')]";
	private String alertBtn = "//button[@id='alert']";
	private String nameof = "//input[@name='name']";
	
	public void openNewBrowser(){
		clickElement(newbrowserBtn,"New Browser Window clicked successfully");
		switchToRightWindow("Cucumber");
		driver.manage().window().maximize();
		System.out.println(getText(sampleTxt));
		closeAllWindowExceptParent("Practiceform");
	}
	public void openNewBrowserTab(){
		clickElement(newBrowserTab,"New Browser Tab clicked successfully");
		switchToRightWindow("Ruby");
		System.out.println(getText(sampleTxt));
		closeAllWindowExceptParent("Practiceform");
	}
	public void clickonAlert(){
		try {
			clickElement(alertBtn);
			System.out.println(driver.switchTo().alert().getText());
			driver.switchTo().alert().accept();
		} catch (UnhandledAlertException e) {
			driver.switchTo().alert().getText();
		}
	}
	public void fillForm(String name,String email, String phone, String country, String company, String mess){
		sendInputValue(nameof,name);
	}
	
}
