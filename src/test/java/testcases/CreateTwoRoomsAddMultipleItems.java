package testcases;
import dataProvider.RoomDataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObject.DesignerPage;
import pageObject.IndexPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateTwoRoomsAddMultipleItems extends BaseClass {

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

    @AfterMethod
    void tearDown() {
        driver.quit();
    }
}























