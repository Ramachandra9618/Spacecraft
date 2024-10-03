package testcases;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class HelperMethods {
    public JSONObject updateFurnitureDetails(Map furniture) {
        JSONParser jsonParser = new JSONParser();
        JSONObject updatedFurnitureData = null;

        try {
            FileReader readerRoom = new FileReader("src/unitEntries.json");
            JSONObject furnitureData = (JSONObject) jsonParser.parse(readerRoom);
            furniture.forEach((key, value) -> {
                furnitureData.put(key, value);
            });
            updatedFurnitureData = furnitureData;
        } catch (Exception e) {
            System.out.println(e);
        }
        return updatedFurnitureData;
    }

    public JSONObject updateRoomDetails(Map roomDetails, JSONArray walls) {
        JSONParser jsonParser = new JSONParser();
        String uniqueKey = UUID.randomUUID().toString().replaceAll("-", "");
        ;
        JSONObject UpdatedRoomData = null;
        try {
            FileReader readerRoom = new FileReader("src/room.json");
            JSONObject roomData = (JSONObject) jsonParser.parse(readerRoom);
            JSONArray boundaries = (JSONArray) roomData.get("boundaryWalls");
            roomData.put("key", uniqueKey);
            roomData.put("id", uniqueKey);
            roomDetails.forEach((key, value) -> {
                roomData.put(key, value);
            });
            for (int i = 0; i < walls.size(); i++) {
                JSONObject wall = (JSONObject) walls.get(i);
                JSONObject keyItem = (JSONObject) boundaries.get(i);

                String wallKey = (String) wall.get("key");
                keyItem.put("key", wallKey);
            }
            roomData.put("boundaryWalls", boundaries);
            UpdatedRoomData = roomData;
        } catch (Exception e) {
            System.out.println(e);
        }
        return UpdatedRoomData;
    }

    public JSONObject updateRoomDetails(Map roomDetails) {
        JSONParser jsonParser = new JSONParser();
        String uniqueKey = (String) UUID.randomUUID().toString().replaceAll("-", "");
        ;
        JSONObject UpdatedRoomData = null;
        try {
            FileReader readerRoom = new FileReader("src/room.json");
            JSONObject roomData = (JSONObject) jsonParser.parse(readerRoom);
            roomData.put("key", uniqueKey);
            roomData.put("id", uniqueKey);
            roomDetails.forEach((key, value) -> {
                roomData.put(key, value);
            });

            UpdatedRoomData = roomData;
        } catch (Exception e) {
            System.out.println(e);
        }
        return UpdatedRoomData;
    }

    public JSONArray updateLengthWalls(int length, int width, int roomCount) {

        JSONParser jsonParser = new JSONParser();
        int assignWidth = width + 100;
        int assignLength = length + 100;

        try {
            double startX;
            double startY;
            JSONArray newWallData = new JSONArray();
            FileReader readerWall = new FileReader("src/walls.json");

            JSONArray wallData = (JSONArray) jsonParser.parse(readerWall);
            if (roomCount > 1) {
                startX = (1000 * roomCount) + assignLength;
                startY = (1000 * roomCount) + assignWidth;
            } else {
                startX = 1000;
                startY = 1000;
            }


            wallData.forEach(value -> {
                String uniqueKey = UUID.randomUUID().toString().replaceAll("-", "");
                JSONObject eachWall = (JSONObject) value;
                eachWall.put("key", uniqueKey);
                String caption = (String) eachWall.get("caption");
                JSONObject endPoint = (JSONObject) eachWall.get("endPoint");
                JSONObject startPoint = (JSONObject) eachWall.get("startPoint");
                JSONObject smpt1 = (JSONObject) eachWall.get("smpt1");
                JSONObject smpt2 = (JSONObject) eachWall.get("smpt2");
                JSONObject empt1 = (JSONObject) eachWall.get("empt1");
                JSONObject empt2 = (JSONObject) eachWall.get("empt2");

                switch (caption) {
                    case "wall A":
                        // Horizontal top wall (runs from startX to startX + length)
                        eachWall.put("length", length);
                        startPoint.put("x", startX);
                        startPoint.put("y", startY);
                        endPoint.put("x", startX + assignLength);
                        endPoint.put("y", startY);
//                        smpt1.put("x", startX+50);
//                        smpt1.put("y", startY+50);
//                        smpt2.put("x", startX-50);
//                        smpt2.put("y", startY-50);
//                        empt1.put("x",startX + assignLength-50 );
//                        empt1.put("y",startY-50 );
//                        empt2.put("x",startX + assignLength+50 );
//                        empt2.put("y",startY+50 );


                        break;

                    case "wall B":
                        // Vertical right wall (runs from end of wall A down by width)
                        eachWall.put("length", width);
                        startPoint.put("x", startX + assignLength);
                        startPoint.put("y", startY);
                        endPoint.put("x", startX + assignLength);
                        endPoint.put("y", startY + assignWidth);
//                        smpt1.put("x", startX+assignLength+50);
//                        smpt1.put("y", startY+50);
//                        smpt2.put("x", startX+assignLength-50);
//                        smpt2.put("y", startY-50);
//                        empt1.put("x",startX + assignLength-50 );
//                        empt1.put("y",startY+assignWidth-50 );
//                        empt2.put("x",startX + assignLength+50 );
//                        empt2.put("y",startY+assignWidth+50 );
                        break;

                    case "wall C":
                        // Horizontal bottom wall (runs from startX + length to startX along bottom)
                        eachWall.put("length", length);
                        startPoint.put("x", startX + assignLength);
                        startPoint.put("y", startY + assignWidth);
                        endPoint.put("x", startX);
                        endPoint.put("y", startY + assignWidth);
//                        smpt1.put("x", startX+assignLength+50);
//                        smpt1.put("y", startY+assignWidth+50);
//                        smpt2.put("x", startX+assignLength-50);
//                        smpt2.put("y", startY+assignWidth-50);
//                        empt1.put("x",startX -50 );
//                        empt1.put("y",startY+assignWidth-50 );
//                        empt2.put("x",startX +50 );
//                        empt2.put("y",startY+assignWidth+50 );
                        break;

                    case "wall D":
                        // Vertical left wall (runs from bottom of wall C back up to start)
                        eachWall.put("length", width);
                        startPoint.put("x", startX);
                        startPoint.put("y", startY + assignWidth);
                        endPoint.put("x", startX);
                        endPoint.put("y", startY);
//                        smpt1.put("x", startX+50);
//                        smpt1.put("y", startY+assignWidth+50);
//                        smpt2.put("x", startX-50);
//                        smpt2.put("y", startY+assignWidth-50);
//                        empt1.put("x",startX -50 );
//                        empt1.put("y",startY-50 );
//                        empt2.put("x",startX +50 );
//                        empt2.put("y",startY+50 );
                        break;
                }

                // Update the wall with new coordinates
                eachWall.put("endPoint", endPoint);
                eachWall.put("startPoint", startPoint);
//                eachWall.put("smpt1", smpt1);
//                eachWall.put("smpt2", smpt2);
//                eachWall.put("empt1", empt1);
//                eachWall.put("empt2", empt2);

                newWallData.add(eachWall);
            });
            return newWallData;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public JSONArray updateWalls(int width) {
        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray newWallData = new JSONArray();
            FileReader readerWall = new FileReader("src/walls.json");

            JSONArray wallData = (JSONArray) jsonParser.parse(readerWall);
            wallData.forEach(value -> {
                JSONObject eachWall = (JSONObject) value;
                String uniqueKey = UUID.randomUUID().toString().replaceAll("-", "");

                eachWall.put("key", uniqueKey);
                JSONObject endPoint = (JSONObject) eachWall.get("endPoint");
                JSONObject startPoint = (JSONObject) eachWall.get("startPoint");
                JSONObject smpt1 = (JSONObject) eachWall.get("smpt1");
                JSONObject smpt2 = (JSONObject) eachWall.get("smpt2");
                JSONObject empt1 = (JSONObject) eachWall.get("empt1");
                JSONObject empt2 = (JSONObject) eachWall.get("empt2");

                // Parse x, y coordinates for both end and start points
                double x = Double.parseDouble(endPoint.get("x").toString());
                double y = Double.parseDouble(endPoint.get("y").toString());
                double xs = Double.parseDouble(startPoint.get("x").toString());
                double ys = Double.parseDouble(startPoint.get("y").toString());

                // Parse coordinates for smpt1, smpt2, empt1, empt2
                double smpt1x = Double.parseDouble(smpt1.get("x").toString());
                double smpt1y = Double.parseDouble(smpt1.get("y").toString());
                double smpt2x = Double.parseDouble(smpt2.get("x").toString());
                double smpt2y = Double.parseDouble(smpt2.get("y").toString());
                double empt1x = Double.parseDouble(empt1.get("x").toString());
                double empt1y = Double.parseDouble(empt1.get("y").toString());
                double empt2x = Double.parseDouble(empt2.get("x").toString());
                double empt2y = Double.parseDouble(empt2.get("y").toString());

                // Adjust coordinates by adding the width
                double newX = x + width;
                double newY = y + width;
                double newXS = xs + width;
                double newYS = ys + width;

                double newSmpt1X = smpt1x + width;
                double newSmpt1Y = smpt1y + width;
                double newSmpt2X = smpt2x + width;
                double newSmpt2Y = smpt2y + width;
                double newEmpt1X = empt1x + width;
                double newEmpt1Y = empt1y + width;
                double newEmpt2X = empt2x + width;
                double newEmpt2Y = empt2y + width;

                // Update JSON objects with new values
                endPoint.put("x", newX);
                endPoint.put("y", newY);
                startPoint.put("x", newXS);
                startPoint.put("y", newYS);
                smpt1.put("x", newSmpt1X);
                smpt1.put("y", newSmpt1Y);
                smpt2.put("x", newSmpt2X);
                smpt2.put("y", newSmpt2Y);
                empt1.put("x", newEmpt1X);
                empt1.put("y", newEmpt1Y);
                empt2.put("x", newEmpt2X);
                empt2.put("y", newEmpt2Y);


                // Add the updated wall data to the newWallData array
                newWallData.add(eachWall);
            });

            return newWallData;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void apply(JSONObject room, JSONArray wall, JSONObject units) {

        String baseURI = "https://sc-backend-production.homelane.com/api/v1.0/";
        String wallEndpoint = baseURI + "project/0db2a0db-e304-4459-b3d6-3d961cb816c4/floor/5f642b7d-ce8a-43f3-b38c-8b909d80e93f/walls";
        String roomEndpoint = baseURI + "project/0db2a0db-e304-4459-b3d6-3d961cb816c4/floors/5f642b7d-ce8a-43f3-b38c-8b909d80e93f/room";
        String unitEntries = baseURI + "project/0db2a0db-e304-4459-b3d6-3d961cb816c4/floors/5f642b7d-ce8a-43f3-b38c-8b909d80e93f/rooms/720c410cf9ba2efe33e7d6d6f2586c93/unitEntries";
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiUk9MRV9ERVNJR05FUiIsInByb2plY3QiOiIwZGIyYTBkYi1lMzA0LTQ0NTktYjNkNi0zZDk2MWNiODE2YzQiLCJlbWFpbCI6InRlc3RvcmdzdHJ1Y3R1cmVkcDFAaG9tZWxhbmUuY29tIiwib3BlbiI6ZmFsc2UsInN1YiI6InRlc3RvcmdzdHJ1Y3R1cmVkcDFAaG9tZWxhbmUuY29tIiwiaWF0IjoxNzI3OTI3MjQzLCJleHAiOjE3MjgwMTM2NDN9.99i4iU6G2hheECZqt536qxq7O-1p_eRuanon_JVQr61cV6LfWF6fS0BqslaMsMqemsT1ba0B2DUozDClwa3ZCw";
        given()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .body(wall)
                .when()
                .post(wallEndpoint).then().statusCode(200);


        given()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(room)
                .when()
                .post(roomEndpoint).then().statusCode(200);

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

    public void creatingRooms(JSONObject room, JSONArray wall) {
        String baseURI = "https://sc-backend-production.homelane.com/api/v1.0/";
        String wallEndpoint = baseURI + "project/0db2a0db-e304-4459-b3d6-3d961cb816c4/floor/5f642b7d-ce8a-43f3-b38c-8b909d80e93f/walls";
        String roomEndpoint = baseURI + "project/0db2a0db-e304-4459-b3d6-3d961cb816c4/floors/5f642b7d-ce8a-43f3-b38c-8b909d80e93f/room";
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiUk9MRV9ERVNJR05FUiIsInByb2plY3QiOiIwZGIyYTBkYi1lMzA0LTQ0NTktYjNkNi0zZDk2MWNiODE2YzQiLCJlbWFpbCI6InRlc3RvcmdzdHJ1Y3R1cmVkcDFAaG9tZWxhbmUuY29tIiwib3BlbiI6ZmFsc2UsInN1YiI6InRlc3RvcmdzdHJ1Y3R1cmVkcDFAaG9tZWxhbmUuY29tIiwiaWF0IjoxNzI3OTI3MjQzLCJleHAiOjE3MjgwMTM2NDN9.99i4iU6G2hheECZqt536qxq7O-1p_eRuanon_JVQr61cV6LfWF6fS0BqslaMsMqemsT1ba0B2DUozDClwa3ZCw";
        given()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .body(wall)
                .when()
                .post(wallEndpoint).then().statusCode(200);
        given()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(room)
                .when()
                .post(roomEndpoint).then().statusCode(200);
    }


}
