package se.nackademin.librarytest.restoperations;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import se.nackademin.librarytest.methods.Randomizers;

public class UsersOperations {
    
    private static final String BASE_URL = "http://localhost:8080/librarytest-rest/";
    private String jsonString = "";
    Randomizers randomizers = new Randomizers();
    
    public Response createRandomUser(){
        String resourceName = "users";
        String displayName = randomizers.generateAlphabeticString(5);
        String email = randomizers.generateAlphabeticString(5);
        String firstName = randomizers.generateAlphabeticString(5);
        String lastName = randomizers.generateAlphabeticString(5);
        String password = randomizers.generateAlphabeticString(5);
        
        String postBodyTemplate = "{\n"
                + "  \"user\": {\n"
                + "    \"displayName\": \"%s\",\n"
                + "    \"email\": \"%s\",\n"
                + "    \"firstName\": \"%s\",\n"
                + "    \"lastName\": \"%s\",\n"
                + "    \"password\": \"%s\",\n"
                + "    \"phone\": \"+46777777777\", \n"
                + "    \"role\":\"LOANER\"\n"
                + "  }\n"
                + "}";
        String postBody = String.format(postBodyTemplate, displayName, email, firstName, lastName, password);
        jsonString = postBody;
        Response postResponse = given().contentType(ContentType.JSON).body(postBody).post(BASE_URL+resourceName);
        return postResponse;
    }
    
    public Response updateUser(int id){
        String resourceName = "users";
        String displayName = randomizers.generateAlphabeticString(5);
        String email = randomizers.generateAlphabeticString(5);
        String firstName = randomizers.generateAlphabeticString(5);
        String lastName = randomizers.generateAlphabeticString(5);
        String password = randomizers.generateAlphabeticString(5);
        
        String putBodyTemplate = "{\n"
                + "  \"user\": {\n"
                + "    \"id\": %s,"
                + "    \"displayName\": \"%s\",\n"
                + "    \"email\": \"%s\",\n"
                + "    \"firstName\": \"%s\",\n"
                + "    \"lastName\": \"%s\",\n"
                + "    \"password\": \"%s\",\n"
                + "    \"phone\": \"+46777777777\", \n"
                + "    \"role\":\"LOANER\"\n"
                + "  }\n"
                + "}";
        String putBody = String.format(putBodyTemplate, id, displayName, email, firstName, lastName, password);
        jsonString = putBody;
        Response postResponse = given().contentType(ContentType.JSON).body(putBody).put(BASE_URL+resourceName);
        return postResponse;
    }
    
    public Response getAllUsers(){
        String resourceName = "users";
        Response getResponse = given().accept(ContentType.JSON).get(BASE_URL+resourceName);
        return getResponse;
    } 
    
    public Response getUser(int id){
        String resourceName = "users/"+id;
        Response getResponse = given().accept(ContentType.JSON).get(BASE_URL+resourceName);
        return getResponse;
    }
    
    public int getLatestUserId(){
        String resourceName = "users";
        Response getIdResponse = given().accept(ContentType.JSON).get(BASE_URL + resourceName);
        String fetchedId = getIdResponse.jsonPath().getString("users.user[-1].id");
        int latestId = Integer.parseInt(fetchedId);
        return latestId;
    }
    
    public Response deleteUserById(int id){
        String resourceName = "users/"+id;
        Response deleteResponse = delete(BASE_URL+resourceName);
        return deleteResponse;
    }
    
}
