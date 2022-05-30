package com.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import groovy.util.logging.Log;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Basics {

	public static void main(String[] args) throws IOException {
		

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		// here we are trying to convert json file to string and pass it as parameter to
		// body() method
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(new String(Files.readAllBytes(Paths.get(
						"D:\\Access to All the Folders And Files\\API Interview Guide\\RahulShetty\\addPlace.json"))))
				.when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().response()
				.asString();

		
		/*
		 * String response =
		 * given().log().all().queryParam("key","qaclick123").header("Content-Type",
		 * "application/json").
		 * body(Paylod.addPlace()).when().post("/maps/api/place/add/json")
		 * .then().assertThat().statusCode(200).extract().response().asString();
		 */	  
		  
		  // System.out.println(response);
		  
		  JsonPath js = new JsonPath(response); String place =
		  js.getString("place_id");
		  
	//	  For updation using put method 
		  String newAddress = "70 Summer walk, USA";
		  given().log().all().queryParam("key","qaclick123").header("Content-Type",
		  "application/json"). body("{\r\n" + "\"place_id\":\""+place+"\",\r\n" +
		  "\"address\":\""+newAddress+"\",\r\n" + "\"key\":\"qaclick123\"\r\n" +
		  "}\r\n" + "").when().put("/maps/api/place/update/json").
		  then().assertThat().log().all().statusCode(200).body("msg",
		  equalTo("Address successfully updated"));
		  
	//	  Get Request 
		  String res =
		  given().log().all().queryParam("key","qaclick123").queryParam("place_id",
		  place).
		  when().get("/maps/api/place/get/json").then().log().all().assertThat().
		  statusCode(200).extract().asString(); JsonPath jsGet = new JsonPath(res);
		  String actAddress = jsGet.getString("address");
		  System.out.println("Address are same "+newAddress.equals(actAddress));
		  Assert.assertEquals(newAddress, actAddress);
		 
	}

}
