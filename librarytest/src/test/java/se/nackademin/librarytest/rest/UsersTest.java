package se.nackademin.librarytest.rest;

import com.jayway.restassured.response.Response;
import org.junit.Test;
import static org.junit.Assert.*;
import se.nackademin.librarytest.restoperations.UsersOperations;

public class UsersTest {
    
    public UsersTest() {
    }
    
    // /users endpoint coverage
    
    @Test
    public void testCreateUser() {
        Response response = new UsersOperations().createRandomUser();
        assertEquals("Status code should be 201", 201, response.getStatusCode());
    }
    
}
