package com.employeeapi.utilities;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


public class Listeners extends TestListenerAdapter {
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extentReports;
    public ExtentTest test;

    public void onStart(ITestContext testContext){
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/myReport.html");

        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Functional Testing");

        htmlReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("Host name","localhost");
        extentReports.setSystemInfo("Environment","QA");
        extentReports.setSystemInfo("user","pavan");
    }

    public void onTestSuccess(ITestResult result){
        test = extentReports.createTest(result.getName());
        test.log(Status.PASS,"Test Case PASSED is:"+result.getName());
    }

    public void onTestFailure(ITestResult result){
        test = extentReports.createTest(result.getName());

        test.log(Status.FAIL, "Test case failed is:"+result.getName());
        test.log(Status.FAIL, "Test case failed is:"+result.getThrowable());
    }

    public void onTestSkipped(ITestResult result){
        test = extentReports.createTest(result.getName());

        test.log(Status.SKIP, "Test case SKIP is:"+result.getName());
    }

    public void onFinish(ITestContext testContext){
        extentReports.flush();
    }
}
