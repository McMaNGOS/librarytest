package se.nackademin.librarytest.rest;

import com.jayway.restassured.response.Response;
import org.junit.Test;
import static org.junit.Assert.*;
import se.nackademin.librarytest.restoperations.UsersOperations;

public class UsersTest {
    
    public UsersTest() {
    }
    
    // /users endpoint coverage (3/3)
    
    @Test
    public void testCreateUser(){
        Response response = new UsersOperations().createRandomUser();
        assertEquals("Status code should be 201", 201, response.getStatusCode());
    }
    
    @Test
    public void testUpdateUser(){
        Response response = new UsersOperations().updateUser(14);
        assertEquals("Status code should be 200", 200, response.getStatusCode());
    }
    
    @Test
    public void testGetAllUsers(){
        Response response = new UsersOperations().getAllUsers();
        assertEquals("Status code should be 200", 200, response.getStatusCode());
    }

    // /users/{id} endpoint coverage (2/2)
    
    @Test
    public void testGetUser(){
        Response response = new UsersOperations().getUser(14);
        assertEquals("Status code should be 200", 200, response.getStatusCode());
    }
    
    @Test
    public void testDeleteUser(){
        Response postResponse = new UsersOperations().createRandomUser();
        assertEquals("Status code should be 201", 201, postResponse.getStatusCode());
        
        int latestUserId = new UsersOperations().getLatestUserId();
        
        Response deleteResponse = new UsersOperations().deleteUserById(latestUserId);
        assertEquals("Status code should be 204", 204, deleteResponse.getStatusCode());
        
        Response getResponse = new UsersOperations().getUser(latestUserId);
        assertEquals("Status code should be 404", 404, getResponse.getStatusCode());
    }
}
