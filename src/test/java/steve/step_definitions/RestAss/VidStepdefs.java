package steve.step_definitions.RestAss;

import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
//import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import steve.RestAssuredExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

import steve.step_definitions.RestAss.pojo.Items;
import steve.step_definitions.RestAss.pojo.IndustryIdentidiers;
import steve.step_definitions.RestAss.pojo.VolumeInfo;


public class VidStepdefs {
    static ResponseOptions<Response> response1;
    static RestAssuredExtension restAssuredExtension  = new RestAssuredExtension();
    static Items items;
    static String sessionID;
    static HashMap<String, Object> postVolumenfoContent = new HashMap<>();
    static JSONObject json = new JSONObject();

    @Given("^i perform post operation with volumeInfo$")
    public static void iPerformPostOperationForWithVolInfo(DataTable dataTable) throws Throwable {
        List<Map<String, String>> table = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> rows : table) {
            for (Map.Entry<String, String> cols : rows.entrySet()) {
                postVolumenfoContent.put(cols.getKey(), cols.getValue());
            }
        }
    }

    @Given("^i perform post operation with items$")
    public static void iPerformPostOperationForWithItems(DataTable dataTable) throws Throwable {
        List<Map<String, String>> table = dataTable.asMaps(String.class, String.class);
        HashMap<String, Object> postItemsContent = new HashMap<>();

        for (Map<String, String> rows : table) {
            for (Map.Entry<String, String> cols : rows.entrySet()) {
                postItemsContent.put(cols.getKey(), cols.getValue());
            }

            postItemsContent.put("volumeInfo", postVolumenfoContent);

            json.put("id", "26");
            json.put("kind", "books#volume");
            json.put("etag", "abqrstu");

            RestAssured.baseURI = "http://localhost:3000";
            given().header("Content-Type", "application/json")
            .and().body(json.toString())
            .when().post("/items");

            HashMap<String, String> pathParams = new HashMap<>();
            pathParams.put("path", "26");
            restAssuredExtension.DeleteOpsWithPathParams("{path}", pathParams);

//post items pojo using object mapper into json object
            Items items = new Items();
            items.setId("26");
            items.setKind("books#volume");
            items.setEtag(("abqrstu"));

            ObjectMapper objectMapper = new ObjectMapper();
            String itemsJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(items);

            RestAssured.baseURI = "http://localhost:3000";
            given().header("Content-Type", "application/json")
                    .and().body(itemsJson.toString())
                    .when().post("/items");
            restAssuredExtension.DeleteOpsWithPathParams("{path}", pathParams);

            Items items1 = objectMapper.readValue(itemsJson, Items.class);
            System.out.println(items1.getKind());

//post json file
            FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\User\\db - Copy.json"));
            given().header("Content-Type", "application/json")
                    .and().body(IOUtils.toString(fileInputStream, "UTF-8"))
                    .when().post("/items");
            pathParams.put("path", "99");
            restAssuredExtension.DeleteOpsWithPathParams("{path}", pathParams);

//post hashmap

            pathParams.put("path", "");
            response1 = restAssuredExtension.ResponseFromPost(postItemsContent, pathParams);

            System.out.println(response1.getStatusCode());

//normally done after posting username / password
            sessionID = response1.getCookies().get("JSESSIONID");

        }
    }

    @Given("^i perform put operation for \"([^\"]*)\" with body$")
    public static void iPerformPutOperationForWithBody(String basePath, DataTable dataTable) throws Throwable {
        HashMap<String, String> pathParams = new HashMap<>();

        List<Map<String, String>> table = dataTable.asMaps(String.class, String.class);
        HashMap<String, String> postContent = new HashMap<>();

        for (Map<String, String> rows : table) {
            for (Map.Entry<String, String> cols : rows.entrySet()) {
                if(cols.getKey().equals("id")) {
                    pathParams.put("path", cols.getValue());
                } else {
                    postContent.put(cols.getKey(), cols.getValue());
                }
            }
            response1 = restAssuredExtension.ResponseFromPut(basePath, postContent, pathParams, sessionID);
        }
    }

    @Then("^i perform get operation for \"([^\"]*)\"$")
    public static void iPerformGetOperationFor(String basePath, DataTable dataTable) throws Throwable {
        List<Map<String, String>> table = dataTable.asMaps(String.class, String.class);

        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("path", table.get(0).get("id"));

        response1 = restAssuredExtension.ResponseFromGet(basePath, pathParams);
//        System.out.println((char[]) response1.getBody().jsonPath().get("volumeInfo.title"));
        items = response1.getBody().as(Items.class);

        assertEquals(items.getId(), "9s1CDwAAQBAJ");
//        assertEquals(items.getEtag(), "steveMc");

        IndustryIdentidiers industryIdentidiers = items.getVolumeInfo().getIndustryIdentifiers().stream().filter(x -> x.getType().equalsIgnoreCase("ISBN_10")).findFirst().orElse(null);
        System.out.println(industryIdentidiers.getIdentifier());

        JSONParser parser = new JSONParser();

        JSONObject joResponse = (JSONObject) parser.parse(response1.getBody().asString());

        getKey(joResponse, "identifier");
    }

    public static void parseObject(JSONObject json, String key) {
        System.out.println(json.get(key));
    }

    public static void getKey(JSONObject json, String key) {
        Boolean exists = json.keySet().contains(key);

        Iterator<?> keys;
        String nextKeys;

        if (!exists) {
            keys = json.keySet().iterator();
            while (keys.hasNext()) {
                nextKeys = (String) keys.next();
                try {
                    if (json.get(nextKeys) instanceof JSONObject) {

                        if (!exists) {
                            getKey((JSONObject) json.get(nextKeys), key);
                        }

                    } else if (json.get(nextKeys) instanceof JSONArray) {
                        JSONArray array = (JSONArray) json.get(nextKeys);

                        for (int i = 0; i < array.size(); i++) {
                                if (!exists) {
                                    getKey((JSONObject) array.get(i), key);
                                }
                            }
                        }
               } catch(Exception e){
                        // ToDo
                }
            }
        } else{
            parseObject(json, key);
    }
}

    @Then("^i delete \"([^\"]*)\" for \"([^\"]*)\"$")
    public static void iDelete(String id, String basePath) throws Throwable {

        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("path", id);

        restAssuredExtension.DeleteOpsWithPathParams(basePath, pathParams);
    }
}
