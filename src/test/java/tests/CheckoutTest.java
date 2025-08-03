package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import DriverUtils.DriverFactory;
import dataProviderutility.CheckoutDataProvider;
import dataProviderutility.LoginDataProvider;
import extentReporter.ExtentReportManager;
import pages.CheckOutPage;
import utils.ConfigReader;
import utils.WaitUtils;

public class CheckoutTest {
	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		driver = DriverFactory.getDriver();
		driver.get(ConfigReader.get("baseUrl") + "inventory.html");
	}

	@Test(dataProvider = "CheckoutDetails", dataProviderClass = CheckoutDataProvider.class)
	public void Check_outTest(String firstName, String lastName, String Zipcode) throws InterruptedException {
		CheckOutPage CheckOut = new CheckOutPage(driver);
//		HomePage products = new HomePage(driver);

		CheckOut.opencart();
		System.err.println("open cart");
		CheckOut.cartCheckout();
		CheckOut.filldata(firstName, lastName, Zipcode);
		CheckOut.cartContinuet();
		CheckOut.cartFinish();
	}

	@AfterSuite
	public void tearDown() {
		ExtentReportManager.flushReports();
//		DriverUtils.DriverFactory.quitDriver();
	}

}
