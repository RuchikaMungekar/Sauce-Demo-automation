package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
	public static WebElement waitForElementVisible(WebDriver driver, By locator, int timeout) {
		return new WebDriverWait(driver, Duration.ofSeconds(timeout))
				.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static WebElement waitForElementClickable(WebDriver driver, By locator, int timeout) {
		return new WebDriverWait(driver, Duration.ofSeconds(timeout))
				.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public static Boolean waitForURLload(WebDriver driver,String url, int timeout) {
		return new WebDriverWait(driver, Duration.ofSeconds(timeout))
				.until(ExpectedConditions.urlContains(url));
	}
	public static void waitForPageLoad(WebDriver driver, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until((ExpectedCondition<Boolean>) webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));
	}
}
