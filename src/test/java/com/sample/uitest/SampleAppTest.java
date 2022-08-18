package com.sample.uitest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import com.sample.base.BasePage;
import com.sample.base.BaseTest;
import com.sample.uipages.SampleAppUI;

public class SampleAppTest extends BaseTest {

	SampleAppUI sampleAppUI;

	@BeforeMethod
	public void launchSampleAppUrl() {
		sampleAppUI = new SampleAppUI();
		BasePage.openUrl("http://uitestingplayground.com/sampleapp", "Sample App");
	}

	@Test
	public void invalidLoginTest() {
		sampleAppUI.login("", "pwd");
		sampleAppUI.verifyloginMsg("Invalid username/password");
	}

	@Test
	public void validLoginTest() {
		String username = BasePage.randomString(10);
		sampleAppUI.login(username, "pwd");
		sampleAppUI.verifyloginMsg("Welcome, " + username + "!");

	}

}
