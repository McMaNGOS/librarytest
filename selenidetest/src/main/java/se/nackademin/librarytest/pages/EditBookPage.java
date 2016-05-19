package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class EditBookPage extends MenuPage {
    @FindBy(css = "#gwt-uid-7")
    private SelenideElement datePublishedField;
    @FindBy(css = "#save-book-button")
    private SelenideElement saveBookButton;
    @FindBy(css = "#v-link v-widget")
    private SelenideElement backToBookButton;
    
    public void setPublishDate(String date){
        setTextFieldValue("date published field", date, datePublishedField);
    }
    
    public void clickSaveBookButton(){
        clickButton("save book button", saveBookButton);
    }
    
    public void clickBackToBookButton(){
        clickButton("back to book button", backToBookButton);
    }
}
