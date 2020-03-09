package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class Pagelocator{
	//BrowserFunction BrwFn = new BrowserFunction();
	
	@SuppressWarnings("unused")
	private WebDriver driver;
	
	public Pagelocator(WebDriver driver) {
		this.driver = driver;
		  // This initElements method will create all WebElemnts
		  PageFactory.initElements(driver, this);
		 }

	
	@FindBy (xpath = "//*[@id='root']//input[@id='fromCity']")
	private WebElement Firstlocator;
	
	public WebElement FirstLoca() {
		return Firstlocator;
		 } 
	
	
	//WebElement source = driver.findElement(By.xpath("//*[@id='root']//input[@placeholder='From']"));
	@FindBy (xpath = "//*[@id='root']//input[@placeholder='From']")
	private WebElement source;
	public WebElement SecondLoca() {
		return source;
		 } 
	
	
	//driver.findElement(By.xpath("//*[@id='root']//input[@id='toCity']//parent ::label/span")).click();
	@FindBy (xpath = "//*[@id='root']//input[@id='toCity']//parent ::label/span")
	private WebElement src;
	public WebElement ThirdLoca() {
		return src;
		 } 
	
	
	//WebElement destination = driver.findElement(By.xpath("//*[@id='root']//input[@placeholder='To']"));
	@FindBy (xpath = "//*[@id='root']//input[@placeholder='To']")
	private WebElement destination;
	public WebElement FourthLoca() {
		return destination;
		 } 
	
	
}
