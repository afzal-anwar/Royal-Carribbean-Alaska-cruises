package com.royalcarribbean.utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreenshots {

	// Taking the ScreenShot
	public static void getScreenshot(WebDriver driver, String fileName) throws IOException {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); // Taking the screenshot and saving it
																				// to a file

		try

		{ // Copy the screen shot to desired location using Copy File Method

			FileUtils.copyFile(src, new File((System.getProperty("user.dir")) + "\\Images\\" + fileName + "["
					+ System.currentTimeMillis() + "]" + ".png"));
		} catch (IOException e)

		{
			System.out.println("Capture Failed " + e.getMessage());
			System.out.println(e.getMessage());

		}
	}
}
