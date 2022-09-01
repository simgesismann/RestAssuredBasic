import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class APITests {
    @Test(priority = 1)
    void getStatusCode200(){
        Response rsp = RestAssured.get("https://reqres.in/api/users?page=2");
        Assert.assertEquals(rsp.statusCode(),200);
    }
    @Test(priority = 2)
    void getStatusCode200BDD(){
        given().get("https://reqres.in/api/users?page=160").
                then().
                statusCode(200);
    }
    @Test(priority = 3)
    public void createUserTest() {
        String postData = "{\n" +
                "  \"name\": \"simge\",\n" +
                "  \"job\": \"leader\"\n" +
                "}";
        given().
                contentType(ContentType.JSON).
                body(postData).
                when().
                post("https://reqres.in/api/users").
                then().
                log().all().
                statusCode(201).
                body("name", equalTo("simge"));
    }
    @Test(priority = 4)
    public void createPet() {
        String postData = "{\n" +
                "  \"id\": 0,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"simge\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";
        given().
                contentType(ContentType.JSON).
                body(postData).
                when().
                post("https://petstore.swagger.io/v2/pet").
                then().
                log().all();
    }
    /*
    * status query parameter == pending , get Pet data
    * If HTTP status code == 200 then it is succesfull
     */
    @Test(priority = 5)
    public void getPet(){
        Response rsp = RestAssured.get("https://petstore.swagger.io/v2/pet/findByStatus?status=pending");
        Assert.assertEquals(rsp.statusCode(),200);
    }

}
