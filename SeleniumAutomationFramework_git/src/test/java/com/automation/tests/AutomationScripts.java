package com.automation.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.utility.PropertiesUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutomationScripts extends BaseTest {

	@Test
	public void login_Error_Message_1() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		Actions ac = new Actions(driver);
		ac.moveToElement(loginidElement).build().perform();
		enterText(loginidElement, userId, "Login Id");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		clearTheField(passwordElement);
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "Login Button");
		WebElement errorElement = driver.findElement(By.id("error"));
		String actualErrorMsg = getText(errorElement);
		String expectedErrorMsg = "Please enter your password.";
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg);

	}

	@Test
	public void login_Success_2() {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "Login Button");
		String actualHomeTitle = driver.getTitle();
		String expectedHomeTitle = "Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actualHomeTitle, expectedHomeTitle);
		closeBrowser();
	}

	@Test

	public void check_Remember_Me_03() {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement rememberMe = driver.findElement(By.id("rememberUn"));
		clickOnElement(rememberMe, "Remember me");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement userMenuDropdown = driver.findElement(By.id("userNavButton"));
		wait.until(ExpectedConditions.visibilityOf(userMenuDropdown));
		wait.until(ExpectedConditions.elementToBeClickable(userMenuDropdown));
		clickOnElement(userMenuDropdown, "User Menu Dropdown");
		WebElement logout = driver.findElement(By.linkText("Logout"));
		wait.until(ExpectedConditions.visibilityOf(logout));
		wait.until(ExpectedConditions.elementToBeClickable(logout));
		clickOnElement(logout, "Logout");
		wait.until(ExpectedConditions.titleContains("Login | Salesforce"));
		verifyTittle("Login | Salesforce");
		WebElement reLoginidElement = driver.findElement(By.id("idcard-identity"));
		wait.until(ExpectedConditions.visibilityOf(reLoginidElement));
		String actualUserId = getText(reLoginidElement);
		Assert.assertEquals(userId, actualUserId);
		closeBrowser();

	}

	@Test

	public void Forgot_Password_04A() throws InterruptedException, AWTException {

		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement forgotPswd = driver.findElement(By.id("forgot_password_link"));
		wait.until(ExpectedConditions.visibilityOf(forgotPswd));
		clickOnElement(forgotPswd, "Forgot Password");
		WebElement userName = driver.findElement(By.id("un"));
		enterText(userName, "hello@g.com", "User Name");
		WebElement continueButton = driver.findElement(By.id("continue"));
		clickOnElement(continueButton, "Continue Button");
		wait.until(ExpectedConditions.titleContains("Check Your Email | Salesforce"));
		WebElement returnToLogin = driver.findElement(By.linkText("Return to Login"));
		Assert.assertTrue(returnToLogin.isDisplayed());
		closeBrowser();
	}

	@Test

	public void Forgot_Password_04B() throws InterruptedException, IOException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String invalidUserId = appProp.getProperty("login.invalid.userid");
		String invalidPassword = appProp.getProperty("login.invalid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, invalidUserId, "Wrong User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, invalidPassword, "Wrong Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		WebElement errorElement = driver.findElement(By.id("error"));
		String actualErrorMsg = getText(errorElement);
		String expectedErrorMsg = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		Assert.assertEquals(expectedErrorMsg, actualErrorMsg);
		closeBrowser();
	}

	@Test
	public void user_Menu_dropdown_05() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "Login Button");
		wait.until(ExpectedConditions.titleIs("Home Page ~ Salesforce - Developer Edition"));
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement userMenuDropdown = driver.findElement(By.id("userNavButton"));
		wait.until(ExpectedConditions.visibilityOf(userMenuDropdown));
		wait.until(ExpectedConditions.elementToBeClickable(userMenuDropdown));
		clickOnElement(userMenuDropdown, "User Menu Dropdown");
		WebElement userMenuOptions = driver.findElement(By.id("userNav-menuItems"));
		List<WebElement> arrMenu = userMenuOptions.findElements(By.tagName("a"));
		List<String> actualDropdown = new ArrayList<String>();
		for (WebElement webEle : arrMenu) {
			actualDropdown.add(webEle.getText());
		}
		List<String> expectedDropdown = new ArrayList<String>(Arrays.asList("My Profile", "My Settings",
				"Developer Console", "Switch to Lightning Experience", "Logout"));
		Assert.assertEquals(actualDropdown, expectedDropdown);
		closeBrowser();
	}

	@Test
	public void user_Menu_MyProfile_06() throws InterruptedException, AWTException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement userMenuDropdown = driver.findElement(By.id("userNavButton"));
		wait.until(ExpectedConditions.visibilityOf(userMenuDropdown));
		wait.until(ExpectedConditions.elementToBeClickable(userMenuDropdown));
		clickOnElement(userMenuDropdown, "User Menu Dropdown");
		WebElement myProfile = driver.findElement(By.linkText("My Profile"));
		clickOnElement(myProfile, "My Profile");
		wait.until(ExpectedConditions.titleContains("User: P Jan ~ Salesforce - Developer Edition"));
		verifyTittle("User: P Jan ~ Salesforce - Developer Edition");
		Thread.sleep(3000);
		/// Updating the last name
		WebElement editProfile = driver.findElement(By.xpath("//*[contains(text(),'Contact')]//a"));
		wait.until(ExpectedConditions.elementToBeClickable(editProfile));
		Thread.sleep(4000);
		clickOnElement(editProfile, "Edit Profile");
		Thread.sleep(4000);
		driver.switchTo().frame("contactInfoContentId");
		Thread.sleep(3000);
		WebElement about = driver.findElement(By.xpath("//*[@id=\"aboutTab\"]/a"));
		clickOnElement(about, "about");
		WebElement lName = driver.findElement(By.id("lastName"));
		String updatedLastName = "Jandh";
		enterText(lName, updatedLastName, "Last Name");
		WebElement save = driver.findElement(By.xpath("//*[@id=\"TabPanel\"]/div/div[2]/form/div/input[1]"));
		clickOnElement(save, "save");
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		WebElement updatedName = driver.findElement(By.id("tailBreadcrumbNode"));
		String name = getText(updatedName);
		if (name.equalsIgnoreCase("P " + updatedLastName + " ")) {
			System.out.println("Pass :Last Name is updated");
		} else {
			System.out.println("Fail :Last Name is not updated ");
		}
		/// changing back the name
		Thread.sleep(2000);
		WebElement reEditProfile = driver.findElement(By.xpath("//*[contains(text(),'Contact')]//a"));
		clickOnElement(reEditProfile, "Edit Profile");
		Thread.sleep(4000);
		driver.switchTo().frame("contactInfoContentId");
		Thread.sleep(3000);
		WebElement reAbout = driver.findElement(By.xpath("//*[@id=\"aboutTab\"]/a"));
		clickOnElement(reAbout, "about");
		WebElement reLName = driver.findElement(By.id("lastName"));
		String updatedLastNameNew = "Jan";
		enterText(reLName, updatedLastNameNew, "Last Name");
		WebElement saveAll = driver.findElement(By.xpath("//*[@id=\"TabPanel\"]/div/div[2]/form/div/input[1]"));
		clickOnElement(saveAll, "saveAll");
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		/// Post content
		Actions actions = new Actions(driver);
		WebElement postLink = driver.findElement(By.xpath("//*[@id=\"publisherAttachTextPost\"]"));
		clickOnElement(postLink, "Post Link");
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		// driver.switchTo().frame("Rich Text Editor, publisherRichTextEditor");

		driver.findElement(By.xpath("//*[@id='cke_43_contents']/iframe")).click();
		String textTotype = "Hi everyone!";
		actions.sendKeys(textTotype).build().perform();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		driver.switchTo().defaultContent();
		WebElement shareButton = driver.findElement(By.id("publishersharebutton"));
		clickOnElement(shareButton, "share Button");

		//// upload file
		Thread.sleep(6000);
		WebElement fileLink = driver.findElement(By.xpath("//*[@id='publisherAttachContentPost']"));
		// driver.findElement(By.id("publisherAttachContentPost"));
		clickOnElement(fileLink, "File Link");
		WebElement uploadFile = driver.findElement(By.linkText("Upload a file from your computer"));
		clickOnElement(uploadFile, "upload File Button");
		Thread.sleep(3000);

		WebElement uploadButton = driver.findElement(By.xpath("//*[@id='chatterFile']"));
		actions.click(uploadButton).build().perform();
		String filePath = "C:\\Users\\jandh\\OneDrive\\Desktop\\tesing.txt";
		copyToClipboard(filePath);
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		System.out.println("completed");
		driver.switchTo().defaultContent();
		WebElement sharefileButton = driver.findElement(By.xpath("//*[@id=\"publishersharebutton\"]"));
		// (By.id("publishersharebutton"));
		clickOnElement(sharefileButton, "share Button");
		Thread.sleep(2000);
		System.out.println("completed");

	}

	@Test
	public void user_Menu_MyProfile_06A_Photo() throws InterruptedException, AWTException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement userMenuDropdown = driver.findElement(By.id("userNavButton"));
		wait.until(ExpectedConditions.visibilityOf(userMenuDropdown));
		wait.until(ExpectedConditions.elementToBeClickable(userMenuDropdown));
		clickOnElement(userMenuDropdown, "User Menu Dropdown");
		WebElement myProfile = driver.findElement(By.linkText("My Profile"));
		clickOnElement(myProfile, "My Profile");
		wait.until(ExpectedConditions.titleContains("User: P Jan ~ Salesforce - Developer Edition"));
		verifyTittle("User: P Jan ~ Salesforce - Developer Edition");
		/// upload photo
		Thread.sleep(2000);
		WebElement moderator = driver.findElement(By.id("displayBadge"));
		Actions actions = new Actions(driver);
		actions.moveToElement(moderator).build().perform();
		System.out.println("Mouse moved");
		WebElement addPhoto = driver.findElement(By.linkText("Add Photo"));
		clickOnElement(addPhoto, "addPhoto");
		Thread.sleep(3000);
		driver.switchTo().frame("uploadPhotoContentId");
		System.out.println("switched the frame");
		Thread.sleep(2000);
		WebElement uplod = driver.findElement(By.xpath("//input[@id='j_id0:uploadFileForm:uploadInputFile']"));
		actions.click(uplod).build().perform();
		String photoPath = "C:\\Users\\jandh\\OneDrive\\Desktop\\Screenshot.jpg";
		copyToClipboard(photoPath);
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		System.out.println("completed");
		Thread.sleep(3000);
		WebElement savePhoto = driver.findElement(By.id("j_id0:uploadFileForm:uploadBtn"));
		clickOnElement(savePhoto, "savePhoto Button");
		Thread.sleep(3000);
		WebElement savePhotoFinal = driver.findElement(By.id("j_id0:j_id7:save"));

		clickOnElement(savePhotoFinal, "save Button");
		Thread.sleep(3000);
		WebElement reModerator = driver.findElement(By.className("moderatorBadge"));
		actions.moveToElement(reModerator).build().perform();
		WebElement deletePhoto = driver.findElement(By.linkText("Delete"));
		Assert.assertTrue(deletePhoto.isDisplayed());
		// delete photo
		clickOnElement(deletePhoto, "delete Button");
		Thread.sleep(2000);
		WebElement okButton = driver.findElement(By.id("simpleDialog0button0"));
		clickOnElement(okButton, "ok Button");

	}

	@Test
	public void user_Menu_MySettings_07() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "Login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement userMenuDropdown = driver.findElement(By.id("userNavButton"));
		wait.until(ExpectedConditions.visibilityOf(userMenuDropdown));
		wait.until(ExpectedConditions.elementToBeClickable(userMenuDropdown));
		clickOnElement(userMenuDropdown, "User Menu Dropdown");
		/// Selecting My Settings from Usermenu
		WebElement mySettings = driver.findElement(By.linkText("My Settings"));
		clickOnElement(mySettings, "My Settings");
		wait.until(ExpectedConditions.titleContains("Hello, P Jan! ~ Salesforce - Developer Edition"));
		verifyTittle("Hello, P Jan! ~ Salesforce - Developer Edition");
		waitForPageLoad(80);
		Thread.sleep(4000);
		// Personal tab options
		WebElement personalTab = driver.findElement(By.xpath("//span[@id='PersonalInfo_font']"));
		clickOnElement(personalTab, "Personal Tab");
		Thread.sleep(2000);
		WebElement loginHistory = driver.findElement(By.xpath("//span[@id='LoginHistory_font']"));
		clickOnElement(loginHistory, "Login History");
		Thread.sleep(2000);
		String download = "Download login history for last six months, including logins from outside the website, such as API logins (Excel .csv file) »";
		WebElement downloadLink = driver.findElement(By.linkText(download));
		clickOnElement(downloadLink, "downloadLink");
		Thread.sleep(2000);

		/// Display and Layout options
		WebElement displayLayouttab = driver.findElement(By.xpath("//span[@id='DisplayAndLayout_font']"));
		clickOnElement(displayLayouttab, "Display Layouttab");
		Thread.sleep(2000);
		WebElement customizeMyTabs = driver.findElement(By.xpath("//span[@id='CustomizeTabs_font']"));
		clickOnElement(customizeMyTabs, "Customize MyTabs");
		Thread.sleep(2000);

		WebElement customAppDropDown = driver.findElement(By.xpath("//select[@id='p4']"));
		Select selectCustomAppDropDown = new Select(customAppDropDown);
		selectCustomAppDropDown.selectByVisibleText("Salesforce Chatter");

		Thread.sleep(2000);
		WebElement availableTab = driver.findElement(By.xpath("//select[@id='duel_select_0']"));
		Select selectAvailableTab = new Select(availableTab);
		selectAvailableTab.selectByVisibleText("Reports");

		Thread.sleep(2000);
		WebElement addButton = driver.findElement(By.xpath("//a/img[@title='Add']"));
		clickOnElement(addButton, "Add Button");
		Thread.sleep(2000);

		/*
		 * String addedOption=selectSelectedTab.getFirstSelectedOption().getText();
		 * if(addedOption.equalsIgnoreCase("Reports")) {
		 * System.out.println("Reports added Sucessfully"); } else {
		 * System.out.println("Reports not added"); }
		 */
		WebElement saveButton = driver.findElement(By.name("save"));
		clickOnElement(saveButton, "Save Button");
		wait.until(ExpectedConditions.titleContains("Hello, P Jan! ~ Salesforce - Developer Edition"));
		verifyTittle("Hello, P Jan! ~ Salesforce - Developer Edition");
		WebElement reDisplayLayouttab = driver.findElement(By.xpath("//span[@id='DisplayAndLayout_font']"));
		clickOnElement(reDisplayLayouttab, "Display Layouttab");
		Thread.sleep(2000);
		WebElement reCustomizeMyTabs = driver.findElement(By.xpath("//span[@id='CustomizeTabs_font']"));
		clickOnElement(reCustomizeMyTabs, "Customize MyTabs");
		Thread.sleep(2000);
		WebElement reCustomAppDropDown = driver.findElement(By.xpath("//select[@id='p4']"));
		Select reSelectCustomAppDropDown = new Select(reCustomAppDropDown);
		
		reSelectCustomAppDropDown.selectByVisibleText("Salesforce Chatter");

		Thread.sleep(2000);
		WebElement selectedTab = driver.findElement(By.xpath("//select[@id='duel_select_1']"));
		Select selectSelectedTab = new Select(selectedTab);
		selectSelectedTab.selectByVisibleText("Reports");

		WebElement remove = driver.findElement(By.xpath("//img[@title='Remove']"));
		clickOnElement(remove, "remove Button");
		Thread.sleep(2000);
		WebElement reSaveButton = driver.findElement(By.name("save"));
		clickOnElement(reSaveButton, "Save Button");
		Thread.sleep(2000);

		// Email options
		WebElement emailTab = driver.findElement(By.xpath("//span[@id='EmailSetup_font']"));
		clickOnElement(emailTab, "email Tab");
		Thread.sleep(2000);
		WebElement emailSetting = driver.findElement(By.xpath("//span[@id='EmailSettings_font']"));
		clickOnElement(emailSetting, "email Setting");
		Thread.sleep(2000);
		WebElement bccRatio = driver.findElement(By.id("auto_bcc1"));
		if (bccRatio.isSelected()) {
			System.out.println("BCC is already selected as YES");
		} else {
			bccRatio.click();
			System.out.println("BCC is selected as YES");
		}

		// Calendar and Remainders options
		WebElement calAndRemindr = driver.findElement(By.xpath("//span[@id='CalendarAndReminders_font']"));
		clickOnElement(calAndRemindr, "Calendar And Remainder");
		Thread.sleep(2000);
		WebElement activityRemainder = driver.findElement(By.xpath("//span[@id='Reminders_font']"));
		clickOnElement(activityRemainder, "Activity Remainder");
		Thread.sleep(2000);
		verifyTittle("Activity Reminders ~ Salesforce - Developer Edition");
		WebElement openTestRemainder = driver.findElement(By.id("testbtn"));
		clickOnElement(openTestRemainder, "Open Test Remainder");
	}

	@Test

	public void user_Menu_DeveloperConsole_08() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement userMenuDropdown = driver.findElement(By.id("userNavButton"));
		wait.until(ExpectedConditions.visibilityOf(userMenuDropdown));
		wait.until(ExpectedConditions.elementToBeClickable(userMenuDropdown));
		clickOnElement(userMenuDropdown, "User Menu Dropdown");

		String baseWindowHandle = driver.getWindowHandle();
		/// Selecting from Developer Console
		WebElement developerConsole = driver.findElement(By.linkText("Developer Console"));
		clickOnElement(developerConsole, "Developer Console");
		Thread.sleep(4000);
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String handle : allWindowHandles) {
			if (!baseWindowHandle.equals(handle)) {
				switchToWindow(handle);
				break;
			}
		}
		String actualHomeTitle = driver.getTitle();
		String expectedHomeTitle = "Developer Console";
		Assert.assertEquals(actualHomeTitle, expectedHomeTitle);
		closeBrowser();
		closeAllBrowsers();

	}

	@Test
	public void logout_09() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement userMenuDropdown = driver.findElement(By.id("userNavButton"));
		wait.until(ExpectedConditions.visibilityOf(userMenuDropdown));
		wait.until(ExpectedConditions.elementToBeClickable(userMenuDropdown));
		clickOnElement(userMenuDropdown, "User Menu Dropdown");
		WebElement logout = driver.findElement(By.linkText("Logout"));
		wait.until(ExpectedConditions.visibilityOf(logout));
		wait.until(ExpectedConditions.elementToBeClickable(logout));
		clickOnElement(logout, "Logout");
		wait.until(ExpectedConditions.titleContains("Login | Salesforce"));
		String actualLoginTitle = driver.getTitle();
		String expectedLoginTitle = "Login | Salesforce";
		Assert.assertEquals(actualLoginTitle, expectedLoginTitle);
		closeBrowser();
	}

	@Test

	public void createAccounts_10() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		Thread.sleep(2000);
		WebElement accountsOption = driver.findElement(By.linkText("Accounts"));
		clickOnElement(accountsOption, "Accounts Option");
		waitForPageLoad(60);
		driver.switchTo().activeElement();
		WebElement closePopup = driver.findElement(By.linkText("Close"));
		if (closePopup.isDisplayed()) {
			clickOnElement(closePopup, "Close Popup");
		}
		wait.until(ExpectedConditions.titleIs("Accounts: Home ~ Salesforce - Developer Edition"));
		WebElement newButton = driver.findElement(By.name("new"));
		clickOnElement(newButton, "New Button");
		wait.until(ExpectedConditions.titleIs("Account Edit: New Account ~ Salesforce - Developer Edition"));
		WebElement accountName = driver.findElement(By.id("acc2"));
		String eneteredName = "hello";
		enterText(accountName, eneteredName, "Account name");
		WebElement typeDropdown = driver.findElement(By.id("acc6"));
		Select select = new Select(typeDropdown);
		select.selectByVisibleText("Technology Partner");
		WebElement priorityDropdown = driver.findElement(By.id("00NHs00000Dje6j"));
		Select select1 = new Select(priorityDropdown);
		select1.selectByVisibleText("High");
		WebElement saveButton = driver.findElement(By.name("save"));
		clickOnElement(saveButton, "Save Button");
		String expectedTitle = "Account: " + eneteredName + " ~ Salesforce - Developer Edition";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(expectedTitle, actualTitle);
		closeBrowser();

	}

	@Test

	public void createNewView_11() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		Thread.sleep(2000);
		WebElement accountsOption = driver.findElement(By.linkText("Accounts"));
		clickOnElement(accountsOption, "Accounts Option");
		waitForPageLoad(60);
		driver.switchTo().activeElement();
		WebElement closePopup = driver.findElement(By.linkText("Close"));
		if (closePopup.isDisplayed()) {
			clickOnElement(closePopup, "Close Popup");
		}
		wait.until(ExpectedConditions.titleIs("Accounts: Home ~ Salesforce - Developer Edition"));
		WebElement createNewViewLink = driver.findElement(By.linkText("Create New View"));
		clickOnElement(createNewViewLink, "Create NewView Link");
		waitForPageLoad(60);
		WebElement viewName = driver.findElement(By.name("fname"));
		Random randomNum = new Random();
		String eneteredName = "New View" + randomNum.nextInt(100);
		enterText(viewName, eneteredName, "View Name");
		WebElement viewUniqueName = driver.findElement(By.name("devname"));
		clickOnElement(viewUniqueName, "View UniqueName");
		/// clearing the field as it takes view name and appends it
		viewUniqueName.clear();
		String uniqueName = "My_view" + randomNum.nextInt(100);
		enterText(viewUniqueName, uniqueName, "View Unique Name");
		WebElement saveButton = driver.findElement(By.name("save"));
		clickOnElement(saveButton, "Save Button");
		waitForPageLoad(60);
		WebElement viewDropdown = driver.findElement(By.name("fcf"));
		Select select = new Select(viewDropdown);
		WebElement defaultSelectedOption = select.getFirstSelectedOption();

		Assert.assertEquals(defaultSelectedOption.getText(), eneteredName);
		closeBrowser();
	}

	@Test

	public void editView_12() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		Thread.sleep(2000);
		WebElement accountsOption = driver.findElement(By.linkText("Accounts"));
		clickOnElement(accountsOption, "Accounts Option");
		waitForPageLoad(60);
		driver.switchTo().activeElement();
		WebElement closePopup = driver.findElement(By.linkText("Close"));
		if (closePopup.isDisplayed()) {
			clickOnElement(closePopup, "Close Popup");
		}
		wait.until(ExpectedConditions.titleIs("Accounts: Home ~ Salesforce - Developer Edition"));
		WebElement editLink = driver.findElement(By.linkText("Edit"));
		clickOnElement(editLink, "Edit Link");
		WebElement viewName = driver.findElement(By.name("fname"));
		Random randomNum = new Random();
		/// Editing view name
		String eneteredName = "New View" + randomNum.nextInt(100);
		enterText(viewName, eneteredName, "View Name");

		// selecting field
		WebElement field = driver.findElement(By.id("fcol1"));
		Select selectField = new Select(field);
		selectField.selectByVisibleText("Account Name");
		// selecting operator
		WebElement operator = driver.findElement(By.id("fop1"));
		Select selectOperator = new Select(operator);
		selectOperator.selectByVisibleText("contains");

		// Entering value

		WebElement value = driver.findElement(By.id("fval1"));
		enterText(value, "&", "Value filter field");

		WebElement saveButton = driver.findElement(By.name("save"));
		clickOnElement(saveButton, "Save Button");
		Thread.sleep(2000);
		String expectedTitle = "Accounts ~ Salesforce - Developer Edition";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(expectedTitle, actualTitle);
		closeBrowser();
	}

	@Test

	public void mergeAccount_13() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		Thread.sleep(2000);
		WebElement accountsOption = driver.findElement(By.linkText("Accounts"));
		clickOnElement(accountsOption, "Accounts Option");
		waitForPageLoad(60);
		driver.switchTo().activeElement();
		WebElement closePopup = driver.findElement(By.linkText("Close"));
		if (closePopup.isDisplayed()) {
			clickOnElement(closePopup, "Close Popup");
		}
		wait.until(ExpectedConditions.titleIs("Accounts: Home ~ Salesforce - Developer Edition"));
		WebElement mergeAccLink = driver.findElement(By.linkText("Merge Accounts"));
		clickOnElement(mergeAccLink, "merge AccLink");
		Thread.sleep(4000);
		WebElement searchAcc = driver.findElement(By.id("srch"));
		enterText(searchAcc, "Hello", "Acc Search");
		WebElement searchAccButton = driver.findElement(By.name("srchbutton"));
		clickOnElement(searchAccButton, "search AccButton");
		Thread.sleep(2000);
		WebElement select1 = driver.findElement(By.xpath("//input[@title='Select row 0']"));
		clickOnElement(select1, "1st Checkbox");
		WebElement select2 = driver.findElement(By.xpath("//input[@title='Select row 1']"));
		clickOnElement(select2, "2nd Checkbox");
		WebElement goNextButton = driver.findElement(By.name("goNext"));
		clickOnElement(goNextButton, "go NextButton");
		waitForPageLoad(60);
		WebElement mergeButton = driver.findElement(By.name("save"));
		clickOnElement(mergeButton, "merge Button");
		String actualAlertContent = driver.switchTo().alert().getText();
		String expectedAlertContent = "These records will be merged into one record using the selected values. Merging can't be undone. Proceed with the record merge?";
		Assert.assertEquals(actualAlertContent, expectedAlertContent);
		driver.switchTo().alert().dismiss();
		closeBrowser();
	}

	@Test

	public void accountReport_14() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		Thread.sleep(2000);
		WebElement accountsOption = driver.findElement(By.linkText("Accounts"));
		clickOnElement(accountsOption, "Accounts Option");
		waitForPageLoad(60);
		driver.switchTo().activeElement();
		WebElement closePopup = driver.findElement(By.linkText("Close"));
		if (closePopup.isDisplayed()) {
			clickOnElement(closePopup, "Close Popup");
		}
		wait.until(ExpectedConditions.titleIs("Accounts: Home ~ Salesforce - Developer Edition"));
		WebElement accReportLink = driver.findElement(By.linkText("Accounts with last activity > 30 days"));
		clickOnElement(accReportLink, "acc ReportLink");
		Thread.sleep(4000);
		verifyTittle("Unsaved Report ~ Salesforce - Developer Edition");
		WebElement fromDate = driver.findElement(By.xpath("//img[@id='ext-gen152']"));
		clickOnElement(fromDate, "from Date");
		Thread.sleep(1000);
		WebElement todayFromDate = driver.findElement(By.id("ext-gen276"));
		clickOnElement(todayFromDate, "today FromDate");
		WebElement toDate = driver.findElement(By.xpath("//img[@id='ext-gen154']"));
		clickOnElement(toDate, "toDate");
		Thread.sleep(2000);
		WebElement todayToDate = driver.findElement(By.id("ext-gen292"));
		clickOnElement(todayToDate, "today ToDate");
		WebElement save = driver.findElement(By.id("ext-gen49"));
		clickOnElement(save, "save");
		Thread.sleep(1000);
		WebElement reportName = driver.findElement(By.id("saveReportDlg_reportNameField"));
		wait.until(ExpectedConditions.visibilityOf(reportName));
		Random randomNum = new Random();
		String rName = "PJ" + randomNum.nextInt(100);
		enterText(reportName, rName, "Report Name");
		String rUniName = "PJan" + randomNum.nextInt(100);
		WebElement reportUniqueName = driver.findElement(By.id("saveReportDlg_DeveloperName"));
		clickOnElement(reportUniqueName, "report UniqueName");
		/// clearing the field as it takes view name and appends it
		reportUniqueName.clear();
		enterText(reportUniqueName, rUniName, "Report Unique Name");
		Thread.sleep(2000);
		WebElement saveFinal = driver.findElement(By.id("ext-gen310"));
		clickOnElement(saveFinal, "save Final");
		Thread.sleep(2000);
		String expectedTitle = rName + " ~ Salesforce - Developer Edition";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(expectedTitle, actualTitle);
		closeBrowser();

	}

	@Test

	public void opty_Menu_dropdown_15() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement optyOption = driver.findElement(By.linkText("Opportunities"));
		wait.until(ExpectedConditions.visibilityOf(optyOption));
		wait.until(ExpectedConditions.elementToBeClickable(optyOption));
		clickOnElement(optyOption, "Opportunities Option");
		Thread.sleep(2000);
		driver.switchTo().activeElement();
		WebElement closePopup = driver.findElement(By.linkText("Close"));
		if (closePopup.isDisplayed()) {
			clickOnElement(closePopup, "Close Popup");
		}
		waitForPageLoad(60);
		verifyTittle("Opportunities: Home ~ Salesforce - Developer Edition");

		WebElement viewDropdown = driver.findElement(By.id("fcf"));
		List<WebElement> arrMenu = viewDropdown.findElements(By.tagName("option"));
		List<String> actualDropdown = new ArrayList<String>();
		for (WebElement webEle : arrMenu) {
			actualDropdown.add(webEle.getText());
		}
		System.out.println(actualDropdown);
		List<String> expectedDropdown = new ArrayList<String>(Arrays.asList("All Opportunities", "Closing Next Month",
				"Closing This Month", "My Opportunities", "New Last Week", "New This Week", "Opportunity Pipeline",
				"Private", "Recently Viewed Opportunities", "Won"));
		Assert.assertEquals(actualDropdown, expectedDropdown);
		closeBrowser();
	}

	@Test

	public void opty_CreateNew_16() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement optyOption = driver.findElement(By.linkText("Opportunities"));
		wait.until(ExpectedConditions.visibilityOf(optyOption));
		wait.until(ExpectedConditions.elementToBeClickable(optyOption));
		clickOnElement(optyOption, "Opportunities Option");
		Thread.sleep(2000);
		driver.switchTo().activeElement();
		WebElement closePopup = driver.findElement(By.linkText("Close"));
		if (closePopup.isDisplayed()) {
			clickOnElement(closePopup, "Close Popup");
		}
		waitForPageLoad(60);
		verifyTittle("Opportunities: Home ~ Salesforce - Developer Edition");
		WebElement newButton = driver.findElement(By.name("new"));
		clickOnElement(newButton, "New Button");
		waitForPageLoad(60);
		WebElement optyName = driver.findElement(By.id("opp3"));
		String oName = "New Opp";
		enterText(optyName, oName, "Opty Name");
		WebElement accName = driver.findElement(By.id("opp4"));
		enterText(accName, "hello123", "Acc Name");
		WebElement closeDate = driver.findElement(By.id("opp9"));
		clickOnElement(closeDate, "closeDate");
		Thread.sleep(1000);
		WebElement todayDate = driver.findElement(By.linkText("Today"));
		clickOnElement(todayDate, "todayDate");
		WebElement stage = driver.findElement(By.id("opp11"));
		Select select = new Select(stage);
		select.selectByIndex(2);

		WebElement probability = driver.findElement(By.id("opp12"));
		clearTheField(probability);
		enterText(probability, "10", "Probability");

		WebElement campaignSource = driver.findElement(By.id("opp17"));
		enterText(campaignSource, "DM Campaign to Top Customers - Nov 12-23, 2001", "Campaign Source");

		WebElement finish = driver.findElement(By.name("save"));
		clickOnElement(finish, "finish");

		String expectedTitle = "Opportunity: " + oName + " ~ Salesforce - Developer Edition";
		String actualTitle = getPagetitle();
		Assert.assertEquals(expectedTitle, actualTitle);
		closeBrowser();
	}

	@Test

	public void test_opty_pipeline_17() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement optyOption = driver.findElement(By.linkText("Opportunities"));
		wait.until(ExpectedConditions.visibilityOf(optyOption));
		wait.until(ExpectedConditions.elementToBeClickable(optyOption));
		clickOnElement(optyOption, "Opportunities Option");
		Thread.sleep(2000);
		driver.switchTo().activeElement();
		WebElement closePopup = driver.findElement(By.linkText("Close"));
		if (closePopup.isDisplayed()) {
			clickOnElement(closePopup, "Close Popup");
		}
		waitForPageLoad(60);
		verifyTittle("Opportunities: Home ~ Salesforce - Developer Edition");

		WebElement optyPipe = driver.findElement(By.linkText("Opportunity Pipeline"));
		clickOnElement(optyPipe, "Opportunities Pipe");
		waitForPageLoad(60);
		String expectedTitle = "Opportunity Pipeline ~ Salesforce - Developer Edition";
		String actualTitle = getPagetitle();
		Assert.assertEquals(expectedTitle, actualTitle);
		closeBrowser();

	}

	@Test

	public void test_opty_Stuck_18() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement optyOption = driver.findElement(By.linkText("Opportunities"));
		wait.until(ExpectedConditions.visibilityOf(optyOption));
		wait.until(ExpectedConditions.elementToBeClickable(optyOption));
		clickOnElement(optyOption, "Opportunities Option");
		Thread.sleep(2000);
		driver.switchTo().activeElement();
		WebElement closePopup = driver.findElement(By.linkText("Close"));
		if (closePopup.isDisplayed()) {
			clickOnElement(closePopup, "Close Popup");
		}
		waitForPageLoad(60);
		verifyTittle("Opportunities: Home ~ Salesforce - Developer Edition");

		WebElement optyStuck = driver.findElement(By.linkText("Stuck Opportunities"));
		clickOnElement(optyStuck, "Opportunities Stuck");
		waitForPageLoad(60);
		String expectedTitle = "Stuck Opportunities ~ Salesforce - Developer Edition";
		String actualTitle = getPagetitle();
		Assert.assertEquals(expectedTitle, actualTitle);
		closeBrowser();

	}

	@Test

	public void test_opty_Report_19() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement optyOption = driver.findElement(By.linkText("Opportunities"));
		wait.until(ExpectedConditions.visibilityOf(optyOption));
		wait.until(ExpectedConditions.elementToBeClickable(optyOption));
		clickOnElement(optyOption, "Opportunities Option");
		Thread.sleep(2000);
		driver.switchTo().activeElement();
		WebElement closePopup = driver.findElement(By.linkText("Close"));
		if (closePopup.isDisplayed()) {
			clickOnElement(closePopup, "Close Popup");
		}
		waitForPageLoad(60);
		verifyTittle("Opportunities: Home ~ Salesforce - Developer Edition");

		WebElement interval = driver.findElement(By.id("quarter_q"));
		selectOptionbyIndex(interval, 2);
		WebElement include = driver.findElement(By.id("open"));
		selectOptionbyVisibleText(include, "Open Opportunities");
		WebElement runReport = driver.findElement(By.xpath("//input[@value='Run Report']"));
		clickOnElement(runReport, "run Report");

		waitForPageLoad(60);
		String expectedTitle = "Opportunity Report ~ Salesforce - Developer Edition";
		String actualTitle = getPagetitle();
		Assert.assertEquals(expectedTitle, actualTitle);
		closeBrowser();

	}

	@Test

	public void test_lead_20() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement leadOption = driver.findElement(By.linkText("Leads"));
		wait.until(ExpectedConditions.visibilityOf(leadOption));
		wait.until(ExpectedConditions.elementToBeClickable(leadOption));
		clickOnElement(leadOption, "Lead Option");
		Thread.sleep(4000);
		WebElement closePopup = driver.findElement(By.linkText("Close"));
		if (closePopup.isDisplayed()) {
			clickOnElement(closePopup, "Close Popup");
		}
		String expectedTitle = "Leads: Home ~ Salesforce - Developer Edition";
		String actualTitle = getPagetitle();
		Assert.assertEquals(expectedTitle, actualTitle);
		closeBrowser();

	}

	@Test

	public void test_lead_dropdown_21() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement leadOption = driver.findElement(By.linkText("Leads"));
		wait.until(ExpectedConditions.visibilityOf(leadOption));
		wait.until(ExpectedConditions.elementToBeClickable(leadOption));
		clickOnElement(leadOption, "Lead Option");
		Thread.sleep(4000);
		WebElement closePopup = driver.findElement(By.linkText("Close"));
		if (closePopup.isDisplayed()) {
			clickOnElement(closePopup, "Close Popup");
		}
		verifyTittle("Leads: Home ~ Salesforce - Developer Edition");
		WebElement leadDropdown = driver.findElement(By.id("fcf"));
		List<WebElement> arrMenu = leadDropdown.findElements(By.tagName("option"));
		List<String> actualDropdown = new ArrayList<String>();
		for (WebElement webEle : arrMenu) {
			actualDropdown.add(webEle.getText());
		}
		System.out.println(actualDropdown);
		List<String> expectedDropdown = new ArrayList<String>(Arrays.asList("All Open Leads", "My Unread Leads",
				"Recently Viewed Leads", "Today's Leads", "View - Custom 1", "View - Custom 2"));
		Assert.assertEquals(actualDropdown, expectedDropdown);
		closeBrowser();
	}

	@Test

	public void test_lead_select_logout_relogin_22() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement leadOption = driver.findElement(By.linkText("Leads"));
		wait.until(ExpectedConditions.visibilityOf(leadOption));
		wait.until(ExpectedConditions.elementToBeClickable(leadOption));
		clickOnElement(leadOption, "Lead Option");
		Thread.sleep(4000);
		WebElement closePopup = driver.findElement(By.linkText("Close"));
		if (closePopup.isDisplayed()) {
			clickOnElement(closePopup, "Close Popup");
		}
		verifyTittle("Leads: Home ~ Salesforce - Developer Edition");
		waitForPageLoad(60);
		WebElement leadDropdown = driver.findElement(By.id("fcf"));
		String selectedLead = "My Unread Leads";
		selectOptionbyVisibleText(leadDropdown, selectedLead);

		WebElement userMenuDropdown = driver.findElement(By.id("userNavButton"));
		wait.until(ExpectedConditions.visibilityOf(userMenuDropdown));
		wait.until(ExpectedConditions.elementToBeClickable(userMenuDropdown));
		clickOnElement(userMenuDropdown, "User Menu Dropdown");
		WebElement logout = driver.findElement(By.linkText("Logout"));
		wait.until(ExpectedConditions.visibilityOf(logout));
		wait.until(ExpectedConditions.elementToBeClickable(logout));
		clickOnElement(logout, "Logout");
		wait.until(ExpectedConditions.titleContains("Login | Salesforce"));
		verifyTittle("Login | Salesforce");
		waitForPageLoad(60);
		WebElement reloginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(reloginidElement));
		enterText(reloginidElement, userId, "User Name");
		WebElement rePasswordElement = driver.findElement(By.name("pw"));
		enterText(rePasswordElement, password, "Password");
		WebElement reloginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(reloginButtonElement, "relogin Button");
		waitForPageLoad(60);
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement releadOption = driver.findElement(By.linkText("Leads"));
		wait.until(ExpectedConditions.visibilityOf(releadOption));
		wait.until(ExpectedConditions.elementToBeClickable(releadOption));
		clickOnElement(releadOption, "relead Option");
		Thread.sleep(4000);
		// WebElement reclosePopup = driver.findElement(By.linkText("Close"));
		// clickOnElement(reclosePopup);
		verifyTittle("Leads: Home ~ Salesforce - Developer Edition");
		waitForPageLoad(60);
		WebElement releadDropdown = driver.findElement(By.id("fcf"));
		Select select = new Select(releadDropdown);
		WebElement defaultSelectedOption = select.getFirstSelectedOption();
		Assert.assertEquals(defaultSelectedOption.getText(), selectedLead);
		closeBrowser();

	}

	@Test
	public void test_lead_todayLeads_23() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement leadOption = driver.findElement(By.linkText("Leads"));
		wait.until(ExpectedConditions.visibilityOf(leadOption));
		wait.until(ExpectedConditions.elementToBeClickable(leadOption));
		clickOnElement(leadOption, "Lead Option");
		Thread.sleep(4000);
		WebElement closePopup = driver.findElement(By.linkText("Close"));
		if (closePopup.isDisplayed()) {
			clickOnElement(closePopup, "Close Popup");
		}
		verifyTittle("Leads: Home ~ Salesforce - Developer Edition");
		waitForPageLoad(60);
		WebElement leadDropdown = driver.findElement(By.id("fcf"));
		selectOptionbyVisibleText(leadDropdown, "Today's Leads");

		WebElement go = driver.findElement(By.name("go"));
		if (go.isDisplayed()) {
			clickOnElement(go, "go");
		}
		waitForPageLoad(60);
		String expectedTitle = "Leads ~ Salesforce - Developer Edition";
		String actualTitle = getPagetitle();
		Assert.assertEquals(expectedTitle, actualTitle);
		closeBrowser();
	}

	@Test
	public void test_lead_new_24() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement leadOption = driver.findElement(By.linkText("Leads"));
		wait.until(ExpectedConditions.visibilityOf(leadOption));
		wait.until(ExpectedConditions.elementToBeClickable(leadOption));
		clickOnElement(leadOption, "Lead Option");
		Thread.sleep(4000);
		WebElement closePopup = driver.findElement(By.linkText("Close"));
		if (closePopup.isDisplayed()) {
			clickOnElement(closePopup, "Close Popup");
		}
		verifyTittle("Leads: Home ~ Salesforce - Developer Edition");
		waitForPageLoad(60);
		WebElement newButton = driver.findElement(By.name("new"));
		clickOnElement(newButton, "New Button");
		verifyTittle("Lead Edit: New Lead ~ Salesforce - Developer Edition");
		String lName = "ABCD";
		WebElement lastName = driver.findElement(By.name("name_lastlea2"));
		enterText(lastName, lName, "LastName");
		WebElement companyName = driver.findElement(By.name("lea3"));
		enterText(companyName, "ABCD", "Company Name");
		WebElement saveButton = driver.findElement(By.name("save"));
		clickOnElement(saveButton, "Save Button");
		String expectedTitle = "Lead: " + lName + " ~ Salesforce - Developer Edition";
		String actualTitle = getPagetitle();
		Assert.assertEquals(expectedTitle, actualTitle);
		closeBrowser();

	}

	@Test
	public void test_contacts_new_25() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement contactsOption = driver.findElement(By.linkText("Contacts"));
		wait.until(ExpectedConditions.visibilityOf(contactsOption));
		wait.until(ExpectedConditions.elementToBeClickable(contactsOption));
		clickOnElement(contactsOption, "Contacts Option");
		Thread.sleep(4000);
		WebElement closePopup = driver.findElement(By.linkText("Close"));
		if (closePopup.isDisplayed()) {
			clickOnElement(closePopup, "Close Popup");
		}
		verifyTittle("Contacts: Home ~ Salesforce - Developer Edition");
		waitForPageLoad(60);
		WebElement newButton = driver.findElement(By.name("new"));
		clickOnElement(newButton, "New Button");
		verifyTittle("Contact Edit: New Contact ~ Salesforce - Developer Edition");
		String lName = "ABCD";
		WebElement lastName = driver.findElement(By.name("name_lastcon2"));
		enterText(lastName, lName, "LastName");
		WebElement companyName = driver.findElement(By.name("con4"));
		enterText(companyName, "hello123", "Company Name");
		WebElement saveButton = driver.findElement(By.name("save"));
		clickOnElement(saveButton, "Save Button");
		String expectedTitle = "Contact: " + lName + " ~ Salesforce - Developer Edition";
		String actualTitle = getPagetitle();
		Assert.assertEquals(expectedTitle, actualTitle);
		closeBrowser();

	}

	@Test

	public void test_contacts_newView_26() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement contactsOption = driver.findElement(By.linkText("Contacts"));
		wait.until(ExpectedConditions.visibilityOf(contactsOption));
		wait.until(ExpectedConditions.elementToBeClickable(contactsOption));
		clickOnElement(contactsOption, "Contacts Option");
		Thread.sleep(4000);
		WebElement closePopup = driver.findElement(By.linkText("Close"));
		if (closePopup.isDisplayed()) {
			clickOnElement(closePopup, "Close Popup");
		}
		verifyTittle("Contacts: Home ~ Salesforce - Developer Edition");
		waitForPageLoad(60);
		WebElement viewLink = driver.findElement(By.linkText("Create New View"));
		clickOnElement(viewLink, "View link");
		verifyTittle("Contacts: Create New View ~ Salesforce - Developer Edition");
		Random randomNum = new Random();
		String eneteredName = "BetterView" + randomNum.nextInt(100);
		WebElement contactViewName = driver.findElement(By.name("fname"));
		enterText(contactViewName, eneteredName, "contact View Name");
		WebElement viewUniqueName = driver.findElement(By.name("devname"));
		clickOnElement(viewUniqueName, "View UniqueName");
		/// clearing the field as it takes view name and appends it
		viewUniqueName.clear();
		String uniqueName = "My_Better_view" + randomNum.nextInt(100);

		WebElement contactUniqueViewName = driver.findElement(By.name("devname"));
		enterText(contactUniqueViewName, uniqueName, "contact View Name");
		WebElement saveButton = driver.findElement(By.name("save"));
		clickOnElement(saveButton, "Save Button");
		Thread.sleep(2000);
		String expectedTitle = "Contacts ~ Salesforce - Developer Edition";
		String actualTitle = getPagetitle();
		Assert.assertEquals(expectedTitle, actualTitle);
		closeBrowser();

	}

	@Test

	public void test_contacts_recentlyCreated_27() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement contactsOption = driver.findElement(By.linkText("Contacts"));
		wait.until(ExpectedConditions.visibilityOf(contactsOption));
		wait.until(ExpectedConditions.elementToBeClickable(contactsOption));
		clickOnElement(contactsOption, "Contacts Option");
		Thread.sleep(4000);
		WebElement closePopup = driver.findElement(By.linkText("Close"));
		if (closePopup.isDisplayed()) {
			clickOnElement(closePopup, "Close Popup");
		}
		verifyTittle("Contacts: Home ~ Salesforce - Developer Edition");
		waitForPageLoad(60);
		WebElement contactDropdown = driver.findElement(By.id("hotlist_mode"));
		selectOptionbyVisibleText(contactDropdown, "Recently Created");
		Select select = new Select(contactDropdown);
		WebElement defaultSelectedOption = select.getFirstSelectedOption();
		Assert.assertEquals(defaultSelectedOption.getText(), "Recently Created");
		closeBrowser();

	}

	@Test

	public void test_Mycontacts_28() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement contactsOption = driver.findElement(By.linkText("Contacts"));
		wait.until(ExpectedConditions.visibilityOf(contactsOption));
		wait.until(ExpectedConditions.elementToBeClickable(contactsOption));
		clickOnElement(contactsOption, "Contacts Option");
		Thread.sleep(4000);
		WebElement closePopup = driver.findElement(By.linkText("Close"));
		if (closePopup.isDisplayed()) {
			clickOnElement(closePopup, "Close Popup");
		}
		verifyTittle("Contacts: Home ~ Salesforce - Developer Edition");
		waitForPageLoad(60);
		WebElement contactDropdown = driver.findElement(By.id("fcf"));
		selectOptionbyVisibleText(contactDropdown, "My Contacts");
		WebElement go = driver.findElement(By.name("go"));
		if (go.isDisplayed()) {
			clickOnElement(go, "go");
		}
		waitForPageLoad(60);
		String expectedTitle = "Contacts ~ Salesforce - Developer Edition";
		String actualTitle = getPagetitle();
		Assert.assertEquals(expectedTitle, actualTitle);
		closeBrowser();

	}

	@Test
	public void test_contacts_recentContact_29() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement contactsOption = driver.findElement(By.linkText("Contacts"));
		wait.until(ExpectedConditions.visibilityOf(contactsOption));
		wait.until(ExpectedConditions.elementToBeClickable(contactsOption));
		clickOnElement(contactsOption, "Contacts Option");
		Thread.sleep(4000);
		WebElement closePopup = driver.findElement(By.linkText("Close"));
		if (closePopup.isDisplayed()) {
			clickOnElement(closePopup, "Close Popup");
		}
		verifyTittle("Contacts: Home ~ Salesforce - Developer Edition");
		waitForPageLoad(60);
		String newlyCreatedContact = "ABCD";
		WebElement viewLink = driver.findElement(By.linkText(newlyCreatedContact));
		waitForPageLoad(60);
		clickOnElement(viewLink, "View link");
		String expectedtitle = "Contact: " + newlyCreatedContact + " ~ Salesforce - Developer Edition";
		String actualTitle = getPagetitle();
		Assert.assertEquals(expectedtitle, actualTitle);
		closeBrowser();
	}

	@Test
	public void test_contacts_newView_Error_30() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement contactsOption = driver.findElement(By.linkText("Contacts"));
		wait.until(ExpectedConditions.visibilityOf(contactsOption));
		wait.until(ExpectedConditions.elementToBeClickable(contactsOption));
		clickOnElement(contactsOption, "Contacts Option");
		Thread.sleep(4000);
		WebElement closePopup = driver.findElement(By.linkText("Close"));
		if (closePopup.isDisplayed()) {
			clickOnElement(closePopup, "Close Popup");
		}
		verifyTittle("Contacts: Home ~ Salesforce - Developer Edition");
		waitForPageLoad(60);
		WebElement viewLink = driver.findElement(By.linkText("Create New View"));
		clickOnElement(viewLink, "View link");
		verifyTittle("Contacts: Create New View ~ Salesforce - Developer Edition");
		WebElement contactUniqueViewName = driver.findElement(By.name("devname"));
		enterText(contactUniqueViewName, "asdf", "contact View Name");
		WebElement saveButton = driver.findElement(By.name("save"));
		clickOnElement(saveButton, "Save Button");
		Thread.sleep(600);
		String expectedErrMsg = "Error: You must enter a value";
		WebElement errorMsg = driver.findElement(By.className("errorMsg"));
		String actualErrMsg = errorMsg.getText();
		Assert.assertEquals(expectedErrMsg, actualErrMsg);
		closeBrowser();

	}

	@Test

	public void test_contacts_newView_31() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement contactsOption = driver.findElement(By.linkText("Contacts"));
		wait.until(ExpectedConditions.visibilityOf(contactsOption));
		wait.until(ExpectedConditions.elementToBeClickable(contactsOption));
		clickOnElement(contactsOption, "Contacts Option");
		Thread.sleep(4000);
		WebElement closePopup = driver.findElement(By.linkText("Close"));
		if (closePopup.isDisplayed()) {
			clickOnElement(closePopup, "Close Popup");
		}
		verifyTittle("Contacts: Home ~ Salesforce - Developer Edition");
		waitForPageLoad(60);
		WebElement viewLink = driver.findElement(By.linkText("Create New View"));
		clickOnElement(viewLink, "View link");
		verifyTittle("Contacts: Create New View ~ Salesforce - Developer Edition");
		WebElement contactViewName = driver.findElement(By.name("fname"));
		String viewName = "New View";
		enterText(contactViewName, viewName, "contact View Name");
		WebElement contactUniqueViewName = driver.findElement(By.name("devname"));
		String viewUnqName = "New Unq View";
		enterText(contactUniqueViewName, viewUnqName, "contact View Name");
		WebElement cancelButton = driver.findElement(By.name("cancel"));
		clickOnElement(cancelButton, "cancel Button");
		Thread.sleep(2000);
		WebElement contactDropdown = driver.findElement(By.id("fcf"));
		ArrayList<String> viewList = new ArrayList<String>(Arrays.asList(contactDropdown.getText()));
		if (!viewList.contains(viewName)) {
			System.out.println("Pass : New view is not created without saving");
		} else
			System.out.println("Fail : New view is created without saving");

	}

	@Test
	public void test_contacts_newView_32() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement contactsOption = driver.findElement(By.linkText("Contacts"));
		wait.until(ExpectedConditions.visibilityOf(contactsOption));
		wait.until(ExpectedConditions.elementToBeClickable(contactsOption));
		clickOnElement(contactsOption, "Contacts Option");
		Thread.sleep(4000);
		WebElement closePopup = driver.findElement(By.linkText("Close"));
		if (closePopup.isDisplayed()) {
			clickOnElement(closePopup, "Close Popup");
		}
		WebElement newButton = driver.findElement(By.name("new"));
		clickOnElement(newButton, "New Button");
		wait.until(ExpectedConditions.titleIs("Contact Edit: New Contact ~ Salesforce - Developer Edition"));
		WebElement lastName = driver.findElement(By.id("name_lastcon2"));
		String eneteredName = "Indian";
		enterText(lastName, eneteredName, "Last name");
		WebElement accountName = driver.findElement(By.id("con4"));
		String eneteredAccName = "Global Media";
		enterText(accountName, eneteredAccName, "Account name");

		WebElement saveNewButton = driver.findElement(By.name("save_new"));
		clickOnElement(saveNewButton, "save NewButton");
		String expectedTitle = "Contact Edit: New Contact ~ Salesforce - Developer Edition";
		String actualTitle = getPagetitle();
		Assert.assertEquals(expectedTitle, actualTitle);
		closeBrowser();

	}

	@Test

	public void random_home_FN_LN_33() throws InterruptedException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement hometab = driver.findElement(By.linkText("Home"));
		clickOnElement(hometab, "Home tab");
		Thread.sleep(4000);
		WebElement closePopup = driver.findElement(By.linkText("Close"));
		if (closePopup.isDisplayed()) {
			clickOnElement(closePopup, "Close Popup");
		}
		String firstNameLastName = "P Jan";
		WebElement fNLn = driver.findElement(By.linkText(firstNameLastName));
		clickOnElement(fNLn, "FirstName LastName");
		String expectedTitle = "User: " + firstNameLastName + " ~ Salesforce - Developer Edition";
		String actualTitle = getPagetitle();
		Assert.assertEquals(expectedTitle, actualTitle);
		closeBrowser();

	}

	@Test
	public void random_Menu_MyProfile_34() throws InterruptedException, AWTException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement userMenuDropdown = driver.findElement(By.id("userNavButton"));
		wait.until(ExpectedConditions.visibilityOf(userMenuDropdown));
		wait.until(ExpectedConditions.elementToBeClickable(userMenuDropdown));
		clickOnElement(userMenuDropdown, "User Menu Dropdown");
		WebElement myProfile = driver.findElement(By.linkText("My Profile"));
		clickOnElement(myProfile, "My Profile");
		wait.until(ExpectedConditions.titleContains("User: P Jan ~ Salesforce - Developer Edition"));
		verifyTittle("User: P Jan ~ Salesforce - Developer Edition");
		Thread.sleep(3000);
		WebElement editProfile = driver.findElement(By.xpath("//*[contains(text(),'Contact')]//a"));
		wait.until(ExpectedConditions.elementToBeClickable(editProfile));
		Thread.sleep(4000);
		clickOnElement(editProfile, "Edit Profile");
		Thread.sleep(4000);
		driver.switchTo().frame("contactInfoContentId");
		Thread.sleep(3000);
		WebElement about = driver.findElement(By.xpath("//*[@id=\"aboutTab\"]/a"));
		clickOnElement(about, "about");
		WebElement lName = driver.findElement(By.id("lastName"));
		String updatedLastName = "Jandh";
		enterText(lName, updatedLastName, "Last Name");
		WebElement save = driver.findElement(By.xpath("//*[@id=\"TabPanel\"]/div/div[2]/form/div/input[1]"));
		clickOnElement(save, "save");
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		WebElement updatedName = driver.findElement(By.id("tailBreadcrumbNode"));
		String name = getText(updatedName);
		if (name.equalsIgnoreCase("P " + updatedLastName + " ")) {
			System.out.println("Pass :Last Name is updated");
		} else {
			System.out.println("Fail :Last Name is not updated ");
		}
		WebElement reUserMenuDropdown = driver.findElement(By.id("userNavButton"));
		String nameOnMenu = getText(reUserMenuDropdown);
		if (nameOnMenu.equalsIgnoreCase("P " + updatedLastName + " ")) {
			System.out.println("Pass :Last Name on menu is updated");
		} else {
			System.out.println("Fail :Last Name on menu is not updated ");
		}
		String updatedTitle = getPagetitle();
		if (updatedTitle.contains("P " + updatedLastName + " ")) {
			System.out.println("Pass :Last Name on page is updated");
		} else {
			System.out.println("Fail :Last Name on page is not updated ");
		}
		/// changing back the name
		Thread.sleep(2000);
		WebElement reEditProfile = driver.findElement(By.xpath("//*[contains(text(),'Contact')]//a"));
		clickOnElement(reEditProfile, "Edit Profile");
		Thread.sleep(4000);
		driver.switchTo().frame("contactInfoContentId");
		Thread.sleep(3000);
		WebElement reAbout = driver.findElement(By.xpath("//*[@id=\"aboutTab\"]/a"));
		clickOnElement(reAbout, "about");
		WebElement reLName = driver.findElement(By.id("lastName"));
		String updatedLastNameNew = "Jan";
		enterText(reLName, updatedLastNameNew, "Last Name");
		WebElement saveAll = driver.findElement(By.xpath("//*[@id=\"TabPanel\"]/div/div[2]/form/div/input[1]"));
		clickOnElement(saveAll, "saveAll");
		driver.switchTo().defaultContent();

	}

	@Test
	public void random_AllTabs_35() throws InterruptedException, AWTException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement allTabs = driver.findElement(By.xpath("//img[@title='All Tabs']"));
		clickOnElement(allTabs, "allTabs Button");
		Thread.sleep(4000);
		verifyTittle("All Tabs ~ Salesforce - Developer Edition");
		WebElement customizeTab = driver.findElement(By.name("customize"));
		clickOnElement(customizeTab, "customizeTab Button");
		Thread.sleep(4000);
		verifyTittle("Customize My Tabs ~ Salesforce - Developer Edition");
		Thread.sleep(4000);
		WebElement selectedTab = driver.findElement(By.id("duel_select_1"));
		String tabToRemove = "Subscriptions";
		selectOptionbyVisibleText(selectedTab, tabToRemove);
		WebElement remove = driver.findElement(By.xpath("//img[@title='Remove']"));
		clickOnElement(remove, "remove Button");
		WebElement save = driver.findElement(By.name("save"));
		clickOnElement(save, "save Button");
		WebElement userMenuDropdown = driver.findElement(By.id("userNavButton"));
		wait.until(ExpectedConditions.visibilityOf(userMenuDropdown));
		wait.until(ExpectedConditions.elementToBeClickable(userMenuDropdown));
		clickOnElement(userMenuDropdown, "User Menu Dropdown");
		WebElement logout = driver.findElement(By.linkText("Logout"));
		wait.until(ExpectedConditions.visibilityOf(logout));
		wait.until(ExpectedConditions.elementToBeClickable(logout));
		clickOnElement(logout, "Logout");
		wait.until(ExpectedConditions.titleContains("Login | Salesforce"));
		verifyTittle("Login | Salesforce");
		WebElement reLoginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(reLoginidElement));
		enterText(reLoginidElement, userId, "User Name");
		WebElement rePasswordElement = driver.findElement(By.name("pw"));
		enterText(rePasswordElement, password, "Password");
		WebElement reLoginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(reLoginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement tabBar = driver.findElement(By.id("tabBar"));
		ArrayList<String> viewList = new ArrayList<String>(Arrays.asList(tabBar.getText()));
		System.out.println(viewList);
		Assert.assertTrue(!viewList.contains(tabToRemove));
		WebElement reAllTabs = driver.findElement(By.xpath("//img[@title='All Tabs']"));
		clickOnElement(reAllTabs, "allTabs Button");
		Thread.sleep(4000);
		verifyTittle("All Tabs ~ Salesforce - Developer Edition");
		WebElement reCustomizeTab = driver.findElement(By.name("customize"));
		clickOnElement(reCustomizeTab, "customizeTab Button");
		Thread.sleep(4000);
		verifyTittle("Customize My Tabs ~ Salesforce - Developer Edition");
		Thread.sleep(4000);
		WebElement availableTab = driver.findElement(By.xpath("//select[@id='duel_select_0']"));
		Select selectAvailableTab = new Select(availableTab);
		selectAvailableTab.selectByVisibleText(tabToRemove);

		Thread.sleep(2000);
		WebElement addButton = driver.findElement(By.xpath("//a/img[@title='Add']"));
		clickOnElement(addButton, "Add Button");
		Thread.sleep(2000);

		WebElement saveButton = driver.findElement(By.name("save"));
		clickOnElement(saveButton, "Save Button");

	}

	@Test

	public void random_home_calendar_36() throws InterruptedException, AWTException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement home = driver.findElement(By.linkText("Home"));
		wait.until(ExpectedConditions.visibilityOf(home));
		wait.until(ExpectedConditions.elementToBeClickable(home));
		clickOnElement(home, "home");
		Thread.sleep(4000);
		WebElement closePopup = driver.findElement(By.linkText("Close"));
		if (closePopup.isDisplayed()) {
			clickOnElement(closePopup, "Close Popup");
		}
		Thread.sleep(2000);
		WebElement dayLink = driver.findElement(By.xpath("//a[contains(text(),'2023')]"));
		clickOnElement(dayLink, "Today Link");
		Thread.sleep(2000);
		WebElement timeSlot = driver.findElement(By.linkText("8:00 PM"));
		clickOnElement(timeSlot, "timeSlot Link");
		WebElement subject = driver.findElement(By.xpath("//img[@title='Subject Combo (New Window)']"));
		String baseWindowHandle = driver.getWindowHandle();
		clickOnElement(subject, "subject Link");
		Thread.sleep(4000);
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String handle : allWindowHandles) {
			if (!baseWindowHandle.equals(handle)) {
				switchToWindow(handle);
				break;
			}
		}

		WebElement other = driver.findElement(By.linkText("Other"));
		clickOnElement(other, "other Link");
		// Thread.sleep(1000);
		switchToWindow(baseWindowHandle);
		WebElement saveButton = driver.findElement(By.name("save"));
		clickOnElement(saveButton, "save Button");
		WebElement otherSaved = driver.findElement(By.xpath(" //a[@title='Busy - Other']"));
		Assert.assertTrue(otherSaved.isDisplayed());
		closeAllBrowsers();
	}

	@Test

	public void random_home_calendar_Details_37() throws InterruptedException, AWTException {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		verifyTittle("Login | Salesforce");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement loginidElement = driver.findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(loginidElement));
		enterText(loginidElement, userId, "User Name");
		WebElement passwordElement = driver.findElement(By.name("pw"));
		enterText(passwordElement, password, "Password");
		WebElement loginButtonElement = driver.findElement(By.name("Login"));
		clickOnElement(loginButtonElement, "login Button");
		verifyTittle("Home Page ~ Salesforce - Developer Edition");
		WebElement home = driver.findElement(By.linkText("Home"));
		wait.until(ExpectedConditions.visibilityOf(home));
		wait.until(ExpectedConditions.elementToBeClickable(home));
		clickOnElement(home, "home");
		Thread.sleep(4000);
		WebElement closePopup = driver.findElement(By.linkText("Close"));
		if (closePopup.isDisplayed()) {
			clickOnElement(closePopup, "Close Popup");
		}
		Thread.sleep(2000);
		WebElement dayLink = driver.findElement(By.xpath("//a[contains(text(),'2023')]"));
		clickOnElement(dayLink, "Today Link");
		Thread.sleep(2000);
		WebElement timeSlot = driver.findElement(By.linkText("4:00 PM"));
		clickOnElement(timeSlot, "timeSlot Link");
		WebElement subject = driver.findElement(By.xpath("//img[@title='Subject Combo (New Window)']"));
		String baseWindowHandle = driver.getWindowHandle();
		clickOnElement(subject, "subject Link");
		Thread.sleep(4000);
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String handle : allWindowHandles) {
			if (!baseWindowHandle.equals(handle)) {
				switchToWindow(handle);
				break;
			}
		}

		WebElement other = driver.findElement(By.linkText("Other"));
		clickOnElement(other, "other Link");
		Thread.sleep(4000);
		switchToWindow(baseWindowHandle);
		WebElement endTimeTextbox = driver.findElement(By.id("EndDateTime_time"));
		clickOnElement(endTimeTextbox, "end Time ");
		WebElement endTime = driver.findElement(By.xpath("//div[@id='timePickerItem_38']"));
		clickOnElement(endTime, "end Time ");
		WebElement isRecurrence = driver.findElement(By.id("IsRecurrence"));
		clickOnElement(isRecurrence, "isRecurrence checkbox ");
		Thread.sleep(1000);
		WebElement weeklyFreq = driver.findElement(By.id("rectypeftw"));
		clickOnElement(weeklyFreq, "weeklyFreq radio button");
		int noOfDays = 14; // i.e two weeks
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		System.out.println(df.format(calendar.getTime()));

		WebElement enddate = driver.findElement(By.id("RecurrenceEndDateOnly"));
		clickOnElement(enddate, "enddate");
		enterText(enddate, df.format(calendar.getTime()), "enddate");

		WebElement saveButton = driver.findElement(By.name("save"));
		clickOnElement(saveButton, "save Button");

		WebElement otherSaved = driver.findElement(By.xpath(" //a[@title='Busy - Other']"));
		Assert.assertTrue(otherSaved.isDisplayed());
		closeAllBrowsers();
	}
}
