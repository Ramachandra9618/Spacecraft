package testcases;


import dataProvider.RoomDataProvider;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObject.DesignerPage;
import pageObject.IndexPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
       String product =  designerPage.findCategory(category, subCategory);
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

    @AfterMethod
    void tearDown() {
        driver.quit();
    }
}



