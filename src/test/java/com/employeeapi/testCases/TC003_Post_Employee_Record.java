package com.employeeapi.testCases;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.MyLogger;
import com.employeeapi.utilities.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC003_Post_Employee_Record extends TestBase {

    String empName = RestUtils.empName("OTC",4);
    String empSalary = RestUtils.empSalary();
    String empAge = RestUtils.empAge();

    @BeforeClass
    public void getEmployeeData() throws InterruptedException {
        MyLogger.log.info("*********** "+ getClass().getSimpleName() +" *************");

        RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", empName);
        requestParams.put("salary", empSalary);
        requestParams.put("age", empAge);

        httpRequest.header("Content-Type","application/json");

        httpRequest.body(requestParams.toJSONString());

        response = httpRequest.request(Method.POST,"/create");

        Thread.sleep(5000);
    }

    @Test
    public void checkResponseBody(){
        MyLogger.log.info("********** Checking Response Body ***********");
        String responseBody = response.getBody().asString();
        MyLogger.log.info("Response Body ==> " + responseBody);
        Assert.assertTrue(responseBody.contains(empName));
        Assert.assertTrue(responseBody.contains(empSalary));
        Assert.assertTrue(responseBody.contains(empAge));
    }

    @Test
    public void checkStatusCode(){
        MyLogger.log.info("********** Checking Status Code ***********");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200);
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

    @AfterClass
    public void tearDown(){
        MyLogger.log.info("********** Finished "+getClass().getSimpleName()+" ***********");
    }
}
