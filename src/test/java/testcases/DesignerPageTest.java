package testcases;


import basicUtilities.ReadDataFromCSV;
import dataProvider.RoomDataProvider;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObject.DesignerPage;
import pageObject.IndexPage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static io.restassured.RestAssured.given;


public class DesignerPageTest extends BaseClass {

    @Test
    void designerTest() throws InterruptedException {

        IndexPage index = new IndexPage();
        DesignerPage designerPage = new DesignerPage();

        String roomType = "Bedroom";
        String width = "3000";
        String length = "3000";
        String furnitureType = "Fitted Furniture";
        String category = "Loft";
        String subCategory = "Box Loft";
        String productName;

        index.clickSkip();  // Skip initial screen
        designerPage.openRoomSettings();  // Open room settings

        // Check if the 'Bedroom' already exists, if not, create a new room
        boolean roomExists = designerPage.findAndSelectRoom(roomType);
        System.out.println(roomExists);
        if (!roomExists) {
            designerPage.closeSidebar();
            designerPage.createNewRoomWithRectangle(width, length);
        }
        Thread.sleep(5000);
        designerPage.switchTo3DView();
        Thread.sleep(5000);
        designerPage.openFurnitureTab();
        Thread.sleep(5000);
        designerPage.selectFurnitureType(furnitureType);

        designerPage.selectCategory(category);
        designerPage.selectSubCategory(subCategory);
        designerPage.hoverOnProduct();
        productName = designerPage.getProductItemText();
        designerPage.addProductToRoom();
        Thread.sleep(5000);
        designerPage.clickQuoteSummary();

        designerPage.selectRoomInQuoteSummary(roomType);
        designerPage.selectProductType(furnitureType);
        String product = designerPage.findCategory(category, subCategory);
        Assert.assertEquals(productName.split("\n")[0], product);
    }

    @Test(dataProvider = "roomData", dataProviderClass = RoomDataProvider.class)
    public void twoRooms(String roomType, List<Map<String, String>> furnitureList, Map<String, String> coordinates) throws InterruptedException {
        IndexPage indexPage = new IndexPage();
        DesignerPage designerPage = new DesignerPage();
        List<Map<String, String>> checks = new ArrayList<>();

        indexPage.clickSkip();
        designerPage.openRoomSettings();

        if (!designerPage.findAndSelectRoom(roomType)) {
            designerPage.createNewRoom(coordinates);
        }

        designerPage.applyFurniture(furnitureList, checks);
        designerPage.addService("Cleaning", "All Cleaning");
        designerPage.verifyFurnitureInQuoteSummary(roomType, checks);
    }
//
//    @AfterMethod
//    void tearDown() {
//        driver.quit();
//    }

    @Test
    public void downloadPricePayloadTest() throws InterruptedException {
        String CSVpath = "src/test/resources/module.csv";
        String sheetName = "sheet1";

        ReadDataFromCSV readDataFromCSV = new ReadDataFromCSV(CSVpath, sheetName);
        String roomType = readDataFromCSV.getData(1, 1);
        String roomName = readDataFromCSV.getData(1, 2);
        String length = readDataFromCSV.getData(1, 3);
        String width = readDataFromCSV.getData(1, 4);
        String furnitureType = readDataFromCSV.getData(1, 5);
        String category = readDataFromCSV.getData(1, 6);
        String subCategory = readDataFromCSV.getData(1, 8);
        String module = readDataFromCSV.getData(1, 10);

        Map<String, String> roomDimensions = new HashMap<>();
        roomDimensions.put("roomType", roomType);
        roomDimensions.put("roomName", roomName);
        roomDimensions.put("startX", "10");
        roomDimensions.put("startY", "10");
        roomDimensions.put("endX", "20");
        roomDimensions.put("endY", "20");
        roomDimensions.put("width", width);
        roomDimensions.put("length", length);


        IndexPage indexPage = new IndexPage();
        DesignerPage designerPage = new DesignerPage();
        indexPage.clickSkip();
        designerPage.openRoomSettings();
        if (!designerPage.findAndSelectRoom(roomName)) {
            designerPage.createNewRoom(roomDimensions);
        }

        designerPage.switchTo3DView();
        Thread.sleep(5000);

        designerPage.openFurnitureTab();

        designerPage.selectFurnitureType(furnitureType);

        designerPage.selectCategory(category);
        designerPage.selectSubCategory(subCategory);
        designerPage.searchModule(module);
        designerPage.hoverOnProduct();
        Thread.sleep(2000);
        designerPage.addProductToRoom();
        designerPage.EnableHideEnvironment();
        designerPage.clickCloud();
        designerPage.selectList();
    }


