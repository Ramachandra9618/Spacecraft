package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import pageObject.DesignerPage;
import pageObject.IndexPage;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CreateTwoRoomsTest extends BaseClass {

    @Test
    void roomTest() {
        List<Map<String, Object>> dimensions = new LinkedList<>();

        // Define dimensions for Living Room
        Map<String, Object> livingRoomDimensions = new HashMap<>();
        livingRoomDimensions.put("roomType", "Living Room");
        livingRoomDimensions.put("roomName", "Living");
        livingRoomDimensions.put("startX", 100);
        livingRoomDimensions.put("startY", 100);
        livingRoomDimensions.put("endX", 200);
        livingRoomDimensions.put("endY", 200);
        livingRoomDimensions.put("width", "1000");
        livingRoomDimensions.put("length", "1000");

        // Define dimensions for Kitchen
        Map<String, Object> kitchenDimensions = new HashMap<>();
        kitchenDimensions.put("roomName", "Kitchen");
        kitchenDimensions.put("roomType", "Kitchen");
        kitchenDimensions.put("startX", 50);
        kitchenDimensions.put("startY", 50);
        kitchenDimensions.put("endX", 90);
        kitchenDimensions.put("endY", 90);
        kitchenDimensions.put("width", "1000");
        kitchenDimensions.put("length", "1000");

        dimensions.add(livingRoomDimensions);
        dimensions.add(kitchenDimensions);

        IndexPage indexPage = new IndexPage();
        DesignerPage designerPage = new DesignerPage();

        indexPage.clickSkip();
        designerPage.clickDrawPlanButton();

        for (Map<String, Object> rect : dimensions) {
            designerPage.createRoom();
            designerPage.openCreateRoomOption();

            designerPage.selectRectangleShapeOption();

            // Draw rectangle on canvas
            designerPage.drawRectangleOnCanvas(
                    (Integer) rect.get("startX"),
                    (Integer) rect.get("startY"),
                    (Integer) rect.get("endX"),
                    (Integer) rect.get("endY")
            );

            designerPage.openRoomTypeDropdown();
            designerPage.selectRoomType((String) rect.get("roomType"));
            designerPage.enterRoomName((String) rect.get("roomName"));
            designerPage.enterWidthAndLength((String) rect.get("width"), (String) rect.get("length"));
            designerPage.confirmRoomSelection();

        }
    }

    @Test
    void Zooming() throws InterruptedException {
        IndexPage indexPage = new IndexPage();
        DesignerPage designerPage = new DesignerPage();

        WebElement zoomElement = driver.findElement(By.xpath("//div[@id='fpApp']//canvas[@tabindex='0']"));
        Actions actions = new Actions(driver);

        indexPage.clickSkip();
        Thread.sleep(3000);
         designerPage.drawRectangleOnCanvas();
        actions.moveToElement(zoomElement).perform();

        actions.scrollByAmount(0, 500).perform();
        actions.scrollByAmount(0, -500).perform();
    }
}
