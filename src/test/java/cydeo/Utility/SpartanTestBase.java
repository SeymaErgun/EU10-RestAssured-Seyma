package cydeo.Utility;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class SpartanTestBase {
    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://54.242.181.245:8000";  ////change your ip and add :1000 (ip+:1000)

        String dbUrl="jdbc:oracle:thin:@54.242.181.245:1521:XE";
        String dbUsername="SP";
        String dbPassword="SP";

        DBUtils.createConnection(dbUrl, dbUsername, dbPassword);



    }
}
