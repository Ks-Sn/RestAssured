package example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class Demo1 {
    public static void main(String[] args) {
        // 35c6655fe6bb04be12af3f029b75ad2f4ee965a0e5444fc886e4f3c7b9b02301

         final String BASE_URL = "https://gorest.co.in/public/v2/";

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri(BASE_URL);
        requestSpecification.header("Authorization", "bearer 35c6655fe6bb04be12af3f029b75ad2f4ee965a0e5444fc886e4f3c7b9b02301");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.accept(ContentType.JSON);

        Response response = requestSpecification.request(Method.GET, "users");
        Assert.assertEquals(200, response.getStatusCode());
        System.out.println(response.prettyPrint());



    }

}
