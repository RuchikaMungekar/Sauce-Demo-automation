package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.WaitUtils;

public class CheckOutPage {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		this.driver = driver;

	}

	By continue_checkoutPage = By.id("continue");
	By cancelbtn = By.id("cancel");
	By cart = By.id("shopping_cart_container");
	By continue_shopping = By.id("continue-shopping");
	By checkOut = By.id("checkout");
	By finish =By.id("finish");
	By firstName = By.id("first-name");
	By lastName = By.id("last-name");
	By postalCode = By.id("postal-code");

	public void opencart() {
		driver.findElement(cart).click();
	}

	public void cartCheckout() {
		driver.findElement(checkOut).click();
		System.out.println("clockimh");
	}
	public void cartContinuet() {
		driver.findElement(continue_checkoutPage).click();
		System.out.println("click");
	}

	public void cartFinish() {
		driver.findElement(finish).click();
	}

	public void CartCancel() {
		driver.findElement(cancelbtn).click();
	}

	public void filldata(String FirstName, String LastName, String PostalCode) {
		WaitUtils.waitForElementVisible(driver, firstName, 10);
		driver.findElement(firstName).sendKeys(FirstName);
		WaitUtils.waitForElementVisible(driver, lastName, 10);
		driver.findElement(lastName).sendKeys(LastName);

		WaitUtils.waitForElementVisible(driver, lastName, 10);
		driver.findElement(postalCode).sendKeys(PostalCode);

	}

}
