package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutPage {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		this.driver = driver;
	}

	By continue_checkoutPage = By.id("continue");
	By cancelbtn = By.id("cancel");

	public void cartFinish() {
		driver.findElement(continue_checkoutPage).click();
	}

	public void CartCancel() {
		driver.findElement(cancelbtn).click();
	}

}
