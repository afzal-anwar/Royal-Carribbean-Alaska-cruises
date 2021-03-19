package com.royalcarribbean.testcases;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.royalcarribbean.testobject.LibraryFunctions;

public class TestCase extends LibraryFunctions {

	// Scrolling to End of The Page
	@Test(priority = 1)
	public void scroll() {
		waitForPageLoad(); // Calling the waitFor Load method to wait for the page to load
		handleUncertainPopUp(); // Calling the method to handle the uncertain pop-up
		ScrollToEndOfPage(); // Scrolling to end of the page
	}

	// Handling the unsure pop up in the page
	public void handleUncertainPopUp() {
		waitForPageLoad(); // Calling the waitFor Load method to wait for the page to load
		boolean check = checkVisibilityOfTheElement("popup_xpath"); // Storing the status after checking for visibility
																	// of element by calling the
																	// checkVisibilityOfElement method
		if (check)
			clickTheElement("popup_xpath"); // Clicking the element if status of visibility of element is true
		else
			System.out.println("Alert did not pop up"); // Writing to console if the popup element did not popup
	}

	// Checking if HolidayCruise Element IS Present in The WebPage
	@Test(dependsOnMethods = { "scroll" })
	public void isHolidayCruiseElementPresent() throws Exception {
		boolean check = checkVisibilityOfTheElement("holidayCruiseElement_xpath"); // Storing the status after checking
																					// for visibility
		if (check) {
			System.out.println("Pass" + " \nHolidayCruise Element is Present"); // printing the pass message in console
																				// if the element is present
			addMessagesToReportList("Pass" + ":HolidayCruise Element is Present"); // Adding the pass message to the
																					// report List
			highlightTheElement("holidayCruiseElement_xpath"); // Highlighting the element using its xpath
			takeAScreenshot("Holiday Cruise Element - marked"); // Taking the screenshot of the page
		} else {
			System.out.println("Fail" + " \nHolidayCruise Element is Not Present"); // printing the fail message in
																					// console if the element is present
			addMessagesToReportList("Fail" + ":nHolidayCruise Element is Not Present"); // Adding the fail message to
																						// the report List
			highlightTheElement("holidayCruiseElement_xpath"); // Highlighting the element using its xpath
			takeAScreenshot("Holiday Cruise Element - issue found"); // Taking the screenshot of the page
			Assert.assertEquals(check, true, "Fail - HolidayCruise Element is Not Present"); // Validating the presence
																								// of the element
		}
	}

	// Clicking on Holiday Cruise
	@Test(dependsOnMethods = { "isHolidayCruiseElementPresent" })
	public void clickOnHolidayCruise() {
		clickTheElement("holidayCruiseElement_xpath"); // Clicking the element by calling the clickTheElement method of
														// LibraryFunctions class and passing the xpath loactorKey as
														// parameter
	}

	// Checking if search option is present in the WebPage
	@Test(dependsOnMethods = { "clickOnHolidayCruise" })
	public void isSearchButtonPresentandclickOnIt() throws Exception {
		waitForPageLoad(); // Waiting for the page to load
		boolean check = checkVisibilityOfTheElement("searchOption_id"); // Storing the status after checking the
																		// visibility of the element
		if (check) {
			highlightTheElement("searchOption_id"); // Highlighting the element using its Id
			takeAScreenshot("Search Button - marked"); // Taking a screenshot of the page
			clickTheElement("searchOption_id"); // Clicking the element
		} else {
			highlightTheElement("searchOption_id"); // Highlighting the element using its Id
			takeAScreenshot("Search Button -issue found"); // Taking a screenshot of the page
			Assert.assertFalse(false, "Search Button Not Present"); // Validating the presence of the element
		}

	}

