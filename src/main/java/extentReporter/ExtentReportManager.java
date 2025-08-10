package extentReporter;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentReportManager {
	private static ExtentReports extent = ExtentReportNG.getExtentReports();
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();


	public static void createTest(String testName) {
		System.out.println("Test created...");
		ExtentTest test = extent.createTest(testName);
		extentTest.set(test);
	}

	public static void logPass(String message) {
		ExtentTest test = getTest();
		if (test != null) {
			test.pass(message);
		} else {
			System.err.println("ExtentTest is null! Did you forget to call createTest()?");
		}
	}

	public static void logFail(String message) {
		ExtentTest test = getTest();
		if (test != null) {
			test.fail(message);
		} else {
			System.err.println("ExtentTest is null! Did you forget to call createTest()?");
		}
	}

	public static void logInfo(String message) {
		ExtentTest test = getTest();
		if (test != null) {
			test.info(message);
		} else {
			System.err.println("ExtentTest is null! Did you forget to call LogInfo()?");
		}

	}

	@AfterSuite
	public static void flushReports() {
		if (extent != null) {
			extent.flush();
			System.out.println("Extent report flushed to disk.");
		}
	}

	public static ExtentTest getTest() {
		return extentTest.get();
	}

	@AfterMethod
	public static void removeTest() {
	    extentTest.remove();
	}
}
