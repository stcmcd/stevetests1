package steve.step_definitions;

import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

public class CollectionsExamples {

        public static void main1(){
            List<String> list = new ArrayList<String>();
            list.add("C");
            list.add("Core Java");
            list.add("Advance Java");
            System.out.println("Initial collection value:"+list);
            Collections.addAll(list, "Servlet","JSP");
            System.out.println("After adding elements collection value:"+list);
            String[] strArr = {"C#", ".Net"};
            Collections.addAll(list, strArr);
            System.out.println("After adding array collection value:"+list);
        }

    public static void main2(){
            List<Integer> list = new ArrayList<Integer>();
            list.add(46);
            list.add(67);
            list.add(24);
            list.add(16);
            list.add(8);
            list.add(12);
            System.out.println("Value of maximum element from the collection: "+Collections.max(list));
        }

    public static void main3(){
            List<Integer> list = new ArrayList<Integer>();
            list.add(46);
            list.add(67);
            list.add(24);
            list.add(16);
            list.add(8);
            list.add(12);
            System.out.println("Value of minimum element from the collection: "+Collections.min(list));
        }

//    public static void main4() {
//        int[] myIntArray = new int[3];
//        int[] myIntArray = {1, 2, 3};
//        int[] myIntArray = new int[]{1, 2, 3};
//
//// Since Java 8. Doc of IntStream: https://docs.oracle.com/javase/8/docs/api/java/util/stream/IntStream.html
//
//        int[] myIntArray = IntStream.range(0, 100).toArray(); // From 0 to 99
//        int[] myIntArray = IntStream.rangeClosed(0, 100).toArray(); // From 0 to 100
//        int[] myIntArray = IntStream.of(12, 25, 36, 85, 28, 96, 47).toArray(); // The order is preserved.
//        int[] myIntArray = IntStream.of(12, 25, 36, 85, 28, 96, 47).sorted().toArray(); // Sort
//
//        String[] myStringArray;
//        myStringArray = new String[]{"a", "b", "c"};
//    }


@Test
public void main() {
    main1();
    main2();
    main3();

}

}
