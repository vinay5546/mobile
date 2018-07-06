package com.ebay.factory;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyManager {
	
static	Logger logger = Logger.getLogger(PropertyManager.class);
	
	private static Properties props = null;
	
	public static String getProperty(String key) {

		props = new Properties();
		InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("config.properties");

		try {
			props.load(is);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		 String value = props.getProperty(key);
		 logger.info("Key = "+key+"  Value = "+value);
		return value;
	}
	
}
