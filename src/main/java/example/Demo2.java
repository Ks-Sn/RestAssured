package example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.RestAssured.responseSpecification;

public class Demo2 {

    String userId = "";
    static RequestSpecification requestSpecification;

    static Response response;

    @Test
    public void TestUserDetails() {
        final String BASE_URL = "https://gorest.co.in/public/v2/";

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri(BASE_URL);
        requestSpecification.header("Authorization", "bearer 35c6655fe6bb04be12af3f029b75ad2f4ee965a0e5444fc886e4f3c7b9b02301");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.accept(ContentType.JSON);

        Response response = requestSpecification
                .request(Method.GET, "users");
        response.prettyPrint(); // чтобы не делал дубликаты

    }

    @Test
    public void createNewUser() {
        final String BASE_URL = "https://gorest.co.in/public/v2/";

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri(BASE_URL);
        requestSpecification.header("Authorization", "Bearer 35c6655fe6bb04be12af3f029b75ad2f4ee965a0e5444fc886e4f3c7b9b02301");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.accept(ContentType.JSON);

        String payload = "{\n" +
                "\"name\": \"John Doe21\",\n" +
                "\"email\": \"jkasiet.com\",\n" +
                "\"gender\": \"male\",\n" +
                "\"status\": \"active\",\n" +
                "}";

        requestSpecification.body(payload);

        response = requestSpecification
                .request(Method.POST, "users");

        JsonPath jsonPath = response.jsonPath();
        userId = jsonPath.getString("id");

        response.prettyPrint(); // чтобы не делал дубликаты
        Assert.assertEquals(201, response.getStatusCode());
    }

    @Test
    public void correctName(){
        final String BASE_URL = "https://gorest.co.in/public/v2/comments";
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri(BASE_URL);
        requestSpecification.header("Authorization", "bearer 35c6655fe6bb04be12af3f029b75ad2f4ee965a0e5444fc886e4f3c7b9b02301");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.accept(ContentType.JSON);

        String payload = "{\n" +
                "    \"name\": \"Janara Doe\", \n" +
                "    \"email\": \"johnkasiet312@doe.com\", \n" +
                "    \"gender\": \"male\", \n" +
                "    \"status\": \"active\", \n" +
                "    }";

        requestSpecification.body(payload);

                 response = requestSpecification
                        .request(Method.PATCH, "user" + userId);
        response.prettyPrint(); // чтобы не делал дубликаты
        Assert.assertEquals(200, response.getStatusCode());
    }


    @Test
    public void updateNewUserName(){

        String payload = "{\n" +
                "    \"name\": \"Jane Doe\", \n" +
                "    \"email\": \"johndon31ddd2@doe.com\", \n" +
                "    \"gender\": \"male\", \n" +
                "    \"status\": \"active\", \n" +
                "    }";

        requestSpecification.body(payload);

       response = requestSpecification
                .request(Method.PATCH, "users/" + userId);
        response.prettyPrint(); // чтобы не делал дубликаты
        Assert.assertEquals(200, response.getStatusCode());
        response.then().body("id", Matchers.is(userId));



    }

    @Test
    public void findUserTest(){
        //Aatmaj Saini
        requestSpecification.queryParam("name", "Elon Maskeee");
        response = requestSpecification
                .request(Method.GET, "users/");
        response.prettyPrint();
    }


}









