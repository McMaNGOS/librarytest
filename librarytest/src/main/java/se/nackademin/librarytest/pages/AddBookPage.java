package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class AddBookPage extends MenuPage {
    @FindBy(css = "#gwt-uid-3")
    private SelenideElement bookTitleField;
    @FindBy(css = ".v-select-twincol-buttons > div:nth-child(1)")
    private SelenideElement addSelectedAuthorButton;
    @FindBy(css = "div.v-button:nth-child(3)")
    private SelenideElement removeSelectedAuthorButton;
    @FindBy(css = "#gwt-uid-5")
    private SelenideElement bookNbrAvailableField;
    @FindBy(css = ".v-select-twincol-options > option:nth-child(1)")
    private SelenideElement firstAuthorInList;
    @FindBy(css = "#gwt-uid-7")
    private SelenideElement bookDatePublishedField;
    @FindBy(css = "#add-book-button")
    private SelenideElement addBookButton;
    @FindBy(css = "div.v-slot:nth-child(17) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > a:nth-child(1)")
    private SelenideElement newlyAddedBookButton;
    @FindBy(css = ".v-label-undef-w")
    private SelenideElement invalidDataMessage;
    @FindBy(css = "#main-content-header")
    private SelenideElement addBookContentHeader;
            
    public void setBookTitle(String title){
        setTextFieldValue("book title", title, bookTitleField);
    }
    
    public void setBookNbrAvailable(String nbrAvailable){
        setTextFieldValue("number available", nbrAvailable, bookNbrAvailableField);
    }
    
    public void setBookDatePublished(String datePublished){
        setTextFieldValue("date published", datePublished, bookDatePublishedField);
    }
    
    public String getInvalidDataMessage(){
        return getTextFromField("invalid data message", invalidDataMessage);
    }
    
    public String getAddBookContentHeader(){
        return getTextFromField("add book content header", addBookContentHeader);
    }
    
    public void clickAddSelectedAuthorButton(){
        clickButton("add selected author button", addSelectedAuthorButton);
    }
    
    public void clickRemoveSelectedAuthorButton(){
        clickButton("remove selected author button", removeSelectedAuthorButton);
    }
    
    public void clickFirstAuthorInList(){
        clickButton("first author in list", firstAuthorInList);
    }
    
    public void clickAddBookButton(){
        clickButton("add book button", addBookButton);
    }
    
    public void clickNewlyAddedBookButton(){
        clickButton("newly added book button", newlyAddedBookButton);
    }
}
