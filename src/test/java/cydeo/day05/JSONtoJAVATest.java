package cydeo.day05;

import cydeo.Utility.SpartanTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class JSONtoJAVATest extends SpartanTestBase {
    @DisplayName("Get one Spartan and deserialize to Map")
    @Test
    public void oneSpartanToMap(){
        Response response =given().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();

        //get the json and convert it to the map

        Map<String,Object> jsonMap = response.as(Map.class);


        System.out.println(jsonMap.toString());
        //after we got the map, we can use hamcrest or junit assertions to do assertion
        String actualName = (String) jsonMap.get("name"); //casting is done
        assertThat(actualName,is("Meta"));


    }
}