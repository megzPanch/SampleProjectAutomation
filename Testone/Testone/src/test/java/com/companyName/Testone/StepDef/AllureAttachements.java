package com.companyName.Testone.StepDef;

import java.io.ByteArrayInputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Allure;

public class AllureAttachements {

	public static void attachScreenshot(WebDriver driver, String message) {
		Allure.addAttachment(message, new ByteArrayInputStream((byte[])((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
	}

	public static void addStepDescription(String meessage) {
		Allure.description(meessage);
	}

}
