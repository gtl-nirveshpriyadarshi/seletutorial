package test.java;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FirstTestNGTest {
	
	static WebDriver driver;
	String URL = "https://test6.insm.eu/test633/modules/UserView";
	
  @Test(description= "Verify the login with valid credentials.", groups = { "logingroup" })
  @Parameters({"UserName", "Password"})
  public void testLogin(String UserName, String Password) {
	  new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.className("username")));
	  
	  WebElement uname = driver.findElement(By.className("username"));
	  uname.sendKeys(UserName);
	  
	  WebElement password = driver.findElement(By.className("password"));
	  password.sendKeys(Password);
	  
	  WebElement loginButton = driver.findElement(By.xpath("//button[text() ='Login']"));
	  loginButton.click();
	  
	  WebElement logoutButton = (new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOfElementLocated(By.className("s-logout")));
	  
	  boolean lgBtnStatus = logoutButton.isDisplayed();
	  
	  System.out.println("logout button status"+ lgBtnStatus);
	  assertTrue(lgBtnStatus, "Logout button not visible.");
	  
  }
  
  @DataProvider(name = "InValidCredentials")
  public static Object[][] inValidcredentials() {
        return new Object[][] { { "test", "TEST" }, { "TEST", "test" }, { "TEST", "TEST" }, {"TeSt", "teST"}};
  }
  
  
  @Test(groups = {"logingroup"}, description = "Verify the logout functionality.")
  @Parameters({"UserName", "Password"})
  public void testLogout(String UserName, String Password){
	  
	  new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.className("username")));
	  
	  WebElement uname = driver.findElement(By.className("username"));
	  uname.sendKeys(UserName);
	  
	  WebElement pwrd = driver.findElement(By.className("password"));
	  pwrd.sendKeys(Password);
	  
	  WebElement loginButton = driver.findElement(By.xpath("//button[text() ='Login']"));
	  loginButton.click();
	  
	  new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.className("s-logout")));
	  WebElement logoutButton = driver.findElement(By.className("s-logout"));
	  logoutButton.click();
	  
	  WebElement unameAL = (new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOfElementLocated(By.className("username")));
	  boolean unameStatus = unameAL.isDisplayed();
	  
	  assertTrue(unameStatus, "Username textfield not visible.");
  }
  
  @Test(description= "Verify the login with invalid credentials.", groups = { "logingroup" }, dataProvider = "InValidCredentials")
  public void testLoginWithWrongCredentials(String UserName, String pwd){
	  
	  new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.className("username")));
	  
	  WebElement uname = driver.findElement(By.className("username"));
	  uname.sendKeys(UserName);
	  
	  WebElement password = driver.findElement(By.className("password"));
	  password.sendKeys(pwd);
	  
	  WebElement loginButton = driver.findElement(By.xpath("//button[text() ='Login']"));
	  loginButton.click();
	  
	  boolean loginBtnStatus = loginButton.isDisplayed();
	  assertTrue(loginBtnStatus, "Login is successful with invalid credentials.");
  }
  
  @BeforeClass(alwaysRun = true)
  @Parameters({"Browser"})
  public void beforeClass(String Browser) {
	  if(Browser.equalsIgnoreCase("chrome")) {
		  String gc_path = "./drivers/chromedriver.exe";
		  System.setProperty("webdriver.chrome.driver", gc_path);
	  } else if(Browser.equalsIgnoreCase("firefox")) {
		  String ff_path = "./drivers/geckodriver.exe";
		  System.setProperty("webdriver.chrome.driver", ff_path);
	  } else if(Browser.equalsIgnoreCase("ie")) {
		  String ie_path = "./drivers/iedriver.exe";
		  System.setProperty("webdriver.chrome.driver", ie_path);
	  }
  }

  @AfterClass(alwaysRun = true)
  public void afterClass() {
  }

  @BeforeMethod(alwaysRun= true)
  @Parameters({"Browser"})
  public void beforeMethod(String Browser) {
	  System.out.println("execute before every method");
	  if(Browser.equalsIgnoreCase("chrome")) {
		  driver = new ChromeDriver();
	  }else if(Browser.equalsIgnoreCase("firefox")){
		  driver = new FirefoxDriver();
	  }else if(Browser.equalsIgnoreCase("IE")){
		  driver = new InternetExplorerDriver();
	  }else{
		  driver = new ChromeDriver();
	  }
	  
	  driver.get(URL);
  }

  @AfterMethod(alwaysRun= true)
  public void afterMethod() {
	  driver.close();
  }
}
