package com.employeeapi.testCases;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.MyLogger;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC005_Delete_Employee_Record extends TestBase {
    @BeforeClass
    public void getEmployeeData() throws InterruptedException {
        MyLogger.log.info("*********** "+ getClass().getSimpleName() +" *************");

        RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();

        response = httpRequest.request(Method.GET,"/employees");

        JsonPath jsonPath = response.jsonPath();

        empID = jsonPath.get("[0].id");
        response = httpRequest.request(Method.DELETE, "/delete/"+empID);

        Thread.sleep(5000);
    }

    @Test
    public void checkResponseBody(){
        MyLogger.log.info("*********** Check Response Body *************");
        String responseBody = response.getBody().asString();
        MyLogger.log.info("Response Body ==> " + responseBody);
        Assert.assertTrue(responseBody.contains("successfully! deleted Records"));
    }

    @Test
    public void checkStatusCode(){
        MyLogger.log.info("********** Checking Status Code ***********");
        int statusCode = response.getStatusCode();
        MyLogger.log.info("Status Code ==> " + statusCode);
        Assert.assertEquals(statusCode,200);
    }

    @Test
    public void checkStatusLine(){
        MyLogger.log.info("********** Checking Status Line ***********");
        MyLogger.log.info("Status Line ==> " + response.getStatusLine());
        Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");
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
