package Files;

public class payload {
	
public static String AddPlace()
	
	{
		return "{\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": 50,\r\n" + 
				"  \"name\": \"Rahul Shetty Academy\",\r\n" + 
				"  \"phone_number\": \"(+91) 993 893 3937\",\r\n" + 
				"  \"address\": \"29, side layout, cohen 09\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"shoe park\",\r\n" + 
				"    \"shop\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \"http://rahulshettyacademy.com\",\r\n" + 
				"  \"language\": \"French-IN\"\r\n" + 
				"}\r\n" + 
				"";
		
		
	}
	
	
	public static String CoursePrice()
	{
		return "{\r\n"
				+ "  \"dashboard\": {\r\n"
				+ "    \"purchaseAmount\": 1210,\r\n"
				+ "    \"website\": \"rahulshettyacademy.com\"\r\n"
				+ "  },\r\n"
				+ "  \"courses\": [\r\n"
				+ "    {\r\n"
				+ "      \"title\": \"Selenium Python\",\r\n"
				+ "      \"price\": 50,\r\n"
				+ "      \"copies\": 6\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"title\": \"Cypress\",\r\n"
				+ "      \"price\": 40,\r\n"
				+ "      \"copies\": 4\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"title\": \"RPA\",\r\n"
				+ "      \"price\": 45,\r\n"
				+ "      \"copies\": 10\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"title\": \"Appium\",\r\n"
				+ "      \"price\": 15,\r\n"
				+ "      \"copies\": 20\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
	}

	

//	public static String addbook()
//	{ //1. you do like this
//		return"{\r\n"
//				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
//				+ "\"isbn\":\"bcd\",\r\n"
//				+ "\"aisle\":\"2926\",\r\n"
//				+ "\"author\":\"John foer\"\r\n"
//				+ "}\r\n"
//				+ "";
		
	
	// 2. or like this both are same = Parameterize the API tests With Single Data Sets
	
	public static String addbook(String isbn, String aisle) {
		
		String payload ="{\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"John foer\"\r\n"
				+ "}\r\n"
				+ "";
		
		return payload;
	}
	
}
	
	


//I will make this method static, so that i can call this method directly into my test without 
//creating object for this class, So if you make that method as static then you can directly 
//call this method with class name dot method name.But if you don't make it static you need 
//to create object for this class and using that object you need to call.
