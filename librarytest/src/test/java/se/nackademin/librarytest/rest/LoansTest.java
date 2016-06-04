package se.nackademin.librarytest.rest;

import com.jayway.restassured.response.Response;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import se.nackademin.librarytest.restoperations.LoansOperations;

public class LoansTest {
    
    // /loans endpoint coverage (3/3)

    @Test
    public void testCreateLoan(){
        Response postResponse = new LoansOperations().createNewLoan(1, 13);
        assertEquals("Status code should be 201", 201, postResponse.getStatusCode());
        
        int latestLoanId = new LoansOperations().getLatestLoanId();
        
        Response deleteResponse = new LoansOperations().deleteLoanById(latestLoanId);
        assertEquals("Status code should be 204", 204, deleteResponse.getStatusCode());
    }
    
    @Test
    public void testUpdateLoan(){
        Response postResponse = new LoansOperations().createNewLoan(1, 13);
        assertEquals("Status code should be 201", 201, postResponse.getStatusCode());
        
        int latestLoanId = new LoansOperations().getLatestLoanId();
        
        Response putResponse = new LoansOperations().updateLoanById(latestLoanId);
        assertEquals("Status code should be 200", 200, putResponse.getStatusCode());
        
        Response deleteResponse = new LoansOperations().deleteLoanById(latestLoanId);
        assertEquals("Status code should be 204", 204, deleteResponse.getStatusCode());
    }
    
    @Test
    public void testGetAllLoans(){
        Response getResponse = new LoansOperations().getAllLoans();
        assertEquals("Status code should be 200", 200, getResponse.getStatusCode());
    }
    
    // /loans/{id} endpoint coverage (2/2)
    
    @Test
    public void testGetLoan(){
        Response postResponse = new LoansOperations().createNewLoan(1, 13);
        assertEquals("Status code should be 201", 201, postResponse.getStatusCode());
        
        int latestLoanId = new LoansOperations().getLatestLoanId();
        
        Response response = new LoansOperations().getLoanById(latestLoanId);
        assertEquals("Status code should be 200", 200, response.getStatusCode());
        
        Response deleteResponse = new LoansOperations().deleteLoanById(latestLoanId);
        assertEquals("Status code should be 204", 204, deleteResponse.getStatusCode());
    }
    
    @Test
    public void testDeleteLoan(){
        Response postResponse = new LoansOperations().createNewLoan(1, 13);
        assertEquals("Status code should be 201", 201, postResponse.getStatusCode());
        
        int latestLoanId = new LoansOperations().getLatestLoanId();
        
        Response deleteResponse = new LoansOperations().deleteLoanById(latestLoanId);
        assertEquals("Status code should be 204", 204, deleteResponse.getStatusCode());
        
        Response response = new LoansOperations().getLoanById(latestLoanId);
        assertEquals("Status code should be 404", 404, response.getStatusCode());
    }
    
    // /loans/ofuser/{user_id} endpoint coverage (1/1)
    
    @Test
    public void testGetLoansOfUser(){
        Response postResponse = new LoansOperations().createNewLoan(1, 13);
        assertEquals("Status code should be 201", 201, postResponse.getStatusCode());
        
        Response getResponse = new LoansOperations().getLoansOfUser(13);
        assertEquals("Status code should be 200", 200, getResponse.getStatusCode());
        
        int latestLoanId = new LoansOperations().getLatestLoanId();
        
        Response deleteResponse = new LoansOperations().deleteLoanById(latestLoanId);
        assertEquals("Status code should be 204", 204, deleteResponse.getStatusCode());
    }
    
    // /loans/ofbook/{book_id} endpoint coverage (1/1)
    
    @Test
    public void testGetAllLoansOfBook(){
        Response postResponse = new LoansOperations().createNewLoan(1, 13);
        assertEquals("Status code should be 201", 201, postResponse.getStatusCode());
        
        Response getResponse = new LoansOperations().getLoansOfBook(1);
        assertEquals("Status code should be 200", 200, getResponse.getStatusCode());
        
        int latestLoanId = new LoansOperations().getLatestLoanId();
        
        Response deleteResponse = new LoansOperations().deleteLoanById(latestLoanId);
        assertEquals("Status code should be 204", 204, deleteResponse.getStatusCode());
    }
    
    // /loans/ofuser/{user_id}/ofbook/{book_id} endpoint coverage (1/1)
    
    @Test
    public void testGetLoanOfBookByUser(){
        Response postResponse = new LoansOperations().createNewLoan(1, 13);
        assertEquals("Status code should be 201", 201, postResponse.getStatusCode());
        
        Response getResponse = new LoansOperations().getLoanOfBookByUser(1, 13);
        assertEquals("Status code should be 200", 200, getResponse.getStatusCode());
        
        int latestLoanId = new LoansOperations().getLatestLoanId();
        
        Response deleteResponse = new LoansOperations().deleteLoanById(latestLoanId);
        assertEquals("Status code should be 204", 204, deleteResponse.getStatusCode());
    }
}
