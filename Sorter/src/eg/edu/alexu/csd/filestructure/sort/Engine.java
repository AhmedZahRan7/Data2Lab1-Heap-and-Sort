package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Engine {
    public static void main(String[] args) {
        while (true) {
            ArrayList<Integer> list1 = new ArrayList() {};
            ArrayList<Integer> list2 = new ArrayList() {};
            ArrayList<Integer> list3 = new ArrayList() {};

            int n;
            Scanner scanner = new Scanner(System.in);
            n = scanner.nextInt();
            Random random = new Random();
            Sort<Integer> sort = new Sort();
            long startTime, endTime ;

            for (int i = 0; i < n; i++) {
                int temp = random.nextInt();
                list1.add(temp);
                list2.add(temp);
                list3.add(temp);
            }

            startTime = System.currentTimeMillis();
            sort.sortFast(list1);
            endTime = System.currentTimeMillis();
            System.out.println("For data with size " + n + " fast sort took " + (endTime - startTime) + " ms");


            startTime = System.currentTimeMillis();
            sort.heapSort(list2);
            endTime = System.currentTimeMillis();
            System.out.println("For data with size " + n + " heap sort took " + (endTime - startTime) + " ms");


//            startTime = System.currentTimeMillis();
//            sort.sortSlow(list3);
//            endTime = System.currentTimeMillis();
//            System.out.println("For data with size " + n + " slow sort took " + (endTime - startTime) + " ms");
        }
    }


}
