// NOTE: Database MUST be refreshed in between testing, otherwise some tests WILL fail.
// Restoring modified entries to the database would be a necessary change to the tests
// if maintainability were to be a requirement.

package se.nackademin.librarytest.rest;

import org.junit.Test;
import com.jayway.restassured.response.Response;
import static org.junit.Assert.assertEquals;
import se.nackademin.librarytest.restoperations.BookOperations;

public class BooksTest {
    private static final String BASE_URL = "http://localhost:8080/librarytest/rest/";
    
    // /books endpoint coverage (3/3)
    
    @Test
    public void testCreateBook(){
        Response createBookResponse = new BookOperations().createRandomBook();
        assertEquals("Status code should be 201", 201, createBookResponse.getStatusCode());
    }
    
    @Test
    public void testUpateBook(){
        Response response = new BookOperations().updateBook(8);
        assertEquals("Stauts code should be 200", 200, response.getStatusCode());
    }
    
    @Test
    public void testGetAllBooks(){
        Response response = new BookOperations().getAllBooks();
        assertEquals("Status code should be 200", 200, response.getStatusCode());
    }
    
    // /books/{id} endpoint coverage (2/2)
    
    @Test
    public void testGetBook(){
        Response response = new BookOperations().getBook(4);
        assertEquals("Status code should be 200", 200, response.getStatusCode());
    }
    
    @Test
    public void testDeleteBook(){
        Response createBookResponse = new BookOperations().createRandomBook();
        assertEquals("Status code should be 201", 201, createBookResponse.getStatusCode());
        
        int latestBookId = new BookOperations().getLatestBookId();
        
        Response deleteBookResponse = new BookOperations().deleteBook(latestBookId);
        assertEquals("Status code should be 204", 204, deleteBookResponse.getStatusCode());
        
        Response getBookResponse = new BookOperations().getBook(latestBookId);
        assertEquals("Status code should be 404", 404, getBookResponse.getStatusCode());
    }
    
    // /books/byauthor/{author_id} endpoint coverage (1/1)
    
    @Test
    public void testGetBooksByAuthor(){
        Response response = new BookOperations().getBooksByAuthor(1);
        assertEquals("Status code should be 200", 200, response.getStatusCode());
    }
    
    // /books/{book_id}/authors endpoint coverage (3/3)
    
    @Test
    public void testGetAuthorsOfBook(){
        Response response = new BookOperations().getAuthorsOfBook(9);
        assertEquals("Status code should be 200", 200, response.getStatusCode());
    }
    
    @Test
    public void testAddAuthorToBook(){
        Response response = new BookOperations().addAuthorToBook(1, 1);
        assertEquals("Status code should be 200", 200, response.getStatusCode());
    }
    
    @Test
    public void testUpdateBookAuthors(){
        // Replaces the author of book 3 with the author(s) of book 4
        Response response = new BookOperations().updateBookWithNewAuthor(3, 4); 
        assertEquals("Status code should be 200", 200, response.getStatusCode());
    }
}
