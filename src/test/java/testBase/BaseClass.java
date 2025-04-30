package testBase;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import utilities.Screenshot;

public class BaseClass{

	public WebDriver driver;
	public Logger logger;
	public static String imagePath;
	 
	
	@BeforeClass(groups= {"ui","smoke","regression","functional"})
	@Parameters({"browser"})  
	public void setup(String browser) {
		logger = LogManager.getLogger(this.getClass());
		switch(browser) {
		case "edge" : 
			driver = new EdgeDriver();
			logger.info("Edge browser started.");
			break;
		case "chrome" : 
			driver = new ChromeDriver(); 
			logger.info("Chrome browser started.");
			break;
		case "firefox" : 
			driver = new FirefoxDriver(); 
			logger.info("Firefox browser started.");
			break;
		default : System.out.println("Invalid browser name"); return;
		}
		
		driver.get("https://quick-temperature-zeta-umber.vercel.app/");
		logger.info("Appilication opened in browser.");
		driver.manage().deleteAllCookies();
		logger.info("Browser cookies deleted.");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		logger.info("Browser window maximized.");
	}
	
	@BeforeMethod(groups= {"ui","smoke","regression","functional"})
	public void testSetUp(ITestResult result) {
		logger = LogManager.getLogger(this.getClass());
		logger.info("Starting "+result.getMethod().getMethodName());
	}
	
	@AfterMethod(groups= {"ui","smoke","regression","functional"})
	public void pageRefresh(ITestResult result) {
		logger = LogManager.getLogger(this.getClass());
		try {
			driver.switchTo().alert().accept();
		}catch(Exception e) {}
		imagePath = Screenshot.takeScreenshot(driver.findElement(By.xpath("//body")), "C:\\Users\\2389221\\eclipse-workspace\\hackathon\\screenshots",result.getMethod().getMethodName());
		logger.info("Test case screenshot taken.");

		if(result.getStatus() == ITestResult.SUCCESS) {
			logger.info("Test " + result.getMethod().getMethodName() + " passed.");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			logger.error("Test " + result.getMethod().getMethodName() + " failed.");
			logger.debug("Something went wrong in " + result.getMethod().getMethodName() + " test case.");
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.warn("Test " + result.getMethod().getMethodName() + " skipped.");
		}
		logger.info("Finished "+result.getMethod().getMethodName());
		driver.navigate().refresh();
		logger.info("Browser refreshed for new instance.");
	}
	
	
	@AfterClass(groups= {"ui","smoke","regression","functional"})
	public void teardown() {
		logger.info("Browser Closed.");
		driver.quit();
	}
}
