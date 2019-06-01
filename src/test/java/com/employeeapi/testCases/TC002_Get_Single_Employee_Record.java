package com.employeeapi.testCases;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.MyLogger;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC002_Get_Single_Employee_Record extends TestBase {

    @BeforeClass
    public void getEmployeeData() throws InterruptedException {
        MyLogger.log.info("*********** "+ getClass().getSimpleName() +" *************");

        RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET,"/employee/"+empID);

        Thread.sleep(3000);
    }

    @Test
    public void checkResponseBody(){
        MyLogger.log.info("********** Checking Response Body ***********");
        String responseBody = response.getBody().asString();
        MyLogger.log.info("Response Body ==> " + responseBody);
        Assert.assertEquals(responseBody.contains(empID),true);
    }

    @Test
    public void checkStatusCode(){
        MyLogger.log.info("********** Checking Response Code ***********");
        int statusCode = response.getStatusCode();
        MyLogger.log.info("Response Code ==> " + statusCode);
        Assert.assertEquals(statusCode,200);
    }

    @Test
    public void checkResponseTime(){
        MyLogger.log.info("********** Checking Response Time ***********");
        MyLogger.log.info("Response Time ==> " + response.getTime());
        if (response.getTime() > 6000)
            MyLogger.log.warn("Response time is greater than 2000");
        Assert.assertTrue(response.getTime()<6000);
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
        if (contentLength > 1500 )
            MyLogger.log.warn("Content Length is more then 1500");

        Assert.assertTrue(contentLength<1500);
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
