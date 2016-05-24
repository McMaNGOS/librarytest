package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends MenuPage {
    @FindBy(css = "#input-username")
    private SelenideElement userNameField;
    @FindBy(css = "#input-password")
    private SelenideElement passwordField;
    @FindBy(css = "#login-button")
    private SelenideElement logInButton;
    
    public void setUsername(String username){
        setTextFieldValue("username", username, userNameField);
    }
    
    public void setPassword(String password){
        setTextFieldValue("password", password, passwordField);
    }
    
    public void clickLogIn(){
        clickButton("log in button", logInButton);
    }
}
