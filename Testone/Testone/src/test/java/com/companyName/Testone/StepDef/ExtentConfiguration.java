package com.companyName.Testone.StepDef;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import org.apache.poi.ss.formula.ExternSheetReferenceToken;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.observer.ExtentObserver;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentConfiguration {
	private static ExtentReports extent;
	public static final String WORKING_DIR = System.getProperty("user.dir");
	private static final String TIME_STAMP = (new SimpleDateFormat("dd.MM.yyyy.HH.mm")).format(new Date());
	private static final String EXTENT_REPORTS_FOLDER = WORKING_DIR + "/AutomationReports";
	private static final String REPORT_NAME = "ExtentReport_" + TIME_STAMP + "_" + Thread.currentThread().getId()
			+ ".html";
	private static final String EXTENT_REPORTS_PATH =EXTENT_REPORTS_FOLDER + File.separator;
	private static Logger logger = Logger.getLogger(ExtentConfiguration.class.getName());

	public static String getExtentReportsFolder() {
		return EXTENT_REPORTS_PATH;
	}

	public static ExtentReports getInstance() {
		if (extent == null) {
			createReportFolder();
			attachReporters();
		}
		return extent;

	}

	private static ExtentReports attachReporters() {
		String ExtentReportRequired = null;
		extent = new ExtentReports();
		try {
			ExtentReportRequired = System.getProperty("ExtentReport", ConfigProvider.getAsString("ExtentReport"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ExtentReportRequired != null
				&& (ExtentReportRequired.equalsIgnoreCase("true") || ExtentReportRequired.equalsIgnoreCase("yes"))) {
			extent.attachReporter(new ExtentObserver[] { (ExtentObserver) initHtmlReporter() });
		} else if (ExtentReportRequired == null || ExtentReportRequired == "") {
			extent.attachReporter(new ExtentObserver[] { (ExtentObserver) initHtmlReporter() });
		}
		return extent;
	}

	public static void createReportFolder() {
		File file = new File(EXTENT_REPORTS_FOLDER);
		if (!file.exists() && !file.mkdir()) {
			logger.warning("Failed to create directory!");
		}
	}

	public static ExtentSparkReporter initHtmlReporter() {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(EXTENT_REPORTS_PATH);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle(REPORT_NAME);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Execution-Status");
		htmlReporter.config().setCss("css-string");
		htmlReporter.config().setJs("js-string");
		htmlReporter.config().setProtocol(Protocol.HTTPS);
		htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		htmlReporter.config().setTimelineEnabled(true);
		return htmlReporter;

	}
}
