package cydeo.day02;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanGetRequests {

 //    Given Accept type application/json
//    When user send GET request to api/spartans end point
//    Then status code must 200
//    And response Content Type must be application/json
//    And response body should include spartan result
 String baseurl="http://54.242.181.245:8000";

    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseurl + "/api/spartans");
        //printing status code from response object
        System.out.println("response.statusCode() = " + response.statusCode());

        //printing response content type from response object
        System.out.println("response.contentType() = " + response.contentType());

        //print whole result body
        response.prettyPrint();

        //how to do API testing then?
        Assertions.assertEquals(response.statusCode(), 200);

        //verify content type is application/json
        Assertions.assertEquals(response.contentType(), "application/json");

    }

 /*
        Given accept header is application/json
        When users sends a get request to /api/spartans/3
        Then status code should be 200
        And content type should be application/json
        and json body should contain Fidole
     */

    @DisplayName("GET one spartan /api/spartans/3 and verify")
    @Test
    public void test2(){
        Response response=RestAssured.given().accept(ContentType.JSON).
                when().get(baseurl+ "/api/spartans/3" );

        //verify status code is 200

        Assertions.assertEquals(response.statusCode(), 200);

        //verify content type is application/json
        Assertions.assertEquals(response.contentType(), "application/json");

        //verify json body contains Fidole
        //(it is not a good usage, because even Fidole is in gender, it will pass because contains in whole string.)

        Assertions.assertTrue(response.body().asString().contains("Fidole"));


    }




}
