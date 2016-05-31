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
    @FindBy(css = ".v-label-undef-w")
    private SelenideElement addUserErrorMessage;
    @FindBy(css = "#gwt-uid-15 > span:nth-child(1) > label")
    private SelenideElement librarianButton;
    
    public void setUsername(String username){
        setTextFieldValue("username field", username, userNameField);
    }
    
    public void setPassword(String password){
        setTextFieldValue("password field", password, passwordField);
    }
    
    public void setEmail(String email){
        setTextFieldValue("email field", email, emailField);
    }
    
    public String getAddUserErrorMessage(){
        return getTextFromField("add user error message", addUserErrorMessage);
    }
    
    public void clickAddUserButton(){
        clickButton("add user button", addUserButton);
    }
    
    public void clickLibrarianButton(){
        clickButton("librarian button", librarianButton);
    }
}
