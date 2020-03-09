package com.Amazon;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ScreenShotClass {

	
	@SuppressWarnings("unused")
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
	
	//Method to take a full page screenshot
		public void fullPgScreenCapture(WebDriver driver, String fname) throws IOException {
			  	  
			  Screenshot  ScrnSht = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
			  ImageIO.write(ScrnSht.getImage(), "jpg", new File(fname));
			  
			  
			 }


}
