
package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chromium.ChromiumNetworkConditions;
import org.openqa.selenium.chromium.HasNetworkConditions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import BaseTest.BaseTest;
import DriverUtils.DriverFactory;

public class NetworkTest extends BaseTest {

	@Test
	public void testNetWork(){

		// Cast to HasNetworkConditions
		HasNetworkConditions networkDriver = (HasNetworkConditions) driver;

		// Simulate Offline Mode
		ChromiumNetworkConditions offline = new ChromiumNetworkConditions();
		offline.setOffline(true);
		networkDriver.setNetworkConditions(offline);

		try {
			driver.get("https://www.saucedemo.com");
			Assert.fail("Expected WebDriverException because network is offline");
		} catch (WebDriverException ex) {
			System.out.println("Network offline test passed: " + ex.getMessage());

			// Restore network
			ChromiumNetworkConditions online = new ChromiumNetworkConditions();
			online.setOffline(false);
			networkDriver.setNetworkConditions(online);
		}

		// Now it should load successfully
		driver.get("https://www.saucedemo.com");
		Assert.assertTrue(driver.getTitle().contains("Swag"), "Page should load when online");
	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
