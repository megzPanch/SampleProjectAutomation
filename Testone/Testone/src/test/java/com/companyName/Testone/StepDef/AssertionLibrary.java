package com.companyName.Testone.StepDef;

import org.openqa.selenium.WebDriver;

public class AssertionLibrary {
	private static WebDriver driver;
	private static final String ACTUAL = "<br> Actual: ";
	private static final String EXPECTED = "<br> Expected: ";

	public AssertionLibrary(WebDriver driver) {
		AssertionLibrary.driver = driver;
	}

	public enum Screenshot {
		REQUIRED, NOT_REQUIRED;
	}

	public static void assertEquals(String actual, String expected, String message, Screenshots screenshot) {
		assertEquals(actual, expected, message, screenshot);
	}

	public static void assertEquals(String actual, String expected, String message) {
		assertEquals(actual, expected, message);
	}
}
