package Section_5;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.ReUsableMethods;
import Files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class basics {

	public static void main(String[] args) {

		// Validate if Add Place API is working as Expected

		RestAssured.baseURI = "https://rahulshettyacademy.com/";

	String response = 	given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(payload.AddPlace())
				
				
				.when().post("maps/api/place/add/json").

				then()
				.assertThat()
				.statusCode(200)
				.body("scope", equalTo("APP")).header("Server", equalTo("Apache/2.4.52 (Ubuntu)"))
		
		
/*Add Place-> Update Place with New Address -> Get Place to validate if new Address is present 
		in response or  not*/
				
				.extract().response().asString();
	
	System.out.println(response); //response body will print
	
	JsonPath js= new JsonPath(response); //JsonPath is the one which takes string as an input and convert that into Json
	String placeID= js.getString("place_id");
	System.out.println(placeID);
	
	//Update Place
	String address ="Faridavad";
	given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
	.body("{\r\n"
			+ "    \"place_id\": \""+placeID+"\",\r\n"
			+ "    \"address\": \""+address+"\",\r\n"
			+ "    \"key\": \"qaclick123\"\r\n"
			+ "}")
	
	.when().put("maps/api/place/update/json")
	
	.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
	

	//Get Place/Location
	String getadd = given().log().all().queryParam("key", "qaclick123")
	.queryParam("place_id", placeID)
	
	.when().get("/maps/api/place/get/json")
	
	.then().assertThat().log().all().statusCode(200).extract().response().asString();
	
	
	//JsonPath jjs =new JsonPath(getadd);
	JsonPath js1 =ReUsableMethods.rawtojson(getadd);
	String vAdd = js1.getString("address");
	
	System.out.println(vAdd);
	
	Assert.assertEquals(vAdd, address);
	
	
	}

}
