package Files;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test
	public void SumofCourses()
	{
		int sum=0;
		JsonPath js = new JsonPath(payload.CoursePrice());
		int count = js.getInt("courses.size()");
		
		for(int i=0;i<count; i++)
		{
			int price = js.get("courses[" + i + "].price");
			int copies = js.get("courses[" + i + "].copies");
			int amount = price*copies;
			System.out.println(amount);
			 sum=sum+amount;
			 
			 
		}
		System.out.println( "Total Sum : "+sum);
		
		int purchaseAmt = js.getInt("dashboard.purchaseAmount");
		
		Assert.assertEquals(sum, purchaseAmt);
		
		
	}
}
