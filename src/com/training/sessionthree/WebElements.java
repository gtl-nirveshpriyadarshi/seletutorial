package com.training.sessionthree;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.util.List;;

public class WebElements {
	
	static WebDriver gcdriver;
	
		public static void main(String[] args){
			
			String gc_path = "./drivers/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", gc_path);
			
			gcdriver = new ChromeDriver();
			File regFile = new File("./data/registration.html"); 
			String URL = "file://"+regFile.getAbsolutePath();
			System.out.println("URL "+URL);
			gcdriver.get(URL);
			
			WebElement firstName = gcdriver.findElement(By.className("name"));
			firstName.sendKeys("first name");
			
			List<WebElement> inputTags = gcdriver.findElements(By.className("name"));
			inputTags.get(1).sendKeys("middle name");
			inputTags.get(2).sendKeys("last name");
			/*
			webElementCommands wec = new webElementCommands();
			wec.inputCommands(gcdriver, "nirvesh", "r", "priyadarshi");
			*/
			//gcdriver.quit();
		}
}

/*
 * clear() : void : WebElement
 * click() : void : WebElement
 * findElement : WebElement
 * findElements : List<WebElement>
 * getAttribute :String : WebElement
 * getText : String : WebElement
 * getLocation(): Point : WebElement
 * getSize(): List size: WebElement
 * getTagName(): String: WebElement
 * isDisplayed() : Boolean : Webelement
 * isEnabled(): Boolean : Webelement
 * isSelected(): Boolean : Webelement
 * sendKeys(String) : void : Webelement
 * submit(): void : Webelement
 * 
 */
class webElementCommands{
	
	public void inputCommands(WebDriver driver, String firstName, String middleName, String lastName ){
		
		WebElement fname = driver.findElement(By.id("firstname"));
		fname.click();
		fname.clear();
		fname.sendKeys(firstName);
		
		WebElement mname = driver.findElement(By.id("middlename"));
		mname.click();
		mname.clear();
		mname.sendKeys(firstName);
		
		WebElement lname = driver.findElement(By.id("lastname"));
		lname.click();
		lname.clear();
		lname.sendKeys(firstName);
	}
}
