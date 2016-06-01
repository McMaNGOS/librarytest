package se.nackademin.librarytest.rest;

import com.jayway.restassured.response.Response;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import se.nackademin.librarytest.restoperations.LoansOperations;

public class LoansTest {
    
    // /loans endpoint coverage (1/3)
        @Ignore
    @Test
    public void testCreateLoan(){
        Response postResponse = new LoansOperations().createNewLoan(1, 13);
        assertEquals("Status code should be 201", 201, postResponse.getStatusCode());
        
        int latestLoanId = new LoansOperations().getLatestLoanId();
        
        Response deleteResponse = new LoansOperations().deleteLoanById(latestLoanId);
        assertEquals("Status code should be 204", 204, deleteResponse.getStatusCode());
    }
    
    // /loans/{id} endpoint coverage (1/2)
    
    @Test

    public void testGetLoan(){
        Response postResponse = new LoansOperations().createNewLoan(1, 13);
        assertEquals("Status code should be 201", 201, postResponse.getStatusCode());
        
        int latestLoanId = new LoansOperations().getLatestLoanId();
        
        Response response = new LoansOperations().getLoanById(latestLoanId);
        assertEquals("Status code should be 200", 200, response.getStatusCode());
    }
}
