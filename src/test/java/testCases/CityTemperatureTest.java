package testCases;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.CityTemperature;
import testBase.BaseClass;
import utilities.DataProviders;

public class CityTemperatureTest extends BaseClass{

	CityTemperature ctObj;
	WebDriverWait wait;
	public Logger logger;
	
	@BeforeMethod(groups= {"ui","smoke","regression","functional"})
	public void pageChange() {
		ctObj = new CityTemperature(driver);
		ctObj.clickCityConv();
		logger.info("City Temperature is displayed.");
	}
	
	@BeforeClass(groups= {"ui","smoke","regression","functional"})
	public void classSetUp() {
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=7,groups= {"functional","ui"})
	public void validateTempConvUI() {
		ctObj = new CityTemperature(driver);
		Assert.assertEquals(ctObj.tempConv.isDisplayed(), true);
		Assert.assertEquals(ctObj.cityTemp.isDisplayed(), true);
		Assert.assertEquals(ctObj.cityTextbox.isDisplayed(), true);
		Assert.assertEquals(ctObj.getTempButton.isDisplayed(), true);
		logger.info("City Temperature page elements are visible.");
	}
	
	@SuppressWarnings("deprecation")
	@Test(dataProvider="CityData", dataProviderClass=DataProviders.class, groups={"functional","smoke"}, priority=8) //15,16,17,18,19
	public void funCityTemp(String city, String result) {
		ctObj = new CityTemperature(driver);
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		ctObj.setCity(city);
		logger.info("Value is entered in city testbox.");
		ctObj.clickTempBtn();
		logger.info("Get Temperature button is clicked.");

		if(result.equalsIgnoreCase("valid")) {
			try {driver.switchTo().alert().accept();}catch(Exception e) {}
			wait.until(ExpectedConditions.visibilityOf(ctObj.cityTempResult));
			Assert.assertTrue(ctObj.cityTempResult.isDisplayed());
			logger.info("Result of city temperature is verified.");
		} else if(result.equalsIgnoreCase("invalid")) {
			if(city.equals("")) {
				String ActualResult = "Please fill out this field.";
				Assert.assertEquals(ctObj.cityTextbox.getAttribute("validationMessage"),ActualResult);
				logger.info("Verified the tooltip of the city textbox when nothing entered.");
			}else {
				wait.until(ExpectedConditions.alertIsPresent());
				driver.switchTo().alert().accept();
				logger.warn("Verification of location other than city.");
			}
		}
	}
	
	@Test(priority=9, groups={"functional","regression"})
	public void verifyBackgroundChange() {
		ctObj = new CityTemperature(driver);
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		String city = "Chennai";
		String ExpectedBgColor = "linear-gradient(90deg, rgb(252, 255, 158) 0%, rgb(198, 119, 0) 100%) or linear-gradient(0deg, rgb(253, 26, 7) 0%, rgb(223, 170, 53) 100%)";
		ctObj.setCity(city);
		logger.info("Value is entered in city testbox.");
		ctObj.clickTempBtn();
		logger.info("Get Temperature button is clicked.");
		wait.until(ExpectedConditions.visibilityOf(ctObj.cityTempResult));
		Assert.assertTrue(ExpectedBgColor.contains(ctObj.getBackgroundColor()));
		logger.info("Background verified based on entered city.");
	}
	
	@Test(priority=10, groups={"functional","regression"})
	public void validateMinMaxTemperatures() {
		ctObj = new CityTemperature(driver);
		String city = "Chennai";
		ctObj.setCity(city);
		logger.info("Value is entered in city testbox.");
		ctObj.clickTempBtn();
		logger.info("Get Temperature button is clicked.");
		Assert.assertNotEquals(ctObj.getMinBar(), ctObj.getMaxBar());
		logger.info("Verification of minimum and maximum values of the city temperature.");
	}
}
