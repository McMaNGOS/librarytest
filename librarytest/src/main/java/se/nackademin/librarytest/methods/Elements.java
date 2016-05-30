package se.nackademin.librarytest.methods;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class Elements {
    
    private static final Logger LOG = Logger.getLogger(Elements.class.getName());
    
    public boolean locateElement(String elementId){
        LOG.log(Level.INFO, "Attemtping to locate element: {0}", elementId);
        try {
            getWebDriver().findElement(By.id(elementId));
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }
}
