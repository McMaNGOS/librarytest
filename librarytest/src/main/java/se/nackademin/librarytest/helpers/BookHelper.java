package se.nackademin.librarytest.helpers;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import se.nackademin.librarytest.model.Book;
import se.nackademin.librarytest.pages.BookPage;
import se.nackademin.librarytest.pages.BrowseBooksPage;
import se.nackademin.librarytest.pages.AddBookPage;
import se.nackademin.librarytest.pages.MenuPage;

public class BookHelper {
    
    public static void createNewBookWithFirstAuthorInList(String title, String nbrAvailable){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToAddBook();
        
        AddBookPage addBookPage = page(AddBookPage.class);
        addBookPage.setBookTitle(title);
        addBookPage.clickFirstAuthorInList();
        addBookPage.clickAddSelectedAuthorButton();
        addBookPage.setBookNbrAvailable(nbrAvailable);
        addBookPage.setBookDatePublished("1995-04-12"); //filler date, has no significance
        addBookPage.clickAddBookButton();
        sleep(100);
    }
    
    public static Book fetchBook(String searchQuery){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToBrowseBooks();
        
        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        browseBooksPage.setTitleField(searchQuery);
        browseBooksPage.clickSearchBooksButton();
        browseBooksPage.clickFirstResultTitle();
        
        BookPage bookPage = page(BookPage.class);
        Book book = new Book();
        book.setTitle(bookPage.getTitle());
        book.setAuthor(bookPage.getAuthor());
        book.setDescription(bookPage.getDescription());
        return book;
    }
}
