package com.tests;

import io.restassured.path.json.JsonPath;

public class Utility {
	
	public static JsonPath rawToJson(String payload) {
		
		JsonPath js = new JsonPath(payload);
		return js;
	}

}
