package com.companyName.Testone.StepDef;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadValueFromProperties {

	public static void main(String[] args) throws IOException {
		File f = new File("C:/Users/panch/workspace/Testone/src/test/resources/Properties/Config.properties");
		FileInputStream fis = new FileInputStream(f);
		Properties prop = new Properties();
		prop.load(fis);
		String url = prop.getProperty("QAUrl");
		System.out.println(url);

	}

}
