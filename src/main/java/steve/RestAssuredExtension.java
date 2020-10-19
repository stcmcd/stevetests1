package steve;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class RestAssuredExtension {
    private static RequestSpecification request;
    private static RequestSpecBuilder builder = new RequestSpecBuilder();
    private static RequestSpecification requestSpec;

    public RestAssuredExtension() {
//        builder.addHeader().setAuth().
        builder.setBaseUri("http://localhost:3000");

        builder.setBasePath("/items");
        builder.setContentType(ContentType.JSON);
    }

    public static ResponseOptions<Response> ResponseFromPost(Map<String, Object> body, Map<String, String> pathParams) {
//        builder.setBasePath("/items");
        builder.addPathParams(pathParams);
        builder.setBody(body);
        return ExecuteAPI("POST");
    }

    public static ResponseOptions<Response> ResponseFromPut(String basePath, Map<String, String> body, Map<String, String> pathParams, String sessionID) {
        builder.setBasePath("/items/" + basePath);
        builder.setBody(body);
        builder.addPathParams(pathParams);

        Cookie myCookie = new Cookie.Builder("session_id", sessionID)
                .setSecured(true)
                .setComment("session id cookie")
                .build();

        builder.addCookie(myCookie);

        return ExecuteAPI("PUT");
    }

    public static ResponseOptions<Response> ResponseFromGet(String basePath, Map<String, String> pathParams) {
        builder.setBasePath("/items/" + basePath);
        builder.addPathParams(pathParams);
        return ExecuteAPI("GET");
    }

    public static ResponseOptions<Response> DeleteOpsWithPathParams(String basePath, Map<String, String> pathParams) {
        builder.setBasePath("/items/" + basePath);
        builder.addPathParams(pathParams);

        return ExecuteAPI("DELETE");
    }

    private static ResponseOptions<Response> ExecuteAPI(String method) {
//        RequestSpecification request = RestAssured.given();
//        request.spec(requestSpecification);

        requestSpec = builder.build();
        request = RestAssured.given().spec(requestSpec);

        if (method.equalsIgnoreCase(apiConstant.apiMethods.POST))
            return request.post();
        else if (method.equalsIgnoreCase(apiConstant.apiMethods.DELETE))
            return request.delete();
        else if (method.equalsIgnoreCase(apiConstant.apiMethods.PUT))
            return request.put();
        else if (method.equalsIgnoreCase(apiConstant.apiMethods.GET))
            return request.get();
        return null;
    }
}