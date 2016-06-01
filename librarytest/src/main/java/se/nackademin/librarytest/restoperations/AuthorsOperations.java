package se.nackademin.librarytest.restoperations;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import java.util.UUID;
import se.nackademin.librarytest.methods.Randomizers;


public class AuthorsOperations {
    
    private static final String BASE_URL = "http://localhost:8080/librarytest-rest/";
    private String jsonString = "";
    Randomizers randomizers = new Randomizers();
    
    public Response createRandomAuthor(){
        String resourceName = "authors";
        String bio = randomizers.generateAlphabeticString(5);
        String country = randomizers.generateAlphabeticString(5);
        String firstName = randomizers.generateAlphabeticString(5);
        String lastName = randomizers.generateAlphabeticString(5);
        String postBodyTemplate = "{\n"
                + "  \"author\": {\n"
                + "    \"bio\": \"%s\",\n"
                + "    \"country\": \"%s\",\n"
                + "    \"firstName\": \"%s\",\n"
                + "    \"lastName\": \"%s\"\n"
                + "  }\n"
                + "}";
        String postBody = String.format(postBodyTemplate, bio, country, firstName, lastName);
        jsonString = postBody;
        Response postResponse = given().contentType(ContentType.JSON).body(postBody).post(BASE_URL+resourceName);
        return postResponse;
    }
    
    public Response getAllAuthors(){
        String resourceName = "authors";
        Response getResponse = given().accept(ContentType.JSON).get(BASE_URL+resourceName);
        return getResponse;
    }
    
    public Response getAuthorById(int id){
        String resourceName = "authors/"+id;
        Response getResponse = given().accept(ContentType.JSON).get(BASE_URL+resourceName);
        return getResponse;
    }
    
    public Response deleteAuthorById(int id){
        String deleteResourceName = "authors/"+id;
        Response deleteResponse = delete(BASE_URL+deleteResourceName);
        return deleteResponse;
    }
    
    public int getLatestAuthorId(){
        String resourceName = "authors";
        Response getIdResponse = given().accept(ContentType.JSON).get(BASE_URL + resourceName);
        String fetchedId = getIdResponse.jsonPath().getString("authors.author[-1].id");
        int latestId = Integer.parseInt(fetchedId);
        return latestId;
    }
    
    public Response updateAuthor(int id){
        String resourceName = "authors";
        String bio = randomizers.generateAlphabeticString(5);
        String country = randomizers.generateAlphabeticString(5);
        String firstName = randomizers.generateAlphabeticString(5);
        String lastName = randomizers.generateAlphabeticString(5);
        String putBodyTemplate = "{\n"
                + "  \"author\": {\n"
                + "    \"id\": %s,"
                + "    \"bio\": \"%s\",\n"
                + "    \"country\": \"%s\",\n"
                + "    \"firstName\": \"%s\",\n"
                + "    \"lastName\": \"%s\"\n"
                + "  }\n"
                + "}";
        String putBody = String.format(putBodyTemplate, id, bio, country, firstName, lastName);
        jsonString = putBody;
        Response response = given().contentType(ContentType.JSON).body(putBody).put(BASE_URL+resourceName);
        return response;
    }
    
}
