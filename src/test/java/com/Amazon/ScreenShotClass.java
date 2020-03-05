package com.Amazon;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotClass {

	private WebDriver driver;
	
	
	
	
	
	//Constructor
	public ScreenShotClass(WebDriver driver) {
		this.driver = driver;
	}
	
	
	
	//Method to take a screenshot
	public void screencapture(WebDriver driver, String fname) throws IOException {
		  TakesScreenshot ts = (TakesScreenshot) driver;
		  File source = ts.getScreenshotAs(OutputType.FILE);
		  FileUtils.copyFile(source, new File(fname));

		 }


}
