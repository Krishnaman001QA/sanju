package Section_10_POJO;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import POJO.Get_Course;
import POJO.courses_api;
import POJO.courses_webAutomation;

public class OAuthToken {

	public static void main(String[] args) {
		
		
		String[] courses= {"Selenium Webdriver Java","Cypress","Protractor"};

		// RestAssured.baseURI="https://rahulshettyacademy.com/";

		// get Access_token

		String res = given().log().all()
				.formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParams("grant_type", "client_credentials")
				.formParams("scope", "trust")

				.when().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

		System.out.println(res);

		JsonPath js = new JsonPath(res);

		String token = js.getString("access_token");
		System.out.println(token);

//		// get course Details
//
//		String res2 = given().queryParams("access_token", token)
//
//				.when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
//
//		System.out.println(res2);

		// get course Details

		Get_Course gc = given().queryParams("access_token", token)

				.when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
				.as(Get_Course.class);
		// get me titles present in api where title is "SoapUI Webservices testing" and
		// then give me the price
		System.out.println(gc.getServices());
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		System.out.println(gc.getCourses().getApi().get(1).getPrice());

		// get me titles present in api where title is "SoapUI Webservices testing" and
		// then give me the price using logic
		List<courses_api> apiCourses = gc.getCourses().getApi();

		for (int i = 0; i < apiCourses.size(); i++) {
			if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println(apiCourses.get(i).getPrice());
			}
		}

		// get me all titles present in web Automation and also verify Title is same or not

		ArrayList<String> a= new ArrayList<String>();
		
		List<courses_webAutomation> webtitles = gc.getCourses().getWebAutomation();

		for (int w = 0; w < webtitles.size(); w++) {
			String tt = webtitles.get(w).getCourseTitle();
			String pp = webtitles.get(w).getPrice();
			System.out.println(tt +" and the price is "+ pp);
			
			a.add(webtitles.get(w).getCourseTitle());
			
		}
		
		List<String> expectedTitles =Arrays.asList(courses);
		
		Assert.assertTrue(a.equals(expectedTitles));

	}

}
