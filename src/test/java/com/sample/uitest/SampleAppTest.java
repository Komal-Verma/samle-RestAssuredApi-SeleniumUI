package com.sample.uitest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import com.sample.base.BasePage;
import com.sample.base.BaseTest;
import com.sample.uitestinghomepage.SampleAppUI;

public class SampleAppTest extends BaseTest{
	
	SampleAppUI sampleAppUI;
	
	@BeforeMethod
	public void launchSampleAppUrl() {
		sampleAppUI = new SampleAppUI(); 
		BasePage.openUrl("http://uitestingplayground.com/sampleapp","Sample App");
	}
 
	@Test(priority = 1)
	public void invalidLoginTest() {		
		sampleAppUI.login("", "pwd");
		sampleAppUI.verifyloginMsg("Invalid username/password");					
	}
	
	@Test(priority = 2)
	public void validLoginTest() {
		String username = BasePage.randomString(10);
		sampleAppUI.login(username, "pwd");
		sampleAppUI.verifyloginMsg("Welcome, "+username+"!");
			
	}
	
	
}

