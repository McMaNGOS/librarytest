package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PageBase {
    
    private static final Logger LOG = Logger.getLogger(PageBase.class.getName());
    
    protected void clickButton(String descriptor, SelenideElement element){
        LOG.log(Level.INFO, "Clicking {0}", descriptor);
        element.click();
    }
    
    protected void setTextFieldValue(String descriptor, String value, SelenideElement element){
        LOG.log(Level.INFO, "Setting {0} to {1}", new Object[]{descriptor, value});
        element.clear();
        element.sendKeys(value);
    }
    
    protected String getTextFromField(String descriptor, SelenideElement element){
        LOG.log(Level.INFO, "Getting text from {0}", descriptor);
        return element.getText();
    }
    
}
