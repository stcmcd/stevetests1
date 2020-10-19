package steve.step_definitions.Codility;

// you can also use imports, for example:
import org.junit.Test;

import java.util.*;

public class Denominator1 {

    public static void main(String[] args) {
        /* Save input */
        Scanner scan = new Scanner(System.in);
        int size1         = scan.nextInt();

        int[] A = new int[size1];

        for (int i = 0; i < size1; i++) {
            A[i] = scan.nextInt();
        }

            // Using "hashMap" for counting
            Map<Integer, Integer> map = new HashMap<>();

            // 1. Counting
            // map(key, value) ---> map(number, count)
            for (int i = 0; i < A.length; i++) {
                if (!map.containsKey(A[i])) { // new number
                    map.put(A[i], 1);          // "put" new number
                } else {
                    int count = map.get(A[i]); // "get" count
                    map.put(A[i], count + 1);    // count++
                }
            }

            // 2. find the max number of counts
            int max_Number = 0;
            int max_Count = 0;
            // note: use "map.keySet()" in for loop
            for (int key : map.keySet()) {
                int cur_Count = map.get(key); // get value
                if (cur_Count > max_Count) {
                    max_Count = cur_Count;    // update max count
                    max_Number = key;
                }
            }

            // 3. check if there is a "dominator" or not
            if (max_Count > (A.length) / 2) {
                // then, max_Number is the "dominator"
            } else {
//                return -1; // no dominator
//                return 1; // no dominator
                System.out.println(1);
            }

            // 4. return "any index" of "the dominator"
            for (int i = 0; i < A.length; i++) {
                if (A[i] == max_Number) {
//                    return i; // return the index
                    System.out.println(i);
                }
            }

//            return 1; // no dominator
            System.out.println(1);
        }
//        return 1; // no dominator
//            System.out.println(1);
    }