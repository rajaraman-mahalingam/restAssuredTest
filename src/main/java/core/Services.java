package core;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.http.*;
import io.restassured.matcher.RestAssuredMatchers.*;
//import io.restassured.module.jsv.JsonSchemaValidator.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.AuthenticationSpecification;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public abstract class Services {

    //private static String baseURI = "http://restapi.demoqa.com";
    private static String baseURI = "https://my-json-server.typicode.com/rajaraman-mahalingam/apiTesting/";
    private static Response response;
    private static RequestSpecification httpRequest;
    private static AuthenticationSpecification authRequest;
    private static JsonPath jsonPath;
    private static JSONObject requestParams = new JSONObject();

    public static int getResponseStatusCode(String URI) {
        return getResponse(URI).statusCode();
    }

    public static Response getResponse(String URI) {
        RestAssured.baseURI = baseURI;
        httpRequest = RestAssured.given();
        authRequest = httpRequest.auth();
        return response = authRequest.none().request(Method.GET,URI);
    }

    public static Headers getResponseHeader(String URI) {
        return getResponse(URI).headers();
    }

    public static Cookies getCookies(String URI) {
        return getResponse(URI).getDetailedCookies();
    }

    public static String getCookie(String URI, Cookie cookieName) {
        return getResponse(URI).cookie(cookieName.toString());
    }

    public static String getResponseHeader(String URI, String header) {
        return getResponse(URI).header(header);
    }

    public static void printResponseBody(String URI) {
        getResponse(URI).body().prettyPrint();
    }

    public static void printResponseHeader(String URI) {
        System.out.println(getResponseHeader(URI));
    }

    public static void printResponseCookies(String URI) {
        System.out.println(getCookies(URI));
    }

    public static void printResponseCookie(String URI, Cookie CookieName) {
        System.out.println(getCookie(URI,CookieName));
    }
    public static JsonPath getResponseBody(String URI) {
       return jsonPath= getResponse(URI).body().jsonPath();
    }

    public static int getResponseBodySize(String URI) {
        List<String> jsonString = getResponse(URI).body().jsonPath().getList("$");
        return jsonString.size();
    }

//    public static Response postResponse(String URI) {
//        RestAssured.baseURI = baseURI;
//        httpRequest = RestAssured.given();
//        requestParams.put("FirstName","Rajaraman");
//        requestParams.put("LastName","Mahalingam");
//        requestParams.put("UserName","cbz162200");
//        requestParams.put("Password","password");
//        requestParams.put("Email","rajaja@ghamail.com");
//        httpRequest.body(requestParams.toString());
//        return response = httpRequest.request(Method.POST,"/customer/register");
//    }

    public static Response postResponse(String URI) {
        RestAssured.baseURI = baseURI;
        httpRequest = RestAssured.given();
        requestParams.put("id",6);
        requestParams.put("title","Mrs");
        requestParams.put("name","Nithya");
        requestParams.put("profession","Digital marketing specialist");
        requestParams.put("maritalstatus","Married");
        return response = httpRequest.contentType("application/json").body(requestParams.toString()).request(Method.POST,URI);
    }

    public static int postResponseStatusCode(String URI) {
        return postResponse(URI).statusCode();
    }

    //public static void
}
