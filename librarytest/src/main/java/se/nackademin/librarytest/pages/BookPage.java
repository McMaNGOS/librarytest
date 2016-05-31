package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.support.FindBy;

public class BookPage extends MenuPage {
    
    private static final Logger LOG = Logger.getLogger(BookPage.class.getName());
    
    @FindBy(css = "#gwt-uid-3")
    private SelenideElement titleField;
    @FindBy(css = "#gwt-uid-5")
    private SelenideElement authorField;
    @FindBy(css = "#gwt-uid-7")
    private SelenideElement descriptionField;
    @FindBy(css = "#gwt-uid-9")
    private SelenideElement isbnField;
    @FindBy(css = "#gwt-uid-11")
    private SelenideElement dateField;
    @FindBy(css = "#gwt-uid-13")
    private SelenideElement copiesLeftField;
    @FindBy(css = "#edit-book-button")
    private SelenideElement editBookButton;
    @FindBy(css = "#borrow-book-button")
    private SelenideElement borrowBookButton;
    @FindBy(css = "#confirmdialog-ok-button")
    private SelenideElement confirmDialogButton;
    @FindBy(css = "#return-book-button")
    private SelenideElement returnBookButton;
    @FindBy(css = "#delete-book-button")
    private SelenideElement deleteBookButton;
    @FindBy(css = ".v-label")
    private SelenideElement invalidBookMessage;
    
    public String getTitle(){
        return getTextFromField("title field", titleField);
    }
    
    public String getAuthor(){
        return getTextFromField("author field", authorField);
    }
    
    public String getDescription(){
        return getTextFromField("description field", descriptionField);
    }
    
    public String getDate(){
        return getTextFromField("date field", dateField);
    }
    
    public String getCopiesLeft(){
        return getTextFromField("copies left field", copiesLeftField);
    }
    
    public String getInvalidBookMessage(){
        return getTextFromField("invalid book error message", invalidBookMessage);
    }
    
    public boolean getBorrowBookButtonExists(){
    LOG.log(Level.INFO, "Checking if borrow book button exists");
    boolean result = borrowBookButton.exists();
    return result;
    }
    
    public void clickEditBookButton(){
        clickButton("edit book button", editBookButton);
    }
    
    public void clickBorrowBookButton(){
        clickButton("borrow book button", borrowBookButton);
    }
    
    public void clickConfirmDialogButton(){
        clickButton("confirm dialog button", confirmDialogButton);
    }
    
    public void clickReturnBookButton(){
        clickButton("return book button", returnBookButton);
    }
    
    public void clickDeleteBookButton(){
        clickButton("delete book button", deleteBookButton);
    }
}
