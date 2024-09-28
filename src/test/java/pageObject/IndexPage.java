package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testcases.BaseClass;

public class IndexPage extends BaseClass {

    public IndexPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "p.HeaderComponent-module__design_button--qW3DU")
WebElement skipButton;
//    @FindBy(xpath = "//p[@class='HeaderComponent-module__design_button--qW3DU']")

    public void clickSkip(){
        skipButton.click();
    }
}
