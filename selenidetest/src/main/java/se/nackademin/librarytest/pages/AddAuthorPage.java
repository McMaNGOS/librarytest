package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class AddAuthorPage extends MenuPage {
    @FindBy(css = "#gwt-uid-7")
    private SelenideElement firstNameField;
    @FindBy(css = "#gwt-uid-9")
    private SelenideElement lastNameField;
    @FindBy(css = "#gwt-uid-3")
    private SelenideElement countryField;
    @FindBy(css = "#gwt-uid-5")
    private SelenideElement biographyField;
    @FindBy(css = "#add-author-button")
    private SelenideElement addAuthorButton;
    
    public void setFirstName(String firstName){
        setTextFieldValue("first name", firstName, firstNameField);
    }
    
    public void setLastName(String lastName){
        setTextFieldValue("last name", lastName, lastNameField);
    }
    
    public void setCountry(String country){
        setTextFieldValue("country", country, countryField);
    }
    
    public void setBiography(String biography){
        setTextFieldValue("biography", biography, biographyField);
    }
    
    public void clickAddAuthorButton(){
        clickButton("add author button", addAuthorButton);
    }
}
