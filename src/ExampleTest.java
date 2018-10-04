package selenium_api;

import static org.testng.AssertJUnit.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExampleTest {
	WebDriver driver = new FirefoxDriver();

	@BeforeClass
	public void setUp() {
		// driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void beforeTest() {
		driver.get("https://www.tripi.vn/hotels/a-la-carte-da-nang-beach-h1285.html?checkIn=15-09-2018&checkOut=20-09-2018&adults=2&children=0&rooms=1&providerId=11&roomTypeId=0&partner=true");

	}

	@Test()

	public void TC_01_SearchTicket_RoundTrip() throws Exception {
	
//		WebElement listroomdiv = driver.findElement(By.xpath("//div[@ng-controller='hotelDetailController']"));
		List<WebElement> listroom = driver.findElements(By.cssSelector(".list-rooms-by-agency"));
		System.out.println("Total room item :" + listroom.size());
		int numroom = listroom.size();
		assertTrue(numroom > 0);

		Thread.sleep(4000);
		WebElement selectBtn = driver.findElement(
				By.xpath("//div[text()='TWIN DELUXE LIGHT STUDIO']/../../following-sibling::*//span[text()='Đặt ngay']"));
		selectBtn.click();
		ArrayList<String> tabs1 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(1));
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("phuong");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys("0942225555");
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("test@gmail.com");
		driver.findElement(By.xpath("//textarea[@name='address']")).sendKeys("bacninh");
		WebElement paymentmethod = driver.findElement(By.cssSelector("#payment-method-3"));
		paymentmethod.click();
		Thread.sleep(1000);
		WebElement paymentbtn = driver.findElement(By.cssSelector(".hotel-payment-button"));
		paymentbtn.click();
		Thread.sleep(2000);
//		WebElement confirmbtn = driver.findElement(By.cssSelector(".btn-success"));
//		confirmbtn.click();
//		for (int i = 0; i < 10; i++) {
//			String currentURL = driver.getCurrentUrl();
//			if (currentURL.contains("booking")) {
//				Thread.sleep(5000);
//			} else {
//				break;
//			}
//		}
//		String currentURL = driver.getCurrentUrl();
//		boolean redirectURL = currentURL.contains("https://pay.vnpay.vn/Transaction/PaymentMethod.html?token=");
//		System.out.print("Đi tới cổng thanh toán thành công");
//		AssertJUnit.assertEquals(redirectURL, true);

	}


	@Test(enabled=false)

	public void Booking() throws Exception {
		// Nguoi lon 1
		driver.findElement(By.cssSelector("input[ng-model='adult.lastName']")).sendKeys("phuong");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[contains(@class,'first-name')]")).sendKeys("tran");
		new Select(driver.findElement(By.xpath("(//select[@ng-model='adult.gender'])[1]"))).selectByVisibleText("Nữ");

		// tre em 1
		driver.findElement(By.xpath("//input[@ng-model='child.lastName']")).sendKeys("phan");
		driver.findElement(By.xpath("//input[@ng-model='child.firstName']")).sendKeys("nguyet");
		new Select(driver.findElement(By.xpath("(//select[@ng-model='child.gender'])[1]"))).selectByVisibleText("Nam");
		Thread.sleep(2000);
		new Select(driver.findElement(By.xpath("//select[@ng-model='child.dayOfBirth']"))).selectByVisibleText("12");
		Thread.sleep(2000);
		new Select(driver.findElement(By.xpath("//select[@ng-model='child.monthOfBirth']"))).selectByVisibleText("03");
		Thread.sleep(2000);
		new Select(driver.findElement(By.xpath("//select[@ng-model='child.yearOfBirth']")))
				.selectByVisibleText("2016");
		Thread.sleep(2000);

		// em be
		driver.findElement(By.xpath("//input[@ng-model='infant.lastName']")).sendKeys("phan");
		driver.findElement(By.xpath("//input[@ng-model='infant.firstName']")).sendKeys("dat");
		new Select(driver.findElement(By.xpath("//select[@ng-model='infant.gender'][1]"))).selectByVisibleText("Nữ");
		Thread.sleep(2000);
		new Select(driver.findElement(By.xpath("//select[@ng-model='infant.dayOfBirth']"))).selectByVisibleText("12");
		Thread.sleep(2000);
		new Select(driver.findElement(By.xpath("//select[@ng-model='infant.monthOfBirth']"))).selectByVisibleText("03");
		Thread.sleep(2000);
		new Select(driver.findElement(By.xpath("//select[@ng-model='infant.yearOfBirth']")))
				.selectByVisibleText("2017");
		Thread.sleep(2016);

		// NHẬP THÔNG TIN LIÊN HỆ

		new Select(driver.findElement(By.id("ticket-booking-select-title"))).selectByVisibleText("Ông");
		driver.findElement(By.xpath("//input[@ng-model='contactInfo.lastName']")).sendKeys("Tran");
		driver.findElement(By.xpath("//input[@ng-model='contactInfo.firstName']")).sendKeys("Phuong");
		driver.findElement(By.xpath("//input[@ng-model='contactInfo.email']")).sendKeys("phuong@gmail.com");
		driver.findElement(By.xpath("//input[@ng-model='contactInfo.phone1']")).sendKeys("0942712719");

		// //Select payment method - trip credit
		WebElement paymentmethod = driver.findElement(By.cssSelector("#payment-method-3"));
		paymentmethod.click();
		Thread.sleep(1000);

		WebElement paymentbtn = driver.findElement(By.cssSelector(".flight-payment-button"));
		paymentbtn.click();
		Thread.sleep(2000);

		WebElement confirmbtn = driver.findElement(By.cssSelector(".btn-success"));
		confirmbtn.click();

		// Đi tới cổng thanh toán
		// WebElement confirmdialog =driver.findElement(By.cssSelector(".btn-success"));
		// confirmdialog.click();
		//
	}
}