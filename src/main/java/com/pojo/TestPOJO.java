package com.pojo;

import static io.restassured.RestAssured.given;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tests.Paylod;

import io.restassured.RestAssured;

public class TestPOJO {

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "http://216.10.245.166";
		
		/*BookDetails[] bd = given().expect().
		when().get("Library/GetBook.php?ID=abc264").as(BookDetails[].class);
		*/
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		String response = given().when().get("Library/GetBook.php?ID=abc264").then().extract().response().asString();


		Dashboard db = objectMapper.readValue(Paylod.coursePrice(), Dashboard.class);
		//Quite an intersting topics, getting lot of errors while deserailization
		List<BookDetails> bd = (List<BookDetails>) objectMapper.readValue(response, BookDetails.class);
		System.out.println(bd.get(0).getAuthor());
		System.out.println(db.getDashboard().getPurchaseAmount());
	//	System.out.println(db.getCourse().);
		
				
		
		//System.out.println(bd[0].getBookName());
	}

}
