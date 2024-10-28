package Section_12_demoSerialization_SpecBuilder;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Location;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class serializeTest {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		// Serialization
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setName("Frontline house");
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setAddress("29, side layout, cohen 09");
		ap.setWebsite("http://google.com");
		ap.setLanguage("French-IN");

		List<String> mylist = new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		ap.setTypes(mylist);

		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		ap.setLocation(loc);

		Response res = given().log().all().queryParam("key", "qaclick123").body(ap).when()
				.post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().response();

		String responseString = res.asString();
		System.out.println(responseString);

	}

}
