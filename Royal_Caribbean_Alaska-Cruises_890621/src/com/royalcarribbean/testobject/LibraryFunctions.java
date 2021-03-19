package com.royalcarribbean.testobject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.royalcarribbean.utility.*;

public class LibraryFunctions extends Driver {

	public static ArrayList<String> reportMessages = null;
	public static Properties prop = null;

	// Initializing the Property File
	@BeforeTest
	public void initializeThePropertiesFile() throws IOException {

		prop = PropertieFile.readProperties();
	}

	// Initializing The List to store the validation messages
	@BeforeSuite
	public static void initializeReportMessageList() {
		reportMessages = new ArrayList<String>();
	}

	// Writing the messages to excel sheet
	@AfterSuite
	public static void logReportToExcel() throws IOException {
		ExcelFile.createReport(reportMessages); // Calling the method to create the excel report
	}

	// Returning the Value from the properties file taking key as input
	public static String getValueFromProperties(String key) {
		try {
			return prop.getProperty(key);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	// Returning the locator using the locator key
	public static By getTheLocator(String locatorKey) throws Exception {
		// using if-else to identify if the locator is xpath or linktext or partial link
		// text using the locator key
		if (locatorKey.contains("xpath"))
			return (By.xpath(getValueFromProperties(locatorKey)));
		else if (locatorKey.contains("id"))
			return (By.id(getValueFromProperties(locatorKey)));
		else if (locatorKey.contains("name"))
			return (By.name(getValueFromProperties(locatorKey)));
		else if (locatorKey.contains("linktext"))
			return (By.linkText(getValueFromProperties(locatorKey)));
		else if (locatorKey.contains("partiallinktext"))
			return (By.partialLinkText(getValueFromProperties(locatorKey)));
		else if (locatorKey.contains("classname"))
			return (By.className(getValueFromProperties(locatorKey)));
		else if (locatorKey.contains("tagname"))
			return (By.tagName(getValueFromProperties(locatorKey)));
		else
			return (By.cssSelector(getValueFromProperties(locatorKey)));

	}

	// Returning the Element using the locator Key
	public static WebElement getTheElement(String locatorKey) throws Exception {

		return driver.findElement(getTheLocator(locatorKey));

	}

	// Clicking on an element taking locator key as input
	public static void clickTheElement(String locatorKey) {
		try {
			WebElement element = getTheElement(locatorKey);
			wait.until(ExpectedConditions.elementToBeClickable(getTheLocator(locatorKey))); // Using the explicit wait
																							// to wait till the expected
																							// condition
			element.click(); // Clicking the element
		} catch (NoSuchElementException e) {
			System.out.println("Element cannot be found " + e);
		} catch (StaleElementReferenceException e) {
			System.out.println(
					"Web element is detached from the current DOM or The element has been deleted entirely " + e);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// Sending Keys to an element taking locator key and the String to be sent as
	// input
	public static void sendTheKeys(String locatorKey, String keys) {
		try {
			WebElement element = getTheElement(locatorKey); // Getting the WebElement by sending the locatorKey to a
															// method
			wait.until(ExpectedConditions.visibilityOf(element)); // Using the explicit wait to wait till the expected
																	// condition
			element.sendKeys(getValueFromProperties(keys)); // sending keys to the element
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	// Checking the visibility of an element taking locator key as input
	public static boolean checkVisibilityOfTheElement(String locatorKey) {
		try {
			WebElement element = getTheElement(locatorKey); // Getting the WebElement by sending the locatorKey to a
			// method
			wait.until(ExpectedConditions.visibilityOf(element)); // Using the explicit wait to wait till the expected
																	// condition
			if (element == null)
				return false; // Returning false if element is null
			else
				return true; // Returning true if element id visible
		} catch (NoSuchElementException e) {
			System.out.println("Element cannot be found " + e);
			return false;
		} catch (StaleElementReferenceException e) {
			System.out.println(
					"Web element is detached from the current DOM or The element has been deleted entirely " + e);
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	// Getting the Text of an element taking locator key as input and returning the
	// text
	public static String getTheText(String locatorKey) {
		try {
			WebElement element = getTheElement(locatorKey); // Getting the WebElement by sending the locatorKey to a
															// method
			wait.until(ExpectedConditions.visibilityOf(element)); // Using the explicit wait to wait till the expected
																	// condition
			return element.getText(); // Getting text from the element and returning it
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	// Add messages to the report list
	public static void addMessagesToReportList(String message) throws Exception {
		reportMessages.add(message); // Calling the add method to add the output messages to reportMessage list
	}

	// Check the greater number between the two
	public static int GreaterNumberBetweenTwo(int a, int b) {
		if (a > b)
			return a;
		else
			return b;
	}

	// Selecting an option from a DropDown taking locator key and option to be
	// selected as input
	public static void selectTheOptionFromDropdown(String locatorKey, String textToBeSelected) {
		try {
			WebElement element = getTheElement(locatorKey); // Getting the WebElement by sending the locatorKey to a
															// method
			wait.until(ExpectedConditions.elementToBeClickable(getTheLocator(locatorKey))); // Using the explicit wait
																							// to wait till the expected
																							// condition
			Select select = new Select(element); // creating instance of Select class
			select.selectByVisibleText(getValueFromProperties(textToBeSelected)); // selecting from dropdown using the
																					// selectByVisibleText
																					// method
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// Take Screenshot of the Page
	public static void takeAScreenshot(String fileName) throws Exception {
		TakeScreenshots.getScreenshot(driver, fileName); // Calling the getScreenshot method of TakeScreenshot class
	}

	// Scroll to End Of Page
	public static void ScrollToEndOfPage() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver; // Parsing the driver to JavaScriptExceutor
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)"); // Scrolling tom end of the page
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Highlight the Element before Taking the Screenshot
	public void highlightTheElement(String locatorKey) throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver; // Parsing the driver to JavaScriptExceutor
		jse.executeScript("arguments[0].style.border='5px solid red'", getTheElement(locatorKey)); // Highlighting an
																									// element using its
																									// locator
	}

	// Function to wait for the page to load completely
	public static void waitForPageLoad() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver; // Parsing the driver to JavaScriptExceutor
			int i = 0;
			while (i != 180) {
				String pageState = (String) js.executeScript("return document.readyState;"); // Storing the state of
																								// page in a string
				if (pageState.equalsIgnoreCase("Complete")) { // Checking if the state of page is complete
					break;
				} else {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
