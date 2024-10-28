package Files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	
	@Test(dataProvider = "BooksData")
	public void addBook(String isbn , String aisle)
	{
		
		//Post
		RestAssured.baseURI ="http://216.10.245.166/";
		
		String rs = given().header("Content-Type", "application/json")
		.body(payload.addbook(isbn, aisle))
		
		.when()
		.post("Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js =ReUsableMethods.rawtojson(rs);
		String id =js.get("ID");
		System.out.println("Which id is : " +id);
		
		
		//deleteBook
		
	}
	
	
	@DataProvider(name="BooksData")
	public Object[][] getData()
	{
		return new Object[][] {{"wjbnjen", "849393"},{"wkkjen", "8497832"},{"gfdbnjen", "8478393"}};
	}

}
