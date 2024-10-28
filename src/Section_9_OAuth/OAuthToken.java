package Section_9_OAuth;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class OAuthToken {

	public static void main(String[] args) {
		
	//	RestAssured.baseURI="https://rahulshettyacademy.com/";
		
		//get Access_token
		
		String res= given().log().all()
		.formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.formParams("grant_type","client_credentials")
		.formParams("scope","trust")
		
		
		.when()
		.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		
		
		System.out.println(res);
		
		JsonPath js = new JsonPath(res);
		
		String token = js.getString("access_token");
		System.out.println(token);

		
		//get course Details
		
		String res2 = given().queryParams("access_token", token)
		
		.when().log().all()
		.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
		
		System.out.println(res2);
		
		
		
		
	}

}
