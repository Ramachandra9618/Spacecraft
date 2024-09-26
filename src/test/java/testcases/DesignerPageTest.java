package testcases;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.DesignerPage;
import pageObject.IndexPage;


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


}
