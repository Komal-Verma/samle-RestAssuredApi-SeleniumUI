package com.sample.uitestinghomepage;

import org.openqa.selenium.By;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class HomeUIPageElements
{
	public By button(String buttonText) {
		return By.id(buttonText.toLowerCase()+"Button");
	}
	
	public By progressBar = By.id("progressBar");
    
	public By resultTag =  By.xpath("//p[@id='result']");	
    
}
