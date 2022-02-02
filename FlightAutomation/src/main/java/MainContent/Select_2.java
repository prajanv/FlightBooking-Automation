package MainContent;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.WriteExcelDataFile;

import baseClass.BaseSetup;

public class Select_2 extends BaseSetup{
	
	WebDriverWait wait = null;
	WebDriver wd;
	List <WebElement> flightName;
	List <WebElement> departureTime;
	List <WebElement> arrivalTime;
	List <WebElement> price;
	
	public Map<Integer, Object[]> itemMap = new LinkedHashMap<Integer, Object[]>();
	
	static Properties prop = readProperties();
	
	public void waitTime() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void selectFilter() {
		driver.findElement(By.id("airlineRowContainer_6E")).click();
        waitTime();
        driver.findElement(By.id("leg0-morning-departure")).click();
        waitTime();
	}
	
	public void selectFlight() {
		WebElement flight= driver.findElement(By.xpath(prop.getProperty("selectFlight")));
		flight.click();
        waitTime();
	}
	
	public void flightDetails() {
		
		flightName = driver.findElements(By.xpath(prop.getProperty("nameFlight")));
		departureTime = driver.findElements(By.xpath(prop.getProperty("flightDeparture")));
		arrivalTime = driver.findElements(By.xpath(prop.getProperty("flightArrival")));
		price = driver.findElements(By.xpath(prop.getProperty("flightPrice")));
		itemMap.put(0, new Object[] { "FlightName", "DepartureTime", "ArrivalTime", "Price" });
		for (int i = 1, j = 0; i <= 3 && j < 3; i++, j++)
			itemMap.put(i, new Object[] { flightName.get(j).getText().replaceAll("\\s", ""),
					departureTime.get(j).getText(), arrivalTime.get(j).getText(), price.get(j).getText() });
		waitTime();
		WriteExcelDataFile.setDataFile(itemMap);
	}

	public Payment_3 noThanks() {
		WebElement forced=driver.findElement(By.id(prop.getProperty("choiceForced")));
		forced.click();
        waitTime();
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        waitTime(); 
		return PageFactory.initElements(driver, Payment_3.class);
	}
}