    @Test
    public void downloadPricePayloadUsingAPITest() throws InterruptedException {
        String CSVpath = "src/test/resources/module.csv";
        String sheetName = "sheet1";

        ReadDataFromCSV readDataFromCSV = new ReadDataFromCSV(CSVpath, sheetName);
        String roomType = readDataFromCSV.getData(1, 1);
        String roomName = readDataFromCSV.getData(1, 2);
        String length = readDataFromCSV.getData(1, 3);
        String width = readDataFromCSV.getData(1, 4);
        String furnitureType = readDataFromCSV.getData(1, 5);
        String categoryID = readDataFromCSV.getData(1, 7);
        String subCategoryID = readDataFromCSV.getData(1, 9);

        Map<String, String> furniture = new HashMap<>();
        furniture.put("unitEntryType", furnitureType.toUpperCase());
        furniture.put("categoryId", categoryID);

        furniture.put("subCategoryId", subCategoryID);
        Map<String, String> roomDimensions = new HashMap<>();
        roomDimensions.put("roomType", roomType);
        roomDimensions.put("roomName", roomName);
        roomDimensions.put("name", roomName);
        roomDimensions.put("width", width);
        roomDimensions.put("length", length);

        JSONArray wall = updateLengthWalls(Integer.parseInt("5000"), Integer.parseInt("3000"), 1);
        JSONObject room = updateRoomDetails(roomDimensions);
        room.put("walls", wall);
        JSONObject units = updateFurnitureDetails(furniture);


        IndexPage indexPage = new IndexPage();
        DesignerPage designerPage = new DesignerPage();


        apply(room, wall, units);
        Thread.sleep(10000);
        indexPage.clickSkip();
        designerPage.openRoomSettings();
        if (!designerPage.findAndSelectRoom(roomName)) {
//             designerPage.createNewRoom(roomDimensions);
        }

        designerPage.switchTo3DView();
        Thread.sleep(5000);
        designerPage.EnableHideEnvironment();
        Thread.sleep(2000);
        designerPage.clickCloud();
        designerPage.selectList();
        System.out.println(updateFurnitureDetails(furniture).toJSONString());

    }


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
        System.out.println(roomCount);
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
                startX = 1000 + (roomCount - 1) * (assignLength + 200);
                startY = 1000;
            } else {
                startX = 1000;
                startY = 1000;
            }


            wallData.forEach(value -> {
                JSONObject eachWall = (JSONObject) value;
                String caption = (String) eachWall.get("caption");
                JSONObject endPoint = (JSONObject) eachWall.get("endPoint");
                JSONObject startPoint = (JSONObject) eachWall.get("startPoint");

                switch (caption) {
                    case "wall A":
                        // Horizontal top wall (runs from startX to startX + length)
                        startPoint.put("x", startX);
                        startPoint.put("y", startY);
                        endPoint.put("x", startX + assignLength);
                        endPoint.put("y", startY);
                        break;

                    case "wall B":
                        // Vertical right wall (runs from end of wall A down by width)
                        startPoint.put("x", startX + assignLength);
                        startPoint.put("y", startY);
                        endPoint.put("x", startX + assignLength);
                        endPoint.put("y", startY + assignWidth);
                        break;

                    case "wall C":
                        // Horizontal bottom wall (runs from startX + length to startX along bottom)
                        startPoint.put("x", startX + assignLength);
                        startPoint.put("y", startY + assignWidth);
                        endPoint.put("x", startX);
                        endPoint.put("y", startY + assignWidth);
                        break;

                    case "wall D":
                        // Vertical left wall (runs from bottom of wall C back up to start)
                        startPoint.put("x", startX);
                        startPoint.put("y", startY + assignWidth);
                        endPoint.put("x", startX);
                        endPoint.put("y", startY);
                        break;
                }

                // Update the wall with new coordinates
                eachWall.put("endPoint", endPoint);
                eachWall.put("startPoint", startPoint);
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
                String uniqueKey = (String) UUID.randomUUID().toString().replaceAll("-", "");

//                eachWall.put("key", uniqueKey);
                JSONObject endPoint = (JSONObject) eachWall.get("endPoint");
                JSONObject startPoint = (JSONObject) eachWall.get("startPoint");

                // Parse x, y coordinates for both end and start points
                double x = Double.parseDouble(endPoint.get("x").toString());
                double y = Double.parseDouble(endPoint.get("y").toString());
                double xs = Double.parseDouble(startPoint.get("x").toString());
                double ys = Double.parseDouble(startPoint.get("y").toString());

                // Adjust the coordinates by adding the width
                double newX = x + width;
                double newY = y + width;
                double newXS = xs + width;
                double newYS = ys + width;

                // Update the endPoint and startPoint with the new values
                endPoint.put("x", newX);
                endPoint.put("y", newY);
                startPoint.put("x", newXS);
                startPoint.put("y", newYS);
                eachWall.put("endPoint", endPoint);
                eachWall.put("startPoint", startPoint);
                newWallData.add(eachWall);
            });
            return newWallData;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void apply(JSONObject room, JSONArray wall, JSONObject units) {
        File uni = new File("src/unitEntries.json");

        String baseURI = "https://sc-backend-production.homelane.com/api/v1.0/";
        String wallEndpoint = baseURI + "project/0db2a0db-e304-4459-b3d6-3d961cb816c4/floor/5f642b7d-ce8a-43f3-b38c-8b909d80e93f/walls";
        String roomEndpoint = baseURI + "project/0db2a0db-e304-4459-b3d6-3d961cb816c4/floors/5f642b7d-ce8a-43f3-b38c-8b909d80e93f/room";
        String unitEntries = baseURI + "project/0db2a0db-e304-4459-b3d6-3d961cb816c4/floors/5f642b7d-ce8a-43f3-b38c-8b909d80e93f/rooms/720c410cf9ba2efe33e7d6d6f2586c93/unitEntries";
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiUk9MRV9ERVNJR05FUiIsInByb2plY3QiOiIwZGIyYTBkYi1lMzA0LTQ0NTktYjNkNi0zZDk2MWNiODE2YzQiLCJlbWFpbCI6InRlc3RvcmdzdHJ1Y3R1cmVkcDFAaG9tZWxhbmUuY29tIiwib3BlbiI6ZmFsc2UsInN1YiI6InRlc3RvcmdzdHJ1Y3R1cmVkcDFAaG9tZWxhbmUuY29tIiwiaWF0IjoxNzI3ODYxMjQ2LCJleHAiOjE3Mjc5NDc2NDZ9.9DpyRYaAPJdWf82U51bERlvxipoy5Wkiod6ceD7iV-Muxy_ypTaBjkE7eBFVl4fpZRsx4BU0clud3d08H5vprQ";

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




    @Test
    public void createMultipleRooms() {
        int count = 5;  // Number of rooms to create
        int width = 4000;  // Base width value
        int length = 2000;
        for (int i = 1; i <= count; i++) {
            System.out.println(width);
            // Map to store room details
            Map<String, String> m = new HashMap<>();
            m.put("width", String.valueOf(width));
            m.put("length", String.valueOf(length));
            m.put("name", "livingRoom" + "" + i);
            Map<String, String> s = new HashMap<>();

            JSONArray wall = updateLengthWalls(width, length, i);
            JSONObject room = updateRoomDetails(m);  // Update room details
            room.put("walls", wall);
            // Print updated room details (for debugging)
            System.out.println(room.toJSONString());

            // Update furniture details (if any)
            JSONObject furniture = updateFurnitureDetails(s);

            // Apply the room, walls, and furniture configuration
            apply(room, wall, furniture);
        }
    }

}



