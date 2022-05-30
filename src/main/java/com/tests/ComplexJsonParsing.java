package com.tests;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParsing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonPath js = new JsonPath(Paylod.coursePrice());
		int count = js.getInt("courses.size()");
		
		for(int i=0;i<count;i++) {
			
			System.out.println(js.getString("courses["+i+"].title"));
		}
		
		
	}

}
