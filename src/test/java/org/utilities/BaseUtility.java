package org.utilities;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public  class BaseUtility {
	public static BaseUtility bu = new BaseUtility();
	public static SoftAssert softAssert = new SoftAssert();
	public WebDriver startup(String url,String browserName) {
		String path = System.getProperty("user.dir");
		WebDriver driver=null;
		if(browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("ch")) {
			System.setProperty("webdriver.chrome.driver",path+"\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if(browserName.equalsIgnoreCase("firefox") || browserName.equalsIgnoreCase("ff")) {
			System.setProperty("webdriver.gecko.driver",path+"\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		//		driver.manage().window().setSize(new Dimension(200, 200));
		//driver.manage().window().minimize();
		driver.get(url);
		return driver;
	}


	public WebDriver startupUsingWM(String url,String browserName) {
		WebDriver driver=null;
		if(browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("ch")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		}else if(browserName.equalsIgnoreCase("edge") || browserName.equalsIgnoreCase("ed")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		//		driver.manage().window().setSize(new Dimension(200, 200));
		//driver.manage().window().minimize();
		driver.get(url);
		return driver;
	}
	public void closeAdPopup(WebDriver driver) {
		driver.findElement(By.cssSelector("img[alt='adplus-dvertising']")).click();
	}
	public boolean isAlertPresent(WebDriver driver, int time) {
		try {
			//WebDriverWait wt = new WebDriverWait(driver, time);
			WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(time));
			wt.until(ExpectedConditions.alertIsPresent());
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	public void scrollTillElement(WebElement ele, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", ele);
	}
	public void clickByJS(WebElement ele, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor)driver; 
		js.executeScript("arguments[0].click();", ele);
	}
	public void clickByAC(WebElement ele, WebDriver driver) {
		Actions act = new Actions(driver);
		act.moveToElement(ele).click().build().perform();
	}
	
	
	
	public void clickBySendKeyes(WebElement ele,String drop, WebDriver driver) {
		Actions act = new Actions(driver);
		act.sendKeys(ele,drop).perform();
	}
	
	
	

	public void forElementToBeDisplayed(WebElement element,WebDriver driver) {

		try {

			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));;
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			Thread.sleep(500);
		}  catch (NoSuchElementException e) {
			softAssert.fail("NoSuchElementException Exception, " + element.toString() + " Element is not present.");
		}
		catch (ElementNotVisibleException e) {
			softAssert.fail("ElementNotVisibleException Exception, " + element.toString() + " Element is not visible.");
		}
		catch(Exception e){
			System.out.println(e);
		}
	}


}
