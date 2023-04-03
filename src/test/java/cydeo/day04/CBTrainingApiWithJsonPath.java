package cydeo.day04;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CBTrainingApiWithJsonPath {
    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "https://api.training.cydeo.com";



    }
    @DisplayName("GET Request to individual student")
    @Test
    public void test1(){
        //send a get request to student id 2 as a parameter and accept header application/json
        //verify status code /content type /Content-Encoding = gzip
        //verify Date header exist
        //verify Date header exists
        //assert that
            /*
                firstName Vera
                batch 14
                section 12
                emailAddress aaa@gmail.com
                companyName Cybertek
                state IL
                zipCode 60606
                using JsonPath
             */

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 6)
                .when().get(baseURI + "/student/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json;charset=UTF-8",response.contentType());
        assertTrue(response.headers().hasHeaderWithName("Date"));

        JsonPath jsonPath = response.jsonPath();


        String name = jsonPath.getString("students[0].firstName");
        String gender =jsonPath.getString("students[0].gender");
        int batchNo=jsonPath.getInt("students[0].batch");
        String companyName= jsonPath.getString("students[0].company.companyName");
        String stateName=jsonPath.getString("students[0].company.address.state");



        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("batchNo = " + batchNo);
        System.out.println("companyName = " + companyName);
        System.out.println("stateName = " + stateName);
    }


}
