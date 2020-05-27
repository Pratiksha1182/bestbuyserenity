package com.playground.bestbuy.crudtest;
import com.playground.bestbuy.steps.StoresSteps;
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
public class StoreCRUDTest extends TestBase {

    static String name = "Hatfield Store";
    static String type = "Essentials";
    static String address = "98, Town Center";
    static String address2 = "Hatfield";
    static String city = "Hertfordshire";
    static String state = "North";
    static String zip = "123456";
    static double lat = 12.234567;
    static double lng = -23.345678;
    static String hours = "Mon: 8-8; Tue: 8-8; Wed: 8-8; Thurs: 8-8; Fri: 8-8; Sat: 8-8; Sun: 10-4";
    static int id;


    @Steps
    StoresSteps storesSteps;

    @Title("Creating a new Store")
    @Test
    public void test001() {
        storesSteps.createNewStore(name, type, address, address2, city, state, zip, lat, lng, hours);

    }

    @Title("Getting a created Store")
    @Test
    public void test002() {
        storesSteps.getStoreById().statusCode(200);

    }

    @Title("This test will update the store information and verify the updated information")
    @Test

    public void test003() {


        name = "Hatfield Store";
        type = "Essentials";
        address = "98, Town Center";
        address2 = "Hatfield";
        city = "Hertfordshoire";
        state = "South";
        zip = "123456";
        lat = 12.234567;
        lng = -23.345678;
        hours = "Mon: 8-7; Tue: 8-7; Wed: 8-7; Thurs: 8-7; Fri: 8-7; Sat: 8-7; Sun: 9-5";

        storesSteps.updateStore(name, type, address, address2, city, state, zip, lat, lng, hours).statusCode(200);
        storesSteps.getStoreById().body("name", equalTo(name));

    }

    @Title("This test will delete the store and verify the store is deleted ")
    @Test
    public void test004() {
        storesSteps.deleteStore().statusCode(200);
        storesSteps.getStoreById().statusCode(404);
    }


}
