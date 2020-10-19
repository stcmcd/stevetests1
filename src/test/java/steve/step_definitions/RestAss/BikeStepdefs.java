package steve.step_definitions.RestAss;

import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import steve.BikesExtension;
import steve.step_definitions.RestAss.pojo.Bikes;
import steve.step_definitions.RestAss.pojo.IndustryIdentidiers;
import steve.step_definitions.RestAss.pojo.Networks;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

//import org.json.JSONObject;


public class BikeStepdefs {
    static ResponseOptions<Response> response1;
    static BikesExtension bikesExtension  = new BikesExtension();
    static Bikes bikes;
    static String sessionID;
    static HashMap<String, Object> postVolumenfoContent = new HashMap<>();
    static JSONObject json = new JSONObject();
    static List<LinkedHashMap<String, String>> networks = new ArrayList<LinkedHashMap<String, String>>();
//    LinkedHashMap<Integer, String> hm1 = new LinkedHashMap<>();

    @Then("^i perform a get operation for \"([^\"]*)\"$")
    public static void iPerformGetOperationFor1(String basePath, DataTable dataTable) throws Throwable {
        List<Map<String, String>> table = dataTable.asMaps(String.class, String.class);

        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("path", table.get(0).get("id"));

        response1 = bikesExtension.ResponseFromGet(basePath, pathParams);
        networks = response1.getBody().jsonPath().getList("networks");

//        System.out.println((char[]) response1.getBody().jsonPath().get("volumeInfo.title"));

//        bikes.getNetwork().getHref()

        bikes = response1.getBody().as(Bikes.class);
        System.out.println(bikes.getNetwork().getLocation().getCountry());
        System.out.println(bikes.getNetwork().getLocation().getCity());

        for (int i=0; i < bikes.getNetwork().getStations().size(); i++) {
            System.out.println(bikes.getNetwork().getStations().get(i).getName());
            System.out.println(bikes.getNetwork().getStations().get(i).getExtra().getAddress());
            System.out.println("Empty slots    " + bikes.getNetwork().getStations().get(i).getEmpty_slots());
            System.out.println("Ebikes         " + bikes.getNetwork().getStations().get(i).getExtra().getEbikes());
            System.out.println("Electric free  " + bikes.getNetwork().getStations().get(i).getExtra().getElectric_free());
            System.out.println("Electric slots " + bikes.getNetwork().getStations().get(i).getExtra().getElectric_slots());
            System.out.println("Normal bikes   " + bikes.getNetwork().getStations().get(i).getExtra().getNormal_bikes());
            System.out.println("Normal free    " + bikes.getNetwork().getStations().get(i).getExtra().getNormal_free());
            System.out.println("Normal slots   " + bikes.getNetwork().getStations().get(i).getExtra().getNormal_slots());
            System.out.println("Slots          " + bikes.getNetwork().getStations().get(i).getExtra().getSlots());
        }


//        assertEquals(items.getId(), "9s1CDwAAQBAJ");
//        assertEquals(items.getEtag(), "steveMc");

//        IndustryIdentidiers industryIdentidiers = items.getVolumeInfo().getIndustryIdentifiers().stream().filter(x -> x.getType().equalsIgnoreCase("ISBN_10")).findFirst().orElse(null);
//        System.out.println(industryIdentidiers.getIdentifier());

        JSONParser parser = new JSONParser();

        JSONObject joResponse = (JSONObject) parser.parse(response1.getBody().asString());

        getKey1(joResponse, "has_ebikes");
    }

    @Then("^i perform aa get operation for \"([^\"]*)\"$")
    public static void iPerformGetOperationFor2(String basePath, DataTable dataTable) throws Throwable {
        List<Map<String, String>> table = dataTable.asMaps(String.class, String.class);



        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("path", table.get(0).get("id"));

        response1 = bikesExtension.ResponseFromGet1(basePath, pathParams);

        List<String> hrefs = response1.getBody().jsonPath().get("networks.href");

        int i=0;
        while (i<50) {
            bikesExtension.ResponseFromGet1(basePath, pathParams);

            pathParams.put("path", hrefs.get(i));
            System.out.println("href " + hrefs.get(i));

            response1 = bikesExtension.ResponseFromGet1(basePath, pathParams);

            if(!((hrefs.get(i)).contains("onroll") || hrefs.get(i).contains("bicincitta-siena"))) {
                bikes = response1.getBody().as(Bikes.class);

                if(bikes.getNetwork().getLocation().getCountry() !=null ) {
                    System.out.println(bikes.getNetwork().getLocation().getCountry());
                } else {
                    System.out.println("Why is this sometimes null??");
                };

                System.out.println(bikes.getNetwork().getLocation().getCity());

                for (int j=0; j<bikes.getNetwork().getStations().size(); j++) {
                    System.out.println(bikes.getNetwork().getStations().get(j).getName());
                    System.out.println(bikes.getNetwork().getStations().get(j).getExtra().getAddress());
                    System.out.println("Empty slots    " + bikes.getNetwork().getStations().get(j).getEmpty_slots());
                    System.out.println("Ebikes         " + bikes.getNetwork().getStations().get(j).getExtra().getEbikes());
                    System.out.println("Electric free  " + bikes.getNetwork().getStations().get(j).getExtra().getElectric_free());
                    System.out.println("Electric slots " + bikes.getNetwork().getStations().get(j).getExtra().getElectric_slots());
                    System.out.println("Normal bikes   " + bikes.getNetwork().getStations().get(j).getExtra().getNormal_bikes());
                    System.out.println("Normal free    " + bikes.getNetwork().getStations().get(j).getExtra().getNormal_free());
                    System.out.println("Normal slots   " + bikes.getNetwork().getStations().get(j).getExtra().getNormal_slots());
                    System.out.println("Slots          " + bikes.getNetwork().getStations().get(j).getExtra().getSlots());
                }
            }
            i++;

            JSONParser parser = new JSONParser();

            JSONObject joResponse = (JSONObject) parser.parse(response1.getBody().asString());

            getKey1(joResponse, "has_ebikes");
        }

//        IndustryIdentidiers industryIdentidiers = items.getVolumeInfo().getIndustryIdentifiers().stream().filter(x -> x.getType().equalsIgnoreCase("ISBN_10")).findFirst().orElse(null);
//        System.out.println(industryIdentidiers.getIdentifier());

    }

    public static void getKey1(JSONObject json, String key) {
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
                            getKey1((JSONObject) json.get(nextKeys), key);
                        }

                    } else if (json.get(nextKeys) instanceof JSONArray) {
                        JSONArray array = (JSONArray) json.get(nextKeys);

                        for (int i = 0; i < array.size(); i++) {
                                if (!exists) {
                                    getKey1((JSONObject) array.get(i), key);
                                }
                            }
                        }
               } catch(Exception e){
                        // ToDo
                }
            }
        } else{
            parseObject1(json, key);
    }
}

    public static void parseObject1(JSONObject json, String key) {
        System.out.println("There are ebikes at this address " + json.get("address"));
    }
}
