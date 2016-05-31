package se.nackademin.librarytest;

import static com.codeborne.selenide.Selenide.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import se.nackademin.librarytest.helpers.AuthorHelper;
import se.nackademin.librarytest.helpers.Table;
import se.nackademin.librarytest.helpers.UserHelper;
import se.nackademin.librarytest.helpers.BookHelper;
import se.nackademin.librarytest.pages.AuthorPage;
import se.nackademin.librarytest.pages.BrowseAuthorsPage;
import se.nackademin.librarytest.pages.BrowseBooksPage;
import se.nackademin.librarytest.pages.EditUserPage;
import se.nackademin.librarytest.pages.MenuPage;
import se.nackademin.librarytest.pages.MyProfilePage;
import se.nackademin.librarytest.methods.Randomizers;
import se.nackademin.librarytest.pages.AddBookPage;
import se.nackademin.librarytest.pages.AddUserPage;
import se.nackademin.librarytest.pages.BookPage;
import se.nackademin.librarytest.pages.EditBookPage;

public class SelenideTest extends TestBase {
    
    public SelenideTest() {
    }
    
    Randomizers randomizers = new Randomizers();
    
    @Test
    public void testBorrowBookWithNoCopiesLeft(){
        UserHelper.logInAsUser("admin", "1234567890");
        
        BookHelper.createNewBookWithFirstAuthorInList(randomizers.generateAlphabeticString(5), "0");
        
        AddBookPage addBookPage = page(AddBookPage.class);
        addBookPage.clickNewlyAddedBookButton();
        
        BookPage bookPage = page(BookPage.class);
        boolean borrowBookButtonExists = bookPage.getBorrowBookButtonExists();
        sleep(5000);
        
        assertFalse("The borrow book element should be missing", borrowBookButtonExists);
    }
    
    @Ignore
    @Test
    public void testCreateNewLibrarian(){
        String username = randomizers.generateAlphabeticString(5);
        String password = randomizers.generateAlphabeticString(5);
        String email = randomizers.generateAlphabeticString(5);
                
        UserHelper.logInAsUser("admin", "1234567890");
        
        UserHelper.createNewUser(username, password, email, true); // Librarian button does not get clicked, must fix
        UserHelper.logInAsUser(username, password);
        
        page(MenuPage.class).navigateToAddBook();
        
        AddBookPage addBookPage = page(AddBookPage.class);
        assertEquals("Librarian should see content header, as they have access to the page", "Add book", addBookPage.getAddBookContentHeader());
    }
    
    @Ignore
    @Test
    public void testDeleteAuthorWithBooks(){
        UserHelper.logInAsUser("admin", "1234567890");
        
        AuthorHelper.searchAuthorByName("Terry Pratchett");
        page(BrowseAuthorsPage.class).clickFirstResultAuthor();
        
        AuthorPage authorPage = page(AuthorPage.class);
        authorPage.clickDeleteAuthorButton();
        authorPage.clickConfirmDialogButton();
        sleep(100);
        
        assertEquals("Error message should ", authorPage.getErrorDescription(), "Unable to delete entity: Conflict, Unable to delete author - author still has books in the database?");
    }
    
    @Ignore
    @Test
    public void testCreateBookWithoutTitle(){
        UserHelper.logInAsUser("admin", "1234567890");
        
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToAddBook();
        
        AddBookPage addBookPage = page(AddBookPage.class);
        addBookPage.setBookTitle("");
        addBookPage.setBookDatePublished(randomizers.generateRandomDate());
        addBookPage.clickAddBookButton();
        sleep(100);
        
        assertEquals("Should display an error saying 'Invalid data, please try again.'", "Invalid data, please try again.", addBookPage.getInvalidDataMessage()); 
    }
    
    @Ignore
    @Test
    public void testCreateUserWithExisitngUsername(){
        String password = randomizers.generateAlphabeticString(5);
        String email = randomizers.generateAlphabeticString(5);
        UserHelper.createNewUser("admin", password, email, false);
        
        AddUserPage addUserPage = page(AddUserPage.class);
        
        assertEquals("Should display error about username already existing", "Unable to add user: Unable to create entity: Bad Request, User with same display name already exists.", addUserPage.getAddUserErrorMessage());
    }
    
    @Ignore
    @Test
    public void testCreateAndDeleteBook(){
        String title = randomizers.generateAlphabeticString(5);
        String nbrAvailable = "1";
        
        UserHelper.logInAsUser("admin", "1234567890");
        
        page(MenuPage.class).navigateToAddBook();
        
        BookHelper.createNewBookWithFirstAuthorInList(title, nbrAvailable);
        
        AddBookPage addBookPage = page(AddBookPage.class);
        addBookPage.clickNewlyAddedBookButton();
        sleep(100);
        
        BookPage bookPage = page(BookPage.class);
        assertEquals("Book title should be " + title, title, bookPage.getTitle());
        
        bookPage.clickDeleteBookButton();
        bookPage.clickConfirmDialogButton();
        sleep(100);
        back(); // returns to the previously deleted book, which should then be missing (asserted below)
        assertEquals("Error message should say 'Invalid book'", "Invalid book.", bookPage.getInvalidBookMessage());
    }
    
    @Ignore
    @Test
    public void testCreateAndDeleteNewAuthor(){
        String authorFirstName = randomizers.generateAlphabeticString(5);
        String authorLastName = randomizers.generateAlphabeticString(5);
        String authorCountry = randomizers.generateAlphabeticString(5);
        String authorBiography = randomizers.generateAlphabeticString(5);
        String authorFullName = authorFirstName + " " + authorLastName;
        
        UserHelper.logInAsUser("admin", "1234567890");
        
        AuthorHelper.createNewAuthor(authorFirstName, authorLastName, authorCountry, authorBiography);
        AuthorHelper.searchAuthorByName(authorFirstName);
        
        BrowseAuthorsPage browseAuthorsPage = page(BrowseAuthorsPage.class);
        browseAuthorsPage.clickFirstResultAuthor();
        
        AuthorPage authorPage = page(AuthorPage.class);
        assertEquals("Author name should be" + authorFullName, authorFullName, authorPage.getAuthorName());
        
        authorPage.clickDeleteAuthorButton();
        authorPage.clickConfirmDialogButton();
        sleep(100);
        
        back(); // Goes back to the previously deleted author, which should result in an error, asserted below
        assertEquals("Error message should say 'Invalid author.'", "Invalid author.", authorPage.getInvalidAuthorMessage());
    }
    
    @Ignore
    @Test
    public void testChangeUserEmail(){
        String username = randomizers.generateAlphabeticString(5);
        String password = randomizers.generateAlphabeticString(5);
        String email = randomizers.generateAlphabeticString(5);
        String newEmail = randomizers.generateAlphabeticString(5);
        
        UserHelper.createNewUser(username, password, email, false);
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
    
    @Ignore
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
    
    @Ignore
    @Test
    public void testBorrowAndReturnBook(){
        String username = randomizers.generateAlphabeticString(5);
        String password = randomizers.generateAlphabeticString(5);
        String email = randomizers.generateAlphabeticString(5);
        
        UserHelper.createNewUser(username, password, email, false);
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