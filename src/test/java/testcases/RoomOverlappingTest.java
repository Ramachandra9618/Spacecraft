package testcases;

import static io.restassured.RestAssured.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RoomOverlappingTest {

    File wallJsonFile = new File("src/walls.json");
    File roomJsonFile = new File("src/room.json");
    File roomJsonFile1 = new File("src/room1.json");
    File wallJsonFile1 = new File("src/walls1.json");

    String baseURI = "https://sc-backend-production.homelane.com/api/v1.0/";
    String wallEndpoint = baseURI + "project/0db2a0db-e304-4459-b3d6-3d961cb816c4/floor/5f642b7d-ce8a-43f3-b38c-8b909d80e93f/walls";
    String roomEndpoint = baseURI + "project/0db2a0db-e304-4459-b3d6-3d961cb816c4/floors/5f642b7d-ce8a-43f3-b38c-8b909d80e93f/room";
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiUk9MRV9ERVNJR05FUiIsInByb2plY3QiOiIwZGIyYTBkYi1lMzA0LTQ0NTktYjNkNi0zZDk2MWNiODE2YzQiLCJlbWFpbCI6InRlc3RvcmdzdHJ1Y3R1cmVkcDFAaG9tZWxhbmUuY29tIiwib3BlbiI6ZmFsc2UsInN1YiI6InRlc3RvcmdzdHJ1Y3R1cmVkcDFAaG9tZWxhbmUuY29tIiwiaWF0IjoxNzI3NDAzNjkyLCJleHAiOjE3Mjc0OTAwOTJ9.JYe31irXuHrRo4b3idurSMLNyt7h8hxIOSAfC999Cbh2P9Ap5tLVz8BHJ3-NulG7UnZ93nvBL91m4llyyEXPAQ";

    public Response apply(File wall, File room) {
        Response response = given()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .body(wall)
                .when()
                .post(wallEndpoint);

        given()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .body(room)
                .when()
                .post(roomEndpoint);

        return response;
    }

    @Test
    public void test() {
        Response response1 = apply(wallJsonFile, roomJsonFile);

        if (!comparision(response1)) {
            Response response2 = apply(wallJsonFile1, roomJsonFile1);
        } else {
            System.out.println("Same end points not allowed");
        }
    }

    public boolean comparision(Response response) {

        List<Map<String, Object>> startPoints = response.jsonPath().getList("startPoint");
        List<Map<String, Object>> endPoints = response.jsonPath().getList("endPoint");

        List<Double> responsePoints = new LinkedList<>();

        // Collect x and y values from start points
        for (Map<String, Object> point : startPoints) {
            double x = ((Number) point.get("x")).doubleValue();
            double y = ((Number) point.get("y")).doubleValue();
            responsePoints.add(roundToTwoDecimals(x));
            responsePoints.add(roundToTwoDecimals(y));
        }

        for (Map<String, Object> point : endPoints) {
            double x = ((Number) point.get("x")).doubleValue();
            double y = ((Number) point.get("y")).doubleValue();
            responsePoints.add(roundToTwoDecimals(x));
            responsePoints.add(roundToTwoDecimals(y));
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Map<String, Object>> secondRoom = objectMapper.readValue(wallJsonFile1, List.class);
            Map<String, Object> startPoint = (Map<String, Object>) secondRoom.get(0).get("startPoint");
            Map<String, Object> endPoint = (Map<String, Object>) secondRoom.get(0).get("endPoint");

            List<Double> requestPoints = new LinkedList<>();

            if (startPoint != null) {
                double x = (double) startPoint.get("x");
                double y = (double) startPoint.get("y");
                requestPoints.add(roundToTwoDecimals(x));
                requestPoints.add(roundToTwoDecimals(y));
            }

            if (endPoint != null) {
                double x = (double) endPoint.get("x");
                double y = (double) endPoint.get("y");
                requestPoints.add(roundToTwoDecimals(x));
                requestPoints.add(roundToTwoDecimals(y));

            }

            boolean isMatch = responsePoints.containsAll(requestPoints);
            return isMatch;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}

