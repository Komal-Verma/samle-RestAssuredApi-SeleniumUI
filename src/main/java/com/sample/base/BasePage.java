package com.sample.base;

import java.time.Duration;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class BasePage
{	
	protected static WebDriver driver;
	BaseTest baseTest;
	
	public BasePage() {
		baseTest = new BaseTest();
		BasePage.driver = baseTest.getDriver();
	}
	
	//step Methods
	
	public static void openUrl(String url, String ...titleAndOtherStrings) {
		try {
			driver.get(url);
			if(titleAndOtherStrings != null) {
				Wait<WebDriver> wait;
				wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				        .pollingEvery(Duration.ofMillis(5));

				wait.until(ExpectedConditions.titleIs(titleAndOtherStrings[0]));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void click(By by) {
		try {
			driver.findElement(by).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
	
	public static String getText(By by) {
		try {
			return driver.findElement(by).getText();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static void sendKeys(By by, String value) {
		try {
			driver.findElement(by).sendKeys(value);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
	
	public static String randomString(int length) {
	
		return RandomStringUtils.randomNumeric(length);
//		String strChrString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//		Random rd = new Random();
//		StringBuilder rndStr = new StringBuilder();
//		for(int i = 0; i < length; i++) {
//			rndStr.append(strChrString.charAt(rd.nextInt(strChrString.length())));
//		}
//		return rndStr.toString();
		
	}
	
	public static Integer randomInteger(int length) {
		return Integer.valueOf(RandomStringUtils.randomNumeric(length));
		
//		String strChrString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//		Random rd = new Random();
//		StringBuilder rndStr = new StringBuilder();
//		for(int i = 0; i < length; i++) {
//			rndStr.append(strChrString.charAt(rd.nextInt(strChrString.length())));
//		}
//		return rndStr.toString();
		
	}
	
}
