package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TemperatureConverter extends BasePage{
	
	public TemperatureConverter(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//div[@class='tabContainer']/button[1]")
	public WebElement tempConvToggle;
	
	@FindBy(xpath="//div[@class='tabContainer']/button[2]")
	public WebElement cityTempToggle;
	
	@FindBy(xpath="//select[@id='unit']")
	public WebElement unitDropdown;
	public Select selectObj = new Select(unitDropdown);
	
	@FindBy(xpath="//input[@id='temper']")
	public WebElement tempTextbox;
	
	@FindBy(xpath="//input[@value='Convert']")
	public WebElement convertButton;
	
	@FindBy(xpath="//p[@id='result']")
	public WebElement tempConvResult;

	public void clickTempConv() {
		tempConvToggle.click();
	}
	
	public void clickCityConv() {
		cityTempToggle.click();
	}
	
	public void selectUnit(String unit) {
		selectObj.selectByVisibleText(unit);
	}
	
	public void setTemperature(String temp) {
		tempTextbox.sendKeys(temp);
	}
	
	public void clickConvert() {
		convertButton.click();
	}
	
	public String getBackgroundColor() {
		return driver.findElement(By.tagName("body")).getCssValue("background-image");
	}
	
	public String getResult() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(tempConvResult));
		try {
			return (String)((JavascriptExecutor)driver).executeScript("return arguments[0].textContent", tempConvResult);
		}catch(Exception e) {
			return e.getMessage();
		}
	}
}
