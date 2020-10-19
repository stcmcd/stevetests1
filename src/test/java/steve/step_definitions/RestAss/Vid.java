package steve.step_definitions.RestAss;

import java.io.FileReader;

import com.jayway.restassured.response.ResponseBody;
import cucumber.api.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.*;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

//import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;

import static org.junit.Assert.assertEquals;
import steve.RestAssuredExtension;
import io.restassured.response.ResponseOptions;
import steve.step_definitions.RestAss.pojo.Posts;
import steve.step_definitions.RestAss.pojo.Items;
import steve.step_definitions.RestAss.pojo.IndustryIdentidiers;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Vid {
//    https://www.youtube.com/watch?v=EcqKnZ1NiwI&list=PL6tu16kXT9PpgqfMbMdzUzDenYgb0gbk0&index=6
    @Test
    public void Vid() {

//        RequestSpecBuilder
         Response response;
         RequestSpecification request, request1;
         ValidatableResponse json;
         Posts posts;
         Items items;
         String responseBody;

         ResponseOptions<Response> response1;

         RestAssuredExtension restAssuredExtension  = new RestAssuredExtension();

//        json = RestAssured.given().contentType(ContentType.JSON).when().get("http://localhost:3000/posts").then().body(("title"), is("json-server"));
//        json.body(("title"), containsInAnyOrder("json-server"));
//        json.body("volumeInfo.industryIdentifiers[0].type", is("ISBN_13"))

        request = given().contentType(ContentType.JSON);
//        response = request.when().get("http://localhost:3000/posts/1");
//        json = response.then().body("title", is("json-server")).statusCode(200);
//        posts = response.getBody().as(Posts.class);
//        assertEquals(posts.getTitle(),"json-server");
//
//        responseBody = response.getBody().asString();
//        assertEquals(responseBody,matchesJsonSchemaInClasspath("post.json"));
//        assertEquals(responseBody,matchesJsonSchema("post.json"));

        response = request.when().get("http://localhost:3000/items/nvgdAwAAQBAJ");
        json = response.then().body("kind", is("books#volume")).statusCode(200);

        assertEquals(response.getBody().jsonPath().get("kind"), "books#volume");

        items = response.getBody().as(Items.class);
        assertEquals(items.getKind(),"books#volume");

        IndustryIdentidiers industryIdentidiers = items.getVolumeInfo().getIndustryIdentifiers().stream().filter(x -> x.getType().equalsIgnoreCase("ISBN_10")).findFirst().orElse(null);

        //path params
        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("path", "1");
        request.pathParams(pathParams);

        response = request.given().contentType(ContentType.JSON).get("http://localhost:3000/posts/{path}");

//query params
        HashMap<String, String> queryParams = new HashMap<>();
                queryParams.put("id", "4");
        request1 = given().contentType(ContentType.JSON);
        request1.queryParams(queryParams);
        response = request1.given().contentType(ContentType.JSON).get("http://localhost:3000/posts");

        HashMap<String, String> postContent = new HashMap<>();
        postContent.put("id", "11");
        postContent.put("title", "rest-assured-course");
        postContent.put("author", "ExecuteAutomation");

// needed to comment out url
        //        response1 = restAssuredExtension.ResponseFromPost("http://localhost:3000/posts", postContent);

        RestAssured.baseURI = "http://localhost:3000/posts";
//        RequestSpecification request = RestAssured.given().contentType(ContentType.JSON).when().get("http://localhost:3000/posts/1").then().body("title"), is("json-server");


// check 'is', 'contains', 'is not'
//cannot do below coz have just done a post not a get
//        assertEquals(response.getBody().jsonPath().get("id"), "10");
    }
}