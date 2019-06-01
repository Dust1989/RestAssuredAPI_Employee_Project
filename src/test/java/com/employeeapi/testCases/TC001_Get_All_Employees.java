package com.employeeapi.testCases;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.MyLogger;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC001_Get_All_Employees extends TestBase {

    @BeforeClass
    public void getAllEmployees() throws InterruptedException {
        MyLogger.log.info("*********** "+ getClass().getSimpleName() +" *************");

        RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET,"/employees");

        Thread.sleep(3000);
    }

    @Test
    public void checkResponseBody(){
        MyLogger.log.info("********** Checking Response Body ***********");

        String responseBody = response.getBody().asString();
        MyLogger.log.info("Response Body ==> "+responseBody);
        Assert.assertTrue(responseBody != null);
    }

    @Test
    public void checkStatusCode(){
        MyLogger.log.info("********** Checking Status Code ***********");
        MyLogger.log.info("Status Code ==> " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void checkResponseTime(){
        MyLogger.log.info("********** Checking Response Time ***********");
        MyLogger.log.info("Response Time ==> " + response.getTime());
        if (response.getTime() > 2000)
            MyLogger.log.warn("Response time is greater than 2000");
        Assert.assertTrue(response.getTime()<2000);
    }

    @Test
    public void checkStatusLine(){
        MyLogger.log.info("********** Checking Status Line ***********");
        MyLogger.log.info("Status Line ==> " + response.getStatusLine());
        Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");
    }

    @Test
    public void checkContentType(){
        MyLogger.log.info("********** Checking Content Type ***********");
        MyLogger.log.info("Content Type ==> " + response.getContentType());
        Assert.assertEquals(response.getContentType(),"text/html; charset=UTF-8");
    }

    @Test
    public void checkServerType(){
        MyLogger.log.info("********** Checking Server Type ***********");
        MyLogger.log.info("Server Type ==> " + response.header("Server"));
        Assert.assertEquals(response.header("Server"),"nginx/1.14.1");
    }

    @Test
    public void checkContentEncoding(){
        MyLogger.log.info("********** Checking Content Encoding ***********");
        MyLogger.log.info("Content Encoding ==> " + response.header("Content-Encoding"));
        Assert.assertEquals(response.header("Content-Encoding"),"gzip");
    }

    @Test
    public void checkContentLength(){
        MyLogger.log.info("********** Checking Content Length ***********");
        int contentLength = Integer.parseInt(response.header("Content-Length"));
        MyLogger.log.info("Content Length ==> " + contentLength);
        if (contentLength < 100 )
            MyLogger.log.warn("Content Length is less then 100");

        Assert.assertTrue(contentLength>100);
    }

    @Test
    public void checkCookies(){
        MyLogger.log.info("********** Checking Cookies ***********");
        String cookie = response.getCookie("PHPSESSID");
        MyLogger.log.info("Cookies ==> " + cookie);
    }

    @AfterClass
    public void tearDown(){
        MyLogger.log.info("********** Finished "+getClass().getSimpleName()+" ***********");
    }
}
