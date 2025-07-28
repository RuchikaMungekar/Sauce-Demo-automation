package utils;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenShotUtil {
	public static void takeScreenshot(WebDriver driver, String TestName) {
		try {
			System.out.println("Taking screenshot");
			TakesScreenshot ts = (TakesScreenshot) driver;
			File outputDir = new File(System.getProperty("user.dir") + "test-output/ErrorScreenshot");
			if (outputDir.exists()) {
				System.out.println("existing");
			} else {
				outputDir.mkdirs();
			}

			String ScreenshotFileName = outputDir.getAbsolutePath() + File.separator + TestName + ".png";

			FileHandler.copy(ts.getScreenshotAs(OutputType.FILE), new File(ScreenshotFileName));

			System.out.println("saving screenshot" + ScreenshotFileName);
		} catch (Exception e) {
			System.out.println("Unable to capture screenshot --> ");
		}
	}
}
