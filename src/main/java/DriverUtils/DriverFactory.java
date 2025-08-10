package DriverUtils;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

import extentReporter.ExtentReportManager;
import utils.ConfigReader;

public class DriverFactory {
	private static WebDriver driver;
//	public static final String BaseUrl = "https://www.saucedemo.com/";

	@BeforeTest
	public static WebDriver getDriver() {
		if (driver == null) {
			String browser = ConfigReader.get("browser");
			switch (browser.toLowerCase()) {
			case "chrome":
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-blink-features=AutomationControlled");
				options.addArguments("--disable-password-manager-reauthentication");
				options.addArguments(
						"--disable-features=AutofillServerCommunication,PasswordManagerEnabled,EnablePasswordCheck");
				options.addArguments("--disable-save-password-bubble"); // Prevent save password popup
				options.addArguments("--incognito"); // Use incognito to avoid saved logins
//				options.addArguments("user-data-dir=/tmp/temporary-profile"); // Temporary profile with no Google account
				options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
				options.setExperimentalOption("useAutomationExtension", false);
				driver = new ChromeDriver(options);
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			default:
				throw new RuntimeException("Browser not supported: " + browser);
			}
			driver.manage().window().maximize();
		}
		return driver;
	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	public static boolean isElementPresent(By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	@AfterSuite
	public void tearDown() {
		ExtentReportManager.flushReports();
		DriverUtils.DriverFactory.quitDriver();
	}
}
