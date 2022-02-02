package MainContent;

import java.util.Properties;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseClass.BaseSetup;

public class Search_1 extends BaseSetup {

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

	public void selectFlightIcon() {
		WebElement flights = driver.findElement(By.xpath(prop.getProperty("flightIcon")));
		flights.click();
	}

	public void selectOneWay() {
		WebElement oneWay = driver.findElement(By.xpath(prop.getProperty("oneWay")));
		oneWay.click();
	}

	public void selectFromCity(String place) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("leaveFrom"))));
		WebElement leavingFrom = driver.findElement(By.xpath(prop.getProperty("leaveFrom")));
		leavingFrom.click();
		leavingFrom.sendKeys(place);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("fromAirport"))));
		WebElement sourceAirport = driver.findElement(By.xpath(prop.getProperty("fromAirport")));
		sourceAirport.click();
		waitTime();
	}

	public void selectToCity(String place) {
		
		WebElement goingTo = driver.findElement(By.xpath(prop.getProperty("leaveTo")));
		goingTo.click();
		goingTo.sendKeys(place);
		WebElement destinationAirport = driver.findElement(By.xpath(prop.getProperty("toAirport")));
		destinationAirport.click();
		waitTime();
	}

	public void selectDate() {
		WebElement departure = driver.findElement(By.id(prop.getProperty("departureButton")));
		departure.click();
		WebElement date = driver.findElement(By.xpath(prop.getProperty("dateSelect")));
		date.click();
		WebElement done = driver.findElement(By.xpath(prop.getProperty("doneButton")));
		done.click();
		waitTime();
	}

	public Select_2 searching() {
		WebElement searchButton = driver.findElement(By.xpath(prop.getProperty("searchButton")));
		searchButton.click();
		waitTime();

		return PageFactory.initElements(driver, Select_2.class);
	}

}