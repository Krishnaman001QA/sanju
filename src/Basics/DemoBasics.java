package Basics;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.ReUsableMethods;
import Files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DemoBasics {

	public static void main(String[] args) {

//Add place
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String respose = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(payload.AddPlace())
		//How to handle	with JSON static Payloads
				//.body(new String(Files.readAllBytes(Paths.get("C:\\User\\owner\\Documents\\addPlace.json"))))
				
				

				.when().post("/maps/api/place/add/json")
				
				.then().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response()
				.asString();

		System.out.println("All we Got : " + respose);

		JsonPath js = new JsonPath(respose);
		String placeID = js.getString("place_id");
		System.out.println(placeID);

//update place

		String newadd = "faridabad";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "    \"place_id\": \"" + placeID + "\",\r\n" + "    \"address\": \"" + newadd
						+ "\",\r\n" + "    \"key\": \"qaclick123\"\r\n" + "}")

				.when().put("/maps/api/place/update/json")

				.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));

//get Place

		String getaddr = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)

				.when().get("/maps/api/place/get/json")

				.then().assertThat().log().all().statusCode(200).extract().response().asString();

		JsonPath jss = ReUsableMethods.rawtojson(getaddr);
		String actualadd = jss.getString("address");

		System.out.println(actualadd);

		Assert.assertEquals(actualadd, newadd);

	}

}
//"address": "29, side layout, cohen 09",