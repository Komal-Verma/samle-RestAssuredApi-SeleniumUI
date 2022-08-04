package com.sample.uitestinghomepage;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.sample.base.BasePage;

public class SampleAppUI extends BasePage {
	// page elements
	public By editBox(String editName) {
		return By.name(editName);
	}

	public By loginLogoutBtn = By.id("login");
	
	public By loginMsg = By.id("loginstatus");

	// step Methods
	public void login(String username,String password) {
		try {
			sendKeys(editBox("UserName"),username);
			sendKeys(editBox("Password"),password);
			click(loginLogoutBtn);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void verifyloginMsg(String msg) {
		try {
			assertTrue(getText(loginMsg).equalsIgnoreCase(msg));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	

}
