package com.companyName.Testone.StepDef;

import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {
	private static Logger logger = Logger.getLogger(TestListener.class.getName());
	private Map<String, String> allParameters = new HashMap<>();
	private Map<String, String> suiteParameters = new HashMap<>();
	private Map<String, String> localParameters = new HashMap<>();
	private List<String> fileList = new ArrayList<>();

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	@Override
	public void onFinish(ITestContext context) {
		ExtentConfiguration.getInstance().flush();
		ExtentTestManager.endTest();
		compressDirectory("AutomationReports", "AutomationReports.zip");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		logger.info("");
	}

	private void compressDirectory(String dir, String zipFile) {
		File directory = new File(dir);
		getFileList(directory);
		try {
			FileOutputStream fos = new FileOutputStream(zipFile);
			try {
				ZipOutputStream zos = new ZipOutputStream(fos);
				try {
					for (String filePath : this.fileList) {
						System.out.println("compressing: " + filePath);
						String name = filePath.substring(directory.getAbsolutePath().length() + 1, filePath.length());
						ZipEntry zipEntry = new ZipEntry(name);
						zos.putNextEntry(zipEntry);
						try {
							FileInputStream fis = new FileInputStream(filePath);
							try {
								byte[] buffer = new byte[1024];
								int length;
								while ((length = fis.read(buffer)) > 0)
									zos.write(buffer, 0, length);
								zos.closeEntry();
								fis.close();
							} catch (Throwable throwable) {
								try {
									fis.close();
								} catch (Throwable throwable1) {
									throwable.addSuppressed(throwable1);
								}
								throw throwable;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					zos.close();
				} catch (Throwable throwable) {
					try {
						fos.close();
					} catch (Throwable throwable1) {
						throwable.addSuppressed(throwable1);
					}
					throw throwable;
				}
				fos.close();
			} catch (Throwable throwable) {
				try {
					fos.close();
				} catch (Throwable throwable1) {
					throwable.addSuppressed(throwable1);
				}
				throw throwable;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getFileList(File directory) {
		File[] files = directory.listFiles();
		if (files != null && files.length > 0)
			for (File file : files) {
				if (file.isFile()) {
					this.fileList.add(file.getAbsolutePath());
				} else {
					getFileList(file);
				}
			}
	}

	@Override
	public void onStart(ITestContext context) {
		this.allParameters = context.getSuite().getXmlSuite().getAllParameters();
		this.suiteParameters = context.getSuite().getXmlSuite().getAllParameters();
		this.localParameters = context.getSuite().getXmlSuite().getAllParameters();
	}

	public Map<String, String> getAllParameters() {
		return this.allParameters;
	}

	public Map<String, String> getSuiteParameters() {
		return this.suiteParameters;
	}

	public Map<String, String> getLocalParameters() {
		return this.localParameters;
	}

	@Override
	public void onTestFailure(ITestResult result) {
		logger.warning(getTestMethodName(result) + " failed!");
		if (ExtentTestManager.getTest() != null)
			if (result.getThrowable().toString().contains("java.lang.AssertionError")) {
				String errMsg = result.getThrowable().getMessage();
				try {
					ExtentTestManager.getTest().log(Status.FAIL,
							"Test step failed due to following err : "
									+ errMsg.substring(0, errMsg.indexOf("expected") - 1).trim(),
							MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot()).build());
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Test step failed: " + result.getThrowable());
			}
	}

	protected static String takeScreenShot() {
		String timeStamp = (new SimpleDateFormat("yyyyMMdd_HHmmss")).format(Calendar.getInstance().getTime());
		String imageName = "c:\\temp\\" + timeStamp + ".png";
		BufferedImage image = null;
		try {
			image = (new Robot()).createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

		} catch (HeadlessException | java.awt.AWTException e) {
			e.printStackTrace();
		}
		try {
			ImageIO.write(image, "png", new File(imageName));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageName;

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		if (ExtentTestManager.getTest() != null)
			ExtentTestManager.getTest().log(Status.SKIP, result.getName() + " execution got skipped");
	}

	public void onTestStart(ITestResult result) {
		ExtentTestManager.startTest(result.getParameters()[0].toString().replaceAll("\"", ""),
				result.getParameters()[1].toString().replaceAll("\"", ""));
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info(result.getName() + " passed successfully!!");
	}

}
