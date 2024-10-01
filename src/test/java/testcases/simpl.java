package testcases;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class simpl {
    public static void main(String[] args) {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("src/room.json")) {
            JSONObject roomData = (JSONObject) jsonParser.parse(reader);
             roomData.put("breadth", 2000);
             roomData.put("roomType", "Kitchen");
             roomData.put("roomName", "rama chandre");
            roomData.put("length", 3000);
            // Print the entire JSON data
            System.out.println(roomData.toJSONString());

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }




        //        File units = new File("src/unitEntries.json");
//        JSONObject furnitureJson = new JSONObject();
//
//        furnitureJson.put("zoneID", "c11aaa89-0e36-4615-b1bc-121de858aef1");
//        furnitureJson.put("unitEntryType", "FITTED_FURNITURE");
//        furnitureJson.put("width", 1000);
//        furnitureJson.put("height", 2000);
//        furnitureJson.put("length", 613);
//
//        // Create position JSONObject
//        JSONObject position = new JSONObject();
//        position.put("x", 4176);
//        position.put("y", 0);
//        position.put("z", 1993.5); // 1993.5000000000002 can be simplified to 1993.5
//
//        // Create rotation JSONObject
//        JSONObject rotation = new JSONObject();
//        rotation.put("x", 0);
//        rotation.put("y", 0);
//        rotation.put("z", 0);
//
//        // Add position and rotation to the main JSONObject
//        furnitureJson.put("position", position);
//        furnitureJson.put("rotation", rotation);
//
//        furnitureJson.put("categoryId", "10003");
//        furnitureJson.put("subCategoryId", "10077");
//        furnitureJson.put("addedFromMySpace", false);
//        furnitureJson.put("profileType", "REGULAR");
//        String token = "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiUk9MRV9ERVNJR05FUiIsInByb2plY3QiOiIwZGIyYTBkYi1lMzA0LTQ0NTktYjNkNi0zZDk2MWNiODE2YzQiLCJlbWFpbCI6InRlc3RvcmdzdHJ1Y3R1cmVkcDFAaG9tZWxhbmUuY29tIiwib3BlbiI6ZmFsc2UsInN1YiI6InRlc3RvcmdzdHJ1Y3R1cmVkcDFAaG9tZWxhbmUuY29tIiwiaWF0IjoxNzI3NzA5Mjg4LCJleHAiOjE3Mjc3OTU2ODh9.lNSFr5reGV5Lmo3CefqC5N2J8m_oMo4nSIABsvFXI5WKf52odqr0V5HRgUky0uMfxcrf3rFmg46p89MIPekbFg";
//        String baseURI = "https://sc-backend-production.homelane.com/api/v1.0/";
//        String unitEntries = baseURI+"project/0db2a0db-e304-4459-b3d6-3d961cb816c4/floors/5f642b7d-ce8a-43f3-b38c-8b909d80e93f/rooms/704f3e754da5b24eef479756790f0fca/unitEntries";
//        Response response = given()
//                .header("Authorization", token)
//                .header("Accept", "*/*")
//                .header("Content-Type", "application/json")
//                .body(units)  // Add the JSON payload as the body
//                .when()
//                .post(unitEntries) // Full endpoint
//                .then()
//                .log().all()       // Log the full response for inspection
//                .extract().response();
    }
}
