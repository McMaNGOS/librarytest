package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class AddUserPage extends MenuPage {
    @FindBy(css = "#gwt-uid-3")
    private SelenideElement userNameField;
    @FindBy(css = "#gwt-uid-5")
    private SelenideElement passwordField;
    @FindBy(css = "#gwt-uid-13")
    private SelenideElement emailField;
    @FindBy(css = "#add-user-button")
    private SelenideElement addUserButton;
    
    public void setUsername(String username){
        setTextFieldValue("username field", username, userNameField);
    }
    
    public void setPassword(String password){
        setTextFieldValue("password field", password, passwordField);
    }
    
    public void setEmail(String email){
        setTextFieldValue("email field", email, emailField);
    }
    
    public void clickAddUserButton(){
        clickButton("add user button", addUserButton);
    }
}
