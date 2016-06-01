package se.nackademin.librarytest.restoperations;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import se.nackademin.librarytest.methods.Randomizers;

public class LoansOperations {
    
    private static final String BASE_URL = "http://localhost:8080/librarytest-rest/";
    private String jsonString = "";
    Randomizers randomizers = new Randomizers();
    
    public Response createNewLoan(int bookId, int userId){
        String resourceName = "loans";
        String postBodyTemplate = "{\n"
                + "  \"loan\": {\n"
                + "    \"book\": {\n"
                + "      \"id\": %s,\n"
//                + "      \"title\": \"Time's Eye\"\n"
                + "    },\n"
                + "    \"dateBorrowed\": \"2016-05-06\",\n"
                + "    \"dateDue\": \"2016-05-07\",\n"
                + "    \"user\": {\n"
                + "      \"id\": %s,\n"
//                + "      \"displayName\": \"lennart\",\n"
                + "    }\n"
                + "  }\n"
                + "}";
        String postBody = String.format(postBodyTemplate, bookId, userId);
        jsonString = postBody;
        Response postResponse = given().contentType(ContentType.JSON).body(postBody).post(BASE_URL+resourceName);
        return postResponse;
    }
    
    public int getLatestLoanId(){
        String resourceName = "loans";
        Response getIdResponse = given().accept(ContentType.JSON).get(BASE_URL + resourceName);
        String fetchedId = getIdResponse.jsonPath().getString("loans.loan[-1].id");
        int latestId = Integer.parseInt(fetchedId);
        return latestId;
    }
    
    public Response deleteLoanById(int id){
        String deleteResourceName = "loans/"+id;
        Response deleteResponse = delete(BASE_URL+deleteResourceName);
        return deleteResponse;
    }
    
    public Response getLoanById(int id){
        String resourceName = "loans/"+id;
        Response getResponse = given().accept(ContentType.JSON).get(BASE_URL+resourceName);
        return getResponse;
    }
    
}
