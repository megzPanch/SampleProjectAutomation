package com.companyName.Testone.StepDef;

import java.util.HashMap;
import java.util.List;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

public class DefaultStepDefinition {

	public static HashMap<String, String> currentIterationMap;
	private HashMap<String, HashMap<String, String>> excelData = new HashMap<String, HashMap<String, String>>();
	private static String rowName;

	@Before
	public void readScenarioName(Scenario scenario) {
	rowName = scenario.getName();
	}
	@Given("^A workbook named \"([^\"]*)\" and sheet named \"([^\"]*)\" is read$")
	public void a_workbook_named_and_sheet_named_is_read (String excelName, String sheetName) {
	List<HashMap<String, String>> data = ReadExcel.readData(excelName, sheetName);
	for (HashMap<String, String> map:data) {
	excelData.put(map.get("TestDataID").trim(), map);
	}
	currentIterationMap = excelData.get(rowName);
}
}
