package com.sample.uipages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;

import com.sample.base.BasePage;

public class SampleAppUI extends BasePage {
	// page elements
	public By editBox(String editName) {
		return By.name(editName);
	}

	public By loginLogoutBtn = By.id("login");

	public By loginMsg = By.id("loginstatus");

	// step Methods
	public void login(String username, String password) {
		sendKeys(editBox("UserName"), username);
		sendKeys(editBox("Password"), password);
		click(loginLogoutBtn);
	}

	public void verifyloginMsg(String msg) {
		assertTrue(getText(loginMsg).equalsIgnoreCase(msg));
	}

}
