package testcases;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class DeleteAllRooms {
    @Test
    void call(){
        DeleteAllRooms deleteAllRooms = new DeleteAllRooms();
        deleteAllRooms.deleteAllRoomsInFloor();
    }
    public void deleteAllRoomsInFloor() {
        String baseURI = "https://sc-backend-production.homelane.com/api/v1.0/";
        String floorsEndpoint = baseURI + "project/0db2a0db-e304-4459-b3d6-3d961cb816c4/floors/5f642b7d-ce8a-43f3-b38c-8b909d80e93f";
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiUk9MRV9ERVNJR05FUiIsInByb2plY3QiOiIwZGIyYTBkYi1lMzA0LTQ0NTktYjNkNi0zZDk2MWNiODE2YzQiLCJlbWFpbCI6InRlc3RvcmdzdHJ1Y3R1cmVkcDFAaG9tZWxhbmUuY29tIiwib3BlbiI6ZmFsc2UsInN1YiI6InRlc3RvcmdzdHJ1Y3R1cmVkcDFAaG9tZWxhbmUuY29tIiwiaWF0IjoxNzI4NjIzOTE3LCJleHAiOjE3Mjg3MTAzMTd9.SpQwR5YPZDukjaLrPq2cpzhZZe0CAd0nG4_My1ZqqDbF6Ypckv--eg5qIaboTYiuciLA9RLReU6guXQ5bH2jVg";

        Response response = given()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .when()
                .get(floorsEndpoint)
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<Map<String, Object>> rooms =response.jsonPath().getList("rooms");
        System.out.println();

        for (Map<String, Object> room : rooms) {
            System.out.println(room.get("boundaryWalls"));
            JSONArray walls = new JSONArray();
            for (Map<String, Object> wall :(List<Map<String, Object>>) room.get("boundaryWalls") ){
                if (wall.get("key") instanceof String) {
                    System.out.println(+
                            111111111);
                    walls.put(wall.get("key").toString());
                }
            }
            System.out.println(walls);
            deleteRoom((String) room.get("id"), walls);
        }
    }

    public void deleteRoom(String roomID, JSONArray walls ) {
        String baseURI = "https://sc-backend-production.homelane.com/api/v1.0/";
        String deleteRoomEndpoint = baseURI + "project/0db2a0db-e304-4459-b3d6-3d961cb816c4/floors/5f642b7d-ce8a-43f3-b38c-8b909d80e93f/rooms/" + roomID;
        String deleteWalls =baseURI+"project/0db2a0db-e304-4459-b3d6-3d961cb816c4/floor/5f642b7d-ce8a-43f3-b38c-8b909d80e93f/walls";
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiUk9MRV9ERVNJR05FUiIsInByb2plY3QiOiIwZGIyYTBkYi1lMzA0LTQ0NTktYjNkNi0zZDk2MWNiODE2YzQiLCJlbWFpbCI6InRlc3RvcmdzdHJ1Y3R1cmVkcDFAaG9tZWxhbmUuY29tIiwib3BlbiI6ZmFsc2UsInN1YiI6InRlc3RvcmdzdHJ1Y3R1cmVkcDFAaG9tZWxhbmUuY29tIiwiaWF0IjoxNzI4NjIzOTE3LCJleHAiOjE3Mjg3MTAzMTd9.SpQwR5YPZDukjaLrPq2cpzhZZe0CAd0nG4_My1ZqqDbF6Ypckv--eg5qIaboTYiuciLA9RLReU6guXQ5bH2jVg";

        // Step to delete the room
        given()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .when()
                .delete(deleteRoomEndpoint)
                .then()
                .statusCode(200);

        given()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .body(walls)
                .when()
                .delete(deleteWalls)
                .then()
                .log().all()
                .statusCode(200);
    }

}
