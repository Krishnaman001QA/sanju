package Section_12_demoSerialization_SpecBuilder;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Serialization_SpecBuilder {

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

		// Serialization_SpecBuilder

		// Request Spec Builder
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

		// Response Spec Builder
		ResponseSpecification respo = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();

		RequestSpecification res = given().spec(req).body(ap);

		
		
		
		Response response = res.when().post("maps/api/place/add/json").

							then().spec(respo).extract().response();

		String responseString = response.asString();
		System.out.println(responseString);

	}

}
