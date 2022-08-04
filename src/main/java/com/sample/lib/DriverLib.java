package com.sample.lib;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverLib {
	
	private WebDriver driver;
	
	public WebDriver getDriver() {
		setDriver();
		return driver;
	}
	public void setDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
}

