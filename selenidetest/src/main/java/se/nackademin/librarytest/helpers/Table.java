package se.nackademin.librarytest.helpers;

import com.codeborne.selenide.SelenideElement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.*;

public class Table {
    
    private static final Logger LOG = Logger.getLogger(Table.class.getName());
    
    SelenideElement rootElement;
    public Table(SelenideElement rootElement){
        this.rootElement = rootElement;
    } 
    
    public int getRowCount(){
        LOG.log(Level.INFO, "Getting row count");
        return rootElement.$$("tr").size() - 1;
    }
    
    public int getColumnCount(){
        LOG.log(Level.INFO, "Getting column count");
        return rootElement.$("tr").$$("th").size();
    }
    
    private SelenideElement getCell(int column, int row){
        LOG.log(Level.INFO, "Getting cell");
        return rootElement.$("tr", row+1).$("td",column);
    }
    
    public String getCellValue(int column, int row){
        LOG.log(Level.INFO, "Getting cell value");
        return getCell(column, row).getText();
    }
    
    public void clickCell(int column, int row){
        LOG.log(Level.INFO, "Clicking cell");
        getCell(column, row).click();
    }
    
    public void searchAndClick(String query, int column){
        LOG.log(Level.INFO, "Searching for and clicking on cell");
        for(int row = 0; row < getRowCount(); row++){
            if (query.equals(getCellValue(column, row))){
                clickCell(column, row);
                return;
            }
        }
        throw new NoSuchElementException("Could not find cell containing " + query);
    }
}
