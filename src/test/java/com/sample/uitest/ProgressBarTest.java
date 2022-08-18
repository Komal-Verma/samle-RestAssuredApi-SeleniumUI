package com.sample.uitest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import com.sample.base.BasePage;
import com.sample.base.BaseTest;
import com.sample.uipages.ProgressBarUI;

public class ProgressBarTest extends BaseTest {

	ProgressBarUI progressBarUI;

	@BeforeMethod
	public void launchProgreeBarUrl() {
		progressBarUI = new ProgressBarUI();
		BasePage.openUrl("http://uitestingplayground.com/progressbar", "Progress Bar");
	}

	@Test
	public void startStopProgress() {
		progressBarUI.clickOnStartOrStopButton("Start");
		progressBarUI.waitUntilStatusValue("35");
		progressBarUI.clickOnStartOrStopButton("Stop");
		progressBarUI.verifyResult("-40");
	}
}