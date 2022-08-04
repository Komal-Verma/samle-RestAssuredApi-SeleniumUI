package com.sample.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.sample.lib.DriverLib;

public class BaseTest {
	
	public static WebDriver driver;
	DriverLib driverLib = new DriverLib();
	
	@BeforeSuite
	public void initiatedDriver() {
		driver = driverLib.getDriver();
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	@AfterSuite
	public void closeDriver() {
		driver.quit();
	}
}

