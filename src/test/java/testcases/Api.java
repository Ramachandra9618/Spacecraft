package testcases;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

import org.testng.annotations.Test;

import java.io.File;

public class Api {

    File  wallJsonFile = new File("src/walls.json");
    File roomJsonFile = new File("src/room.json");
    File roomJsonFile1 = new File("src/room1.json");
    File wallJsonFile1 = new File("src/walls1.json");
    File units = new File("src/unitEntries.json");



    String baseURI = "https://sc-backend-production.homelane.com/api/v1.0/";
    String wallEndpoint = baseURI + "project/0db2a0db-e304-4459-b3d6-3d961cb816c4/floor/5f642b7d-ce8a-43f3-b38c-8b909d80e93f/walls";
    String roomEndpoint = baseURI + "project/0db2a0db-e304-4459-b3d6-3d961cb816c4/floors/5f642b7d-ce8a-43f3-b38c-8b909d80e93f/room";
    String unitEntries = baseURI+"project/0db2a0db-e304-4459-b3d6-3d961cb816c4/floors/5f642b7d-ce8a-43f3-b38c-8b909d80e93f/rooms/c4a521f05d53c23534f535e3b8b34ad4/unitEntries";
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiUk9MRV9ERVNJR05FUiIsInByb2plY3QiOiIwZGIyYTBkYi1lMzA0LTQ0NTktYjNkNi0zZDk2MWNiODE2YzQiLCJlbWFpbCI6InRlc3RvcmdzdHJ1Y3R1cmVkcDFAaG9tZWxhbmUuY29tIiwib3BlbiI6ZmFsc2UsInN1YiI6InRlc3RvcmdzdHJ1Y3R1cmVkcDFAaG9tZWxhbmUuY29tIiwiaWF0IjoxNzI4NTM0NDI0LCJleHAiOjE3Mjg2MjA4MjR9.r5iABN3V5l0x_vw56djNuStGiD1PAbq8ePVixYwaceFSaoGtG9p1_U_SFE4IYSk8SmSXTzcortJ7taVeF9xtjw";
    public void apply(File wall, File room) {
        given()
                .header("Authorization", token)
//                .header("accept", "*/*")
                .header("Content-Type", "application/json")
                .body(wall)
                .when()
                .post(wallEndpoint).then()
                .statusCode(200);


        given()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .body(room)
                .when()
                .post(roomEndpoint)
                .then()
                .statusCode(200);

        Response response = given()
                .header("Authorization", token)
                .header("Accept", "*/*")
                .header("Content-Type", "application/json")
                .body(units)
                .when()
                .post(unitEntries)
                .then()
                .log().all().statusCode(200)
                .extract().response();

    }
    @Test
    public void test(){
        apply(wallJsonFile, roomJsonFile);
    }
}
