package utilities;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

import testBase.BaseClass;


public class Screenshot extends BaseClass{
	static int sscount=0;
	public static String takeScreenshot(WebElement web, String filePath, String testCaseName) {
		sscount++;
        // Capture screenshot as OutputType.FILE
        File screenshot = web.getScreenshotAs(OutputType.FILE);
 
        // Create a count for unique filenames
        String fileName = "ss"+sscount+"_"+testCaseName+".png";
 
        try {
            // Save the screenshot to the specified location
            FileUtils.copyFile(screenshot, new File(filePath + "\\" + fileName));
            System.out.println("Screenshot saved at: " + filePath + "\\" + fileName);
            return (filePath + "\\" + "ss"+(sscount+1)+"_"+testCaseName+".png");
        } catch (Exception e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
            return e.getMessage();
        }
	}
}
