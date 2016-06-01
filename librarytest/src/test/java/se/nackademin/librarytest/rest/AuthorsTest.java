package se.nackademin.librarytest.rest;

import org.junit.Test;
import static org.junit.Assert.*;
import com.jayway.restassured.response.Response;
import se.nackademin.librarytest.restoperations.AuthorsOperations;

public class AuthorsTest {
    
    // /authors endpoint coverage (3/3)
    
    @Test
    public void testCreateAuthor() {
        Response response = new AuthorsOperations().createRandomAuthor();
        assertEquals("Status code should be 201", 201, response.getStatusCode());
    }
    
    @Test
    public void testUpdateAuthor() {
        Response response = new AuthorsOperations().updateAuthor(3);
        assertEquals("Status code should be 200", 200, response.getStatusCode());
    }
    
    @Test
    public void testGetAllAuthors() {
        Response response = new AuthorsOperations().getAllAuthors();
        assertEquals("Status code should be 200", 200, response.getStatusCode());
    }
    
    // /authors/{id} endpoint coverage (2/2)
    
    @Test
    public void testGetAuthorById(){
        Response response = new AuthorsOperations().getAuthorById(1);
        assertEquals("Status code should be 200", 200, response.getStatusCode());
    }
    
    @Test
    public void testDeleteAuthorById(){
        Response createAuthorResponse = new AuthorsOperations().createRandomAuthor();
        assertEquals("Status code should be 201", 201, createAuthorResponse.getStatusCode());
        
        int latestAuthorId = new AuthorsOperations().getLatestAuthorId();
        
        Response deleteAuthorResponse = new AuthorsOperations().deleteAuthorById(latestAuthorId);
        assertEquals("Status code should be 204", 204, deleteAuthorResponse.getStatusCode());
        
        Response getAuthorResponse = new AuthorsOperations().getAuthorById(latestAuthorId);
        assertEquals("Status code should be 404", 404, getAuthorResponse.getStatusCode());
    }
}