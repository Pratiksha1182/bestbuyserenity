package com.playground.bestbuy.testsuite;


import com.playground.bestbuy.constants.EndPoint;
import com.playground.bestbuy.steps.ProductsSteps;
import com.playground.bestbuy.testbase.TestBase;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Pratiksha
 */
@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ProductsTestWithTags extends TestBase {

    static String name = "Lumia Power Battries";
    static String type = "Lumia";
    static String upc = "92535464785";
    static double price = 25.95;
    static String description = "This is the long lasting battrey design";
    static String model = "LUM13333UK";

    @Steps
    ProductsSteps productsSteps;
    @WithTags({@WithTag("Features:POSITIVE"), @WithTag("Features:REGRESSION")})
    @Title("This test is creating a new products and verify it is created")
    @Test
    public void test001() {
        productsSteps.createNewProduct(name, type, upc, (int) price, description, model);

    }

    @WithTags({@WithTag("Features:SANITY"), @WithTag("Features:REGRESSION")})
    @Title("Getting the information of the product created by ID")
    @Test
    public void test002() {
        productsSteps
                .getProductById()
                .statusCode(200);

    }

    @WithTags({@WithTag("Features:SMOKE"), @WithTag("Features:REGRESSION")})
    @Title("Getting the information of the All products")
    @Test
    public void test003() {
        SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoint.GET_ALL_PRODUCTS)
                .then().log().all();
    }

    @WithTags({@WithTag("Features:POSITIVE"), @WithTag("Features:REGRESSION")})

    @Title("This test will update the product information and verify the updated information")
    @Test
    public void test004() {
        name = name + "_Changed";
        price = 60.99;
        upc = upc + "_added";
        productsSteps
                .updateProduct(name, type, upc, (int) price, description, model)
                .statusCode(200);
        productsSteps.getProductById().body("name", equalTo(name));
    }

    @WithTags({@WithTag("Features: NEGATIVE"), @WithTag("Features:REGRESSION")})
    @Title("Deleting the Product information with productId : {0} ")
    @Test
    public void test005() {
        SerenityRest.rest()
                .given()
                .when()
                .delete(EndPoint.DELETE_PRODUCT_BY_ID + "/" + 9999682)
                .then()
                .statusCode(404);
    }
}
