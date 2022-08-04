package com.sample.jsonbuilder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;

import com.sample.base.BasePage;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PetsRequest extends BasePage{

	public Pet createPetObject(LinkedHashMap<String, String> petData) {
		List<String> photoUrls = null;
		List<Tag> tags = null;
		Pet pet = null;
		try {
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

			pet = new Pet.Builder().withId(BasePage.randomInteger(5)).withName(petData.get("Name"))
					.withPhotoUrls(photoUrls).withStatus(Status.valueOf(petData.get("Status"))).withTags(tags)
					.inCategory(new Category(BasePage.randomInteger(5), petData.get("Category"))).build();
		} catch (Exception e) {
			Reporter.log(e.getMessage());
		}

		return pet;

	}

	public Response addNewPet(String endPoint, Pet pet) {
		Response response = null;
		try {
			RestAssured.reset();
			RestAssured.baseURI = endPoint;

			RequestSpecification rs = RestAssured.given();

			rs.headers("Content-Type", "application/json");
			rs.body(pet);

			response = rs.request(Method.POST);

			Assert.assertEquals(response.getStatusCode(), 200);
		} catch (Exception e) {
			Reporter.log(e.getMessage());
		}

		return response;

	}

	public Response searchPetById(String endPoint, String petId) {
		Response response = null;
		try {
			RestAssured.reset();
			RestAssured.baseURI = endPoint;
			RestAssured.basePath = petId;

			RequestSpecification rs = RestAssured.given();

			rs.contentType("application/json");

			response = rs.request(Method.GET);

			Assert.assertEquals(response.getStatusCode(), 200);
		} catch (Exception e) {
			Reporter.log(e.getMessage());
		}

		return response;

	}

}