package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import testcases.BaseClass;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @FindBy(css = "div[class='modal-body'] input[class='inputBoxGrid null form-control']") List <WebElement> widthLengthInputs;

    @FindBy(xpath = "//div[@class='ViewSwitch__tab--2v2Vs false 3DSwitch']")
    WebElement threeDViewButton;

    @FindBy(xpath = "//div[@class='ViewSwitch__tab--2v2Vs false']")WebElement twoDViewButton;

    @FindBy(xpath = "//div[@id='fpApp']//canvas[@tabindex='0']")
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

    @FindBy(css = "input[placeholder='Enter Room Name']") WebElement roomNameInput;

    // multi

    @FindBy(css = "div.common__title--3ro7T svg:nth-child(1)")
    WebElement backToCategoryButton;

    @FindBy(css = "div.common__subHeader--3e66K svg:nth-child(1)")
    WebElement backSubCategoryButton;

    @FindBy(xpath = "//div[@class='common__cardWrapperProduct--3M-vq']")
    WebElement firstProductItem;

    @FindBy(css = "div.common__crossIcon--2OZmq span:nth-child(1)")
    WebElement closeButton;

    @FindBy(id = "services")
    WebElement servicesTabButton;

    @FindBy(css = ".common__zoneAddToRoom--hKYqF")
    WebElement firstAddToRoomButton;

    @FindBy(css = "div.SidebarCatalog__serviceAddBtn--3P3h2")
    WebElement addToRoomButton1;

    @FindBy(css = ".common__addButton--1ZLrJ")
    WebElement addToQuoteButton;

    @FindBy(css = "div.productInfo__ctaButton--ykwdD")
    WebElement serviceAddToQuoteButton;

    @FindBy(css = "div.SidebarCatalog__serviceName--1zWkm")
    WebElement serviceNameText;

    public String getServiceNameText() {
        return serviceNameText.getText();
    }

    public void clickServiceAddToQuoteButton() {
        serviceAddToQuoteButton.click();
    }

    public void clickServicesTabButton() {
        servicesTabButton.click();
    }

    public void clickAddToRoomButton() {
        addToRoomButton1.click();
    }

    public void clickCloseButton() {
        closeButton.click();
    }

    public void selectService(String service) {
        WebElement serviceElement = driver.findElement(By.xpath("//div[contains(text(),'" + service + "')]"));
        waitForElementToBeVisible(serviceElement, 50);
        if (serviceElement.isDisplayed()) {
            serviceElement.click();
        }
    }

    public void selectSubService(String subService) {
        WebElement subServiceElement = driver.findElement(By.xpath("//li[contains(text(),'" + subService + "')]"));
        waitForElementToBeVisible(subServiceElement, 50);
        if (subServiceElement.isDisplayed()) {
            subServiceElement.click();
        }
    }

    public String getFirstProductItemName() {
        WebElement ele = driver.findElement(By.className("common__cardTitle--2WZxw"));
        return ele.getText();
    }

    public void clickBackToCategory() {
        backToCategoryButton.click();
    }

    public void clickBackSubCategory() {
        backSubCategoryButton.click();
    }

    public void hoverOnFirstProductItem() {
        waitForElementToBeVisible(firstProductItem, 20);
        actions.moveToElement(firstProductItem).perform();
    }

    public void clickFirstAddToRoomButton() {
        firstAddToRoomButton.click();
    }

    public void clickAddToQuoteButton() {
        addToQuoteButton.click();
    }


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

    public void enterRoomName(String name){
        roomNameInput.clear();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        roomNameInput.sendKeys(name);
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
    public void selectRoomType(String roomType){
        WebElement ele = driver.findElement(By.linkText(roomType));
        ele.click();
    }
    public void enterWidthAndLength(String width, String length){
        System.out.println(widthLengthInputs.size());
       widthLengthInputs.get(0).clear();
        widthLengthInputs.get(0).sendKeys(width);
        widthLengthInputs.get(1).clear();
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

    public void drawRectangleOnCanvas(int startX, int startY, int endX, int endY) {
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

    public void switchTo2DView(){
        twoDViewButton.click();
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
        Thread.sleep(2000);
        selectRectangleShapeOption();
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
            System.out.println(
                    element.getText()
            );
            if (element.getText().indexOf("|") != -1) {

                String[] categoryParts = element.getText().split(" \\| ");
                String[] subCategoryParts = categoryParts[1].split(" - ");


                if (categoryParts[0].equals(category.toUpperCase()) && subCategoryParts[0].equals(subCategory.toUpperCase())) {
                    WebElement itemName = driver.findElement(By.cssSelector("div.RoomSummary__zoneDiv--3qxb8 div.RoomSummary__zoneContainer--fGX_s p:nth-child(2)"));
                    return itemName.getText();
                }
            } else {
                // For other categories without the '|' character
                String[] subCategoryParts = element.getText().split(" - ");
                if(subCategoryParts[0].equals(category.toUpperCase())) {
                    WebElement itemName = driver.findElement(By.cssSelector("div.RoomSummary__productName--2qEeu"));
                    return itemName.getText();
                }
            }
        }
        return "Category not found";
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

    // Method to create a new room
    public void createNewRoom( Map<String, String> coordinates)  {
        clickDrawPlanButton();
        createRoom();
        openCreateRoomOption();
        selectRectangleShapeOption();
        waitForElementToBeVisible(designCanvas, 20);

        drawRectangleOnCanvas(
                Integer.parseInt(coordinates.get("startX")),
                Integer.parseInt(coordinates.get("startY")),
                Integer.parseInt(coordinates.get("endX")),
                Integer.parseInt(coordinates.get("endY"))
        );

        openRoomTypeDropdown();
        selectRoomType(coordinates.get("roomType"));
        enterWidthAndLength(coordinates.get("width"), coordinates.get("length"));
        confirmRoomSelection();
    }

    // Method to apply furniture to the room
    public void applyFurniture( List<Map<String, String>> furnitureList, List<Map<String, String>> checks) throws InterruptedException {
        switchTo3DView();
        Thread.sleep(5000);
        openFurnitureTab();
        Thread.sleep(5000);

        for (Map<String, String> furniture : furnitureList) {
            selectFurnitureType(furniture.get("furnitureType"));
            Thread.sleep(2000);
            selectCategory(furniture.get("category"));
            selectSubCategory(furniture.get("subCategory"));

            String productName;
            Map<String, String> temp = new HashMap<>();

            if (furniture.get("furnitureType").equals("Fitted Furniture")) {
                hoverOnProduct();
                productName = getProductItemText().split("\n")[0];
                temp.put("furnitureType", "Fitted Furniture");
            } else {
                hoverOnFirstProductItem();
                productName = getFirstProductItemName();
                temp.put("furnitureType", furniture.get("furnitureType"));
            }

            temp.put("category", furniture.get("category"));
            temp.put("subCategory", furniture.get("subCategory"));
            temp.put("product", productName);
            checks.add(temp);

            addFurnitureToRoom(furniture);
        }
    }

    // Helper method to add furniture to the room
    public void addFurnitureToRoom( Map<String, String> furniture) throws InterruptedException {
        if (furniture.get("furnitureType").equals("Fitted Furniture")) {
            addProductToRoom();
            Thread.sleep(5000);
            clickBackToCategory();
        } else {
            clickFirstAddToRoomButton();
            clickAddToQuoteButton();
            clickCloseButton();
            openFurnitureTab();
        }
    }

    // Method to add a service to the room
    public void addService( String serviceType, String subServiceType) throws InterruptedException {
        clickServicesTabButton();
        selectService(serviceType);
        Thread.sleep(2000);
        selectSubService(subServiceType);
        System.out.println(getServiceNameText());
        clickAddToRoomButton();
        clickServiceAddToQuoteButton();
        Thread.sleep(2000);
    }

    // Method to verify furniture in the quote summary
    public void verifyFurnitureInQuoteSummary( String roomType, List<Map<String, String>> checks)  {
        clickQuoteSummary();
        selectRoomInQuoteSummary(roomType);

        for (Map<String, String> check : checks) {
            selectProductType(check.get("furnitureType"));
            String product = findCategory(check.get("category"), check.get("subCategory"));
            Assert.assertEquals(check.get("product").toUpperCase(), product.toUpperCase());
        }
    }

}
