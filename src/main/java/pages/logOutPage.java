package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.WaitUtils;

public class logOutPage {

	WebDriver driver;

	public logOutPage(WebDriver driver) {
		this.driver = driver;
	}

//	By Hamburger = By.className("react-burger-menu-btn");
//	By Logoutbtn = By.id("logout_sidebar_link");
	By Hamburger = By.className("bm-burger-button");
	public By Logoutbtn = By.linkText("Logout");

	public void logOut() {
		WaitUtils.waitForElementVisible(driver, Hamburger, 10);
		driver.findElement(Hamburger).click();
		WaitUtils.waitForElementVisible(driver, Logoutbtn, 10);
		driver.findElement(Logoutbtn).click();

	}

}
