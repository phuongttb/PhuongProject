package vn.tripi.testing.commons;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
	protected WebDriver driver = null;

	@BeforeClass
	public void beforeClass() {	
		System.setProperty("webdriver.chrome.driver","./libs/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.tripi.vn/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterClass
	public void afterClass() {
		if (driver != null)
			driver.quit();
	}
}
