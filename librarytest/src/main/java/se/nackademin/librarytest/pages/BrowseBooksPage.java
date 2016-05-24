package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class BrowseBooksPage extends MenuPage {
    @FindBy(css = "#gwt-uid-3")
    private SelenideElement titleField;
    @FindBy(css = "#search-books-button")
    private SelenideElement searchBooksButton;
    @FindBy(css = "td.v-grid-cell:nth-child(1) > a:nth-child(1)")
    private SelenideElement firstResultTitle;
    
    public void setTitleField(String title){
        setTextFieldValue("book title", title, titleField);
    }
    
    public void clickSearchBooksButton(){
        clickButton("search books button", searchBooksButton);
    }
    
    public void clickFirstResultTitle(){
        clickButton("first result title", firstResultTitle);
    }
}
