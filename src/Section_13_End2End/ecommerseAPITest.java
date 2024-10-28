package Section_13_End2End;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import POJO.Login_Response;
import POJO.Loginn_Request;
import POJO.Oders;
import POJO.order_Details;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class ecommerseAPITest {

	public static void main(String[] args) {
//Login Call Done
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.setContentType(ContentType.JSON).build();

		Loginn_Request loginBody = new Loginn_Request();
		loginBody.setUserEmail("himanshusharmahs671@gmail.com");
		loginBody.setUserPassword("9650SaRuu...@");

		RequestSpecification reqlogin = given().log().all().spec(req).body(loginBody);

		Login_Response responseLogin = reqlogin.when().log().all().post("/api/ecom/auth/login")

				.then().log().all().extract().response().as(Login_Response.class);

		String token = responseLogin.getToken();
		System.out.println(token);

		String uid = responseLogin.getUserId();
		System.out.println(uid);

//Add Product

		RequestSpecification addProduct = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).build();

		RequestSpecification reqaddProduct = given().spec(addProduct).param("productName", "laptop")
				.param("productAddedBy", uid).param("productCategory", "fashion").param("productSubCategory", "Shirt")
				.param("productPrice", "11500").param("productDescription", "Dell").param("productFor", "men")
				.multiPart("productImage", new File("C://Users//Fleek//Downloads//profile-pic (1).png"))
				.param("Authorization", token);

		String addProductresponse = reqaddProduct.when().log().all().post("/api/ecom/product/add-product")

				.then().log().all().extract().response().asString();

		JsonPath js = new JsonPath(addProductresponse);

		String pID = js.get("productId");
		System.out.println(pID);

//Create Order
		RequestSpecification createOrder = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.addHeader("Authorization", token).setContentType(ContentType.JSON).build();

		order_Details orderDetail = new order_Details();
		orderDetail.setCountry("India");
		orderDetail.setProductOrderedId(pID);

		List<order_Details> orderDetaList = new ArrayList<order_Details>();
		orderDetaList.add(orderDetail);

		Oders orders = new Oders();
		orders.setOrders(orderDetaList);

		RequestSpecification createOrderreq = given().log().all().spec(createOrder).body(orders);

		String createOrderResponse = createOrderreq.when().log().all().post("/api/ecom/order/create-order").then().log()
				.all().extract().response().asString();

		System.out.println("ll  " + createOrderResponse);

		JsonPath js1 = new JsonPath(createOrderResponse);
		String pcreateOrderID = js.get("orders");

		System.out.println("llc  " + pcreateOrderID);

//Delete Order	
		RequestSpecification deleteOrder = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.addHeader("Authorization", token).setContentType(ContentType.JSON).build();

		RequestSpecification deleteOrderreq = given().log().all().spec(deleteOrder).pathParam("productId", pID);

		String deleteOrderResponse = deleteOrderreq.when().delete("api/ecom/product/delete-product/{productId}").then()
				.log().all().extract().response().asString();

		System.out.println("Delete Order Respose " + deleteOrderResponse);
		JsonPath js2 = new JsonPath(deleteOrderResponse);

		String orderdeletemsg = js2.get("message");
		System.out.println(orderdeletemsg);
		Assert.assertEquals("Product Deleted Successfully", js2.get("message"));
	}

}
