package steve.step_definitions;

//import org.openqa.selenium.remote.server.log.StdOutHandler;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.io.*;

import static java.util.Arrays.asList;

public class Arrays1 {

            public static void main(String[] args)
            {
//                @When("^enter values for claim creation:$")
//                public void enter_values_for_filingTypes_selection(List<CaseManagementModel> eFileCaseModelList) throws Throwable {

//                List<Map<String, String>> table = featuresAccessories.asMaps(String.class, String.class);
////        table.get(1).get("Option") Colour
////        table.get(0).get("Option") Screen
//
//                for (Map<String, String> row : table) {
//                    for (Map.Entry<String, String> col : row.entrySet()) {
//                        System.out.println(col.getValue());
//                        System.out.println(col.getKey());
//                    }
//                }
                HashMap<String, String> capitalCities = new HashMap<String, String>();
                capitalCities.put("England", "London");capitalCities.put("Germany", "Berlin");capitalCities.put("Norway", "Oslo");
                capitalCities.put("USA", "Washington DC");

                System.out.println(capitalCities.get("England"));
                System.out.println(capitalCities.values().toArray()[0]);
                System.out.println(capitalCities.keySet().toArray()[0]);
//                capitalCities.values().toArray()[2] London

                String[][] x = new String[9][6];
                x[1][1] = "www";

                String[] x1 = new String[]{"qwe", "asd", "zxce"};
                String[] x2 = Arrays.stream(x1).filter(q->q.contains("e")).toArray( String[]::new );
                List<String> x3 = Arrays.stream(x1).filter(q->q.contains("e")).collect(Collectors.toList());
                x3.add("eee");

                int[] x4 = {1, 3, 2, 4};
                List<Integer> x5 = Arrays.stream(x4).boxed().collect(Collectors.toList());
                x5.add(6);
                Collections.reverse(x5);
                Collections.sort(x5);

                List<String> x1List = Arrays.asList(x1);
                // cannot do below
                //                x1List.add("eee");

//                List<String> o = Arrays.asList(new String[]{"qwe", "asd", "zxc"});
                String o1 = "qwe,asd,zxc";
//                String[] o = o1.split(",");
                List<String> o = asList(o1.split(","));
//                o.add("one");
//                o.add("two");

                ArrayList<String> a = new ArrayList<>();
                // you can even dynamically add arrays to a list
                a.add("werwqa");
                a.add("werkkk");
                a.add(new String("wqe"));
                String Str = "gdfgdf";
                a.add(Str);

                Collections.sort(a);
                Collections.reverse(a);
                LinkedHashMap<Integer, String> hm1 = new LinkedHashMap<>();
                x(a, hm1);
                x(a, hm1);

                ArrayList<String[]> ls = new ArrayList<>();
                ls.add(new String[]{"wer", "ws", "wqa"});
                String[] z1 = {"dfg", "dfg", "dfgdfg", "dfg", "dfgdfg", "dfg", "dfgdfg", "dfg", "dfgdfg", "dfg", "dfgdfg", "dfg", "dfgdfg", "dfg", "dfgdfg"};
                ls.add(z1);
                ls.add(new String[]{"wer", "ws", "wqa", "kkk"});

                Collections.reverse(ls);
//                Collections.sort(ls);

                LinkedHashMap<Integer, String[]> hm3 = new LinkedHashMap<>();
                hmx(ls, hm3);
                // you cannot dynamically add values to an array
                String[][] d2a = {{"wer", "ws", "wqa"}, {"asdf", "asdf", "asdgf"}};

                for(String[] sa1 : d2a) {
                    for( String sa2: sa1) {
                        System.out.println(sa2);}}

                for(int i=0; i<d2a.length; i++) {
                     for( int j=0; j<d2a[i].length; j++) {
                        System.out.println(d2a[i][j]);
                     }
                }

                HashMap<String, String> hm2 = new HashMap<>();
                hm2.put("qwr1", "safds");
                hm2.put("qwr2", "safds");
                hm2.put("qwr3", "safds");

                StringBuilder sb = new StringBuilder();
                List<String[]> line = new ArrayList<>();

                String[] row = {};
                sb.setLength(0);
                String prefix = "";
                Collection<String> coll = hm2.keySet();
                for (String colName : coll) {
                     sb.append(prefix);
                     sb.append(hm2.get(colName));
                     prefix = ",";
                }

                row = sb.toString().split(",");
                line.add(row);
                String csvName = "C:\\Projects\\SteveTests1\\"+"csvname"+".csv";
                try{
                    CSVWriter writer = new CSVWriter(new FileWriter(csvName, false));
                    writer.writeAll(line); //And the second argument is boolean which represents whether you want to write header columns (table column names) to file or not.
                    writer.close();
                    CSVReader reader = new CSVReader(new FileReader(csvName + ".csv"));
                } catch(Exception e) {}

                int countExpected = countCSVrows(csvName);

                char[] z = String.valueOf(1234).toCharArray();
                Arrays.sort(z);
                String y="";

                for(char c1 : z) {
                    System.out.println(c1);
                }
                for(int i=z.length; i>0; i--) {
                    y += z[i-1];
                }
                System.out.println(y);


                String T = "SSMLS";
                z = T.toCharArray();
                Arrays.sort(z);
                y="";
                for(int i=z.length; i>0; i--) {
                     y += z[i-1];
                }
                System.out.println(y);

                String text = "addLettersFor:9-loops,padLeftFor:99-loops,padRightFor:999-loops,paddingStyle:left";
                String[] textArray = text.split(",");
                textArray[0].indexOf("a"); // 0
                textArray[0].substring(3,9); // ‘Letter’
                textArray[0].substring(3); //‘LettersFor:9-loops’
                textArray[0].contains("Letter"); //rue

                List<String> textList = asList(text.split("-"));
                textList.get(0).indexOf("a");
                textList.get(0).substring(3,9); //‘Letter’
                textList.get(0).substring(3); //‘LettersFor:9-loops’
                textList.get(0).contains("Letter"); //rue

                Collections.sort(textList);
                // not allowed as not a List
                //Collections.sort(textArray);

                String calculatedDate = "20-02-2019";
                String[] dateArray = calculatedDate.split("-");

                for(String w : dateArray) {
                    System.out.println(w);
                }

                char[] letters = {'a', 'b', 'c', 'd', 'e', 'f'};
                // Below gives compiler error - are incompatible types
                // List<String> ad = Arrays.asList(letters);


                for(int i=0; i<=letters.length-1; i++) {
                    System.out.println(letters[i]);
                }

                for(char q : letters) {
                    System.out.println(q);
                }

                int[] data = {1,2,3,4,5,6,7,8,9,10};

// To boxed array
                Integer[] what = Arrays.stream( data ).boxed().toArray( Integer[]::new );
                Integer[] ever = IntStream.of( data ).boxed().toArray( Integer[]::new );

// To boxed list
                List<Integer> you  = Arrays.stream( data ).boxed().collect( Collectors.toList() );
                List<Integer> like = IntStream.of( data ).boxed().collect( Collectors.toList() );

                int[] num1 = { 2, 4, 7, 5, 9 };
                // Below gives compiler error - are incompatible types
                //List<Integer> ad = Arrays.asList(num1);
                Integer[] num = new Integer[num1.length];
                int p = 0;
                for (int value : num1) {
                    num[p++] = Integer.valueOf(value);
                }

                List<Integer> numList = new ArrayList<Integer>();
                // using Collections.min() to find minimum element
                // using only 1 line.
                int min = Collections.min(asList(num));
                int max = Collections.max(asList(num));

                Collections.sort(asList(num));
                Collections.reverse(asList(num));

                for(int i : num1) {
//                for(i=0; i<= num.length-1 ; i++) {
                    System.out.println(i);
                    numList.add(i);
                    System.out.println(numList.size());
                    System.out.println(numList.contains('9'));
                    System.out.println(numList.indexOf('9'));
                }

                // printing minimum and maximum numbers
                System.out.println("Minimum number of array is : " + min);
                System.out.println("Maximum number of array is : " + max);
            }
            public static List x(List<String> y, HashMap<Integer, String> hm) {
                int i = 0;

                for(String z : y) {
                    System.out.println(z);
                    hm.put(i++, z);
                }

            return y;
            }

    public static List hmx(List<String[]> y, HashMap<Integer, String[]> hm) {
        int i = 0;

        for(String[] z : y) {
            hm.put(i++, z);
        }

        return y;
    }

    public static int countCSVrows(String csvName) {
        String input;
        int count = 0;
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(csvName));

            while ((input = bufferedReader.readLine()) != null) {
                count++;
            }

            bufferedReader.close();

        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit ( 1 );
        }

        return count;
    }


}