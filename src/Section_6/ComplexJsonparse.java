package Section_6;

import org.testng.Assert;

import Files.payload;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ComplexJsonparse {

	public static void main(String[] args) {

		// 1. print No. of Courses return by API

		JsonPath js = new JsonPath(payload.CoursePrice());
		int count = js.getInt("courses.size()");
		System.out.println("1. print No. of Courses return by API : "+count);
		
		System.out.println();

		// 2. Print Purchase Amount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("2. Print Purchase Amount : "+totalAmount);
		
		System.out.println();
		

		// 3. Print title of the first course

		String titlefirstcourse0 = js.get("courses[0].title");
		System.out.println("3. Print title of the first course : "+titlefirstcourse0);

		String titlefirstcourse1 = js.get("courses[1].title");
		System.out.println("3. Print title of the Second course : "+titlefirstcourse1);

		String titlefirstcourse2 = js.get("courses[2].title");
		System.out.println("3. Print title of the Third course : "+titlefirstcourse2);
		
		String titlefirstcourse3 = js.get("courses[3].title");
		System.out.println("3. Print title of the Fourth course : "+titlefirstcourse3);

		System.out.println();
		// 4. Print all courses title and their respective Price

		for (int i = 0; i < count; i++) {
			String titles = js.get("courses[" + i + "].title");
//					System.out.println(titles);
//			System.out.println(js.get("courses[" + i + "].title").toString()); System.out.println is always Expect String argument


			int price = js.get("courses[" + i + "].price");
//					System.out.println(price);
//			System.out.println(js.get("courses[" + i + "].title").toString()); System.out.println is always Expect String argument

			System.out.println("4. Print all courses title and their respective Price - " + "Booke Title is : " + titles + " __And Price of Book is : " + price);
		}

		System.out.println();
		
		
		// 5. Print No. of copies sold by RPA Courses

		System.out.println("Print No. of copies sold by RPA Courses");

		for (int i = 0; i < count; i++) 
		{
			String titles = js.get("courses[" + i + "].title");
			if (titles.equalsIgnoreCase("RPA"));
			
			{
				int copies = js.get("courses[" + i + "].copies");
				System.out.println(copies);
				break;

			}

		}
		
		System.out.println();
		

		// 6. Verify if sum of all courses matches with Purchase Amount
		
		int sum=0;
		JsonPath js5 = new JsonPath(payload.CoursePrice());
		int count1 = js5.getInt("courses.size()");
		
		for(int i=0;i<count1; i++)
		{
			int price = js.get("courses[" + i + "].price");
			int copies = js.get("courses[" + i + "].copies");
			String titless = js.getString("courses[" + i + "].title");
			int amount = price*copies;
			System.out.println(titless +"-has Earned this amount "+ amount);
		//	System.out.println("dd"+amount);
			 sum=sum+amount;
			 
			 
		}
		System.out.println( "Total Sum : "+sum);
		
		int purchaseAmt = js.getInt("dashboard.purchaseAmount");
		
		Assert.assertEquals(sum, purchaseAmt);

	}

}