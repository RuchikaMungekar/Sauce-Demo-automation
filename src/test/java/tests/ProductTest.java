package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import DriverUtils.DriverFactory;
import extentReporter.ExtentReportManager;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;

public class ProductTest {
	WebDriver driver;

	@BeforeMethod
	public void setUp(Object[] testData) {
//		ExtentReportManager.createTest(testData[2].toString());
		driver = DriverFactory.getDriver();
		driver.get(ConfigReader.get("baseUrl"));

	}

	@Test
	public void AddProductTocart() {
		LoginPage login = new LoginPage(driver);
		HomePage products = new HomePage(driver);
		ExtentReportManager.createTest("AddToCart " );
		// actions
		login.login("standard_user", "secret_sauce");
		products.addAllProductsToCart();
		products.removeProdHome();
	}
	

	@AfterSuite
	public void tearDown() {
		ExtentReportManager.flushReports();
//		DriverFactory.quitDriver();
	}

}
