package com.companyName.Testone.CommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.companyName.Testone.StepDef.Screenshots;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePageObject {
	public static WebDriver driver;

	protected void startDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		Screenshots.addStepWithScreenshotInReport(driver, "Driver Launched");
	}

	protected void clickElement(final String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
		Screenshots.addStepWithScreenshotInReport(driver, element.getText() + " Clicked successfully");
	}

	protected void sendInputValue(final String loacator, final String value) {
		WebElement element = driver.findElement(By.xpath(loacator));
		element.sendKeys(value);
		Screenshots.addStepWithScreenshotInReport(driver, element.getText() + " :Set value as " + value);
	}

	protected void clickElement(final String locator, final String description) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
		System.out.println(description);
		Screenshots.addStepWithScreenshotInReport(driver, description);
	}

	protected void switchToRightWindow(final String title) {
		String parent = driver.getWindowHandle();
		Set<String> cW = driver.getWindowHandles();
		List<String> handles = new ArrayList<String>(cW);
		for (String chWin : handles) {
			String windowTitle = driver.switchTo().window(chWin).getTitle();
			if (windowTitle.contains(title)) {
				System.out.println("we are in right window");
				Screenshots.addStepWithScreenshotInReport(driver, "we are in right window");
			}
		}
	}

	protected void closeAllWindowExceptParent(final String Parenttitle) {
		String parent = driver.getWindowHandle();
		Set<String> cW = driver.getWindowHandles();
		List<String> handles = new ArrayList<String>(cW);
		for (String chWin : handles) {
			String windowTitle = driver.switchTo().window(chWin).getTitle();
			if (!(windowTitle.contains(Parenttitle))) {
				driver.close();
			}
		}
		try {
			driver.switchTo().window(parent);
		} catch (NoSuchWindowException e) {
			Set<String> cW1 = driver.getWindowHandles();
			List<String> handles1 = new ArrayList<String>(cW1);
			System.out.println(handles1.size());
			for (String child : handles1) {
				driver.switchTo().window(child);
			}
		}
	}

	protected String getText(final String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		String txt = element.getText();
		Screenshots.addStepWithScreenshotInReport(driver, "Text is: "+txt);
		return txt;
	}

}
