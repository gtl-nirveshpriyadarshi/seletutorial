package com.training.sessiontwo;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/*
 * Browser commands.
 
 */

public class browserCommands {
	
	static WebDriver gcdriver;
	public static void main(String[] args){
		
		String gc_path = "./drivers/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", gc_path);
		
		gcdriver = new ChromeDriver();
		String URL = "https://in.bookmyshow.com";
		baseCommands baseCMDs = new baseCommands();
		baseCMDs.driverCommands(gcdriver, URL);
	}

}
/*
* get() - void : Webdriver
* close() - void: Webdriver
* quit() - void: webdriver
* getCurrentURL() : String : Webdriver
* getPageTitle() : String : Webdriver
* getPageSource(): String : Webdriver
* getWindowHandle(): String : Webdriver
* getWindowHandles(): Set<string> : Webdriver
*/
class baseCommands{
	
	public void driverCommands(WebDriver driver, String URL){
		
//		To maximize the window.
		driver.manage().window().maximize();
		
//		To clear the cache & cookie.
		driver.manage().deleteAllCookies();
		
//		To open the web site.
		driver.get(URL);
		
//		To retrieve the current URL.
		String currentURL = driver.getCurrentUrl();
		System.out.println("Current URL: "+currentURL);
		
//		To get the page title.
		String pageTitle = driver.getTitle();
		System.out.println("Page Title: "+pageTitle);
		
//		To get the current tab window handle.
		String currentTabHandle = driver.getWindowHandle();
		System.out.println("Current Tab Handle"+currentTabHandle);
		
//		To get the list of all opened window handles.
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.open();");
		Set<String> windowHanldes = driver.getWindowHandles();
		ArrayList<String> wh = new ArrayList<String>();
		for(String handle: windowHanldes){
			wh.add(handle);
		}
		String secondTabHandle = wh.get(wh.size() - 1);
		driver.switchTo().window(secondTabHandle);
		
		driver.get("https://google.co.in");
	
	}
}

/*
 * driver.navigate().
 * to - void: navigation navigate to another webpage.
 * forward() -void : navigation : forward click - does not takes or does not returns anything.
 * back() - void: navigation : Performs back click.
 * refresh() - void : refreshes the page.

class navigationCommands{
	
	
	
}
*/