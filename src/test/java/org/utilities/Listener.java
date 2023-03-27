package org.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestContext;  
import org.testng.ITestListener;  
import org.testng.ITestResult;  
public class Listener implements ITestListener   
{  
	WebDriver driver= null;
	String filePath = "C:\\Users\\vivek\\eclipse-workspace\\New\\Screnshots";
	
	
	public void onTestStart(ITestResult result) {  

	}  

	public void onTestSuccess(ITestResult result) {  

		System.out.println("Success of test cases and its details are : "+result.getName());  
	}  
	public void onTestFailure(ITestResult result) {
		System.out.println("***** Error "+result.getName()+" test has failed *****");

		String methodName = result.getName().toString().trim();
		ITestContext context = result.getTestContext();
		WebDriver driver = (WebDriver)context.getAttribute("driver");
		System.out.println(driver);
		takeScreenShot(methodName, driver);
	}

	public void takeScreenShot(String methodName, WebDriver driver) {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//The below method will save the screen shot in d drive with test method name 
		try {
			FileUtils.copyFile(scrFile, new File(filePath+methodName+".png"));
			System.out.println("***Placed screen shot in "+filePath+" ***");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {  
		// TODO Auto-generated method stub  
		System.out.println("Skip of test cases and its details are : "+result.getName());  
	}  

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {  
		// TODO Auto-generated method stub  
		System.out.println("Failure of test cases and its details are : "+result.getName());  
	}  

	public void onStart(ITestContext context) {  

	}  

	public void onFinish(ITestContext context) {  

	}  
}  