package se.nackademin.librarytest.helpers;

import static com.codeborne.selenide.Selenide.page;
import se.nackademin.librarytest.pages.AddAuthorPage;
import se.nackademin.librarytest.pages.BrowseAuthorsPage;
import se.nackademin.librarytest.pages.MenuPage;

public class AuthorHelper {
    
    public static void createNewAuthor(String firstName, String lastName, String country, String biography){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToAddAuthor();
        
        AddAuthorPage addAuthorPage = page(AddAuthorPage.class);
        addAuthorPage.setFirstName(firstName);
        addAuthorPage.setLastName(lastName);
        addAuthorPage.setCountry(country);
        addAuthorPage.setBiography(biography);
        addAuthorPage.clickAddAuthorButton();
    }
    
    public static void searchAuthorByName(String query){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToBrowseAuthors();
        
        BrowseAuthorsPage browseAuthorsPage = page(BrowseAuthorsPage.class);
        browseAuthorsPage.setNameField(query);
        browseAuthorsPage.clickSearchAuthorsButton();
    }
    
}
