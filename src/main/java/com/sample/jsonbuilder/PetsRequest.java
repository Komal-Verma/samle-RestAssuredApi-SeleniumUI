package com.sample.jsonbuilder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.base.BasePage;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PetsRequest {

	public Pet createPetObject(LinkedHashMap<String, String> petData) {
		List<String> photoUrls = null;
		List<Tag> tags = null;
		Pet pet = null;

		photoUrls = new ArrayList<>();
		String pUrls = petData.get("PhotoUrls");
		if (pUrls != null) {
			String[] urls = pUrls.split(";");
			for (String url : urls)
				photoUrls.add(url);
		}

		tags = new ArrayList<>();
		String tagsList = petData.get("Tags");
		if (tagsList != null) {
			String[] tagsArray = tagsList.split(";");
			for (String tag : tagsArray)
				tags.add(new Tag(BasePage.randomInteger(5), tag));
		}

		pet = new Pet.Builder().withId(BasePage.randomInteger(5)).withName(petData.get("Name")).withPhotoUrls(photoUrls)
				.withStatus(Status.valueOf(petData.get("Status"))).withTags(tags)
				.inCategory(new Category(BasePage.randomInteger(5), petData.get("Category"))).build();

		return pet;

	}

	public Response addNewPet(String endPoint, Pet pet) {
		Response response = null;

		RestAssured.reset();
		RestAssured.baseURI = endPoint;

		RequestSpecification rs = RestAssured.given();

		rs.headers("Content-Type", "application/json");
		rs.body(pet);

		response = rs.request(Method.POST);

		Assert.assertEquals(response.getStatusCode(), 200);

		return response;

	}

	public Response searchPetById(String endPoint, String petId) {
		Response response = null;

		RestAssured.reset();
		RestAssured.baseURI = endPoint;
		RestAssured.basePath = petId;

		RequestSpecification rs = RestAssured.given();

		rs.contentType("application/json");

		response = rs.request(Method.GET);

		Assert.assertEquals(response.getStatusCode(), 200);

		return response;

	}
	
	public void compareResponse(Response response1, Response response2) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper objectMapper;
		JsonNode jsonNode1;
		JsonNode jsonNode2;
		
		objectMapper = new ObjectMapper();
		jsonNode1 = objectMapper.readTree(response1.getBody().asString());
		jsonNode2 = objectMapper.readTree(response2.getBody().asString());
		
		Assert.assertTrue(jsonNode1.equals(jsonNode2));
		
	}

}