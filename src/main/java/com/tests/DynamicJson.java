package com.tests;


import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	
	@Test(dataProvider="isbnData")
	public void addBook(String isbn) {
				
		RestAssured.baseURI = "http://216.10.245.166";
		
		String response = given().header("Content-Type","application/json").
		body(Paylod.addPlaceLibrary(isbn)).
		when().post("Library/Addbook.php").
		then().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js= Utility.rawToJson(response);
		
		String id = js.getString("ID");
		System.out.println(id);
		System.out.println(js.getString("Msg"));
	}
	
	@DataProvider(name="isbnData")
	public Object[] getData() {
	//	return new Object[][] {{},{},{}};
		return new Object[] {"abc","drf","fgfd"};
		
	}
		

}
