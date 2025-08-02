package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.WaitUtils;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver DriverName) {
		this.driver = DriverName;
	}

	private By userName = By.id("user-name");
	private By PassWord = By.id("password");
	private By loginBtn = By.className("submit-button");

	public void Enter_username(String username) {
		WaitUtils.waitForElementVisible(driver, userName, 10);
		if (username != null) {
			driver.findElement(userName).sendKeys(username);
		} else {
			System.out.println("username is null");
		}
	}

	public void login(String username, String password) {

		WaitUtils.waitForElementVisible(driver, userName, 10);
		if (username != null) {
			driver.findElement(userName).sendKeys(username);
		} else {
			System.out.println("username is null");
		}

		WaitUtils.waitForElementVisible(driver, PassWord, 10);
		if (password != null) {
			driver.findElement(PassWord).sendKeys(password);
		} else {
			System.out.println("Password is null");
		}

		WaitUtils.waitForElementClickable(driver, loginBtn, 10);
		driver.findElement(loginBtn).click();
	}

	public String getErrorMessage() {
		try {
			By Error = By.cssSelector("h3[data-test='error']");
			WaitUtils.waitForElementVisible(driver, Error, 10);

			return ((WebElement) Error).getText();
		} catch (Exception e) {
			return "No error message displayed";
		}
	}

	public boolean isLoginSuccessful() {

		try {
			WaitUtils.waitForURLload(driver, "/inventory.html", 10);
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	public boolean isLogoutSuccessful() {

		try {
			WaitUtils.waitForURLload(driver, "", 10);
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

}
