package com.Amazon;

import com.pages.*;

import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class TC3o4o14 extends BrowserFunction{
	String url = "https://rahulshettyacademy.com/seleniumPractise/#/";
	String[] itemsNeeded = {"Cucumber","Brocolli","Beetroot"};
	Pagelocator PL;
	
	
	@Test
  public void Step_1() {
	  
	  driver.get(url);
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));
		
		int j = 0;
		for (int i = 0; i < products.size(); i++) {
			
			String name  = products.get(i).getText();
			//format name to get actual vegetable name
			String[] veggieFirstName = name.split("-");
			String formattedName = veggieFirstName[0].trim();
			
			//check whether the name you extracted is present in array
			//convert array into array list for easy search
			List<String> itemsNeededList = Arrays.asList(itemsNeeded);
			//We are not usng array list directly since array take less memory
			
			
			System.out.println(name);
			if(itemsNeededList.contains(formattedName)) {
				//click on add to Cart
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
			
				//Incrementing counter
				j++;
				System.out.println(j);
				
				//Breaking the loop when all the items in the arraylist are added to cart
				if(j==itemsNeeded.length) {
					break;
				}//End of inner if
				
			}//End of outer if

		}//End of four Loop
	  
  }// End of Test
  
@Test
public void Step_2() throws InterruptedException {
	PL = new Pagelocator(BrowserFunction.driver);
	String url = "https://www.makemytrip.com/";
	driver.get(url);
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	Thread.sleep(3000);
	PL.FirstLoca().click();
	WebElement source = PL.SecondLoca();
	source.click();
	source.clear();
	source.sendKeys("mum");
	Thread.sleep(3000);
	source.sendKeys(Keys.ARROW_DOWN);
	source.sendKeys(Keys.ENTER);	
	
}//End of firstTestSecond

@Test
public void Step_3() throws InterruptedException {
	PL = new Pagelocator(BrowserFunction.driver);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	PL.ThirdLoca().click();

	WebElement destination = PL.FourthLoca();
	destination.click();
	destination.clear();
	destination.sendKeys("del");
	Thread.sleep(3000);
	destination.sendKeys(Keys.ARROW_DOWN);
	destination.sendKeys(Keys.ENTER);
}// End of firstTestThird

	
  
  
  
}// End of TC3o4o14 Class
