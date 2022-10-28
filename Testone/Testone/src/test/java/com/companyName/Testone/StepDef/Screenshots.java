package com.companyName.Testone.StepDef;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class Screenshots {
	private static final Logger logger = Logger.getLogger(Screenshots.class);
	private static String screenshotsFolderPath;
	private static final String SCREENSHOTS_FOLDER = "\\screenshots\\";

	static {
		createDirectory();
	}

	protected static String captureScreenshot(WebDriver driver, String screenshotName) {
		String randomNumber = RandomStringUtils.randomNumeric(5);
		String destinationPath = screenshotsFolderPath + screenshotName + " .png";
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = (File) ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(destinationPath));
		} catch (IOException e) {
			logger.warn("Not able to capture screenshot");
		}
		return destinationPath.substring(destinationPath.indexOf("/") + 1);
	}

	public static String getScreenshotsFolderPath() {
		return screenshotsFolderPath;

	}

	public static void addStepWithScreenshotInReport(WebDriver driver, String message) {
		ExtentTest extentTest = ExtentTestManager.getTest();
		if (extentTest != null) {
			if (driver != null) {
				String randomNumber = RandomStringUtils.randomNumeric(5);
				String path = captureScreenshot(driver, "screenshot"+randomNumber+"");
				try {
					extentTest.pass(message, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
					AllureAttachements.attachScreenshot(driver, message);
				} catch (Exception e) {
					logger.warn(message);
				}
			} else {
				extentTest.pass(message);
			}
		}
	}

	public void addStepInReport(String message) {
		ExtentTest extentTest = ExtentTestManager.getTest();
		if (extentTest != null) {
			extentTest.pass(message);
		}
	}

	public void addStepInReport(String message, boolean condition) {
		ExtentTest extentTest = ExtentTestManager.getTest();
		if (extentTest != null) {
			if (condition) {
				extentTest.pass(message);
			} else {
				extentTest.fail(message);
			}

		}
	}

	protected static String captureDesktop(String screenShotName) throws AWTException {
		String randomNumber = RandomStringUtils.randomNumeric(5);
		String destinationPath = screenshotsFolderPath + screenShotName + randomNumber + ".png";
		Robot r = new Robot();
		Rectangle capture = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		BufferedImage image = r.createScreenCapture(capture);
		try {
			ImageIO.write(image, "png", new File(destinationPath));
		} catch (Exception e) {
			logger.warn("Not able to capture screenshots");
		}
		return destinationPath;
	}

	public static void addStepWithDesktopScreenInReport(String message) throws Exception {
		String path = captureDesktop("screenshot");
		try {
			ExtentTestManager.getTest().pass(message, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createDirectory() {
		String drive = getDriveWithFreeSpace();
		if (drive != null && drive.contains("C:")) {
			screenshotsFolderPath = FileUtils.getTempDirectoryPath() + "\\screenshots\\";
		} else {
			screenshotsFolderPath = drive + "\\screenshots\\";
		}
		screenshotsFolderPath = "AutomationReports/screenshots/";
		File file = new File(screenshotsFolderPath);
		if (!file.exists() && !file.mkdir())
			logger.warn("Failed to create directory!");
	}

	private static String getDriveWithFreeSpace() {
		String driveWithFreeSpace = null;
		File[] availableDrives = File.listRoots();
		if (availableDrives.length > 1)
			for (File file : availableDrives) {
				if (file.getFreeSpace() > 100000000L) {
					driveWithFreeSpace = file.toString();
					break;
				}
			}
		return driveWithFreeSpace;

	}

}
