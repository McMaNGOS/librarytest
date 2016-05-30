package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class AuthorPage extends MenuPage {
    @FindBy(css = "#gwt-uid-3")
    private SelenideElement nameField;
    @FindBy(css = ".v-Notification-description")
    private SelenideElement authorError;
    @FindBy(css = "#delete-author-button")
    private SelenideElement deleteAuthorButton;
    @FindBy(css = "#confirmdialog-ok-button")
    private SelenideElement confirmDialogButton;
    @FindBy(css = ".v-label")
    private SelenideElement invalidAuthorMessage;
    
    public String getAuthorName(){
        return getTextFromField("author name field", nameField);
    }
    
    public String getErrorDescription(){
        return getTextFromField("error message field", authorError);
    }
    
    public String getInvalidAuthorMessage(){
        return getTextFromField("invalid author message", invalidAuthorMessage);
    }
    
    public void clickDeleteAuthorButton(){
        clickButton("delete author button", deleteAuthorButton);
    }
    
    public void clickConfirmDialogButton(){
        clickButton("confirm dialog button", confirmDialogButton);
    }
    
}
