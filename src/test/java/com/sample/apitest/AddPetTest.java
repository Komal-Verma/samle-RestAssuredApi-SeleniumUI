package com.sample.apitest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sample.base.BaseTest;
import com.sample.jsonbuilder.Pet;
import com.sample.jsonbuilder.PetsRequest;
import com.sample.lib.Excel;

import io.restassured.response.Response;

import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.Assert;

public class AddPetTest extends BaseTest{

	PetsRequest petsRequest;
	String baseUri = "https://petstore.swagger.io/v2/pet";
	
	@BeforeMethod
	public void beforeMethod() {
		petsRequest = new PetsRequest();
	}

	@Test(dataProvider = "AddPetData")
	public void addNewPetAndVerify(LinkedHashMap<String, String> testData) {	

		Pet pet = petsRequest.createPetObject(testData);

		Response petPostResponse = petsRequest.addNewPet(baseUri, pet);
		
		String petId = petPostResponse.getBody().jsonPath().getString("id");

		Response petGetResponse = petsRequest.searchPetById(baseUri, petId);

		String petName = petGetResponse.getBody().jsonPath().getString("name");

		Assert.assertEquals(petName, pet.getName());

	}

	@DataProvider(name = "AddPetData")
	public static Object[][] petTestData() {

		List<LinkedHashMap<String, String>> testData = Excel.readExcelHashMap(
				Paths.get("").toAbsolutePath().toString() + "//src//test//resources//Test Data//PetData//TestData.xlsx",
				"AddPet", "Execute", "Y");

		Object[][] ob = new Object[testData.size()][1];

		Iterator<LinkedHashMap<String, String>> tdRow = testData.iterator();

		int i = 0;
		while (tdRow.hasNext()) {
			ob[i][0] = tdRow.next();
			i++;

		}
		return ob;
	}

}