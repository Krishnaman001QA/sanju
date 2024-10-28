package Basics;


import Files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonparse {

	public static void main(String[] args) { 
		// 1. print No. of Courses return by API

		JsonPath js = new JsonPath(payload.CoursePrice());
		int count = js.getInt("courses.size()");
		System.out.println(count);

		// 2. Print Purchase Amount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);

		// 3. Print title of the first course

		String titlefirstcourse = js.get("courses[0].title");
		System.out.println(titlefirstcourse);

		String titlefirstcourse1 = js.get("courses[1].title");
		System.out.println(titlefirstcourse1);

		String titlefirstcourse2 = js.get("courses[2].title");
		System.out.println(titlefirstcourse2);

		// 4. Print all courses title and their respective Price

		for (int i = 0; i < count; i++) {
			String titles = js.get("courses[" + i + "].title");
//			System.out.println(titles);

			int price = js.get("courses[" + i + "].price");
//			System.out.println(price);

			System.out.println("Booke Title is : " + titles + " __And Price of Book is : " + price);
		}

		// 5. Print No. of copies sold by RPA Courses

		System.out.println("Print No. of copies sold by RPA Courses");

		for (int i = 0; i < count; i++) {
			String titles = js.get("courses[" + i + "].title");
			if (titles.equalsIgnoreCase("RPA"))
				;
			{
				int copies = js.get("courses[" + i + "].copies");
				System.out.println(copies);
				break;

			}

		}
		
		// 6. Verify if sum of all courses matches with Purchase Amount

	}

}