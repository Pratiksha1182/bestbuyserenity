package com.playground.bestbuy.testsuite;

import com.playground.bestbuy.constants.EndPoint;
import com.playground.bestbuy.pojo.StoresPojo;
import com.playground.bestbuy.steps.StoresSteps;
import com.playground.bestbuy.testbase.TestBase;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.*;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Created by Pratiksha
 */
@RunWith(SerenityRunner.class)
public class StoresTestWithTags extends TestBase {

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
    @Steps
    StoresSteps storesSteps;

    @WithTags({
            @WithTag("Features:POSITIVE"),
            @WithTag("Features:REGRESSION")
    })
    @Title("Creating a new Store")
    @Test
    public void test001() {
        storesSteps.createNewStore(name, type, address, address2, city, state, zip, lat, lng, hours);

    }

    @WithTags({
            @WithTag("Features:SANITY"),
            @WithTag("Features:REGRESSION")
    })

    @Title("Getting a created Store")
    @Test
    public void test002() {
        storesSteps.getStoreById().statusCode(200);
    }

    @WithTags({
            @WithTag("Features:SMOKE"),
            @WithTag("Features:REGRESSION")
    })
    @Title("Getting the all store")
    @Test
    public void test003() {
        SerenityRest.rest()
                .given()
                .when()
                .get(EndPoint.GET_ALL_STORES)
                .then();
    }

    @WithTags({
            @WithTag("Features:POSITIVE"),
            @WithTag("Features:REGRESSION")
    })
    @Title("Updating Store information with  name:{0}, type:{1}. address:{2}, address2:{3}, city:{4}, state:{5}, zip:{6}, lat:{7}, lng:{8}, hours:{9}")
    @Test
    public void test004() {

        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);

        SerenityRest.rest().given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(storesPojo)
                .patch(EndPoint.UPDATE_STORE_BY_ID + "/" + 7)
                .then();
    }

    @WithTags({
            @WithTag("Features: NEGATIVE"),
            @WithTag("Features:REGRESSION")
    })
    @Title("Deleting the store with store ID")
    @Test
    public void test005() {
        SerenityRest.rest()
                .given()
                .when()
                .delete(EndPoint.DELETE_STORE_BY_ID + "/" + 7)
                .then();


    }

}
