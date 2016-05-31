package se.nackademin.librarytest.restoperations;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import java.util.UUID;


public class AuthorsOperations {
    
    private static final String BASE_URL = "http://localhost:8080/librarytest-rest/";
    private String jsonString = "";
    
    public Response createRandomAuthor(){
        String resourceName = "authors";
        String name = UUID.randomUUID().toString();
        String postBodyTemplate = ""
                + "{\n"
                + "\"author\":\n"
                + "  {\n"
                + "    \"name\": \"%s\"\n"
                + "  }\n"
                + "}";
        String postBody = String.format(postBodyTemplate, name);
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
        String putBodyTemplate = ""
                + "{\n"
                + "\"author\":\n"
                + "  {\n"
                + "    \"id\": %s,\n"
                + "    \"name\": \"Test name\"\n"
                + "  }\n"
                + "}";
        String putBody = String.format(putBodyTemplate, id);
        jsonString = putBody;
        Response response = given().contentType(ContentType.JSON).body(putBody).put(BASE_URL+resourceName);
        return response;
    }
    
}
