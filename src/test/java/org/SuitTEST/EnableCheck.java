package org.SuitTEST;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.utilities.BaseUtility;

public class EnableCheck {


	WebDriver driver;
	
	@Test(priority = 1 )
	public void login() {
		String url = "https://sfa.contineonx.com";
		driver = BaseUtility.bu.startupUsingWM(url, "ed");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// User name credentials
		driver.findElement(By.xpath("//input[@aria-label='Username']")).sendKeys("Autouser");
		// Password Credentials
		driver.findElement(By.xpath("//input[@aria-label='Password']")).sendKeys("Omkar@123");
		//  click on login button
		driver.findElement(By.xpath("//span[contains(text(),'Login')]")).click();
		// click on Home Link
		driver.findElement(By.xpath("//span[contains(text(),'I Agree')]")).click();

	}


	@Test(priority=2)
	public void EntityAdd() throws InterruptedException {
		//  click on Meetings drop
		WebElement Master =driver.findElement(By.xpath("//div[contains(text(),'Account Management')]"));
		BaseUtility.bu.clickByJS(Master, driver);
		//click on Meetings link
		WebElement Account =driver.findElement(By.xpath("//div[contains(@class,'q-item__section column q-item__section--main justify-center')][normalize-space()='Accounts']"));
		BaseUtility.bu.clickByJS(Account, driver);
		// click on meeting Add +
		WebElement	Add = driver.findElement(By.xpath("//i[normalize-space()='add']")); 
		BaseUtility.bu.clickByJS(Add, driver);
		BaseUtility.bu.isAlertPresent( driver, 4);
		Thread.sleep(2000);
		
	
		
	}

	@Test(priority=3)
	public void Checking() throws InterruptedException {
		//  click on Meetings drop
		WebElement Name =driver.findElement(By.xpath("//div[contains(@functionid,'.0a9c946e_c6c6_4f57_8494_22c6a9c0c42c')]//label//div"));
		//BaseUtility.bu.clickByJS(Name, driver);
		Name.isEnabled();
		System.out.println("Result="+Name.isEnabled());
		 String bc =Name.getAttribute("readonly");
		 System.out.println(bc);
		
		//click on Meetings link
		WebElement user =driver.findElement(By.xpath("//input[@aria-label='Creation Flag']"));
		 String abc =user.getAttribute("readonly");
		 System.out.println("readonly="+ abc);
		
		
	}

}
