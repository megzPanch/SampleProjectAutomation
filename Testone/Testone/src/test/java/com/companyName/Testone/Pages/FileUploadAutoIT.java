package com.companyName.Testone.Pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FileUploadAutoIT {
public static WebDriver driver;
	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://pdf2jpg.net/");
		Thread.sleep(1000);
		driver.findElement(By.id("advanced_pdf_file")).click();
		Thread.sleep(10000);
		Runtime.getRuntime().exec("C:/Users/panch/Downloads/fileUpload.exe");//AutoIT
	}

}
