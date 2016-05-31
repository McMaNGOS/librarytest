package se.nackademin.librarytest.restoperations;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookOperations {
    
    private static final String BASE_URL = "http://localhost:8080/librarytest-rest/";
    private String jsonString = "";
    
    private static final Logger LOG = Logger.getLogger(BookOperations.class.getName());
    
    public Response getBook(int id){
        LOG.log(Level.INFO, "Getting book with ID: {0}", id);
        String resourceName = "books/"+id;
        Response response = given().accept(ContentType.JSON).get(BASE_URL+resourceName);
        return response;
    }
    
    public Response createRandomBook(){
        LOG.log(Level.INFO, "Creating random book");
        String resourceName = "books";
        String title = UUID.randomUUID().toString();
        String description = UUID.randomUUID().toString();
        String isbn = UUID.randomUUID().toString();
        String postBodyTemplate = ""
                + "{\n"
                + "  \"book\": {\n"
                + "    \"author\": {\n"
                + "      \"id\": 4,\n"
                + "      \"country\":\"Great Britain\",\n"
                + "      \"firstName\": \"Arthur C.\",\n"
                + "      \"lastName\": \"Clarke\"\n"
                + "    },\n"
                + "    \"description\": \"%s\",\n"
                + "    \"isbn\": \"%s\",\n"
                + "    \"nbrPages\": %s,\n"
                + "    \"publicationDate\": \"1990-01-01\",\n"
                + "    \"title\": \"%s\",\n"
                + "    \"totalNbrCopies\": 4\n"
                + "  }\n"
                + "}";
        String postBody = String.format(postBodyTemplate, description, isbn, ""+new Random().nextInt(500), title);
        jsonString = postBody;
        Response postResponse = given().contentType(ContentType.JSON).body(postBody).post(BASE_URL+resourceName);
        return postResponse;
    }
    
    public String getLatestJsonString(){
        LOG.log(Level.INFO, "Getting latest JSON string");
        return jsonString;
    }
    
    public Response getAllBooks(){
        LOG.log(Level.INFO, "Getting all books");
        String resourceName = "books";
        Response getResponse = given().accept(ContentType.JSON).get(BASE_URL+resourceName);
        return getResponse;
    }
    
    public Response deleteBook(int id){
        LOG.log(Level.INFO, "Deleting book with ID: {0}", id);
        String deleteResourceName = "books/"+id;
        Response deleteResponse = delete(BASE_URL+deleteResourceName);
        return deleteResponse;
    }
    
    public Response updateBook(int id){
        LOG.log(Level.INFO, "Updating book with ID: {0}", id);
        String resourceName = "books/";
        String putBodyTemplate = ""
                + "{\n"
                + "    \"book\": {\n"
                + "        \"description\": \"Test Description\",\n"
                + "        \"id\": %s,\n"
                + "        \"isbn\": \"Test isbn\",\n"
                + "        \"nbOfPage\": 324,\n"
                + "        \"title\": \"Test title\"\n"
                + "    }\n"
                + "}";
        
        String putBody = String.format(putBodyTemplate, id);
        jsonString = putBody;
        
        Response response = given().contentType(ContentType.JSON).body(putBody).put(BASE_URL+resourceName);
        return response;
    }
    
    public int getLatestBookId(){
        LOG.log(Level.INFO, "Getting ID of most recently created book");
        String resourceName = "books";
        Response getIdResponse = given().accept(ContentType.JSON).get(BASE_URL + resourceName);
        String fetchedId = getIdResponse.jsonPath().getString("books.book[-1].id");
        int latestId = Integer.parseInt(fetchedId);
        return latestId;
    }
    
    public Response getBooksByAuthor(int id){
        LOG.log(Level.INFO, "Getting books by author with ID: {0}", id);
        String resourceName = "books/byauthor/"+id;
        Response response = given().accept(ContentType.JSON).get(BASE_URL+resourceName);
        return response;
    }
    
    public Response getAuthorsOfBook(int id){
        LOG.log(Level.INFO, "Getting authors of book with ID: {0}", id);
        String resourceName = "books/"+id+"/authors";
        Response response = given().accept(ContentType.JSON).get(BASE_URL+resourceName);
        return response;
    }
    
    public Response addAuthorToBook(int bookId, int authorId){
        LOG.log(Level.INFO, "Adding author with ID {0} to book with ID {1}", new Object[]{authorId, bookId});
        String resourceName = "books/"+bookId+"/authors";
        String resourceAuthor = "authors/"+authorId;
        String postBody = given().accept(ContentType.JSON).get(BASE_URL+resourceAuthor).asString();
        Response response = given().contentType(ContentType.JSON).body(postBody).post(BASE_URL+resourceName);
        return response;
    }
    
    public Response updateBookWithNewAuthor(int bookId, int authorOfBookId){
        LOG.log(Level.INFO, "Replacing the authors of book with ID {0} with the authors from book with ID {1}", new Object[]{bookId, authorOfBookId});
        String resourceName = "books/"+bookId+"/authors";
        String resourceAuthor = "books/"+authorOfBookId+"/authors";
        String putBody = given().accept(ContentType.JSON).get(BASE_URL+resourceAuthor).asString();
        Response response = given().contentType(ContentType.JSON).body(putBody).put(BASE_URL+resourceName);
        return response;
    }
}