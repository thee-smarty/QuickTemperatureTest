package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.TemperatureConverter;
import testBase.BaseClass;
import utilities.DataProviders;

public class TemperatureConverterTest extends BaseClass {
	
	TemperatureConverter tcObj;
	WebDriverWait wait;
	public Logger logger;
	
	@BeforeClass(groups= {"ui","smoke","regression","functional"})
	public void setUpLogger() {
		logger = LogManager.getLogger(this.getClass());
	}

	@Test(priority=1, groups={"functional","ui"})
	public void openURL() {
		String ExpectedTitle = "Temperature Converter";
		Assert.assertTrue(driver.getTitle().contains(ExpectedTitle));
		logger.info("Web application is displayed.");
		logger.info("Temperature Converter is displayed.");
	}
	
	@Test(priority=2, groups={"ui","functional"})
	public void validateTempConvUI() {
		tcObj = new TemperatureConverter(driver); 
		Assert.assertTrue(tcObj.tempConvToggle.isDisplayed());
		Assert.assertTrue(tcObj.cityTempToggle.isDisplayed());
		Assert.assertTrue(tcObj.unitDropdown.isDisplayed());
		Assert.assertTrue(tcObj.tempTextbox.isDisplayed());
		Assert.assertTrue(tcObj.convertButton.isDisplayed());
		logger.info("Temperature Converter page elements are visible.");
	}
	
	// Getting data providers from different class
	@Test(dataProvider="UnitData", dataProviderClass=DataProviders.class, groups={"functional","regression"}, priority=3) // 3,4,5
	public void funSelectUnit(String unit) {
		tcObj = new TemperatureConverter(driver);
		tcObj.selectUnit(unit);
		logger.info("Unit dropdown element is selected.");
		Assert.assertEquals(tcObj.selectObj.getFirstSelectedOption().getText(), unit);
		logger.info("Selected unit in unit dropdown is verified.");
	}
	
	
	@SuppressWarnings("deprecation")
	@Test(priority=4, groups={"functional","regresssion"})
	public void funSelectNullUnit() {
		tcObj = new TemperatureConverter(driver);
		tcObj.clickTempConv();
		logger.info("Convert button is clicked.");
		String ActualResult = "Please select an item in the list.";
		Assert.assertEquals(tcObj.unitDropdown.getAttribute("validationMessage"),ActualResult);
		logger.info("Verified the tooltip of the unit dropdown when none selected.");
	}
	
	@SuppressWarnings("deprecation")
	@Test(dataProvider="TemperatureData", dataProviderClass=DataProviders.class, groups={"smoke","functional"}, priority=5) //7,8,9,10
	public void funTempConv(String unit, String temperature, String result) {
		tcObj = new TemperatureConverter(driver);
		tcObj.selectUnit(unit);
		logger.info("Unit is selected in unit dropdown.");
		tcObj.setTemperature(temperature);
		logger.info("Value entered in temperature textbox.");
		tcObj.clickConvert();
		logger.info("Convert button is clicked.");
		
		if(result.equalsIgnoreCase("valid")) {
			double temp = Double.parseDouble(temperature);
			String ActualResult = tcObj.getResult().replaceAll("\\s", "");
			String ExpectedResult =String.format("Celsius:%.2f°CFahrenheit:%.2f°FKelvin:%.2fK",temp,(temp*9.0/5.0)+32.0,(temp + 273.15));
			Assert.assertEquals(ActualResult,ExpectedResult);
			logger.info("Result of temperature conversion is verified.");
		}else if(result.equalsIgnoreCase("invalid")){
			if(temperature.equals("")) {
				String ActualResult = "Please fill out this field.";
				Assert.assertEquals(tcObj.tempTextbox.getAttribute("validationMessage"),ActualResult);
				logger.info("Verified the tooltip of the temperature textbox when nothing entered.");
			}else {
				Assert.assertTrue(tcObj.tempConvResult.isDisplayed(),"The temperature textbox shows the error pops up saying \"please enter a valid value. The two nearest valid values are 2 and 3.\"");
				logger.warn("Verification of the temperature conversion result.");
			}
		}
	}
	
	@Test(dataProvider="BackgroundChangeData", dataProviderClass=DataProviders.class, groups={"functional","regression"}, priority=6) //11,12,13
	public void verifyBackgroundChange(String unit, String temperature, String result) {
		tcObj = new TemperatureConverter(driver);
		tcObj.selectUnit(unit);
		logger.info("Unit is selected in unit dropdown.");
		tcObj.setTemperature(temperature);
		logger.info("Value entered in temperature textbox.");
		tcObj.clickConvert();
		logger.info("Convert button is clicked.");
		Assert.assertEquals(tcObj.getBackgroundColor(), result);
		logger.info("Background verified based on the inputs.");
	}
}
