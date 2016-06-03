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
        String fetchedId = getIdResponse.jsonPath().getString("loans.loan.id");
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
    
    public Response getAllLoans(){
        String resourceName = "loans";
        Response getResponse = given().accept(ContentType.JSON).get(BASE_URL+resourceName);
        return getResponse;
    }
    
    public Response updateLoanById(int id){
        String resourceName = "loans";
        String dateBorrowed = randomizers.generateRandomDate();
        String dateDue = randomizers.generateRandomDate();
        String putBodyTemplate = "{\n"
                + "        \"loan\": {\n"
                + "            \"id\": %s,\n"
                + "            \"book\": {\n"
                + "                \"id\": 1,\n"
                + "                \"author\": {\n"
                + "                    \"id\": 1,\n"
                + "                },\n"
                + "            },\n"
                + "            \"dateBorrowed\": \"%s\",\n"
                + "            \"dateDue\": \"%s\",\n"
                + "            \"user\": {\n"
                + "                \"id\": 13,\n"
                + "            }\n"
                + "        }\n"
                + "}";
        String putBody = String.format(putBodyTemplate, id, dateBorrowed, dateDue);
        Response putResponse = given().contentType(ContentType.JSON).body(putBody).put(BASE_URL+resourceName);
        return putResponse;
    }
    
    public Response getLoansOfUser(int id){
        String resourceName = "loans/ofuser/"+id;
        Response getResponse = given().accept(ContentType.JSON).get(BASE_URL+resourceName);
        return getResponse;
    }
    
    public Response getLoansOfBook(int id){
        String resourceName = "loans/ofbook/"+id;
        Response getResponse = given().accept(ContentType.JSON).get(BASE_URL+resourceName);
        return getResponse;
    }
    
    public Response getLoanOfBookByUser(int bookId, int userId){
        String resourceName = "loans/ofuser/"+userId+"/ofbook/"+bookId;
        Response getResponse = given().accept(ContentType.JSON).get(BASE_URL+resourceName);
        return getResponse;
    }
}
