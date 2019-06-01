package com.employeeapi.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

    public static String empName(){
        String generatedString = RandomStringUtils.randomAlphabetic(1);
        return generatedString;
    }

    public static String empName(int length){
        String generatedString = RandomStringUtils.randomAlphabetic(length);
        return generatedString;
    }

    public static String empName(String base, int length){
        String generatedString = base + RandomStringUtils.randomAlphabetic(length);
        return generatedString;
    }

    public static String empSalary(){
        String generatedString = RandomStringUtils.randomNumeric(5);
        return generatedString;
    }

    public static String empAge(){
        String generatedString = RandomStringUtils.randomNumeric(2);
        return generatedString;
    }

    public static void main(String[] args) {
        System.out.println(empSalary());
    }
}
