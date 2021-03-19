package com.royalcarribbean.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertieFile {

	// Using the properties file to store the key value pairs
	public static Properties readProperties() {
		Properties prop = null; // Creating new properties file
		try {
			prop = new Properties();
			InputStream ReadFile = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\com\\royalcarribbean\\testobject\\Config.properties"); // Reading
																													// the
																													// file
																													// config
																													// properties
			prop.load(ReadFile); // Loading the file to the properties

			
		} catch (Exception e) {
			
		}
		return prop;
	}
}
