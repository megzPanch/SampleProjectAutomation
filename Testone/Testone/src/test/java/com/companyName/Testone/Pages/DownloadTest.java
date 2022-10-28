package com.companyName.Testone.Pages;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DownloadTest {

	public static WebDriver driver;
	public static void main(String[] args) throws InterruptedException, IOException {
		String downloadPath = System.getProperty("user.dir");
		WebDriverManager.chromedriver().setup();
		
		HashMap<String, Object> chrompref = new HashMap<String, Object>();
		
		chrompref.put("profile.default_content_settings.popups", 0);
		chrompref.put("download.default_directory", downloadPath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chrompref);
		
		driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/download");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='download/sample.pdf']")).click();
		Thread.sleep(10000);
		File f = new File(downloadPath);
		if(f.exists()){
			System.out.println("File found");
			
		}
	}
}
