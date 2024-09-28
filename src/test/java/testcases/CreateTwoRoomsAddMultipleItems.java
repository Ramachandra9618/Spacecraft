package testcases;
import dataProvider.RoomDataProvider;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.DesignerPage;
import pageObject.IndexPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateTwoRoomsAddMultipleItems extends BaseClass {

    @Test(dataProvider = "roomData", dataProviderClass = RoomDataProvider.class)
    public void twoRooms(String roomType,  List<Map<String, String>> furnitureList, Map<String, String> coordinates) throws InterruptedException {
        IndexPage indexPage = new IndexPage();
        DesignerPage designerPage = new DesignerPage();
        List<Map<String, String>> checks = new ArrayList<>();

        indexPage.clickSkip();
        designerPage.openRoomSettings();

        if (!designerPage.findAndSelectRoom(roomType)) {
            createNewRoom(designerPage, coordinates);
        }

        applyFurniture(designerPage, furnitureList, checks);
        addService(designerPage, "Cleaning", "All Cleaning");
        verifyFurnitureInQuoteSummary(designerPage, roomType, checks);
    }



    // Method to create a new room
    private void createNewRoom(DesignerPage designerPage, Map<String, String> coordinates) throws InterruptedException {
        designerPage.clickDrawPlanButton();
        designerPage.createRoom();
        designerPage.openCreateRoomOption();
        designerPage.selectRectangleShapeOption();
        Thread.sleep(5000);

        designerPage.drawRectangleOnCanvas(
                Integer.parseInt(coordinates.get("startX")),
                Integer.parseInt(coordinates.get("startY")),
                Integer.parseInt(coordinates.get("endX")),
                Integer.parseInt(coordinates.get("endY"))
        );

        designerPage.openRoomTypeDropdown();
        designerPage.selectRoomType(coordinates.get("roomType"));
        designerPage.enterWidthAndLength(coordinates.get("width"), coordinates.get("length"));
        designerPage.confirmRoomSelection();
    }

    // Method to apply furniture to the room
    private void applyFurniture(DesignerPage designerPage, List<Map<String, String>> furnitureList, List<Map<String, String>> checks) throws InterruptedException {
        designerPage.switchTo3DView();
        Thread.sleep(5000);
        designerPage.openFurnitureTab();
        Thread.sleep(5000);

        for (Map<String, String> furniture : furnitureList) {
            designerPage.selectFurnitureType(furniture.get("furnitureType"));
            Thread.sleep(2000);
            designerPage.selectCategory(furniture.get("category"));
            designerPage.selectSubCategory(furniture.get("subCategory"));

            String productName;
            Map<String, String> temp = new HashMap<>();

            if (furniture.get("furnitureType").equals("Fitted Furniture")) {
                designerPage.hoverOnProduct();
                productName = designerPage.getProductItemText().split("\n")[0];
                temp.put("furnitureType", "Fitted Furniture");
            } else {
                designerPage.hoverOnFirstProductItem();
                productName = designerPage.getFirstProductItemName();
                temp.put("furnitureType", furniture.get("furnitureType"));
            }

            temp.put("category", furniture.get("category"));
            temp.put("subCategory", furniture.get("subCategory"));
            temp.put("product", productName);
            checks.add(temp);

            addFurnitureToRoom(designerPage, furniture);
        }
    }

    // Helper method to add furniture to the room
    private void addFurnitureToRoom(DesignerPage designerPage, Map<String, String> furniture) throws InterruptedException {
        if (furniture.get("furnitureType").equals("Fitted Furniture")) {
            designerPage.addProductToRoom();
            Thread.sleep(5000);
            designerPage.clickBackToCategory();
        } else {
            designerPage.clickFirstAddToRoomButton();
            designerPage.clickAddToQuoteButton();
            designerPage.clickCloseButton();
            designerPage.openFurnitureTab();
        }
    }

    // Method to add a service to the room
    private void addService(DesignerPage designerPage, String serviceType, String subServiceType) throws InterruptedException {
        designerPage.clickServicesTabButton();
        designerPage.selectService(serviceType);
        Thread.sleep(2000);
        designerPage.selectSubService(subServiceType);
        System.out.println(designerPage.getServiceNameText());
        designerPage.clickAddToRoomButton();
        designerPage.clickServiceAddToQuoteButton();
        Thread.sleep(2000);
    }

    // Method to verify furniture in the quote summary
    private void verifyFurnitureInQuoteSummary(DesignerPage designerPage, String roomType, List<Map<String, String>> checks) throws InterruptedException {
        designerPage.clickQuoteSummary();
        designerPage.selectRoomInQuoteSummary(roomType);

        for (Map<String, String> check : checks) {
            designerPage.selectProductType(check.get("furnitureType"));
            String product = designerPage.findCategory(check.get("category"), check.get("subCategory"));
            Assert.assertEquals(check.get("product").toUpperCase(), product.toUpperCase());
        }
    }

}















































