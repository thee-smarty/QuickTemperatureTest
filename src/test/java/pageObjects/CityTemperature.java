package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CityTemperature extends BasePage{

	public CityTemperature(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//div[@class='tabContainer']/button[1]")
	public WebElement tempConv;
	
	@FindBy(xpath="//div[@class='tabContainer']/button[2]")
	public WebElement cityTemp;
	
	@FindBy(xpath="//input[@id='city']")
	public WebElement cityTextbox;
	
	@FindBy(xpath="//input[@value='Get Temperature']")
	public WebElement getTempButton;
	
	@FindBy(xpath="//p[@id='cityResult']")
	public WebElement cityTempResult;
	
	@FindBy(xpath="//div[@id='chart_div']")
	public WebElement barchart;
	
	@FindBy(xpath="//tbody/tr/td[2]")
	public WebElement minBar;
	
	@FindBy(xpath="//tbody/tr/td[3]")
	public WebElement maxBar;
	
	@FindBy(xpath="//body")
	public WebElement background;
	

	public void clickTempConv() {
		tempConv.click();
	}
	
	public void clickCityConv() {
		cityTemp.click();
	}
	
	public void setCity(String city) {
		cityTextbox.sendKeys(city);
	}
	
	public void clickTempBtn() {
		getTempButton.click();
	}
	
	public String getMinBar() {
		return (String)((JavascriptExecutor)driver).executeScript("return arguments[0].textContent", minBar);
	}
	
	public String getMaxBar() {
		return (String)((JavascriptExecutor)driver).executeScript("return arguments[0].textContent", maxBar);
	}
	
	public String getBackgroundColor() {
		return driver.findElement(By.tagName("body")).getCssValue("background-image");
	}
	
	public void clearCityTextbox() {
		cityTextbox.clear();
	}
}
