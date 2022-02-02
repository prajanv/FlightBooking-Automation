package baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import MainContent.Search_1;
import utilities.DateUtil;
import utilities.ExtentReportManager;

public class BaseSetup 
{
	public static WebDriver driver;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;
	static Properties prop = readProperties();

	public static Properties readProperties() {
		File file = new File("prop.properties");

		FileInputStream fileInput = null;

		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Properties prop = new Properties();

		// load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	public void setup() throws Exception 
	{
		String brow = prop.getProperty("browser");
		System.out.println("Initiating Browser...");
		if (brow.equalsIgnoreCase("firefox")) {
			// FirefoxOptions options = new FirefoxOptions();
			FirefoxOptions fo = new FirefoxOptions();
			fo.addArguments("--disable-infobars");
			fo.addPreference("dom.webnotifications.enabled", false);
			fo.addPreference("geo.enabled", false);
			fo.addPreference("geo.prompt.testing", false);
			fo.addPreference("geo.prompt.testing.allow", false);
			System.setProperty(prop.getProperty("firefoxWebDriver"), prop.getProperty("firefoxWebDriverPath"));
			driver = new FirefoxDriver(fo);
			driver.manage().window().maximize();
			System.out.println("Firefox Initiated!");
		} else if (brow.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-infobars");
			System.setProperty(prop.getProperty("chromeWebDriver"), prop.getProperty("chromeWebDriverPath"));
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			System.out.println("Chrome Initiated!");
		} else if(brow.equalsIgnoreCase("edge")){
			System.setProperty(prop.getProperty("edgeWebDriver"), prop.getProperty("edgeWebDriverPath"));
			EdgeOptions eo = new EdgeOptions();
			eo.getCapability("--disable-infobars");
			driver = new EdgeDriver(eo);
			driver.manage().window().maximize();
			System.out.println("Edge Initiated!");
		}else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void reportPass(String reportString) 
	{
		logger.log(Status.PASS, reportString);
	}

	public void takeScreenShot() 
	{
		TakesScreenshot ss = (TakesScreenshot) driver;
		File src = ss.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + DateUtil.getTimeStamp() + ".png");

		try {
			FileUtils.copyFile(src, dest);
			logger.addScreenCaptureFromPath(System.getProperty("user.dir") + DateUtil.getTimeStamp() + ".png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getFlight(String expectedTitle) {
		try {
			Assert.assertEquals(driver.getTitle(), expectedTitle);
			reportPass("Actual Title : " + driver.getTitle() + " - equals to Expected Title : " + expectedTitle);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}
	
	public void reportFail(String reportString) 
	{
		logger.log(Status.FAIL, reportString);
		takeScreenShot();
		Assert.fail();
	}

	public void clickElement(String LocatorValue) 
	{

		if (LocatorValue.endsWith("_Id")) {
			driver.findElement(By.id(LocatorValue)).click();
		} else if (LocatorValue.endsWith("_XPath")) {
			driver.findElement(By.xpath(LocatorValue)).click();
		} else if (LocatorValue.endsWith("_ClassName")) {
			driver.findElement(By.className(LocatorValue)).click();
		} else if (LocatorValue.endsWith("_TagName")) {
			driver.findElement(By.tagName(LocatorValue)).click();
		} else if (LocatorValue.endsWith("_LinkText")) {
			driver.findElement(By.linkText(LocatorValue)).click();
		}

	}

	public void ExplicitWait(String LocatorValue) 
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		if (LocatorValue.endsWith("_Id")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LocatorValue)));
		} else if (LocatorValue.endsWith("_XPath")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocatorValue)));
		} else if (LocatorValue.endsWith("_ClassName")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(LocatorValue)));
		} else if (LocatorValue.endsWith("_TagName")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(LocatorValue)));
		} else if (LocatorValue.endsWith("_LinkText")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(LocatorValue)));
		}

	}
	
	public void waitTime() 
	{
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public Search_1 openUrl() 
	{
		driver.get(prop.getProperty("URL"));
		System.out.println("Opening Website...");
		waitTime();
		return PageFactory.initElements(driver, Search_1.class);
	}

	@AfterSuite
	public void close() 
	{
		report.flush();
		driver.quit();
	}
	
}
