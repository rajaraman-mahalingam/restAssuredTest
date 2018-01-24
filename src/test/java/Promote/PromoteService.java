package Promote;
import core.*
import org.junit.Assert;
import org.junit.Test;

public class PromoteService extends Services {

    //public static final String URI = "/utilities/weather/city/Chennai";
    public static final String URI = "/posts";
    @Test
    public void verifyGetURIReturnsSuccess() {
        Assert.assertEquals(Services.getResponseStatusCode(URI),200);
        Services.printResponseBody(URI);
        Services.printResponseCookies(URI);
        Services.printResponseCookie(URI,"Domain");
    }

    @Test
    public void verifyGetURIHeadersDisplayAsExpected() {
        Assert.assertTrue(Services.getResponseHeader(URI).exist());
        Services.printResponseHeader(URI);
    }

    @Test
    public void verifyGetURIHeaderValueMatchesExpectedResult() {
        Assert.assertEquals(Services.getResponseHeader(URI,"Server"),"cloudflare");
    }

    @Test
    public void verifyGetURIBodyValueMatchesExpectedResult() {
        Assert.assertEquals(Services.getResponseBody(URI+"/1").get("profession"),"Engineer");
    }

    @Test
    public void verifyPostURIWorksSuccessfully() {
        Assert.assertEquals(Services.postResponse(URI).statusCode(),201);
    }

    @Test
    public void verifyResponseBodySize() {
        Assert.assertEquals(Services.getResponseBodySize(URI),100);
    }
}
