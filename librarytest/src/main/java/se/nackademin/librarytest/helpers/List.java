package se.nackademin.librarytest.helpers;

import com.codeborne.selenide.SelenideElement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.*;

public class List {
    
    private static final Logger LOG = Logger.getLogger(List.class.getName());
    
    SelenideElement rootElement;
    public List(SelenideElement rootElement){
        this.rootElement = rootElement;
    }
    
    public int getNumberOfEntries(){
        LOG.log(Level.INFO, "Getting number of entries in list");
        return rootElement.$("#v-select-twincol").$$("option value").size();
    }
}
