package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class MyProfilePage extends MenuPage {
    @FindBy(css = "#gwt-uid-5")
    private SelenideElement userNameField;
    @FindBy(css = "#gwt-uid-13")
    private SelenideElement emailField;
    @FindBy(css = "#edit-user")
    private SelenideElement editUserButton;
    
    public String getUserName(){
        return getTextFromField("username field", userNameField);
    }
    
    public String getEmail(){
        return getTextFromField("email field", emailField);
    }
    
    public void clickEditUserButton(){
        clickButton("edit user button", editUserButton);
    }
}
