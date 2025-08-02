package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.WaitUtils;

public class HomePage {
	WebDriver driver;

	public HomePage(WebDriver DriverName) {
		this.driver = DriverName;
	}

	public void addAllProductsToCart() {
		List<WebElement> addToCartButtons = driver.findElements(By.cssSelector("[id^='add-to-cart']"));

		for (WebElement button : addToCartButtons) {
			WaitUtils.waitForElementClickable(driver, button, 10);
			button.click();
		}
	}

	public void removeProdHome() {
		List<WebElement> addToCartButtons = driver.findElements(By.cssSelector("[id^='remove-sauce-labs']"));
		System.out.println(addToCartButtons);
		WebElement removeButton = driver.findElement(By.cssSelector("[id$='bike-light']"));
		removeButton.click();
	}

}
