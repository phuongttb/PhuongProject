package vn.tripi.testing.searching;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import vn.tripi.testing.commons.BaseClass;

public class FLightValidate extends BaseClass {

	private void commonTestProcedure(String flightfromairportv, String flighttoairportv, String flightcheckindatev)
			throws InterruptedException {

		driver.findElement(By.xpath("//div[contains(text(),'Vé máy bay')]")).click();

		WebElement eFromAirport = driver.findElement(By.id("flight-from-airport-value"));
		eFromAirport.sendKeys(flightfromairportv);
		Thread.sleep(2000);
		eFromAirport.sendKeys(Keys.RETURN);

		WebElement eToAirport = driver.findElement(By.id("flight-to-airport-value"));
		eToAirport.sendKeys(flighttoairportv);
		Thread.sleep(2000);
		eToAirport.sendKeys(Keys.RETURN);

		WebElement depaturedate = driver.findElement(By.xpath("//input[@id='flight-checkin-date']"));
		depaturedate.click();
		selectdatepicker(flightcheckindatev);
	}

	public void selectdatepicker(String date) {
		WebElement dateWidget = driver.findElement(By.className("startHoliday"));
		List<WebElement> columns = dateWidget.findElements(By.tagName("td"));
		for (WebElement cell : columns) {
			if (cell.getText().equals(date)) {
				cell.click();
				break;
			}
		}
	}

	@Test(priority = 1)
	@Parameters({ "flightfromairportv", "flighttoairportv", "flightcheckindatev" })
	public void TC_01_Validatesamedestination(String flightfromairportv, String flighttoairportv,
			String flightcheckindatev) throws Exception {
		commonTestProcedure(flightfromairportv, flighttoairportv, flightcheckindatev);

		WebElement searchbutton = driver
				.findElement(By.xpath("//button[@class='flight-search-button btn btn-search']"));
		searchbutton.click();
		Thread.sleep(2000);
		String errormssage = driver.findElement(By.xpath("//div[@data-ng-bind-html='message']")).getText();
		Assert.assertEquals(errormssage, "Điểm đến không được trùng với điểm khởi hành. Vui lòng lựa chọn lại.");
		System.out.println(errormssage);
		driver.navigate().refresh();
	}

	@Test(priority = 2)
	@Parameters({ "flightfromairportv", "flighttoairportv", "flightcheckindatev" })
	public void TC_02_NoSearchResults(String flightfromairportv, String flighttoairportv, String flightcheckindatev)
			throws Exception {
		commonTestProcedure(flightfromairportv, flighttoairportv, flightcheckindatev);

		WebElement searchbutton = driver
				.findElement(By.xpath("//button[@class='flight-search-button btn btn-search']"));
		searchbutton.click();
		Thread.sleep(2000);
		String errormssage = driver.findElement(By.xpath("//*[contains(text(),'Không có chuyến bay')]")).getText();
		Assert.assertEquals(errormssage, "Không có chuyến bay");
		System.out.println(errormssage);

	}
}