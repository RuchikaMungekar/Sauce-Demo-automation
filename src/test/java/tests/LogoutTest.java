package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import DriverUtils.DriverFactory;
import dataProviderutility.LoginDataProvider;
import extentReporter.ExtentReportManager;
import pages.LoginPage;
import pages.logOutPage;
import utils.ConfigReader;

public class LogoutTest {
	WebDriver driver;

	@BeforeMethod
	public void setUp(Object[] testData) {
//		ExtentReportManager.createTest(testData[2].toString());
		driver = DriverFactory.getDriver();
		driver.get(ConfigReader.get("baseUrl"));

	}

	@Test(dataProvider = "loginData", dataProviderClass = LoginDataProvider.class)
	public void LogoutTests(String username, String password, String TestName) {
		ExtentReportManager.createTest("logoutFor " + TestName);
		LoginPage login = new LoginPage(driver);
		login.login(username, password);

		try {
			if (login.isLoginSuccessful()) {
				logOutPage logout = new logOutPage(driver);
				logout.logOut();
				ExtentReportManager.logPass(TestName);
			} else {
				System.out.println("login Failed for Logout Test -> " + username);
				ExtentReportManager.logFail("could not login for "+ TestName + " Where userName is" + username);

			}
		} catch (Exception e) {
			System.out.println("logout failed" + e);
			ExtentReportManager.logFail(TestName + username + e);

		}

	}

	@AfterSuite
	public void tearDown() {
		ExtentReportManager.flushReports();
		DriverUtils.DriverFactory.quitDriver();
	}

}
