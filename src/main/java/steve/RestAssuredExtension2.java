////https://github.com/executeautomation/restassured/blob/master/src/main/java/utilities/RestAssuredExtension.java
//package steve;
//
//import io.restassured.RestAssured;
//import io.restassured.builder.RequestSpecBuilder;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import io.restassured.response.ResponseOptions;
//import io.restassured.specification.RequestSpecification;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class RestAssuredExtension2 {
//    private static RequestSpecification request;
//
//
//    public RestAssuredExtension() {
//        RequestSpecBuilder builder = new RequestSpecBuilder();
//        builder.setBaseUri("http://localhost:3000");
//        builder.setContentType(ContentType.JSON);
//        RequestSpecification requestSpec = builder.build();
//        request = RestAssured.given().spec(requestSpec);
//    }
//
//    public static void RequestGetWithPathParamters(String url, Map<String, String> pathParams) {
//        request.pathParams(pathParams);
//
//        try {
//            request.get(new URI(url));
//        } catch(URISyntaxException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //check out the way the return statement
////    public ResponseOptions<Response> ResponseFromGet(String url) {
//    public static ResponseOptions<Response> ResponseFromGet(String url, Map<String, String> pathParams) {
//        try {
//            request.pathParams(pathParams);
//            return request.get(new URI(url));
//        } catch(URISyntaxException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static ResponseOptions<Response> ResponseFromPost(String url, Map<String, String> body) {
//        try {
//            request.with().body(body);
//            return request.when().post(new URI(url));
//        } catch(URISyntaxException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    public static ResponseOptions<Response> ResponseFromPut(String url, Map<String, String> body, Map<String, String> pathParams) {
//        request.with().body(body);
//        request.pathParams(pathParams);
//        return request.when().put(url);
//
//    }
//    public static ResponseOptions<Response> DeleteOpsWithPathParams(String url, Map<String, String> pathParams)  {
//        request.pathParams(pathParams);
//
////        request.pathParams(pathParams);
//        return request.delete(url);
//    }
//
////    private ResponseOptions<Response> ExecuteAPI() {
////        RequestSpecification requestSpecification = builder.build();
////        RequestSpecification request = RestAssured.given();
////        request.contentType(ContentType.JSON);
////        request.spec(requestSpecification);
////
////        if (this.method.equalsIgnoreCase(apiConstant.apiMethods.POST))
////            return request.post(this.url);
////        else if (this.method.equalsIgnoreCase(apiConstant.apiMethods.DELETE))
////            return request.delete(this.url);
////        else if (this.method.equalsIgnoreCase(apiConstant.apiMethods.GET))
////            return request.get(this.url);
////        return null;
////
////    }
//
//}
