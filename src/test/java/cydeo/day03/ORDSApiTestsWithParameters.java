package cydeo.day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestsWithParameters {
    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://54.242.181.245:1000/ords/hr";  ////change your ip and add :1000 (ip+:1000)

    }

    /*
        Given accept type is Json
        And parameters: q = {"region_id":2}
        When users sends a GET request to "/countries"
        Then status code is 200
        And Content type is application/json
        And Payload should contain "United States of America"
     */

    @DisplayName("GET request to /countries with Query Param")
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .log().all()
                .when()
                .get("/countries");



        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.header("Content-Type"));
        assertTrue(response.body().asString().contains("United States of America"));

        response.prettyPrint();
    }

    /*
        Send a GET request to employees and get only employees who works as a IT_PROG
     */

    @DisplayName("GET request to /employees with Query Param")
    @Test
    public void test2(){
        Response response=given().accept(ContentType.JSON)
                .and().queryParams("q","{\"job_id\": \"IT_PROG\"}")
                .log().all().when().get("/employees");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.header("Content-Type"));
        assertTrue(response.body().asString().contains("IT_PROG"));

        //i need to verify only job_id is IT_prog, so I go inside the json. navigate inside json and get the data.
        //asString().contains() is not proper test, because it check all string, not the specific part

        response.prettyPrint();

    }

}
