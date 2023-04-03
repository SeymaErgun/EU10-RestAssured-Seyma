package cydeo.day04;

import cydeo.Utility.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;

public class ORDSApiWithJsonPath extends HRTestBase {
    @DisplayName("GET request to Countries")
    @Test
    public void test1() {
        Response response = get("/countries");

        //get the second country name with JsonPath


        //to use jsonpath we assign response to JsonPath
        JsonPath jsonPath = response.jsonPath();
        String secondCountryName = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //get all country ids
        List<Integer> allCountryId = jsonPath.getList("items.country_id");
        System.out.println("allCountryId = " + allCountryId);

        //get all country names where their region id is equal to 2
        List<Integer> allCountryNameWithRegionId2 = jsonPath.getList("items.findAll {it.region_id==3}.country_name");

        //it is kind of iterator; {it.region_id==3}--->condition country_name---->value

        System.out.println("allCountryNameWithRegionId2 = " + allCountryNameWithRegionId2);
    }
        @DisplayName("GET request to /employees with query param")
        @Test
        public void test2 () {
        //we added limit query param to get 107 employees
           Response response= given().queryParam("limit", 107)
                    .when().get("/employees");



            JsonPath jsonPath=response.jsonPath();

            //get me all email of employees who is working as IT_PROG
            List<String> employeesITProgs =jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
            System.out.println(employeesITProgs);

           //get me first name of employees who is makimg more than 10000

            List<Integer>empNames=jsonPath.getList("items.findAll {it.salary>10000}.first_name");
            System.out.println(empNames);

            //get the max salary first_name
            String kingFirstName=jsonPath.getString("items.max {it.salary}.first_name");
            String kingNameWithPathMethod=response.path("items.max {it.salary}.first_name");
            System.out.println("kingNameWithPathMethod = " + kingNameWithPathMethod);
            System.out.println("kingFirstName = " + kingFirstName);
        }

    }

