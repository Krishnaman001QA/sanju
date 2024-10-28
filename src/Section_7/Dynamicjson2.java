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

public class Dynamicjson2 {

//	2. Parameterize the API tests With multiple Data Sets

	@Test(dataProvider = "booksData")
	public void addBook(String isbn, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		String repo = given().header("Content-Type", "application/json")
				.body(payload.addbook(isbn, aisle))

				.when().post("/Library/Addbook.php")

				.then().assertThat().statusCode(200).extract().response().asString();

//	JsonPath js= ReUsableMethods.rawtojson(repo);
		JsonPath js = ReUsableMethods.rawtojson(repo);
		String idd = js.get("ID");
		System.out.println(idd);

	}

	@DataProvider(name = "booksData")
	public Object[][] getData() {
		// array =collection of elements
		// multiDimensional array =collection of array
		return new Object[][] { { "sgdh", "88565" }, { "jvcm", "43342" }, { "rtrg", "35534" } };

	}

}
