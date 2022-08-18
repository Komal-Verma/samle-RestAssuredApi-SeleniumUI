package com.sample.uipages;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.sample.base.BasePage;

public class ProgressBarUI extends BasePage {

	// page elements
	public By button(String buttonText) {
		return By.id(buttonText.toLowerCase() + "Button");
	}

	public By progressBar = By.id("progressBar");

	public By resultTag = By.xpath("//p[@id='result']");

	// step Methods
	public void clickOnStartOrStopButton(String buttonName) {
		driver.findElement(button(buttonName)).click();
	}

	public void verifyResult(String resultValue) {
		assertTrue(driver.findElement(resultTag).getText().startsWith("Result: " + resultValue));
	}

	public void waitUntilStatusValue(String statusValue) {
		Wait<WebDriver> wait;
		wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofMillis(5));
		wait.until(ExpectedConditions.attributeToBe(driver.findElement(progressBar), "aria-valuenow", statusValue));

	}

}
