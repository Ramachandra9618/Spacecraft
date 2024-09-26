package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import testcases.BaseClass;

import java.time.Duration;
import java.util.List;

public class DesignerPage extends BaseClass {

    public DesignerPage(){
        PageFactory.initElements(driver, this);
    }
    Actions actions = new Actions(driver);


    @FindBy(xpath = "//div[@class='Sidebar__icon--v3PMc']")
    WebElement roomsIcon;

    @FindBy(css = "div.TopBar__tool--xv6vc.TopBar__lockedTool--2Abm2")
    WebElement drawPlanButton;

    @FindBy(id = "create room")
    WebElement createRoomButton;

    @FindBy(className = "TopBar__createRoomOption--2Tezg")
    WebElement createRoomOption;


    @FindBy(css = "div[class='subOptionList'] p:nth-child(1)") WebElement rectangleShapeOption;

    @FindBy(xpath = "//div[@class='Rooms__roomListItem--1-UbQ']//p")
    List<WebElement> roomList;

    @FindBy(css = "input .inputBoxGrid null form-control") List <WebElement> widthLengthInputs;

    @FindBy(xpath = "//div[@class='ViewSwitch__tab--2v2Vs false 3DSwitch']")
    WebElement threeDViewButton;

    @FindBy(css = "canvas[tabindex='0'][width='1500']")
    WebElement designCanvas;

    @FindBy(xpath = "//div[@class='form-group col']//button[@id='dropdown-button-drop-down']")
    WebElement roomTypeDropdown;

    @FindBy(linkText = "Bedroom")
    WebElement bedroomOption;

    @FindBy(css = "button[type='submit']")
    WebElement confirmButton;

    @FindBy(id = "furniture")
    WebElement furnitureTab;

    @FindBy(className = "CategoryList__categoryItem--1posh")
    List<WebElement> furnitureTypeList;

    @FindBy(className = "Sidebar__headerIco--1lZyt")
    WebElement closeSidebarButton;


    @FindBy(xpath = "//div[@class='ProductList__cardWrapperProduct--VpD71']")
    WebElement productItem;

    @FindBy(className = "ProductList__zoneAddToRoom--2CrTL")
    WebElement addToRoomButton;
    //22
    @FindBy(css = "span.Topbar__noErrorPrice--2vw4K")
     WebElement quoteSummaryOption;

    @FindBy(css = "div.RoomSummary__roomsListItem--1gKUX span.RoomSummary__roomName--1MEL9")
     List<WebElement> roomNames;

    @FindBy(css = "select.RoomSummary__roomSummarySelect--2zRHB")
     WebElement roomSummarySelectElement;

    @FindBy(css = "div.RoomSummary__zoneDiv--3qxb8 div.RoomSummary__zoneContainer--fGX_s p:nth-child(1)")
     List<WebElement> categoriesInQuoteSummary;


    // Actions

    public void closeSidebar() {
        closeSidebarButton.click();
    }

    public void hoverOnProduct() {
        waitForElementToBeVisible(productItem, 10);
        actions.moveToElement(productItem).perform();
    }

    public void addProductToRoom() {
        addToRoomButton.click();
    }

    public void selectFurnitureType(String category) {
        for (WebElement furnitureType : furnitureTypeList) {
            if (furnitureType.getText().equals(category)) {
                furnitureType.click();
                break;
            }
        }
    }

    public void selectCategory(String categoryName) {
        WebElement categoryElement = driver.findElement(By.xpath("//div[contains(text(),'" + categoryName + "')]"));
        waitForElementToBeVisible(categoryElement, 50);
        if (categoryElement.isDisplayed()) {
            categoryElement.click();
        }
    }

    public void selectSubCategory(String subCategoryName) {
        WebElement subCategoryElement = driver.findElement(By.xpath("//div[contains(text(),'" + subCategoryName + "')]"));
        if (subCategoryElement.isDisplayed()) {
            subCategoryElement.click();
        }
    }

    public void openFurnitureTab() {
        waitForElementToBeVisible(furnitureTab, 50);
        furnitureTab.click();
    }

    public void openRoomSettings() {
        roomsIcon.click();
    }

    public void openRoomTypeDropdown() {
        roomTypeDropdown.click();
    }

    public void confirmRoomSelection() {
        confirmButton.click();
    }

    public void selectBedroom() {
        bedroomOption.click();
    }
    public void enterWidthAndLength(String width, String length){
       widthLengthInputs.get(0).sendKeys(width);
       widthLengthInputs.get(1).sendKeys(length);

    }

    public void drawRectangleOnCanvas() {
        int startX = 100;
        int startY = 100;
        int endX = 200;
        int endY = 200;
        actions.moveToElement(designCanvas, startX, startY)
                .clickAndHold()
                .moveByOffset(endX - startX, endY - startY)
                .release()
                .perform();
        actions.moveToElement(designCanvas, endX, endY).click().perform();
    }

    public void createRoom() {
        createRoomButton.click();
    }

    public void openCreateRoomOption() {
        createRoomOption.click();
    }

    public void selectRectangleShapeOption() {
        waitForElementToBeVisible(rectangleShapeOption, 10);
        rectangleShapeOption.click();
    }

    public void clickDrawPlanButton() {
        drawPlanButton.click();
    }

    public void switchTo3DView() {
        threeDViewButton.click();
    }

    public boolean findAndSelectRoom(String roomName) throws InterruptedException {
        for (WebElement room : roomList) {
            System.out.println(room.getText());
            if (room.getText().equals(roomName)) {
                Thread.sleep(2000);
                room.click();
                return true;
            }
        }
        return false;
    }

    public void createNewRoomWithRectangle(String width, String length) throws InterruptedException {

        clickDrawPlanButton();
        createRoom();
        openCreateRoomOption();
        selectRectangleShapeOption();
        System.out.println(1111);
        Thread.sleep(10000);
        drawRectangleOnCanvas();

        waitForElementToBeVisible(roomTypeDropdown, 20);
        openRoomTypeDropdown();
        selectBedroom();
        enterWidthAndLength(width, length);
        confirmRoomSelection();
    }



    public void selectProductType(String productType) {
        Select roomSummarySelect = new Select(roomSummarySelectElement);
        roomSummarySelect.selectByVisibleText(productType);
    }

    public String findCategory(String category, String subCategory) {
        for (WebElement element : categoriesInQuoteSummary) {
            String[] categoryParts = element.getText().split(" \\| ");
            String[] subCategoryParts = categoryParts[1].split(" - ");

            System.out.println(categoryParts[0]);
            System.out.println(subCategoryParts[0]);

            if (categoryParts[0].equalsIgnoreCase(category) && subCategoryParts[0].equalsIgnoreCase(subCategory)) {
                WebElement itemName = driver.findElement(By.cssSelector("div.RoomSummary__zoneDiv--3qxb8 div.RoomSummary__zoneContainer--fGX_s p:nth-child(2)"));
                return itemName.getText();
            }
        }
        return "Category not found";  // More informative return message
    }

    public void clickQuoteSummary() {
        quoteSummaryOption.click();
    }

    public void selectRoomInQuoteSummary(String roomName) {
        List<WebElement> roomElements = driver.findElements(By.cssSelector("div.RoomSummary__roomsListItem--1gKUX span.RoomSummary__roomName--1MEL9"));

        for (WebElement element : roomElements) {
            if (element.getText().equals(roomName)) {
                element.click();
                return;
            }
        }
        System.out.println("Room not found: " + roomName);
    }

    public String getProductItemText() {
        return productItem.getText();
    }

    public void waitForElementToBeVisible(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
