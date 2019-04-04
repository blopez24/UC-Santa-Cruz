package winter18.assignment3;

import java.util.Scanner;

public class PathTest {

    /*
     * Test 1
     * Should have the following at the bottom of the console
     * false
     * 2
     * 1.4142135623730951
     * 1.4142135623730951
     */
    private static String test1 = "1 0 0 1 1 1 2 5 5 2 3 3 islonger numpoints getlength getdistance q";

    /*
     * Test 2
     * Should have the following at the bottom of the console
     * 4.0
     * 5
     * 3.1622776601683795
     * Please enter which point you would like to get from the first path
     * (2,4)
     * Please enter 2 integers to add as a point to path two
     * Please enter 2 integers to add as a point to path two
     * true
     * 3.1622776601683795
     * 0.0
     * 9.886349517372675
     */
    private static String test2 = "1 1 1 1 1 2 1 1 3 1 2 3 1 2 4 getlength numpoints getdistance getpoint 4 2 0 0 2 1 1 islonger getdistance addpath getdistance getlength q";

    /*
     * Test 3
     * Should have the following at the bottom of the console
     * false
     * 2
     * 4
     */
    private static String test3 = "1 0 0 1 5 5 2 0 0 2 6 6 islonger numpoints addpath numpoints q";

    /*
     * Test 4
     * Should have the following at the bottom of the console
     * Please enter which point you would like to remove from the first path
     * true
     * Please enter which point you would like to get from the first path
     * (1,1)
     * Please enter which point you would like to remove from the first path
     * false
     */
    private static String test4 = "1 1 1 2 2 2 1 2 2 removepoint 1 getpoint 0 removepoint 9 q";

    /*
     * Test 5
     * Should have the following at the bottom of the console
     * 2.0
     * 2.0
     * 2
     * true
     * 3.0
     * 7.0
     * 4
     */
    private static String test5 = "1 0 0 1 0 2 2 0 0 2 0 3 islonger getdistance getlength numpoints addpath islonger getdistance getlength numpoints q";

    /*
     * Test 6
     * Should have the following at the bottom of the console
     * Please enter which point you would like to get from the first path
     * (0,0)
     * Please enter which point you would like to get from the first path
     * (0,2)
     * Please enter which point you would like to get from the first path
     * (0,3)
     */
    private static String test6 = "1 0 0 1 0 2 1 0 3 getpoint  0 getpoint 1 getpoint 2 q";

    public static void main(String[] args) {
        // To run one of the above tests You can replace System.in with the test variable ie:
        // Scanner scan = new Scanner(test1);
        // Be sure to only have one scanner object at a time

        // Otherwise you can still use the console to enter data
        Scanner scan = new Scanner(test2);

        Path p1 = new Path();
        Path p2 = new Path();
        String command = "";
        while (command != "q") {
          command = scan.next();
          switch(command) {
            case "1":
              System.out.println("Please enter 2 integers to add as a point to path one");
              p1.addPoint(scan.nextInt(), scan.nextInt());
              break;
            case "2":
              System.out.println("Please enter 2 integers to add as a point to path two");
              p2.addPoint(scan.nextInt(), scan.nextInt());
              break;
            case "getpoint":
              System.out.println("Please enter which point you would like to get from the first path");
              System.out.println(p1.getPoint(scan.nextInt()));
              break;
            case "numpoints":
              System.out.println(p1.numOfPoints());
              break;
            case "removepoint":
              System.out.println("Please enter which point you would like to remove from the first path");
              System.out.println(p1.removePoint(scan.nextInt()));
              break;
            case "addpath":
              p1.addPath(p2);
              break;
            case "getlength":
              System.out.println(p1.getLength());
              break;
            case "getdistance":
              System.out.println(p1.getDistance());
              break;
            case "islonger":
              System.out.println(p1.isLonger(p2));
              break;
            case "q":
              System.exit(0);
              break;
          }
        }
      }
}
