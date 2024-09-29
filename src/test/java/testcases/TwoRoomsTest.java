package testcases;

import org.testng.annotations.Test;
import pageObject.DesignerPage;
import pageObject.IndexPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoRoomsTest extends BaseClass  {

    @Test
    public void twoRooms() throws InterruptedException {
        IndexPage indexPage = new IndexPage();
        DesignerPage designerPage = new DesignerPage();
        List<Map<String, String>> checks = new ArrayList<>();

        // Room 1 data
        List<Map<String, String>> room1Furniture = new ArrayList<>();
        Map<String, String> fittedFurniture = new HashMap<>();
        fittedFurniture.put("furnitureType", "Fitted Furniture");
        fittedFurniture.put("category", "Loft");
        fittedFurniture.put("subCategory", "Box Loft");

        Map<String, String> looseFurniture = new HashMap<>();
        looseFurniture.put("furnitureType", "Loose Furniture");
        looseFurniture.put("category", "Sofa & Sofa Sets");
        looseFurniture.put("subCategory", "All Sofa & Sets");

        room1Furniture.add(fittedFurniture);
        room1Furniture.add(looseFurniture);

        // Room 1 dimensions (Living Room)
        Map<String, String> livingRoomDimensions = new HashMap<>();
        livingRoomDimensions.put("roomType", "Living Room");
        livingRoomDimensions.put("roomName", "Living");
        livingRoomDimensions.put("startX", "100");
        livingRoomDimensions.put("startY", "100");
        livingRoomDimensions.put("endX", "200");
        livingRoomDimensions.put("endY", "200");
        livingRoomDimensions.put("width", "3000");
        livingRoomDimensions.put("length", "3000");

        // Room 2 data
        List<Map<String, String>> room2Furniture = new ArrayList<>();
        Map<String, String> decorFurniture = new HashMap<>();
        decorFurniture.put("furnitureType", "Appliances & Decor");
        decorFurniture.put("category", "Ceiling Panels");
        decorFurniture.put("subCategory", "All Ceiling Panels");

        room2Furniture.add(fittedFurniture);  // Adding the same fitted furniture for room 2
        room2Furniture.add(decorFurniture);

        // Room 2 dimensions (Bedroom)
        Map<String, String> bedroomDimensions = new HashMap<>();
        bedroomDimensions.put("roomType", "Bedroom");
        bedroomDimensions.put("roomName", "Kitchen");
        bedroomDimensions.put("startX", "10");
        bedroomDimensions.put("startY", "10");
        bedroomDimensions.put("endX", "20");
        bedroomDimensions.put("endY", "20");
        bedroomDimensions.put("width", "4000");
        bedroomDimensions.put("length", "4000");

        // Start the test
        indexPage.clickSkip();
        designerPage.openRoomSettings();

        // Create and configure Room 1 (Living Room)
        if (!designerPage.findAndSelectRoom("Livingroom")) {
            designerPage.createNewRoom(livingRoomDimensions);
        }
        designerPage.applyFurniture(room1Furniture, checks);
        designerPage.addService("Cleaning", "All Cleaning");
        designerPage.verifyFurnitureInQuoteSummary("Livingroom", checks);
        Thread.sleep(5000);
        designerPage.switchTo2DView();

        // Create and configure Room 2 (Bedroom)
        designerPage.openRoomSettings();
        if (!designerPage.findAndSelectRoom("Bedroom")) {
            designerPage.createNewRoom(bedroomDimensions);
        }
        designerPage.applyFurniture(room2Furniture, checks);
        designerPage.addService("Cleaning", "All Cleaning");
        designerPage.verifyFurnitureInQuoteSummary("Bedroom", checks);
        designerPage.switchTo2DView();
    }
}
