package dataProvider;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomDataProvider {

    @DataProvider(name = "roomData")
    public static Object[][] getRoomData() {
        // Define furniture sets for Room 1
        List<Map<String, String>> room1Furniture = new ArrayList<>();
        Map<String, String> fittedFurniture = new HashMap<>();
        fittedFurniture.put("furnitureType", "Fitted Furniture");
        fittedFurniture.put("category", "Loft");
        fittedFurniture.put("subCategory", "Box Loft");

        Map<String, String> looseFurniture = new HashMap<>();
        looseFurniture.put("furnitureType", "Loose Furniture");
        looseFurniture.put("category", "Sofa & Sofa Sets");
        looseFurniture.put("subCategory", "All Sofa & Sets");

        Map<String, String> service = new HashMap<>();
        service.put("serviceType", "Cleaning");
        service.put("subService", "All Cleaning");

        room1Furniture.add(fittedFurniture);
        room1Furniture.add(looseFurniture);

        // Define furniture sets for Room 2
        List<Map<String, String>> room2Furniture = new ArrayList<>();
        Map<String, String> decorFurniture = new HashMap<>();
        decorFurniture.put("furnitureType", "Appliances & Decor");
        decorFurniture.put("category", "Ceiling Panels");
        decorFurniture.put("subCategory", "All Ceiling Panels");

        room2Furniture.add(fittedFurniture);
        room2Furniture.add(decorFurniture);

        Map<String, String> livingRoomDimensions = new HashMap<>();
        livingRoomDimensions.put("roomType", "Living Room");
        livingRoomDimensions.put("roomName", "Living");
        livingRoomDimensions.put("startX", "100");
        livingRoomDimensions.put("startY", "100");
        livingRoomDimensions.put("endX", "200");
        livingRoomDimensions.put("endY", "200");
        livingRoomDimensions.put("width", "3000");
        livingRoomDimensions.put("length", "3000");

        Map<String, String> kitchenDimensions = new HashMap<>();
        kitchenDimensions.put("roomType", "Bedroom");
        kitchenDimensions.put("roomName", "Kitchen");
        kitchenDimensions.put("startX", "150");
        kitchenDimensions.put("startY", "150");
        kitchenDimensions.put("endX", "250");
        kitchenDimensions.put("endY", "250");
        kitchenDimensions.put("width", "4000");
        kitchenDimensions.put("length", "4000");

        return new Object[][] {
                {"Livingroom", room1Furniture, livingRoomDimensions},
                {"Bedroom", room2Furniture, kitchenDimensions}
        };
    }
}
