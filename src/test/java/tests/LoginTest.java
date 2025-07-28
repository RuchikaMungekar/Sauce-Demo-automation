package tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import DriverUtils.DriverFactory;
import dataProviderutility.LoginDataProvider;
import extentReporter.ExtentReportManager;
import pages.LoginPage;
import pages.logOutPage;
import utils.ConfigReader;
import utils.ScreenShotUtil;

public class LoginTest {
	WebDriver driver;

	@BeforeMethod
	public void setUp(Object[] testData) {
		ExtentReportManager.createTest(testData[2].toString());
		driver = DriverFactory.getDriver();
		driver.get(ConfigReader.get("baseUrl"));

	}

	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\Credentials.xlsx";
	String sheetName = "sheet1";

	@Test(dataProvider = "loginData", dataProviderClass = LoginDataProvider.class)
	public void loginTest(String username, String password, String TestName) throws IOException {
		LoginPage loginData = new LoginPage(driver);

		ExtentReportManager.logInfo(username);
//		System.out.println("Username: " + username + ", Password: " + password + "testName" + TestName);
		loginData.login(username, password);
		switch (username) {
		case "standard_user":
		case "problem_user":
		case "performance_glitch_user":
		case "visual_user":
		case "error_user":
			Assert.assertTrue(loginData.isLoginSuccessful(), "Expected successful login for user: " + username);
			ExtentReportManager.logPass("Login successful for user: " + username);

			if (loginData.isLoginSuccessful()) {
				logOutPage logout = new logOutPage(driver);
				try {
					logout.logOut();
				} catch (Exception e) {
					System.out.println("not able to logout" + e);
					ExtentReportManager.logFail("exception" + TestName + e);
				}
			}
			break;

		case "locked_out_user":
			Assert.assertFalse(loginData.isLoginSuccessful(), "[FAILED] Expected login failure for user: " + username);
			ExtentReportManager.logFail("Login failed for user: " + TestName);
			ScreenShotUtil.takeScreenshot(driver, TestName);
			break;
		default:
			ScreenShotUtil.takeScreenshot(driver, TestName);
			System.out.print("Invalid username" + username + TestName);
			ExtentReportManager.logInfo("Invalid username/password" + username);
		}
	}

	@AfterSuite
	public void tearDown() {
		ExtentReportManager.flushReports();
		DriverUtils.DriverFactory.quitDriver();
	}

}
