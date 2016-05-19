package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class EditUserPage extends MenuPage {
    @FindBy(css = "#gwt-uid-13")
    private SelenideElement emailField;
    @FindBy(css = "#save-user-button")
    private SelenideElement saveUserButton;
    
    public void editEmailField(String email){
        setTextFieldValue("email", email, emailField);
    }
    
    public void clickSaveUserButton(){
        clickButton("save user button", saveUserButton);
    }
}
