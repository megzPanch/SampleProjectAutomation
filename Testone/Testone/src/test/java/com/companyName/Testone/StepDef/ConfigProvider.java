package com.companyName.Testone.StepDef;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import freemarker.log.Logger;

public class ConfigProvider {
	private static Properties props;
	private static Map<String, Properties> configMap = new HashMap<>();
	private static Logger logger = Logger.getLogger(ConfigProvider.class.getName());

	private static Properties getInstance(String propertyFileName) {
		Map.Entry entry;
		Properties props = null;
		if (configMap.size() == 0) {
			props = loadProperties(propertyFileName);
			configMap.put(propertyFileName, props);
			return props;
		}
		Iterator<Entry<String, Properties>> var2 = configMap.entrySet().iterator();
		do {
			if (!var2.hasNext()) {
				props = loadProperties(propertyFileName);
				configMap.put(propertyFileName, props);
				return props;
			}
			entry = var2.next();
		} while (!((String) entry.getKey()).equals(propertyFileName));
		return (Properties) entry.getValue();
	}

	private static Properties loadProperties() {
		Properties props = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			java.io.InputStream inputStreambdd = loader.getClass().getResourceAsStream("/properties/Config.properties");
			props.load(inputStreambdd);
		} catch (NullPointerException e) {
			System.out.println("Properties file not found... searching again");
			try {
				InputStream inputStreambdd = ConfigProvider.class.getResourceAsStream("/properties/Config.properties");
				props.load(inputStreambdd);
				System.out.println("Properties file found");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			java.io.InputStream in = ConfigProvider.class.getResourceAsStream("/properties");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String resource;
			while ((resource = br.readLine()) != null) {
				System.out.println("Properties file found: " + resource);
				java.io.InputStream is = ConfigProvider.class.getResourceAsStream("/properties/" + resource);
				props.load(is);
			}
			br.close();
		} catch (NullPointerException e) {
			throw new NullPointerException("Properties file not found");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}

	private static Properties loadProperties(String propertyFile) {
		Properties props = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		java.io.InputStream is = ConfigProvider.class
				.getResourceAsStream("/properties/" + propertyFile + ".properties");
		try {
			props.load(is);
		} catch (NullPointerException e) {
			throw new NullPointerException("" + propertyFile + " Not found");
		} catch (IOException e) {
			logger.warn("Not able to load property");
		}
		return props;
	}

	public static String getAsString(String key) {
		return getInstance().getProperty(key);
	}

	public static int getAsInt(String key) {
		return Integer.parseInt(getInstance().getProperty(key));
	}

	public static String getAsString(String fileName, String key) {
		return getInstance(fileName).getProperty(key);
	}

	public static int getAsInt(String fileName, String key) {
		return Integer.parseInt(getInstance(fileName).getProperty(key));
	}

	private static Properties getInstance() {
		if (props == null) {
			props = loadProperties();
			return props;
		}
		return props;
	}

}
