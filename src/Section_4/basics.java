package Section_4;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;

public class basics {

	public static void main(String[] args) {

		//Validate if Add Place API is working as Expected
		
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		
		given()
		.queryParam("key", "qaclick123").header("Content-Type","application/json" )
		.body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Himanshu Sigh ji\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 823 6140\",\r\n"
				+ "  \"address\": \"faridabad\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}").
		
		when()
		.post("maps/api/place/add/json").
		
		then().log().all().assertThat().statusCode(200);
		
	}

}
