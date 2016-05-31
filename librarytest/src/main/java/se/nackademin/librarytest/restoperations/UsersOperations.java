package se.nackademin.librarytest.restoperations;

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
    
}
