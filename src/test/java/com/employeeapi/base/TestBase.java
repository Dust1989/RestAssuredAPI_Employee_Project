package com.employeeapi.base;


import com.employeeapi.utilities.MyLogger;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Level;
import org.testng.annotations.BeforeClass;

public class TestBase {
    public static RequestSpecification httpRequest;
    public static Response response;
    public String empID = "2687"; //Hard coded - Input for Get details of Single Employee & update employee

    @BeforeClass
    public void setup(){
        MyLogger.log.setLevel(Level.DEBUG);
    }

}
