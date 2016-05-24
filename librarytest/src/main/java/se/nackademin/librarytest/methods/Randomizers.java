package se.nackademin.librarytest.methods;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.RandomStringUtils;

public class Randomizers {
    
    private static final Logger LOG = Logger.getLogger(Randomizers.class.getName());
    
    public String generateAlphabeticString(int count){
        LOG.log(Level.INFO, "Generating alphabetic string with {0} characters", count);
        String randomString = RandomStringUtils.randomAlphabetic(count);
        return randomString;
    }
    
    public String generateRandomDate(){
        LOG.log(Level.INFO, "Generating random date");
        // Set days and months to not be < 10 because I had trouble appending zeroes
        // before single integers in the final string (which is required for later assertion), 
        // but since it is all for testing purposes it shouldn't be much of an issue.
        int year = 1900 + (int)(Math.random() * ((2015 - 1900) + 1));
        int month = 10 + (int)(Math.random() * ((12 - 10) + 1));
        int day = 10 + (int)(Math.random() * ((28 - 10) + 1));
        String date = year + "-" + month + "-" + day;
        return date;
    }
}