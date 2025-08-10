package BaseTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import DriverUtils.DriverFactory;
import extentReporter.ExtentReportManager;
import extentReporter.ExtentReportNG;
import utils.ConfigReader;

public class BaseTest {

    protected static WebDriver driver;
	
	   @BeforeTest
	    public void setup() {
	        driver = DriverFactory.getDriver(); // This will initialize driver
	    }

	
    @BeforeSuite
    public void initializeSuite() {
        System.out.println("Initializing Suite...");
        ConfigReader.loadConfig();
        ExtentReportNG.getExtentReports();  
    }


	@AfterMethod
	public void tearDown() {
		DriverFactory.quitDriver();
	}

	@AfterSuite
	public void ExReports() {
		ExtentReportManager.flushReports();

	}

}