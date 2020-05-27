package com.playground.bestbuy.testbase;


import com.playground.bestbuy.utils.PropertyReader;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

/**
 * Created by Pratiksha
 */
public class TestBase {

    public static PropertyReader propertyReader;

    @BeforeClass
    public static void init() {

        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI = propertyReader.getProperty("baseUrl");
        RestAssured.port = Integer.parseInt(propertyReader.getProperty("port"));


    }

}
