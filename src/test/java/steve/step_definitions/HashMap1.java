package steve.step_definitions;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class HashMap1 {
// Import the HashMap class
        public static void main(String[] args) {
            // Create a HashMap object called capitalCities
            HashMap<String, String> capitalCities = new HashMap<String, String>();

            // Add keys and values (Country, City)
            capitalCities.put("England", "London");
            capitalCities.put("Germany", "Berlin");
            capitalCities.put("Norway", "Oslo");
            capitalCities.put("USA", "Washington DC");

            System.out.println(capitalCities.get("England"));
            System.out.println(capitalCities.values().toArray()[0]);
            System.out.println(capitalCities.keySet().toArray()[0]);

            LinkedHashMap<String, String> capitalCities1 = new LinkedHashMap<String, String>();

            // Add keys and values (Country, City)
            capitalCities1.put("England", "London");
            capitalCities1.put("Germany", "Berlin");
            capitalCities1.put("Norway", "Oslo");
            capitalCities1.put("USA", "Washington DC");

            System.out.println(capitalCities1.get("England"));
            System.out.println(capitalCities1.values().toArray()[0]);
            System.out.println(capitalCities1.keySet().toArray()[0]);
        }

}