	// Searching for "Rhapsody of the Seas"
	@Test(dependsOnMethods = { "isSearchButtonPresentandclickOnIt" })
	public void DoTheSearch() {
		sendTheKeys("searchTextBox_id", "SearchTextBoxText"); // Sending the required keys to the textBox by calling the
																// method sendTheKeys of the Library Functions
		clickTheElement("searchButton_id"); // Clicking the element by calling the method clickThElement of the
											// libraryFunctions class and passing the locatorKey as parameter
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS); // Waiting for the page to load

	}

	// Getting the number of search results displayed and Comparing it with 300000
	@Test(dependsOnMethods = { "DoTheSearch" })
	public void compareResults() throws Exception {
		waitForPageLoad(); // Waiting for the page to load completely
		highlightTheElement("searchResultsToBeCompared_xpath"); // Highlighting the element
		takeAScreenshot("Number of Results - marked"); // taking the screenshot of the page
		String results = getTheText("searchResultsToBeCompared_xpath"); // Storing the Number of results from the page
																		// in the variable results
		String[] noOfResults = results.split(" "); // Splitting the string to extract the number
		String[] result = noOfResults[1].split(",");
		int finalResult = Integer.parseInt(result[0] + result[1]); // Parsing the string (noOfResults) to integer
		int resultToBeComparedWith = (Integer.parseInt(getValueFromProperties("NumberToBeComparedWithResults"))); // Comparing
																													// the
																													// results
																													// with
																													// user
																													// given
																													// value
		if (GreaterNumberBetweenTwo(finalResult, resultToBeComparedWith) == finalResult) {
			System.out.println("Pass" + " \nResults are greater than 300000"); // Printing pass message in console if
																				// the results are greater then the user
																				// given results
			addMessagesToReportList("Pass" + ":Results are greater than 300000"); // Adding the pass message to the
																					// Reportlist(to be added in the
																					// excel)
		} else {
			System.out.println("Fail" + " \nResults are not greater than 300000"); // Printing fail message in console
																					// if the results are greater then
																					// the user given results
			addMessagesToReportList("Fail" + ":Results are not greater than 300000"); // Adding the pass message to the
																						// Reportlist(to be added in the
																						// excel)
			Assert.assertFalse(false, "Results are not greater than 300000"); // validating the test case based on the
																				// comparison of results
		}
	}

	// Finding and clicking on "Deck Plans | Rhapsody of the Seas | Royal Caribbean
	// Cruises" in the search result"
	@Test(priority = 2, dependsOnMethods = { "DoTheSearch" })
	public void findAndclickOnResult() {
		clickTheElement("DeckLink_linktext"); // Clicking the element
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS); // Waiting for the page to load
	}

	// Checking for the Deck plan image
	@Test(dependsOnMethods = { "findAndclickOnResult" })
	public void isImagePresent() throws Exception {
		waitForPageLoad(); // calling the waitForPageLoad method to wait till the page loads completely
		boolean check = checkVisibilityOfTheElement("Image_xpath"); // storing the status of visibility of an element in
																	// check variable
		if (check) {
			System.out.println("Pass" + "\nDeck plan image is present"); // printing the pass message
			addMessagesToReportList("Pass" + ":Deck plan image is present"); // adding the pass message to the
																				// reportlist
			highlightTheElement("Image_xpath"); // Highlighting the element
			takeAScreenshot("Deck Plan Image -marked"); // taking the screenshot of the page
		} else {
			System.out.println("Fail" + "\nDeck plan image is not present"); // printing the fail message
			addMessagesToReportList("Fail" + ":Deck plan image is not present"); // adding the fail message to the
																					// reportlist
			highlightTheElement("Image_xpath"); // Highlighting the element
			takeAScreenshot("Deck Plan Image - issue found"); // taking the screenshot of the page
			Assert.assertFalse(false, "Deck plan image is not present"); // validating the presence of the image
		}
	}

	// Selecting View as Deck Eight
	@Test(dependsOnMethods = { "isImagePresent" })
	public void selectView() {
		selectTheOptionFromDropdown("selectViewDeckDropDown_id", "ViewToBeSelecetedFromDropdown"); // selecting an
																									// option froma a
																									// dropdown by
																									// calling the
																									// selectTheOptionFromDropDown
																									// method from
																									// Library Functions
																									// class

	}

	// Checking the presence of "Royal Suite"
	@Test(dependsOnMethods = { "selectView" })
	public void isRoyalSuitePresent() throws Exception {
		boolean check = checkVisibilityOfTheElement("RoyalSuite_xpath"); // Storing the status of visibility of element
																			// in check variable

		if (check) {
			System.out.println("Pass" + "\nRoyal Suite is Present"); // printing the pass message in the console
			addMessagesToReportList("Pass" + ":Royal Suite is Present"); // adding the pass message to the reportLIst
		} else {
			System.out.println("Fail" + "\nRoyal Suite is Not Present"); // printing the fail message to the console
			addMessagesToReportList("Fail" + ":Royal Suite is Not Present"); // adding the fail message to the
																				// reportlist
			Assert.assertEquals(check, true, "Fail - Royal Suite is Not Present"); // validating the presence of the
																					// element using the assertEquals
																					// method
		}
	}
}
