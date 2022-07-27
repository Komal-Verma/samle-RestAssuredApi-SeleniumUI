package com.sample.apitest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sample.jsonbuilder.AddPetJsonBuilder;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
 
public class APITestPetStore {
 
	public enum status {
		available, pending, sold
	}
	
	AddPetJsonBuilder addPet;
	Map<String,Object> jsonBodyUsingMap;
	
	@BeforeMethod
	public void petInfo() {
		Integer petId = new Random().nextInt(100);
		String  petName = "Dog"+new Random().nextInt(100);
		
		AddPetJsonBuilder addPet = new AddPetJsonBuilder();
		
		addPet.setId(petId);
		
		Map<String,Object> category = new HashMap<>();
		category.put("id", new Random().nextInt(100));
		category.put("name", "Dog");
		addPet.setCategory(category);
		
		addPet.setName(petName);
		
		List<String> photoUrls = new ArrayList<String>();
		addPet.setPhotoUrls(photoUrls);
		
		Map<String,Object> tag = new HashMap<>();
		tag.put("id", new Random().nextInt(100));
		tag.put("name", "BullDog");
		addPet.setTags(tag);
		tag = new HashMap<>();
		tag.put("id", new Random().nextInt(100));
		tag.put("name", "Black");
		addPet.setTags(tag);
		
		addPet.setStatus(status.available.toString());
		
		jsonBodyUsingMap = addPet.getPetJson();
	}
	
	@Test
	public void addPetAndVerify() {		
		
		RestAssured.baseURI = "https://petstore.swagger.io/v2/pet";
		
		RequestSpecification rs = RestAssured.given();
		rs.contentType("application/json");
		rs.body(jsonBodyUsingMap);
 
		Response response = rs.request(Method.POST);
 
		System.out.println(response.asString());
		System.out.println(response.statusCode());
		System.out.println(response.getBody().jsonPath().getString("name"));
		assertTrue(response.statusCode() == 200);
		assertTrue(response.getBody().jsonPath().getString("id").toString().equalsIgnoreCase(String.valueOf(jsonBodyUsingMap.get("id"))));
		
	}
	
}