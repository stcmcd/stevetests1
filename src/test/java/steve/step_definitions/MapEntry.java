package steve.step_definitions;

import java.util.*;


public class MapEntry {

    public static void main(String[] args) {
//        HashMap<String, String> map = new HashMap<>();
//        map.put("AREA_DS_ID", "1,5,9,13,17,21,25,29,33");
//        map.put("PROJECTS_ID", "13,78,267,18,28,33,55,99");
//        map.put("SIGNAL_NAME", "a");
//        map.put("ASSESSMENTNAME", "a");
//
//        List<String> entryList = new ArrayList<>();
//        StringBuilder sb = new StringBuilder();
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            String value = entry.getKey() + '=' + entry.getValue();
//            entryList.add(value);
//            sb.append(value);
//        }
//        String[] entries = entryList.toArray(new String[entryList.size()]);
//        String mapAsString = sb.toString();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("AREA_DS_ID", "1,5,9,13,17,21,25,29,33");
        map.put("PROJECTS_ID", "13,78,267,18,28,33,55,99");
        map.put("SIGNAL_NAME", "a");
        map.put("ASSESSMENTNAME", "a");

        List<String> entryList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String value = entry.getKey() + '=' + entry.getValue();
            entryList.add(value);
            sb.append(value);
        }
        String[] entries = entryList.toArray(new String[entryList.size()]);
        String mapAsString = sb.toString();
    }
}
