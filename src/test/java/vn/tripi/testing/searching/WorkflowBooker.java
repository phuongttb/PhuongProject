package vn.tripi.testing.searching;

import static org.testng.AssertJUnit.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import vn.tripi.testing.commons.BaseClass;

public class WorkflowBooker extends BaseClass {

	String username, password, order_id, baggage_price ,company_name,tax_number,comp_address,recipient_name,recipient_address,recipient_email,recipient_phone, note;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "./libs/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://dev.tripi.vn/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		username = "0942127129";
		password = "1235456";
		order_id = "6865";
		baggage_price = "407.000đ";
		company_name="Tripi Company";
		tax_number="12345678963";
		comp_address="81 A Tran Quoc Toan";
		recipient_name="Tran Bich Phuong" ;
		recipient_address="Tu Son Bac Ninh";
		recipient_email="bichphuong1209@gmail.com";
		recipient_phone="0942127129";
		note="noteabc" ;
	}

	@Test(enabled=false)
	public void TC_01_AddBaggagePaymentbyATM() throws InterruptedException {

		driver.findElement(By.cssSelector(".i-user-w-t")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("username")).sendKeys(username);
		// Thread.sleep(1000);
		WebElement pass = driver.findElement(By.cssSelector("#password"));
		pass.sendKeys(password);
		driver.findElement(By.id("submit-btn")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".username")).click();
		driver.findElement(By.xpath("//a[@data-ng-if='user.moduleBooker']")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("a[ui-sref='booker.bookingManage']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("btn-filter")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("bookingIdInput")).sendKeys(order_id);
		Thread.sleep(200);
		driver.findElement(By.cssSelector(".btn-confirm")).click();
		Thread.sleep(200);
		WebElement bookinglisttable = driver
				.findElement(By.xpath("//div[@data-ng-controller='bookingManageController']"));
		List<WebElement> bookinglist = bookinglisttable.findElements(By.cssSelector(".list-booking"));
		int numOfBooking = bookinglist.size();
		assertTrue(numOfBooking > 0);
		System.out.println("Tổng số booking:" + bookinglist.size());

		WebElement enable = driver.findElement(By.xpath("//div[@data-ng-click='assignDetailFlightBooking(f)']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", enable);
		Thread.sleep(100);

		WebElement baggage = driver
				.findElement(By.xpath("//div[@id='bookerLayout']//div[@data-ng-click='addBaggageModal()']"));
		baggage.click();
		Thread.sleep(300);
		WebElement radioButton = driver
				.findElement(By.xpath("//td[@class='outbound']//div[@class='baggage ng-scope']//td[contains(.,'"
						+ baggage_price + "')]/preceding-sibling::td"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", radioButton);
		Thread.sleep(300);
		driver.findElement(By.xpath("//div[@ng-if='!data.inbound']//a[text()='Lưu']")).click();
		Thread.sleep(400);
		WebElement paymentmethod = driver.findElement(By.xpath("//label[input[@name='paymentMethod']]/span"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", paymentmethod);
		driver.findElement(By.xpath("//div[@class='body-modal']//button[text()='Tiếp tục']")).click();
		Thread.sleep(400);
		String currentURL = driver.getCurrentUrl();
		System.out.println(currentURL);
		boolean redirectURL = currentURL.contains("https://sandbox.napas.com.vn/gateway/migs?referenceId=");
		System.out.print("Đi tới cổng thanh toán thành công");
		Assert.assertEquals(redirectURL, true);
	}

	@Test(enabled=false)
	public void TC_02_AddBaggagePaymentbyTripCredit() throws InterruptedException {

		driver.findElement(By.cssSelector(".i-user-w-t")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("username")).sendKeys(username);
		// Thread.sleep(1000);
		WebElement pass = driver.findElement(By.cssSelector("#password"));
		pass.sendKeys(password);
		driver.findElement(By.id("submit-btn")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".username")).click();
		driver.findElement(By.xpath("//a[@data-ng-if='user.moduleBooker']")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("a[ui-sref='booker.bookingManage']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("btn-filter")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("bookingIdInput")).sendKeys(order_id);
		Thread.sleep(200);
		driver.findElement(By.cssSelector(".btn-confirm")).click();
		Thread.sleep(200);
		WebElement enable = driver.findElement(By.xpath("//div[@data-ng-click='assignDetailFlightBooking(f)']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", enable);
		Thread.sleep(100);
		WebElement baggage = driver
				.findElement(By.xpath("//div[@id='bookerLayout']//div[@data-ng-click='addBaggageModal()']"));
		baggage.click();
		Thread.sleep(300);
		WebElement radioButton = driver
				.findElement(By.xpath("//td[@class='outbound']//div[@class='baggage ng-scope']//td[contains(.,'"
						+ baggage_price + "')]/preceding-sibling::td"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", radioButton);
		Thread.sleep(300);
		driver.findElement(By.xpath("//div[@ng-if='!data.inbound']//a[text()='Lưu']")).click();
		Thread.sleep(400);
		WebElement paymentmethod = driver.findElement(By.xpath("//div[@class='payment-item ng-scope']//input[@value='9']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", paymentmethod);
		driver.findElement(By.xpath("//div[@class='body-modal']//button[text()='Tiếp tục']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='ng-scope']//button[@ng-click='confirmPayment()']")).click();
		Thread.sleep(2000);
		String currentURL = driver.getCurrentUrl();
		System.out.println(currentURL);
		boolean redirectURL = currentURL.contains("https://dev.tripi.vn/checkout/success/baggages/");
		System.out.print("The system redirects to confirm page successfully");
		Assert.assertEquals(redirectURL, true);

	}

	@Test(enabled=false)
	public void TC_03_CancelOrder() throws InterruptedException {
		driver.findElement(By.cssSelector(".i-user-w-t")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("username")).sendKeys(username);
		WebElement pass = driver.findElement(By.cssSelector("#password"));
		pass.sendKeys("123456");
		driver.findElement(By.id("submit-btn")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".username")).click();
		driver.findElement(By.xpath("//a[@data-ng-if='user.moduleBooker']")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("a[ui-sref='booker.bookingManage']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("btn-filter")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("bookingIdInput")).sendKeys(order_id);
		Thread.sleep(200);
		driver.findElement(By.cssSelector(".btn-confirm")).click();
		Thread.sleep(200);
		WebElement enable = driver.findElement(By.xpath("//div[@data-ng-click='assignDetailFlightBooking(f)']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", enable);
		Thread.sleep(100);
		driver.findElement(By.xpath("//div[@class='item-action']//div[text()='Hoàn Huỷ']")).click();
		driver.findElement(By.xpath("//div[@data-ng-if='!detailFlightBooking']//a[text()='Đồng ý']")).click();
		Thread.sleep(300);
		driver.findElement(By.xpath("//div[@data-ng-if='successCancel']//a[text()='OK']")).click();
        WebElement button = driver.findElement(By.cssSelector("div.bhc-rlr-row:nth-child(1) > div:nth-child(2) > div:nth-child(1)"));
		button.isDisplayed();
		Assert.assertTrue(button.isDisplayed());
	}
	@Test(enabled=false)
	public void TC_4_ExportInvoices() throws InterruptedException {
		driver.findElement(By.cssSelector(".i-user-w-t")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("username")).sendKeys(username);
		WebElement pass = driver.findElement(By.cssSelector("#password"));
		pass.sendKeys("123456");
		driver.findElement(By.id("submit-btn")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".username")).click();
		driver.findElement(By.xpath("//a[@data-ng-if='user.moduleBooker']")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("a[ui-sref='booker.bookingManage']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("btn-filter")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("bookingIdInput")).sendKeys(order_id);
		Thread.sleep(200);
		driver.findElement(By.cssSelector(".btn-confirm")).click();
		Thread.sleep(200);
		WebElement enable = driver.findElement(By.xpath("//div[@data-ng-click='assignDetailFlightBooking(f)']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", enable);
		Thread.sleep(100);
		
		//INPUT INVOICES'S INFORMATION
		driver.findElement(By.xpath("//div[@class='diff-acction ng-scope']//div[text()='Xuất hóa đơn VAT']")).click();
		driver.findElement(By.xpath("//div[@id='modalInvoiceVAT']//input[@ng-model='invoiceRequests.vatInvoiceInfo.companyName']")).sendKeys(company_name);
		driver.findElement(By.xpath("//div[@id='modalInvoiceVAT']//input[@ng-model='invoiceRequests.vatInvoiceInfo.taxIdNumber']")).sendKeys(tax_number);
		driver.findElement(By.xpath("//div[@id='modalInvoiceVAT']//input[@ng-model='invoiceRequests.vatInvoiceInfo.companyAddress']")).sendKeys(comp_address);
		driver.findElement(By.xpath("//div[@id='modalInvoiceVAT']//input[@ng-model='invoiceRequests.vatInvoiceInfo.recipientName']")).sendKeys(recipient_name);
		driver.findElement(By.xpath("//div[@id='modalInvoiceVAT']//input[@ng-model='invoiceRequests.vatInvoiceInfo.recipientAddress']")).sendKeys(recipient_address);
		driver.findElement(By.xpath("//div[@id='modalInvoiceVAT']//input[@ng-model='invoiceRequests.vatInvoiceInfo.email']")).sendKeys(recipient_email);
		driver.findElement(By.xpath("//div[@id='modalInvoiceVAT']//input[@ng-model='invoiceRequests.vatInvoiceInfo.recipientPhone']")).sendKeys(recipient_phone);
		driver.findElement(By.xpath("//div[@id='modalInvoiceVAT']//textarea[@ng-model='invoiceRequests.vatInvoiceInfo.note']")).sendKeys(note);
		driver.findElement(By.xpath("//div[@id='modalInvoiceVAT']//button[@ng-click='confirmRequestInvoice()']")).click();
		driver.findElement(By.xpath("//div[@id='modalConfirmInvoiceVat']//button[@ng-click='confirmInvoiceVATDirect()']")).click();
		Thread.sleep(300);
	/*	WebElement SuccessMsg = driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']"));
		SuccessMsg.isDisplayed();
		Assert.assertTrue(SuccessMsg.isDisplayed());*/
		
		//GET INVOICE'S INFORMATION AND CHECK INFORMATION 
		Assert.assertEquals(driver.findElement(By.xpath("//div[contains(.,'Tên đơn vị')]/b")).getText(), company_name);
		Assert.assertEquals(driver.findElement(By.xpath("//div[contains(.,'Địa chỉ')]/b")).getText(), comp_address);
		Assert.assertEquals(driver.findElement(By.xpath("//div[contains(.,'Mã số thuế')]/b")).getText(), tax_number);
		Assert.assertEquals(driver.findElement(By.xpath("//div[contains(.,'Tên người nhận')]/b")).getText(), recipient_name);
		Assert.assertEquals(driver.findElement(By.xpath("(//div[contains(text(),'Địa chỉ')]/b)[2]")).getText(),recipient_address);
		Assert.assertEquals(driver.findElement(By.xpath("(//div[contains(.,'Số điện thoại')]/b)[2]")).getText(), recipient_phone);
		Assert.assertEquals(driver.findElement(By.xpath("(//div[contains(.,'Email')]/b)[2]")).getText(), recipient_email);
		Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Ghi chú')]/b")).getText(), note);
		
	}
	@Test()
	public void TC_05_ChangeItinerary() throws InterruptedException {
		driver.findElement(By.cssSelector(".i-user-w-t")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("username")).sendKeys(username);
		WebElement pass = driver.findElement(By.cssSelector("#password"));
		pass.sendKeys("123456");
		driver.findElement(By.id("submit-btn")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".username")).click();
		driver.findElement(By.xpath("//a[@data-ng-if='user.moduleBooker']")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("a[ui-sref='booker.bookingManage']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("btn-filter")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("bookingIdInput")).sendKeys(order_id);
		Thread.sleep(200);
		driver.findElement(By.cssSelector(".btn-confirm")).click();
		Thread.sleep(200);
		WebElement enable = driver.findElement(By.xpath("//div[@data-ng-click='assignDetailFlightBooking(f)']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", enable);
		Thread.sleep(100);
		driver.findElement(By.xpath("//div[@data-ng-if='detailFlightBooking.hasAction']//div[@data-ng-click='changeSchedule()']")).click();
		driver.findElement(By.xpath("//div[@ng-if='data.outbound.supportChangeItinerary']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//div[@id='modalBookerChangeFlight']//div[@class='it-left'])[1]" )).click();;
		WebElement selectpaymentmethod = driver.findElement(By.xpath("//td[contains(text(),'Trip Credits')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectpaymentmethod);
		Thread.sleep(100);
		WebElement confirmbtn = driver.findElement(By.xpath("button[contains(text(),'Xác nhận')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmbtn);
		Thread.sleep(100);
		String currentURL = driver.getCurrentUrl();
		System.out.println(currentURL);
		boolean redirectURL = currentURL.contains("https://dev.tripi.vn/checkout/success/itinerary_changing/");
		System.out.print("The system redirects to confirm page successfully");
		Assert.assertEquals(redirectURL, true);

		
	}
	public String getHTML5ValidationMessage(WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", element);
	}

}
