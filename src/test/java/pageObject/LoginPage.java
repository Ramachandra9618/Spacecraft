package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import testcases.BaseClass;

public class LoginPage  extends BaseClass {

    LoginPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "#jdnd")
    WebElement lax;
}
