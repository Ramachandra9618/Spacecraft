package testcases;


import basicUtilities.ReadDataFromCSV;
import dataProvider.RoomDataProvider;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObject.DesignerPage;
import pageObject.IndexPage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//
//    @AfterMethod
//    void tearDown() {
//        driver.quit();
//    }

    @Test
    public void downloadPricePayloadTest() throws InterruptedException {
        String CSVpath ="src/test/resources/module.csv";
        String sheetName ="sheet1";
        ReadDataFromCSV readDataFromCSV = new ReadDataFromCSV(CSVpath, sheetName);
       String roomType=  readDataFromCSV.getData(1,1);
        String roomName=  readDataFromCSV.getData(1,2);
        String length = readDataFromCSV.getData(1,3);
        String width = readDataFromCSV.getData(1,4);
        String furnitureType = readDataFromCSV.getData(1,5);
        String category = readDataFromCSV.getData(1,6);
        String subCategory = readDataFromCSV.getData(1,8);
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
}



