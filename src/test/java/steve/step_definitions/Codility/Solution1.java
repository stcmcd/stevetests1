package steve.step_definitions.Codility;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//  Time Complexity: O(n)
// Space Complexity: O(1) by doing an "in place" rotation
public class Solution1 {
    public static void main(String[] args) {
        /* Save input */
        Scanner scan = new Scanner(System.in);
        int size         = scan.nextInt();
        int numRotations = scan.nextInt();
//        int size         = args.length();
//        int numRotations = args.length();
        int array[] = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scan.nextInt();

// for mixes of doubles and strings haveto do a scan.next()
//            array[i] = args[i];
        }

//        Scanner scan = new Scanner(System.in);
//        int i = scan.nextInt();
//        Double d = scan.nextDouble();
//        String s = scan.next();
//        System.out.println("String: " + s + " to HackerRank's java tutorials:");
//        System.out.println("Double: " + d);
//        System.out.println("Int: " + i);

        scan.close();

        /* Rotate array (in place) using 3 reverse operations */

        numRotations %= size; // to account for numRotations > size
        int rotationSpot = size - 1 - numRotations;
        reverse(array, 0, size - 1);
        reverse(array, 0, rotationSpot);
        reverse(array, rotationSpot + 1, size - 1);

        /* Print rotated array */
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
    }

    /* Reverses array from "start" to "end" inclusive */
    private static void reverse(int[] array, int start, int end) {
        if (array == null || start < 0 || start >= array.length || end < 0 || end >= array.length) {
            return;
        }
        while (start < end) {
            swap(array, start++, end--);
        }
    }

    private static void swap(int [] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}