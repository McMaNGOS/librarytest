package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class BrowseAuthorsPage extends MenuPage {
    @FindBy(css = "#gwt-uid-3")
    private SelenideElement nameField;
    @FindBy(css = "#gwt-uid-5")
    private SelenideElement countryField;
    @FindBy(css = "#search-authors-button")
    private SelenideElement searchAuthorsButton;
    @FindBy(css = ".v-grid-cell-focused > a:nth-child(1)")
    private SelenideElement firstResultAuthor;
    
    public void setNameField(String name){
        setTextFieldValue("name", name, nameField);
    }
    
    public void setCountryField(String country){
        setTextFieldValue("country", country, countryField);
    }
    
    public void clickSearchAuthorsButton(){
        clickButton("search authors button", searchAuthorsButton);
    }
    
    public void clickFirstResultAuthor(){
        clickButton("first result author", firstResultAuthor);
    }
}
