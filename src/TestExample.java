package vn.tripi.testing.searching;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import vn.tripi.testing.utils.WebDriverFactory;

public class TestExample {

	private WebDriver driver = null;

	@BeforeClass
	public void beforeClass() {
		driver = WebDriverFactory.getDriver();
		driver.manage().window().maximize();
		driver.get(System.getProperty("baseURL"));
	}

	@AfterClass
	public void afterClass() {
		if (driver != null)
			driver.quit();
	}

	@Test
	public void TC_EXAMPLE_001() {

		Assert.assertEquals(1 + 1, 2);
	}

	@Test
	public void TC_EXAMPLE_002() {
		Assert.assertEquals(1 * 1, 2);
	}
}
