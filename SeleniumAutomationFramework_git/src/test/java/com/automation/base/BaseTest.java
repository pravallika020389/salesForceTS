package com.automation.base;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.automation.utility.Constants;
import com.automation.utility.ExtentReportsUtility;
// reportsUtility;
import com.automation.utility.PropertiesUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static WebDriver driver = null;
	protected Logger log;
	protected ExtentReportsUtility report = ExtentReportsUtility.getInstance();
	
	@BeforeTest
	public void setUpForBeforeTest() {
		log=LogManager.getLogger(BaseTest.class.getName());
		
	}
	
	@BeforeMethod
	@Parameters("browerName")
	public  void setUpBeforeTestMethod(@Optional("chrome")String brower){
		PropertiesUtility pro =new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String url=appProp.getProperty("url");
		launchBrowser(brower);
		getUrl(url);
		
		
	}
	
	public void tearDownAfterTestMethod(){
		driver.close();
	}
	

	public  void launchBrowser(String browserName) {
		if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		} else if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
	}

	public   void getUrl(String url) {
		driver.get(url);
		log.info(url + " is entered");


	}
	public   String getPagetitle() {
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		String actualTitle=driver.getTitle();
		log.info(actualTitle + " title is captured");
		return actualTitle;

	}
	public  void verifyTittle(String expectedTitle) {
		String actualTitle = driver.getTitle();
		if (expectedTitle.equals(actualTitle)) {
			log.info("Pass: Title matched with " + expectedTitle);
		} else {
			log.error("Fail : Title not matched with " + expectedTitle);
		}

	}

	public  void enterText(WebElement element, String data, String objectName) {
		if (element.isDisplayed()) {
			element.clear();
			element.sendKeys(data);
			log.info("Pass: " + objectName + " is entered ");
			////report.logTestInfo("Pass: " + objectName + " is entered ");
		} else {
			log.error("Fail: " + objectName + " element is not displayed");
		}
	}

	public  void clickOnElement(WebElement element,String label) {
		if (element.isDisplayed()) {
			element.click();

			log.info("Pass: Element " + label + " is clicked");
			// // report.logTestInfo("Pass: Element " + label + " is clicked");
		} else {
			log.error("Fail: Element not displayed");

		}
	}

	public  void clearTheField(WebElement element) {
		if (element.isDisplayed()) {
			String label = element.getText();
			element.clear();

			log.info("Pass: Field " + label + " is cleared");
			// report.logTestInfo("Pass: Field " + label + " is cleared");
		} else {
			log.error("Fail: Element not displayed");

		}

	}

	public  String getText(WebElement element) {
		String fetchedData = element.getText();
		log.info("Text captured is " + fetchedData);
		// report.logTestInfo("Text captured is " + fetchedData);
		return fetchedData;

	}

	public  void goToAlert(WebElement element) {
		driver.switchTo().alert();
		log.info("Focus is moved to alert");
		// report.logTestInfo("Focus is moved to alert");
	}

	public  void switchToWindow(String handle) {
		driver.switchTo().window(handle);
		log.info("Focus is moved to window");
		// report.logTestInfo("Focus is moved to window");
	}

	public  void switchToDefault() {
		driver.switchTo().defaultContent();
		log.info("Focus is moved to the default content");
		// report.logTestInfo("Focus is moved to the default content");
	}

	public  void copyToClipboard(String text) {

		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection strse1 = new StringSelection(text);
		clip.setContents(strse1, strse1);
		log.info("Text copied to clipboard");
		// report.logTestInfo("Text copied to clipboard");
	}

	public  void closeBrowser() {
		driver.close();
		log.info("Browser is closed");
		// report.logTestInfo("Browser is closed");
	}

	public  void closeAllBrowsers() {
		driver.quit();
		log.info("All Browser are closed");
		// report.logTestInfo("All Browser are closed");
	}

	public  void takescreenshot(String filepath) throws IOException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");  
		   LocalDateTime now = LocalDateTime.now();  
		    
		TakesScreenshot screenCapture = (TakesScreenshot) driver;
		File srcFile = screenCapture.getScreenshotAs(OutputType.FILE);
		String path = filepath + "_"+dtf.format(now)+".png";
		File destFile = new File(path);
		FileUtils.copyFile(srcFile, destFile);

	}
	
	public  void selectOptionbyIndex(WebElement element,int indexNumber) {
		
		Select select = new Select(element);
		select.selectByIndex(indexNumber);
		
	}
	
public  void selectOptionbyValue(WebElement element,String value) {
		
		Select select = new Select(element);
		select.selectByValue(value);
		
	}

public  void selectOptionbyVisibleText(WebElement element,String text) {
	
	Select select = new Select(element);
	select.selectByVisibleText(text);
	
}

public  void waitForPageLoad(int time) {
	driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
}

	public File takescreenshot(WebDriver driver) 
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");  
		   LocalDateTime now = LocalDateTime.now();  
		    
		TakesScreenshot screenCapture = (TakesScreenshot) driver;
		File srcFile = screenCapture.getScreenshotAs(OutputType.FILE);
		String path = Constants.SCREENSHOTS_DIRECTORY_PATH + "_"+dtf.format(now)+".png";
		File destFile = new File(path);
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destFile;

	}

}
