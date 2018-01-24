import core.Services;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;
import org.json.*;

import static io.restassured.RestAssured.given;

public class UserServices extends Services {
    private static String baseURI = "http://restapi.demoqa.com";
    private static Response response;
    private static RequestSpecification httpRequest;
    private static JsonPath jsonPath;
    private static JSONObject requestParams = new JSONObject();


    @Test
    public void getResponseStatusCodeAndDisplayResponse() {
        RestAssured.baseURI = baseURI;
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET,"/utilities/weather/city/Chennai");
        Assert.assertEquals(response.statusCode(),200);
        response.prettyPrint();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void getResponseHeaders() {
        RestAssured.baseURI = baseURI;
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET,"/utilities/weather/city/Bangalore");
        Assert.assertEquals(response.header("Server"),"nginx/1.12.2");
        System.out.println(response.headers());
    }

    @Test
    public void getResponseBody() {
        RestAssured.baseURI = baseURI;
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET,"/utilities/weather/city/Mumbai");
        jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("Humidity"),"46 Percent");
    }
    @Test
    public void addNewEntryAndVerifyIfSuccessfullyAdded() {
        RestAssured.baseURI = baseURI;
        httpRequest = RestAssured.given();
        requestParams.put("FirstName","Rajaraman");
        requestParams.put("LastName","Mahalingam");
        requestParams.put("UserName","cbz16220");
        requestParams.put("Password","password");
        requestParams.put("Email","rajaja@ghamail.com");
        httpRequest.body(requestParams.toString());
        response = httpRequest.request(Method.POST,"/customer/register");
        System.out.println(response.statusCode());
        response.prettyPrint();
    }
}
