package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;
import pageObject.DesignerPage;
import pageObject.IndexPage;

import java.util.Map;

public class CreateRoomTest extends BaseClass{

    @Test
    void roomTest() throws InterruptedException {
        IndexPage indexPage = new IndexPage();
        DesignerPage designerPage = new DesignerPage();
        indexPage.clickSkip();
        designerPage.clickDrawPlanButton();
        designerPage.createRoom();
        designerPage.openCreateRoomOption();
        Thread.sleep(2000);
        designerPage.selectRectangleShapeOption();
        designerPage.drawRectangleOnCanvas();
        designerPage.openRoomTypeDropdown();
        designerPage.selectBedroom();
        designerPage.enterRoomName("me");
        designerPage.enterWidthAndLength("4000", "2000");
        designerPage.confirmRoomSelection();
    }
}
