package vn.tripi.testing.searching;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import vn.tripi.testing.commons.BaseClass;

public class HuntTicket extends BaseClass {	

	@Test()
	@Parameters({ "flightfromairportv", "flighttoairportv", "flightcheckindatev" })
	public void TC_HunTicket() throws Exception {
		
		
		driver.findElement(By.xpath("//div[contains(text(),'Vé máy bay')]")).click();
		WebElement hunticket = driver.findElement(By.xpath(".//*[@id='flight-list']/div/a/img"));
		hunticket.click();
		Thread.sleep(3000);
	}

	
}