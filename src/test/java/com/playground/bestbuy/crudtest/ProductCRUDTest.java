package com.playground.bestbuy.crudtest;

import com.playground.bestbuy.steps.ProductsSteps;
import com.playground.bestbuy.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Pratiksha
 */
@RunWith(SerenityRunner.class)
public class ProductCRUDTest extends TestBase {

    static String name = "Lumia Power Battries";
    static String type = "Lumia";
    static String upc = "92535464785";
    static double price = 25.95;
    static String description = "This is the long lasting battrey design";
    static String model = "LUM13333UK";
    static long id;

    @Steps
    ProductsSteps productsSteps;

    @Title("This test is creating a new products and verify it is created")
    @Test
    public void test001() {
        productsSteps.createNewProduct(name, type, upc, (int) price, description, model);

    }

    @Title("This test is getting a created product by ID")
    @Test
    public void test002() {
        productsSteps.getProductById().statusCode(200);

    }

    @Title("This test will update the product information and verify the updated information")
    @Test
    public void test003() {
        name = name + "_Changed";
        price = 29.99;
        upc = upc + "_added";
        productsSteps.updateProduct(name, type, upc, (int) price, description, model).statusCode(200);
        productsSteps.getProductById().body("name", equalTo(name));

    }

    @Title("This test will delete the product information and verify the product is deleted ")
    @Test
    public void test004() {
        productsSteps.deleteProduct().statusCode(200);
        productsSteps.getProductById().statusCode(404);
    }
}