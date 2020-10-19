package steve.step_definitions.RestAss;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import steve.BikesExtension;
import steve.step_definitions.RestAss.pojo.Bikes;
import steve.step_definitions.RestAss.pojo.Locations;
import java.util.*;

/// this is  aa comment


public class BikeStepdefs {
    static ResponseOptions<Response> response;
    static BikesExtension bikesExtension  = new BikesExtension();
    static Bikes bikes;
    static List<LinkedHashMap<String, String>> networks = new ArrayList<LinkedHashMap<String, String>>();

    @Then("^the city \"([^\"]*)\" is in \"([^\"]*)\" is displayed in the console$")
    public static void iPerformGetOperationFor2(String city, String country) throws Throwable {
        Boolean found = false;

        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("path", "v2/networks");

        response = bikesExtension.ResponseFromGet("{path}", pathParams);

        List<Locations> locations = response.getBody().jsonPath().get("networks.location");

        int i = 0;
        while (i < locations.size() && !found) {
            if (locations.toArray()[i].toString().contains(city) && locations.toArray()[i].toString().contains(country)) {//            if(networks.get(i).get("location").get("city").equals("Frankfurt") && networks.get(i).get("location").get("country").equals("DE")) {
                System.out.println(locations.toArray()[i].toString());
                found = true;
            } else {
                i++;
            }
        }

        if(!found) {
            System.out.println("There is no City " + city + " and country " + country );
        }
    }

    @Then("^display in console what extras are in stations \"([^\"]*)\"$")
    public static void displayInConsoleWhatExtrasAreInStations(String basePath, DataTable dataTable) throws Throwable {
        List<Map<String, String>> table = dataTable.asMaps(String.class, String.class);

        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("path", table.get(0).get("network"));

        response = bikesExtension.ResponseFromGet(basePath, pathParams);

        List<String> hrefs = response.getBody().jsonPath().get("networks.href");

        int i=0;
        while (i<50) {
            bikesExtension.ResponseFromGet(basePath, pathParams);

            pathParams.put("path", hrefs.get(i));
            System.out.println("href " + hrefs.get(i));

            response = bikesExtension.ResponseFromGet(basePath, pathParams);

            if(!((hrefs.get(i)).contains("onroll") || hrefs.get(i).contains("bicincitta-siena"))) {
                bikes = response.getBody().as(Bikes.class);

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
        }
    }

    @Then("^Find bike stations that the extra \"([^\"]*)\" is available \"([^\"]*)\"$")
    public static void displayInConsoleWhatSpecificExtrasAreInStations(String extra, String basePath, DataTable dataTable) throws Throwable {
        List<Map<String, String>> table = dataTable.asMaps(String.class, String.class);

        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("path", table.get(0).get("network"));

        response = bikesExtension.ResponseFromGet(basePath, pathParams);

        List<String> hrefs = response.getBody().jsonPath().get("networks.href");

        int i=0;
        while (i<50) {
            bikesExtension.ResponseFromGet(basePath, pathParams);

            pathParams.put("path", hrefs.get(i));
            System.out.println("href " + hrefs.get(i));

            response = bikesExtension.ResponseFromGet(basePath, pathParams);

            if(!((hrefs.get(i)).contains("onroll") || hrefs.get(i).contains("bicincitta-siena"))) {
                bikes = response.getBody().as(Bikes.class);

            }
            i++;

            JSONParser parser = new JSONParser();

            JSONObject joResponse = (JSONObject) parser.parse(response.getBody().asString());

            getKey1(joResponse, extra);
        }
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
