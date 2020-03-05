package com.Amazon;

import java.io.File;
import java.io.IOException;

import org.codehaus.plexus.util.ExceptionUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BrowserFunction extends TestInMethod {

	public static WebDriver driver;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	TestInMethod ts = new TestInMethod();
	ImageClass Im = new ImageClass();
	ScreenShotClass SSh = new ScreenShotClass(driver);
	
	// path of TestScript excel file
	static String fileName = "C:\\Users\\P10444427\\Desktop\\Trial Frame Project\\TestWorksheet.xlsx";
	static int i;

	@BeforeSuite
	public void initialioseBrowser() {

		htmlReporter = new ExtentHtmlReporter("./Reports/One_Extent.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		System.out.println("Extent Start");

		extent.setSystemInfo("Windows", "Mac Sierra");
		extent.setSystemInfo("Host Name", "Test");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", "Gaurav Garje");

		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Extent report");
		htmlReporter.config().setReportName("Final Report");
		System.out.println("Extent end");

		System.setProperty("webdriver.chrome.driver", "C:\\Training\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@BeforeClass
	public void beforeclass() {
		System.out.println(this.getClass().getSimpleName());
		test = extent.createTest(this.getClass().getSimpleName());

		// For Step and Descrip
		// making iterator i=0 before every TestNG class
		i = 0;
		
		
		String cellContent = this.getClass().getSimpleName();
		String cellContent1 = cellContent.replace("TC", "");
		String cellContent2 = cellContent1.replace("o", ".");
		System.out.println(cellContent2);
		
		//Creating folder for Screenshots
		String NewFilename = cellContent2;
		String path = "C:\\Users\\P10444427\\Desktop\\Projects from GIT\\TrialFrame-master\\Screenshot\\" + cellContent2;
		//Creating a File object
	    File file = new File(path);
	    //Creating the directory
	    boolean bool = file.mkdir();
	    
	    //Creating folder for Word Document
	    //String Wordfilename = cellContent2;
		String Wordpath = "C:\\Users\\P10444427\\Desktop\\Projects from GIT\\TrialFrame-master\\WordDocs\\" + cellContent2;
		//Creating a File object
	    File wordfile = new File(Wordpath);
	    //Creating the directory
	    boolean wordbool = wordfile.mkdir();
		
	}

	@AfterMethod
	public void getResult(ITestResult result) throws IOException {
		
		
		
		// Start of Step and Descrip
		// Gets the current TestNG class Name
		String cellContent = this.getClass().getSimpleName();
		String cellContent1 = cellContent.replace("TC", "");
		String cellContent2 = cellContent1.replace("o", ".");
		System.out.println(cellContent2);
		
		System.out.println(cellContent2);
		// Searches for the Class name in Excel and return step name
		String StepName = ts.GetStepName(fileName, cellContent2, i, 7);
		// Searches for the Class name in Excel and returns description of step
		String StepDescrip = ts.GetStepName(fileName, cellContent2, i, 8);
		// Here we merge the step and description to be put in word
		String merged = StepName + ": " + StepDescrip;
		System.out.println(merged);
		i++;

		System.out.println("In After method");
		String MethodName = result.getMethod().getConstructorOrMethod().getMethod().getName();
		System.out.println(MethodName);
		
		
		//String issueDescription = result.getThrowable().getMessage();
		//issueDescription.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));
		//test.fail(issueDescription);
		
		// First get the Step Name and Description name in here

		// System.out.println(this.getClass().getSimpleName());
		// test=
		// extent.createTest(result.getMethod().getConstructorOrMethod().getMethod().getName());
		if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println("Test failed entering in Report");
			String issueDescription = result.getThrowable().getMessage();
			issueDescription.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));
			
			test.log(Status.FAIL, MarkupHelper.createLabel(StepName + " : "+ StepDescrip + " Test case FAILED due to below issues: " + issueDescription,
					ExtentColor.RED));
			
			//test.fail(issueDescription);
			System.out.println(issueDescription);
			
			//Taking Screenshot of the test
			String Screenshotpath = "C:\\Users\\P10444427\\Desktop\\Projects from GIT\\TrialFrame-master\\Screenshot\\" + cellContent2 + "\\"+ MethodName + ".png";
			SSh.screencapture(driver, Screenshotpath);
			String issueToWord = "Failure: " + issueDescription;
			//Attaching screenshot in 
			Im.ImageAttachClass(cellContent2,MethodName,StepName,StepDescrip,Screenshotpath,issueToWord);
		
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			System.out.println("Test passed entering in report");
			test.log(Status.PASS, MarkupHelper.createLabel(StepName + " : "+ StepDescrip + " Test Case PASSED", ExtentColor.GREEN));
		
			//Taking Screenshot of the test
			String Screenshotpath = "C:\\Users\\P10444427\\Desktop\\Projects from GIT\\TrialFrame-master\\Screenshot\\" + cellContent2 + "\\"+ MethodName + ".png";
			SSh.screencapture(driver, Screenshotpath);
			
			//Attaching screenshot in 
			Im.ImageAttachClass(cellContent2,MethodName,StepName,StepDescrip,Screenshotpath,"");
			
			
		} else {
			test.log(Status.SKIP,
					MarkupHelper.createLabel(StepName + " : "+ StepDescrip + " Test Case SKIPPED", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}
		
		
		
		System.out.println("At end of after method");
	}

	@AfterSuite
	public void closeBrowser() {

		System.out.println("In after suite");
		// finishing the excel instance
		
		driver.quit();
		extent.flush();
		System.out.println("Extent Flushed");
		ts.finish();
	}

}
