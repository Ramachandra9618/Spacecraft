package testcases;

import static io.restassured.RestAssured.*;


import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.IOException;
import org.testng.annotations.Test;

import java.io.File;

public class Api {

    File  wallJsonFile = new File("src/walls.json");
    File roomJsonFile = new File("src/room.json");
    File roomJsonFile1 = new File("src/room1.json");
    File wallJsonFile1 = new File("src/walls1.json");
    File units = new File("src/unitEntries.json");
    File graph  = new File("src/graphqhl.json");


    String baseURI = "https://sc-backend-production.homelane.com/api/v1.0/";
    String wallEndpoint = baseURI + "project/0db2a0db-e304-4459-b3d6-3d961cb816c4/floor/5f642b7d-ce8a-43f3-b38c-8b909d80e93f/walls";
    String roomEndpoint = baseURI + "project/0db2a0db-e304-4459-b3d6-3d961cb816c4/floors/5f642b7d-ce8a-43f3-b38c-8b909d80e93f/room";
    String unitEntries = baseURI+"project/0db2a0db-e304-4459-b3d6-3d961cb816c4/floors/5f642b7d-ce8a-43f3-b38c-8b909d80e93f/rooms/d41baa875256e6385a6ab6885c9560b4/unitEntries";

    String token = "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiUk9MRV9ERVNJR05FUiIsInByb2plY3QiOiIwZGIyYTBkYi1lMzA0LTQ0NTktYjNkNi0zZDk2MWNiODE2YzQiLCJlbWFpbCI6InRlc3RvcmdzdHJ1Y3R1cmVkcDFAaG9tZWxhbmUuY29tIiwib3BlbiI6ZmFsc2UsInN1YiI6InRlc3RvcmdzdHJ1Y3R1cmVkcDFAaG9tZWxhbmUuY29tIiwiaWF0IjoxNzI3NzA5Mjg4LCJleHAiOjE3Mjc3OTU2ODh9.lNSFr5reGV5Lmo3CefqC5N2J8m_oMo4nSIABsvFXI5WKf52odqr0V5HRgUky0uMfxcrf3rFmg46p89MIPekbFg";

    public void apply(File wall, File room) {
        System.out.println(wall);
        given()
                .header("Authorization", token)
//                .header("accept", "*/*")
                .header("Content-Type", "application/json")
                .body(wall)
                .when()
                .post(wallEndpoint)
                .then().log().all()
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
                .log().all()
                .extract().response();

    }
    @Test
    public void test(){

//        JSONParser jsonParser = new JSONParser();
//        try (FileReader reader = new FileReader("src/room.json")) {
//            JSONObject roomData = (JSONObject) jsonParser.parse(reader);
//            roomData.put("breadth", 2000);
//            roomData.put("roomType", "Kitchen");
//            roomData.put("roomName", "rama chandra");
//            roomData.put("length", 3000);
//            // Print the entire JSON data
//            System.out.println(roomData.toJSONString());
//            apply(roomData, roomJsonFile);
//
//        } catch (IOException | ParseException e) {
//            e.printStackTrace();
//        }

        apply(wallJsonFile1, roomJsonFile1);
    }
    }