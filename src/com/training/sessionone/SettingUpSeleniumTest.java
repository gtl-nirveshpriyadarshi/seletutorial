package com.training.sessionone;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.security.UserAndPassword;

public class SettingUpSeleniumTest {
	
	public static void main(String[] args){
		String URL = "https://www.google.co.in";

//		open firefox browser.
		String ff_path = "./drivers/geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", ff_path);
		
		WebDriver ffdriver = new FirefoxDriver();
		ffdriver.get(URL);
		ffdriver.quit();

//		open chrome browser.		
		String gc_path = "./drivers/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", gc_path);
		
		WebDriver gcdriver = new ChromeDriver();
		gcdriver.manage().window().maximize();
		gcdriver.get(URL);
		JavascriptExecutor js = (JavascriptExecutor)gcdriver;
		js.executeScript("window.open();");
		Set<String> windowHanldes = gcdriver.getWindowHandles();
		ArrayList<String> wh = new ArrayList<String>();
		for(String handle: windowHanldes){
			wh.add(handle);
		}
		
		System.out.println(windowHanldes.size());
		int secondTabHandle = windowHanldes.size() - 1;
		gcdriver.switchTo().window(wh.get(secondTabHandle));
		gcdriver.switchTo().alert().authenticateUsing(new UserAndPassword("jainpan", "App123$"));
		gcdriver.get("https://test6.insm.eu/test633/modules/UserView");
		gcdriver.quit();
	}

}
