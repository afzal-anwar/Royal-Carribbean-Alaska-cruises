package com.royalcarribbean.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class Driver {

	public static WebDriver driver = null;
	public static WebDriverWait wait = null;

	// Loading the driver and Loading the URL in the driver
	@BeforeTest
	@Parameters({ "browser", "baseUrl" })
	public void setupDriver(String browser, String baseUrl) throws Exception {
		String path = System.getProperty("user.dir");
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", path + "\\Resources\\Drivers\\chromedriver.exe"); // Setting
																											// the path
																											// for the
																											// browser
			driver = new ChromeDriver(); // Opening the Chrome Browser

		} else if (browser.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver", path + "\\Resources\\Drivers\\geckodriver.exe"); // Setting the
																											// path for
																											// the
																											// browser
			driver = new FirefoxDriver(); // Opening the Firefox Browser

		} else {
			System.out.println("Wrong Browser");
			return;
		}

		driver.manage().window().maximize(); // Maximizing the browser window
		driver.get(baseUrl); // Loading the URL to the browser
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); // Adding an implicit wait to the driver
																			// instance
		wait = new WebDriverWait(driver, 60); // Initializing an Explicit wait
	}

	// Closing the Browser
	@AfterTest
	public void killTheBrowser() throws Exception {
		driver.quit(); // quiting the browser
	}
}
