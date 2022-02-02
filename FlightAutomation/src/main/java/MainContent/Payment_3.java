package MainContent;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseClass.BaseSetup;

//comment the above line and uncomment below line to use Chrome
import org.openqa.selenium.chrome.ChromeDriver;

public class Payment_3 extends BaseSetup {

	WebDriverWait wait = null;
	static Properties prop = readProperties();

	public void waitTime() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void bookButton() {
		WebElement btn = driver.findElement(By.xpath(prop.getProperty("buttonBook")));
		btn.click();
		waitTime();
	}

	public void fillDetails(String titleSend, String lnameSend, String firstnameSend, String numberSend) {
		WebElement title = driver.findElement(By.xpath(prop.getProperty("titleXpath")));
		title.click();
		title.sendKeys(titleSend);
		WebElement lname = driver.findElement(By.xpath(prop.getProperty("lnameXpath")));
		lname.click();
		lname.sendKeys(lnameSend);
		WebElement fname = driver.findElement(By.xpath(prop.getProperty("firstnameXpath")));
		fname.click();
		fname.sendKeys(firstnameSend);
		WebElement pno = driver.findElement(By.xpath(prop.getProperty("numberXpath")));
		pno.click();
		pno.sendKeys(numberSend);
		waitTime();
	}

	public void paymentMode(String cardnumSend, String secnumSend, String addressSend, String citySend, String pinSend,
			String emailSend) {
		WebElement cardname = driver.findElement(By.xpath(prop.getProperty("cardnameXpath")));
		cardname.click();
		waitTime();
		WebElement cardnum = driver.findElement(By.xpath(prop.getProperty("cardnumXpath")));
		cardnum.click();
		cardnum.sendKeys(cardnumSend);
		WebElement month2 = driver.findElement(By.xpath(prop.getProperty("month2Xpath")));
		for (int i = 0; i < 2; i++) {
			month2.sendKeys(Keys.DOWN);
		}
		WebElement year2 = driver.findElement(By.xpath(prop.getProperty("year2Xpath")));
		for (int i = 0; i < 4; i++) {
			year2.sendKeys(Keys.DOWN);
		}
		WebElement secnum = driver.findElement(By.xpath(prop.getProperty("secnumXpath")));
		secnum.click();
		secnum.sendKeys(secnumSend);
		WebElement address = driver.findElement(By.xpath(prop.getProperty("addressXpath")));
		address.click();
		address.sendKeys(addressSend);
		waitTime();
		WebElement city = driver.findElement(By.xpath(prop.getProperty("cityXpath")));
		city.click();
		city.sendKeys(citySend);
		WebElement pin = driver.findElement(By.xpath(prop.getProperty("pinXpath")));
		pin.click();
		pin.sendKeys(pinSend);
		WebElement email = driver.findElement(By.xpath(prop.getProperty("emailXpath")));
		email.click();
		email.sendKeys(emailSend);
		waitTime();
	}

	public void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {

		// Convert web driver object to TakeScreenshot

		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

		// Call getScreenshotAs method to create image file

		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		// Move image file to new destination

		File DestFile = new File(fileWithPath);

		// Copy file at destination

		FileUtils.copyFile(SrcFile, DestFile);

	}

}