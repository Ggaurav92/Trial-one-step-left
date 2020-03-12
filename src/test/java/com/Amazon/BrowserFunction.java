package com.Amazon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.plexus.util.ExceptionUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
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
	
	//Initiating ExtentReport
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	//Instantiating TestIn method to get into Excel
	TestInMethod ts = new TestInMethod();
	
	//Creating instance of ImageClass to put image in word
	ImageClass Im = new ImageClass();
	
	//Creating instance of GivingData class
	GivingData gd = new GivingData();
	
	//Initializing for taking screenshot
	ScreenShotClass SSh = new ScreenShotClass(driver);
	
	// path of TestScript excel file
	static String fileName = "./Test Data/TestWorksheet.xlsx";
	static int i;
	
	
	//Array for merging document
    ArrayList<String> paths = new ArrayList<String>();
	
	

	@BeforeSuite
	public void initialioseBrowser() {

		//showing the path of ExtentReport
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

		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		driver = new ChromeDriver();
	}

	@SuppressWarnings("unused")
	@BeforeClass
	public void beforeclass() {
		System.out.println(this.getClass().getSimpleName());
		test = extent.createTest(this.getClass().getSimpleName());

		// For Step and Descrip
		// making iterator i=0 before every TestNG class
		i = 0;
		
		
		// Gets the current TestNG class Name
		String cellContent = this.getClass().getSimpleName();
		//Replacing 'o' with '.' and remove TC in ClassName
		String ClassName = gd.currentClassName(cellContent); //Replacing 'o' with '.' and remove TC in ClassName
		
		//Creating folder for Screenshots
		String path = "./Screenshot/" + ClassName;
		//Creating a File object
	    File file = new File(path);
	    //Creating the directory
	    boolean bool = file.mkdir();
	    
	    //Creating folder for Word Document
		String Wordpath = "./WordDocs/" + ClassName;
		//Creating a File object
	    File wordfile = new File(Wordpath);
	    //Creating the directory
	    boolean wordbool = wordfile.mkdir();
	    
	}//End of BeforeClass
		

	@AfterMethod
	public void getResult(ITestResult result) throws IOException {
		
		// Start of Step and Description
		// Gets the current TestNG class Name
		String cellContent = this.getClass().getSimpleName();
		//Replacing 'o' with '.' and remove TC in ClassName
		String ClassName = gd.currentClassName(cellContent);
		
		//System.out.println(ClassName);
		// Searches for the Class name in Excel and return step name
		String StepName = ts.GetStepName(fileName, ClassName, i, 7);
		// Searches for the Class name in Excel and returns description of step
		String StepDescrip = ts.GetStepName(fileName, ClassName, i, 8);
		// Here we merge the step and description to be put in word
		//String merged = StepName + ": " + StepDescrip;
		//System.out.println(merged);
		
		//increase the iterator by 1
		i++;

		System.out.println("In After method");
		String MethodName = result.getMethod().getConstructorOrMethod().getMethod().getName();
		//System.out.println(MethodName);
		
		
		
		if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println("Test failed entering in Report");
			String issueDescription = result.getThrowable().getMessage();
			issueDescription.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));
			//Taking results out of ITestResult and entering them in extent Report
			test.log(Status.FAIL, MarkupHelper.createLabel(StepName + " : "+ StepDescrip + " Test case FAILED due to below issues: " + issueDescription,
					ExtentColor.RED));
			
			//Taking Screenshot of the test
			String Screenshotpath = "./Screenshot/" + ClassName + "/"+ MethodName + ".png";
			
			//Current page screenshot
			//SSh.screencapture(driver, Screenshotpath);
			
			//full page screenshot
			SSh.fullPgScreenCapture(driver, Screenshotpath);
			
			String issueToWord = "Failure: " + issueDescription;
			//Attaching screenshot in 
			Im.ImageAttachClass(ClassName,MethodName,StepName,StepDescrip,Screenshotpath,issueToWord);
		
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			System.out.println("Test passed entering in report");
			//Taking results out of ITestResult and entering them in extent Report
			test.log(Status.PASS, MarkupHelper.createLabel(StepName + " : "+ StepDescrip + " Test Case PASSED", ExtentColor.GREEN));
		
			//Taking Screenshot of the test
			String Screenshotpath = "./Screenshot/" + ClassName + "/"+ MethodName + ".png";
			
			//Current page screenshot
			SSh.screencapture(driver, Screenshotpath);
			
			//full page screenshot
			SSh.fullPgScreenCapture(driver, Screenshotpath);
			
			//Attaching screenshot in 
			Im.ImageAttachClass(ClassName,MethodName,StepName,StepDescrip,Screenshotpath,null);
			
			
		} else {
			//Taking results out of ITestResult and entering them in extent Report
			test.log(Status.SKIP,
					MarkupHelper.createLabel(StepName + " : "+ StepDescrip + " Test Case SKIPPED", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}
		
		 String fileName = "./WordDocs/" + ClassName +"/"+ MethodName + ".docx";
		 
		 //the path of the word doc for current step to paths List
		 paths.add(fileName);
		System.out.println("At end of after method");
	}//End of AfterMethod
	
	@AfterClass
	public void afterClass() throws Exception {
		// Gets the current TestNG class Name
		String cellContent = this.getClass().getSimpleName();
		//Replacing 'o' with '.' and remove TC in ClassName
		String ClassName = gd.currentClassName(cellContent);
		
		String File2Path = "./WordDocs/" + ClassName +"/"+ ClassName + ".docx";
		FileOutputStream faos = new FileOutputStream(File2Path);
    	WordMerged wm =new WordMerged(faos);
    	
    	for(String InpStrm : paths) {
            wm.add(new FileInputStream(InpStrm));	
            }
    	
    	wm.doMerge();
        wm.close();
        
        paths.clear();
        
        System.out.println("Sucessfully merged");
	}//End of AfterClass
	

	@AfterSuite
	public void closeBrowser() {

		System.out.println("In after suite");
		// finishing the excel instance
		
		driver.quit();
		extent.flush();
		System.out.println("Extent Flushed");
		System.out.println("Test Ended");
		//ts.finish();
	}//End of AfterSuite

}
