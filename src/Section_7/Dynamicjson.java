package Section_7;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.ReUsableMethods;
import Files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Dynamicjson {
	
//	1. Dynamically build json payload with external data inputs 
//		& Parameterize the API tests With Single Data Sets
	
	@Test
	public void addBook()
	{
		RestAssured.baseURI="http://216.10.245.166";
		String repo = given().header("Content-Type","application/json")
				.body(payload.addbook("tyyrh", "65757"))
		
		.when().post("/Library/Addbook.php")
		
		.then().assertThat().statusCode(200).extract().response().asString();
		
		
//		JsonPath js= ReUsableMethods.rawtojson(repo);
		JsonPath js = ReUsableMethods.rawtojson(repo);
		String idd = js.get("ID");
		System.out.println(idd);
		
	}

}
