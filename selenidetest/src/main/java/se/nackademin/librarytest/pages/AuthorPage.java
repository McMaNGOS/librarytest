package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class AuthorPage extends MenuPage {
    @FindBy(css = "#gwt-uid-3")
    private SelenideElement nameField;
    
    public String getAuthorName(){
        return getTextFromField("author name field", nameField);
    }
}
