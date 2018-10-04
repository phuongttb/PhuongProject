package vn.tripi.testing.searching;

import static org.testng.AssertJUnit.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import vn.tripi.testing.commons.BaseClass;

public class ManageCustomer extends BaseClass {

	@Test()
	@Parameters({ "username", "password", "searchname" })
	public void TC_01_Filterorder(String username, String password, String searchname) throws InterruptedException {

		driver.findElement(By.cssSelector(".header-menu")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("username")).sendKeys(username);
		Thread.sleep(1000);
		WebElement pass = driver.findElement(By.id("password"));
		pass.sendKeys(password);
		driver.findElement(By.id("submit-btn")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".username")).click();
		driver.findElement(By.xpath("//a[@data-ng-if='user.moduleBooker']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath(".//*[@id='bookerLayout']/div/div[1]/ul/li[6]/a/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("filterName")).sendKeys(searchname);
		Thread.sleep(3000);
		WebElement listcustomer=driver.findElement(By.xpath(".//*[@id='customerManage']/div/div/div/div/div/div[2]"));
		List<WebElement> customerlist = listcustomer.findElements(By.cssSelector(".list-booking"));
		System.out.print(customerlist);
		int numOfcustomer = customerlist.size();
		assertTrue(numOfcustomer > 0);
		System.out.println("Tong so khach hang :" + customerlist.size());
		
	}

}
