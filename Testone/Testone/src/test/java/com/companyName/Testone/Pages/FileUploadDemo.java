package com.companyName.Testone.Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FileUploadDemo {

	public static void main(String[] args) throws InterruptedException, AWTException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://pdf2jpg.net/");
		Thread.sleep(1000);
		driver.findElement(By.id("advanced_pdf_file")).click();
		//Actions act = new Actions(driver);
		Thread.sleep(10000);
		upLoadFile("C:\\Users\\panch\\Downloads\\aggre.pdf");
		//act.sendKeys("C:\\Users\\panch\\Downloads\\aggre.pdf").build().perform();
		
	}
	public static void setClipBoard(String file){
		StringSelection obj = new StringSelection(file);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(obj, null);
	}
	public static void upLoadFile(String filePath) throws AWTException{
		setClipBoard(filePath);
		Robot rb = new Robot();
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		
	}
	
	
	

}
