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
                + "    \"loan\": {\n"
                + "        \"id\": %s,\n"
                + "        \"book\": {\n"
                + "            \"id\": 1,\n"
                + "            \"author\": {\n"
                + "                \"id\": 1,\n"
                + "                \"bio\": \"Margaret Eleanor Atwood, CC OOnt FRSC (born November 18, 1939) is a Canadian poet, novelist, literary critic, essayist, and environmental activist. She is a winner of the Arthur C. Clarke Award and Prince of Asturias Award for Literature, has been shortlisted for the Booker Prize five times, winning once, and has been a finalist for the Governor General's Award several times, winning twice. In 2001, she was inducted into Canada's Walk of Fame.\",\n"
                + "                \"country\": \"Canada\",\n"
                + "                \"firstName\": \"Margaret\",\n"
                + "                \"lastName\": \"Atwood\"\n"
                + "            },\n"
                + "            \"description\": \"The novel focuses on a post-apocalyptic character named Snowman, living near a group of primitive human-like creatures whom he calls Crakers.\",\n"
                + "            \"isbn\": \"0-7710-0868-6\",\n"
                + "            \"nbrPages\": 411,\n"
                + "            \"publicationDate\": \"2003-04-01\",\n"
                + "            \"title\": \"Oryx and Crake\",\n"
                + "            \"totalNbrCopies\": 1\n"
                + "        },\n"
                + "        \"dateBorrowed\": \"%s\",\n"
                + "        \"dateDue\": \"%s\",\n"
                + "        \"user\": {\n"
                + "            \"id\": 13,\n"
                + "            \"displayName\": \"lennart\",\n"
                + "            \"email\": \"lennart@example.com\",\n"
                + "            \"firstName\": \"Lennart\",\n"
                + "            \"lastName\": \"Moraeus\",\n"
                + "            \"password\": \"sha1:64000:18:tfMovlaWJGz+PJCFqB4nty2hBqki4AEB:KLf0RBdYxtHhIFm06Us7DXGz\",\n"
                + "            \"phone\": \"+46000333444\",\n"
                + "            \"role\": \"LOANER\"\n"
                + "        }\n"
                + "    }\n"
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
