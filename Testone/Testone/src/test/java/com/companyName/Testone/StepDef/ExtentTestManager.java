package com.companyName.Testone.StepDef;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
	private static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
	private static Set<Integer> extentThreadList = new HashSet<>();
	private static ExtentReports extent;

	public static synchronized ExtentTest getTest() {
		return extentTestMap.get(Integer.valueOf(getCurrentThread()));
	}

	private static synchronized int getCurrentThread() {
		return (int) Thread.currentThread().getId();
	}

	public static synchronized void endTest() {
		System.out.println("end test start: " + Thread.currentThread().getId());
		extentThreadList.remove(Integer.valueOf(getCurrentThread()));
		if (!extentTestMap.isEmpty() && extentThreadList.isEmpty()) {
			Set<Integer> s1 = new HashSet<>();
			s1 = extentTestMap.keySet();
			for (Integer i : s1) {
				extent.removeTest(extentTestMap.get(i));
				System.out.println(extentTestMap);
			}
		}

	}

	static synchronized ExtentTest startTest(String testName, String desc) {
		System.out.println("start test " + Thread.currentThread().getId());
		extent=ExtentConfiguration.getInstance();
		ExtentTest test =extent.createTest(testName, desc);
		extentTestMap.put(Integer.valueOf(getCurrentThread()), test);
		extentThreadList.add(Integer.valueOf(getCurrentThread()));
		return test;

	}

}
