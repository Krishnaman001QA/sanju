package Files;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods {
	
	
	public static JsonPath rawtojson(String response)
	{
		JsonPath jjs = new JsonPath(response);
		return jjs;

	}

}
