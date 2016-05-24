package se.nackademin.librarytest;

import static com.codeborne.selenide.Selenide.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import se.nackademin.librarytest.helpers.AuthorHelper;
import se.nackademin.librarytest.helpers.Table;
import se.nackademin.librarytest.helpers.UserHelper;
import se.nackademin.librarytest.pages.AuthorPage;
import se.nackademin.librarytest.pages.BrowseAuthorsPage;
import se.nackademin.librarytest.pages.BrowseBooksPage;
import se.nackademin.librarytest.pages.EditUserPage;
import se.nackademin.librarytest.pages.MenuPage;
import se.nackademin.librarytest.pages.MyProfilePage;
import se.nackademin.librarytest.methods.Randomizers;
import se.nackademin.librarytest.pages.BookPage;
import se.nackademin.librarytest.pages.EditBookPage;

public class SelenideTest extends TestBase {
    
    public SelenideTest() {
    }
    
    Randomizers randomizers = new Randomizers();
    
    @Test
    public void testCreateNewAuthor(){
        String authorFirstName = RandomStringUtils.randomAlphabetic(5);
        String authorLastName = RandomStringUtils.randomAlphabetic(5);
        String authorCountry = RandomStringUtils.randomAlphabetic(5);
        String authorBiography = RandomStringUtils.randomAlphabetic(5);
        String authorFullName = authorFirstName + " " + authorLastName;
        
        UserHelper.logInAsUser("admin", "1234567890");
        
        AuthorHelper.createNewAuthor(authorFirstName, authorLastName, authorCountry, authorBiography);
        AuthorHelper.searchAuthorByName(authorFirstName);
        
        BrowseAuthorsPage browseAuthorsPage = page(BrowseAuthorsPage.class);
        browseAuthorsPage.clickFirstResultAuthor();
        
        AuthorPage authorPage = page(AuthorPage.class);
        assertEquals("Author name should be" + authorFullName, authorFullName, authorPage.getAuthorName());
    }
    
    @Test
    public void testChangeUserEmail(){
        String username = randomizers.generateAlphabeticString(5);
        String password = randomizers.generateAlphabeticString(5);
        String email = randomizers.generateAlphabeticString(5);
        String newEmail = randomizers.generateAlphabeticString(5);
        
        UserHelper.createNewUser(username, password, email);
        UserHelper.logInAsUser(username, password);
        
        page(MenuPage.class).navigateToMyProfile();
        
        MyProfilePage myProfilePage = page(MyProfilePage.class);
        myProfilePage.clickEditUserButton();
        
        EditUserPage editUserPage = page(EditUserPage.class);
        editUserPage.editEmailField(newEmail);
        editUserPage.clickSaveUserButton();
        
        page(MenuPage.class).navigateToMyProfile();
        assertEquals("Email should be " + newEmail, newEmail, myProfilePage.getEmail());
    }
    
    @Test
    public void testChangePublishDate(){
        String newDate = randomizers.generateRandomDate();
        
        UserHelper.logInAsUser("admin", "1234567890");
        
        page(MenuPage.class).navigateToBrowseBooks();
        
        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        browseBooksPage.setTitleField("Good Omens");
        browseBooksPage.clickSearchBooksButton();
        Table table = new Table($(".v-grid-tablewrapper"));
        table.searchAndClick("Good Omens", 0);
        
        BookPage bookPage = page(BookPage.class);
        bookPage.clickEditBookButton();
        
        EditBookPage editBookPage = page(EditBookPage.class);
        editBookPage.setPublishDate(newDate);
        editBookPage.clickSaveBookButton();
        
        back(); // Navigates back to the book page
        
        assertEquals("Date should be " + newDate, newDate, bookPage.getDate());
    }
    
    @Test
    public void testBorrowBook(){
        String username = randomizers.generateAlphabeticString(5);
        String password = randomizers.generateAlphabeticString(5);
        String email = randomizers.generateAlphabeticString(5);
        
        UserHelper.createNewUser(username, password, email);
        UserHelper.logInAsUser(username, password);
        
        page(MenuPage.class).navigateToBrowseBooks();
        
        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        browseBooksPage.setTitleField("Good Omens");
        browseBooksPage.clickSearchBooksButton();
        Table bookPageTable = new Table($(".v-grid-tablewrapper"));
        bookPageTable.searchAndClick("Good Omens", 0);
        
        BookPage bookPage = page(BookPage.class);
        int availableCopiesBeforeBorrow = Integer.parseInt(bookPage.getCopiesLeft());
        bookPage.clickBorrowBookButton();
        bookPage.clickConfirmDialogButton();
        sleep(300); // Ensures that the amount of copies left has time to update
        int availableCopiesAfterBorrow = Integer.parseInt(bookPage.getCopiesLeft());
        assertEquals("Should be one less copy available", availableCopiesBeforeBorrow - 1, availableCopiesAfterBorrow);
        
        page(MenuPage.class).navigateToMyProfile();
        Table myProfileTable = new Table($(".v-grid-tablewrapper"));
        myProfileTable.searchAndClick("Good Omens", 0);
        assertEquals("Should end up on book page for Good Omens", "Good Omens", bookPage.getTitle());
        
        bookPage.clickReturnBookButton();
        bookPage.clickConfirmDialogButton();
        assertEquals("Should be one additional copy available", availableCopiesAfterBorrow + 1, availableCopiesBeforeBorrow);
    }
}