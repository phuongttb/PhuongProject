package vn.tripi.testing.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
	private static final String FIREFOX = "FIREFOX";
	private static final String CHROME = "CHROME";

	private static final String BROWSER_TYPE = "browserType";

	public static WebDriver getDriver() {

		String driverType = (System.getProperty(BROWSER_TYPE) + "").replaceAll("\\s+", "").toUpperCase();
		switch (driverType) {
		case CHROME:
			return new ChromeDriver();
		case FIREFOX:
			return new FirefoxDriver();
		default:
			return new FirefoxDriver();
		}
	}
}
