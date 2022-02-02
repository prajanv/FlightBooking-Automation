package testClass;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import MainContent.Payment_3;
import MainContent.Search_1;
import MainContent.Select_2;
import baseClass.BaseSetup;
import utilities.TestDataProvider;

public class TestExpedia extends BaseSetup {

	Search_1 sea;
	Select_2 sel;
	Payment_3 pay;

	@Test(dataProvider = "expediaTestData")
	public void testing(Hashtable<String, String> testData) throws Exception {
		logger = report.createTest("Report");

		BaseSetup bs = new BaseSetup();
		logger.log(Status.INFO, "Opening the browser");

		bs.setup();
		logger.log(Status.PASS, "Browser opened");

		logger.log(Status.INFO, "Opening Url");
		sea = bs.openUrl();
		logger.log(Status.PASS, "URL openend");

		logger.log(Status.INFO, "Clicking On Flight Icon");
		sea.selectFlightIcon();
		logger.log(Status.PASS, "Flight Icon Chosen");

		logger.log(Status.INFO, "Clicking On Flight Icon");
		sea.selectOneWay();
		logger.log(Status.PASS, "Flight Icon Chosen");

		logger.log(Status.INFO, "Selecting From City");
		sea.selectFromCity(testData.get("fromDestination"));
		logger.log(Status.PASS, "From City is selected");

		logger.log(Status.INFO, "Selecting To City");
		sea.selectToCity(testData.get("toDestination"));
		logger.log(Status.PASS, "To City is selected");

		logger.log(Status.INFO, "Selecting Date");
		sea.selectDate();
		logger.log(Status.PASS, "Date is selected");

		logger.log(Status.INFO, "Clicking the Search Button");
		sel = sea.searching();
		logger.log(Status.PASS, "Search button is clicked");

		logger.log(Status.INFO, "Selecting the filters");
		sel.selectFilter();
		logger.log(Status.PASS, "Filters are selected");

		logger.log(Status.INFO, "Adding flight details to Excel");
		sel.flightDetails();
		logger.log(Status.PASS, "Flight details added to excel");

		logger.log(Status.INFO, "Selecting the flight");
		sel.selectFlight();
		logger.log(Status.PASS, "Flight is selected");

		logger.log(Status.INFO, "Selecting No Thanks pop up button");
		pay = sel.noThanks();
		logger.log(Status.PASS, "No Thanks is selected");

		logger.log(Status.INFO, "Selecting Book Button");
		pay.bookButton();
		logger.log(Status.PASS, "Book Button selected");

		logger.log(Status.INFO, "Filling the details");
		pay.fillDetails(testData.get("titleSend"), testData.get("lnameSend"), testData.get("firstnameSend"),
				testData.get("numberSend"));
		logger.log(Status.PASS, "Details are filled");

		logger.log(Status.INFO, "Filling payment page");
		pay.paymentMode(testData.get("cardnumSend"), testData.get("secnumSend"), testData.get("addressSend"),
				testData.get("citySend"), testData.get("pinSend"), testData.get("emailSend"));
		logger.log(Status.PASS, "Payment page filled");

		logger.log(Status.INFO, "Taking snapshot");
		pay.takeSnapShot(driver, System.getProperty("user.dir") + "\\screenshots\\photo.png");
		logger.log(Status.PASS, "Snapshot taken");
	}

	@DataProvider
	public Object[][] expediaTestData() {
		return TestDataProvider.getTestData("ExpediaFlight.xlsx", "Travel", "BookingDetails");
	}

}
