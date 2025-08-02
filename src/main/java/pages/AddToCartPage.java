package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddToCartPage {
	WebDriver driver;

	public AddToCartPage(WebDriver driver) {
		this.driver = driver;
	}
	
	By cart = By.id("shopping_cart_container");
	By continue_shopping = By.id("continue-shopping");
	By cancelbtn = By.id("cancel");
	By checkOut = By.id("checkout");	
	
	public void opencart() {
		driver.findElement(cart).click();
	}
	
	public void cartContinue() {
		driver.findElement(cart).click();
	}
	
}
