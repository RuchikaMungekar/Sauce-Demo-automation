package extentReporter;

import java.io.File;

import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

	private static ExtentReports extent;

	@BeforeSuite
	public static ExtentReports getExtentReports() {
		if (extent == null) {
			// Set report directory and path
			String reportDir = System.getProperty("user.dir") + File.separator + "reports";
			File reportFolder = new File(reportDir);

			if (!reportFolder.exists()) {
				reportFolder.mkdirs();
			}

			// Check write permission
			if (!reportFolder.canWrite()) {
				System.err.println("❌ Cannot write to report directory: " + reportFolder.getAbsolutePath());
			} else {
				System.out.println("✅ Can write to report directory: " + reportFolder.getAbsolutePath());
			}

			String path = reportDir + File.separator + "index.html";
			System.out.println("Report will be generated at: " + path);

			// Setup ExtentSparkReporter
			ExtentSparkReporter spark = new ExtentSparkReporter(path);
			spark.config().setReportName("Sauce Demo Automation Report");
			spark.config().setDocumentTitle("Extent Report");

			extent = new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("Tester", "Ruchika Mungekar");
		}

		return extent;
	}
}
