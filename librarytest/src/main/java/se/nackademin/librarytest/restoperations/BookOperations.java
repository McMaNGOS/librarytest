package se.nackademin.librarytest.restoperations;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import se.nackademin.librarytest.methods.Randomizers;

public class BookOperations {
    
    private static final String BASE_URL = "http://localhost:8080/librarytest-rest/";
    private String jsonString = "";
    Randomizers randomizers = new Randomizers();
    
    public Response getBook(int id){
        String resourceName = "books/"+id;
        Response response = given().accept(ContentType.JSON).get(BASE_URL+resourceName);
        return response;
    }
    
    public Response createRandomBook(){
        String resourceName = "books";
        String description = randomizers.generateAlphabeticString(5);
        String publicationDate = randomizers.generateRandomDate();
        String title = randomizers.generateAlphabeticString(5);
        
        String postBodyTemplate = "{\n"
                + "  \"book\": {\n"
                + "    \"author\": {\n"
                + "      \"id\": 4,\n"
                + "      \"country\":\"Great Britain\",\n"
                + "      \"firstName\": \"Arthur C.\",\n"
                + "      \"lastName\": \"Clarke\"\n"
                + "    },\n"
                + "    \"description\": \"%s\",\n"
                + "    \"isbn\": \"1-234-5678-9\",\n"
                + "    \"nbrPages\": 123,\n"
                + "    \"publicationDate\": \"%s\",\n"
                + "    \"title\": \"%s\",\n"
                + "    \"totalNbrCopies\": 1\n"
                + "  }\n"
                + "}";
        String postBody = String.format(postBodyTemplate, description, publicationDate, title);
        jsonString = postBody;
        Response postResponse = given().contentType(ContentType.JSON).body(postBody).post(BASE_URL+resourceName);
        return postResponse;
    }
    
    public String getLatestJsonString(){
        return jsonString;
    }
    
    public Response getAllBooks(){
        String resourceName = "books";
        Response getResponse = given().accept(ContentType.JSON).get(BASE_URL+resourceName);
        return getResponse;
    }
    
    public Response deleteBook(int id){
        String deleteResourceName = "books/"+id;
        Response deleteResponse = delete(BASE_URL+deleteResourceName);
        return deleteResponse;
    }
    
    public Response updateBook(int id){
        String description = randomizers.generateAlphabeticString(5);
        String publicationDate = randomizers.generateRandomDate();
        String title = randomizers.generateAlphabeticString(5);
        String resourceName = "books/";
        String putBodyTemplate = "{\n"
                + "  \"book\": {\n"
                + "    \"id\": %s,"
                + "    \"author\": {\n"
                + "      \"id\": 4,\n"
                + "      \"country\":\"Great Britain\",\n"
                + "      \"firstName\": \"Arthur C.\",\n"
                + "      \"lastName\": \"Clarke\"\n"
                + "    },\n"
                + "    \"description\": \"%s\",\n"
                + "    \"isbn\": \"1-234-5678-9\",\n"
                + "    \"nbrPages\": 123,\n"
                + "    \"publicationDate\": \"%s\",\n"
                + "    \"title\": \"%s\",\n"
                + "    \"totalNbrCopies\": 1\n"
                + "  }\n"
                + "}";
        
        String putBody = String.format(putBodyTemplate, id, description, publicationDate, title);
        jsonString = putBody;
        Response response = given().contentType(ContentType.JSON).body(putBody).put(BASE_URL+resourceName);
        return response;
    }
    
    public int getLatestBookId(){
        String resourceName = "books";
        Response getIdResponse = given().accept(ContentType.JSON).get(BASE_URL + resourceName);
        String fetchedId = getIdResponse.jsonPath().getString("books.book[-1].id");
        int latestId = Integer.parseInt(fetchedId);
        return latestId;
    }
    
    public Response getBooksByAuthor(int id){
        String resourceName = "books/byauthor/"+id;
        Response response = given().accept(ContentType.JSON).get(BASE_URL+resourceName);
        return response;
    }
    
    public Response getAuthorsOfBook(int id){
        String resourceName = "books/"+id+"/authors";
        Response response = given().accept(ContentType.JSON).get(BASE_URL+resourceName);
        return response;
    }
    
    public Response addAuthorToBook(int bookId, int authorId){
        String resourceName = "books/"+bookId+"/authors";
        String putBodyTemplate = "{\n"
                + "    \"authors\": {\n"
                + "        \"author\": {\n"
                + "            \"id\": %s\n"
                + "        }\n"
                + "    }\n"
                + "}";
        String putBody = String.format(putBodyTemplate, authorId);
        jsonString = putBody;
        Response postResponse = given().contentType(ContentType.JSON).body(putBody).put(BASE_URL+resourceName);
        return postResponse;
    }
    
    public Response updateBookWithNewAuthor(int bookId, int authorOfBookId){
        String resourceName = "books/"+bookId+"/authors";
        String resourceAuthor = "books/"+authorOfBookId+"/authors";
        String putBody = given().accept(ContentType.JSON).get(BASE_URL+resourceAuthor).asString();
        Response response = given().contentType(ContentType.JSON).body(putBody).put(BASE_URL+resourceName);
        return response;
    }
}