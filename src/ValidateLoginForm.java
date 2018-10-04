package vn.tripi.testing.searching;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import vn.tripi.testing.commons.BaseClass;

public class ValidateLoginForm extends BaseClass {
	
	public String getHTML5ValidationMessage(WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", element);
		}

	private void commonProcedure(String username, String password, String expectedOutput) throws InterruptedException {
		driver.get("https://www.tripi.vn/");

		driver.findElement(By.cssSelector(".header-menu")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("username")).sendKeys(username);
		WebElement pass = driver.findElement(By.id("password"));
		pass.sendKeys(password);
		driver.findElement(By.id("submit-btn")).click();
		Thread.sleep(3000);
	}

	// VALIDATE CASE INPUT PASSWORD INCORRECT
	@Test
	@Parameters({ "username", "password", "expectedOutput" })
	public void TC_01_ValidatePassword(String username, String password, String expectedOutput)
			throws InterruptedException {
		commonProcedure(username, password, expectedOutput);
		driver.findElement(By.xpath("//div[@class='tlp-login-form']"));
		String errormssage = driver.findElement(By.xpath("//h4[contains(text(),'Mật khẩu không hợp lệ')]")).getText();
		System.out.println("Validate Passoword: " + errormssage);
		Assert.assertEquals(errormssage, expectedOutput);
	
	}

	// CASE 2 : VALIDATE INPUT USERNAME INCORRECT//
	@Test
	@Parameters({ "username", "password", "expectedOutput" })
	public void TC_02_ValidateUsername(String username, String password, String expectedOutput)
			throws InterruptedException {
		commonProcedure(username, password, expectedOutput);
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("password")).clear();
		String errormessage = driver.findElement(By.xpath("//h4[contains(text(),'Tài khoản chưa được đăng ký tại hệ thống')]")).getText();
		System.out.println("Validate username : " + errormessage);
		Assert.assertEquals(errormessage, expectedOutput);
	}
	// CASE 3: Let blank user_name 
	@Test()
	@Parameters({ "username", "password", "expectedOutput" })
	public void TC_03_Validate_Username_Empty(String username, String password, String expectedOutput)
			throws InterruptedException {
		commonProcedure(username, password, expectedOutput);
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("password")).clear();
		String nameMessage = getHTML5ValidationMessage(driver.findElement(By.xpath("//input[@id='username']")));
		Assert.assertEquals(expectedOutput, nameMessage);
	}
}