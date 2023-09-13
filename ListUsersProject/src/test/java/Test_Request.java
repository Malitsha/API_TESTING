import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
//import static org.hamcrest.Matchers.*;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Test_Request {

	@Test
	void test_01() {

		System.out.println("_______________________________________________________\nTest 1");

		Response response = RestAssured.get("https://reqres.in/api/users/2");

		System.out.println("Status code: " + response.getStatusCode());
		System.out.println("Body: " + response.getBody().asString());
		System.out.println("Time: " + response.getTime());

		int StatusCode = response.getStatusCode();

		Assert.assertEquals(StatusCode, 200);

	}

	@Test
	public void testListUsers() {

		System.out.println("_______________________________________________________\nList User Test");

		
		Response response = RestAssured.get("https://reqres.in/api/users?page=2");

		System.out.println("Status code: " + response.getStatusCode());
		System.out.println("Body: " + response.getBody().asString());
		System.out.println("Time: " + response.getTime());

		// Verify response status code (200 OK)
		assertEquals(response.getStatusCode(), 200, "Expected status code: 200");
		assertTrue(response.body().asString().contains("data"), "Response should contain user data");
	}

	@Test
	public void testSingleUser() {

		System.out.println("_______________________________________________________\nSingle User  Test");

		
		Response response = RestAssured.get("https://reqres.in/api/users/2");

		System.out.println("Status code: " + response.getStatusCode());
		System.out.println("Body: " + response.getBody().asString());
		System.out.println("Time: " + response.getTime());

		
		assertEquals(response.getStatusCode(), 200, "Expected status code: 200");

		

	}

	@Test
	public void testSingleUserNotFound() {

		System.out.println("_______________________________________________________\nSingle User Not Found Test");

		Response response = RestAssured.get("https://reqres.in/api/users/23");

		System.out.println("Status code: " + response.getStatusCode());
		System.out.println("Body: " + response.getBody().asString());
		System.out.println("Time: " + response.getTime());

		assertEquals(response.getStatusCode(), 404, "Expected status code: 404");

	}

	@Test
	public void testList_Resource() {
		
		System.out.println("_______________________________________________________\nList <Resource> Test");

		Response response = RestAssured.get("https://reqres.in/api/unknown");

		System.out.println("Status code: " + response.getStatusCode());
		System.out.println("Body: " + response.getBody().asString());
		System.out.println("Time: " + response.getTime());

		
		assertEquals(response.getStatusCode(), 200, "Expected status code: 200");

	}

	@Test
	public void testSingle_Resource() {
		
		System.out.println("_______________________________________________________\nSingle <Resource> Test");
		Response response = RestAssured.get("https://reqres.in/api/unknown/2");

		System.out.println("Status code: " + response.getStatusCode());
		System.out.println("Body: " + response.getBody().asString());
		System.out.println("Time: " + response.getTime());

		
		assertEquals(response.getStatusCode(), 200, "Expected status code: 200");

	}

	@Test
	public void testSingle_Resource_NotFound() {
		
		System.out.println("_______________________________________________________\nSingle <Resource Not Found> Test");
		Response response = RestAssured.get("https://reqres.in/api/unknown/23");

		System.out.println("Status code: " + response.getStatusCode());
		System.out.println("Body: " + response.getBody().asString());
		System.out.println("Time: " + response.getTime());

		
		assertEquals(response.getStatusCode(), 404, "Expected status code: 404");

	}

	@Test
	public void testDelete() {
		
		System.out.println("_______________________________________________________\nDelete Test");
		Response response = RestAssured.delete("https://reqres.in/api/users/2");

		System.out.println("Status code: " + response.getStatusCode());
		System.out.println("Body: " + response.getBody().asString());
		System.out.println("Time: " + response.getTime());

		
		assertEquals(response.getStatusCode(), 204, "Expected status code: 204");

	}

	@Test
	public void testPost_Create() {

		System.out.println("_______________________________________________________\nCreate Test");
		JSONObject req = new JSONObject();

		req.put("name", "morpheus");
		req.put("job", "leader");

		Response response = 
				given()
					.body(req.toJSONString())
				.when()
					.post("https://reqres.in/api/users")
				.then()
					.statusCode(201)
					.extract().response(); 

		
		System.out.println("Status code: " + response.getStatusCode());
		System.out.println("Body: " + response.getBody().asString());
		System.out.println("Time: " + response.getTime());
	}

	@Test
	void Put_Update() {
		System.out.println("_______________________________________________________\nPut Update Test");

		
		JSONObject req = new JSONObject();
		req.put("name", "kevin");
		req.put("job", "tester");

		Response response =
		    given()
		        .body(req.toJSONString())
		    .when()
		        .put("https://reqres.in/api/users/2") 
		    .then()
		        .statusCode(200) 
		        .extract().response(); 

		
		System.out.println("Status code: " + response.getStatusCode());
		System.out.println("Body: " + response.getBody().asString());
		System.out.println("Time: " + response.getTime());
	}
	@Test
	void Patch_Update() {
		System.out.println("_______________________________________________________\nPatch Update Test");

		
				JSONObject req = new JSONObject();
				req.put("name", "kevin");
				req.put("job", "tester");

				Response response =
				    given()
				        .body(req.toJSONString())
				    .when()
				        .patch("https://reqres.in/api/users/2") 
				    .then()
				        .statusCode(200) 
				        .extract().response(); 

				
				System.out.println("Status code: " + response.getStatusCode());
				System.out.println("Body: " + response.getBody().asString());
				System.out.println("Time: " + response.getTime());
	}
	
	@Test
	void Post_Register_Successful() {
		System.out.println("_______________________________________________________\nPost Register Successful Test");

		
		JSONObject req = new JSONObject();
		req.put("email", "eve.holt@reqres.in");
		req.put("password", "pistol");

		Response response =
		    given()
		        .body(req.toJSONString())
		    .when()
		        .post("https://reqres.in/api/register")
		    .then()
		        .statusCode(200)
		        .extract().response(); 
		
		System.out.println("Status code: " + response.getStatusCode());
		System.out.println("Body: " + response.getBody().asString());
		System.out.println("Time: " + response.getTime());
	}
	@Test
	void Post_Register_Unsuccessful() {
		System.out.println("_______________________________________________________\nPost Register Unsuccessful Test");

		
        JSONObject req = new JSONObject();
        req.put("email", "sydney@fife"); 
       
        Response response =
                given()
                        .body(req.toJSONString())
                .when()
                        .post("https://reqres.in/api/register")
                .then()
                        .statusCode(400) 
                        .extract().response();
        
        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Body: " + response.getBody().asString());
	}
	@Test
	void Post_Login_Succesful() {
		System.out.println("_______________________________________________________\nPost Login Succesful Test");

		
        JSONObject req = new JSONObject();
        req.put("email", "ipfi9910@gmail.com"); 
        req.put("password", "cityslicka");

        Response response =
                given()
                        .body(req.toJSONString())
                .when()
                        .post("https://reqres.in/api/login")
                .then()
                        .statusCode(200) 
                        .extract().response();
        
        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Body: " + response.getBody().asString());
	}
	@Test
	void Post_Login_UnSuccesful() {
		System.out.println("_______________________________________________________\nPost Login UnSuccesful Test");

		 
        JSONObject req = new JSONObject();
        req.put("email", "kevin@gmail.com"); 
       

        Response response =
                given()
                        .body(req.toJSONString())
                .when()
                        .post("https://reqres.in/api/login")
                .then()
                        .statusCode(400) 
                        .extract().response();
        
        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Body: " + response.getBody().asString());
	}
	@Test
	void Get_Delayed() {
		System.out.println("_______________________________________________________\nGet Delayed Test");

		Response response =
                given()
                .when()
                        .get("https://reqres.in/api/users?delay=3") 
                .then()
                        .statusCode(200) 
                        .extract().response();

        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Body: " + response.getBody().asString());
	}
	

}
