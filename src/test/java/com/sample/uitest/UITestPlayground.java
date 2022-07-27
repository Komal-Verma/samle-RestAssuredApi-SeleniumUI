package com.sample.uitest;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.sample.uitestinghomepage.HomeUIPageElements;
 
public class UITestPlayground extends HomeUIPageElements{
	
	HomeUIPageElements homeUIPageElements;
	WebDriver driver;
	Wait<WebDriver> wait;
	
	@SuppressWarnings("deprecation")
	@BeforeTest
	public void launchUITestPlayGroundUrl() {
		homeUIPageElements = new HomeUIPageElements(); 
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://uitestingplayground.com/progressbar");
		
		wait = new FluentWait(driver)
				.withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.MILLISECONDS);

		wait.until(ExpectedConditions.titleIs("Progress Bar"));
	}
 
	
	@Test
	public void startStopProgress() {
		try {
			driver.findElement(homeUIPageElements.button("Start")).click();
			
			wait.until(ExpectedConditions.attributeToBe(driver.findElement(homeUIPageElements.progressBar), "aria-valuenow","35"));
			
			driver.findElement(homeUIPageElements.button("Stop")).click();
			
			System.out.println(driver.findElement(homeUIPageElements.resultTag).getText());
			
			assertTrue(driver.findElement(homeUIPageElements.resultTag).getText().startsWith("Result: -40,"));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}
	
}

