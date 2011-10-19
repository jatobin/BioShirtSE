package com.bioshirt.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyHelper {
	
	private static PropertyHelper propertyHelper;
	private File propertyFile;
	private String propertyFilePath;
	private FileInputStream inputStream;
	private FileOutputStream outputStream;
	private Properties appProperties;
	
	private PropertyHelper() {
		propertyFilePath = "util/bioshirtse.properties";
		appProperties = new Properties();
		try {
			propertyFile = new File(propertyFilePath);
			if (!propertyFile.exists()) {
				propertyFile.createNewFile();
			}
			inputStream = new FileInputStream(propertyFile);
			appProperties.load(inputStream);
			inputStream.close();
		} catch(IOException e) {
			System.out.println("Failed to load properties, no properties are loaded.\nExiting system because database connection cannot be established.");
			e.printStackTrace();
			System.exit(5);
		}
		
		//get the file name 
	}
	
	public static synchronized PropertyHelper getInstance() {
		if (propertyHelper == null) {
			propertyHelper = new PropertyHelper();
			return propertyHelper;
		} else {
			return propertyHelper; 	
		}
	}
	
	public Properties getProperties() {
		return appProperties;
	}
	
	public String getProperty(String property) {
		return appProperties.getProperty(property);
	}
	
	public synchronized void saveProperty(String key, String value) {
		
		if (outputStream == null ) { 
			try {
				outputStream = new FileOutputStream(propertyFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		appProperties.put(key, value);
		try {
			appProperties.store(outputStream, "");
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public synchronized void removeProperty(String key)  {
		
		if (outputStream == null ) { 
			try {
				outputStream = new FileOutputStream(propertyFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		appProperties.remove(key);
		try {
			appProperties.store(outputStream, "");
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	


}
