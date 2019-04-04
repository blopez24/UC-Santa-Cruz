package winter18.assignment3;

import java.util.Random;
import java.util.Scanner;

public class BingoTest 
{
    // Test 1
    // Should say "Player0 Player1" at the bottom
    private static String test1 = "2 1 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 1 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 5 1 2 3 4 5";

    // Test 2
    // Should say "Player0" at the bottom
    private static String test2 = "2 1 10 30 45 66 82 3 25 11 63 78 22 4 13 46 90 5 23 12 6 85 1 88 67 2 44 1 11 31 46 67 83 4 26 12 64 79 23 5 14 47 90 6 24 13 7 86 2 89 68 3 45 9 10 11 30 46 45 66 47 90 82";

    // Test 3
    // Should say "Player2 Player3" at the bottom
    private static String test3 = "4 1 10 30 45 66 82 3 25 11 63 78 22 4 13 46 90 5 23 12 6 85 1 88 67 2 44 1 11 31 46 67 83 4 26 12 64 79 23 5 14 47 90 6 24 13 7 86 2 89 68 3 45 1 1 3 5 7 9 11 13 15 17 19 21 23 25 27 29 31 33 35 37 39 41 43 45 47 49 1 2 4 6 8 10 12 14 16 19 20 22 24 26 28 30 32 34 36 38 40 42 44 46 48 50 16 11 21 22 32 9 10 17 25 44 13 12 14 20 15 16 19";

    // Test 4
    // Should say "Player0 Player3" at the bottom
    private static String test4 = "4 1 10 30 45 66 82 3 25 11 63 78 22 4 13 46 90 5 23 12 6 85 49 88 67 2 44 1 11 31 46 67 83 4 26 12 64 79 23 5 14 47 90 6 24 13 7 86 2 88 68 3 45 1 1 3 5 7 9 11 13 15 17 19 21 23 25 27 29 31 33 35 37 39 41 43 45 47 49 1 2 4 6 8 10 12 14 16 19 20 22 24 26 28 30 32 34 36 38 40 42 44 46 48 49 11 44 41 88 42 48 46 43 67 47 2 49";

    // Test 5
    // Should say "Player0 Player3" at the bottom
    private static String test5 = "4 2 10 30 45 66 82 3 25 11 63 78 22 4 13 46 90 5 23 12 6 85 49 88 67 2 44 3 6 9 12 15 82 85 5 11 30 1 7 15 10 4 2 8 14 77 38 60 65 17 29 40 1 11 31 46 67 83 4 26 12 64 79 23 5 14 47 90 6 24 13 7 86 2 88 68 3 45 1 1 3 5 7 9 11 13 15 17 19 21 23 25 27 29 31 33 35 37 39 41 43 45 47 49 1 2 4 6 8 10 12 14 16 19 20 22 24 26 28 30 32 34 36 38 40 42 44 46 48 49 11 44 41 88 42 48 46 43 67 47 2 49";

    // Test 6
    // Should say "Player0" at the bottom
    private static String test6 = "4 2 10 30 45 66 82 3 25 11 63 78 22 4 13 46 90 5 23 12 6 85 49 88 67 2 44 3 6 9 12 15 82 85 5 11 30 1 7 15 10 4 2 8 14 77 38 60 65 17 29 40 1 11 31 46 67 83 4 26 12 64 79 23 5 14 47 90 6 24 13 7 86 2 88 68 3 45 1 1 3 5 7 9 11 13 15 17 19 21 23 25 27 29 31 33 35 37 39 41 43 45 47 49 1 2 4 6 8 10 12 14 16 19 20 22 24 26 28 30 32 34 36 38 40 42 44 46 48 49 9 22 1 7 13 46 15 10 90 4";

    // Test 7
    // Should say "Player2" at the bottom
    private static String test7 = "3 2 10 30 45 66 82 3 25 11 63 78 22 4 13 46 90 5 23 12 6 85 49 88 67 2 44 3 6 9 12 15 82 85 5 11 30 1 7 15 10 4 2 8 14 77 38 60 65 17 29 40 2 11 31 46 67 83 4 26 12 64 79 23 5 14 47 90 6 24 13 7 86 2 88 68 3 45 1 3 5 7 9 11 13 15 17 19 21 23 25 27 29 31 33 35 37 39 41 43 44 47 49 1 2 4 6 8 10 12 14 16 19 20 22 24 26 28 30 32 34 36 38 40 42 44 46 48 49 5 12 14 16 20 19";

    public static void main(String[] args){
      // To run one of the above tests You can replace System.in with the test variable ie:
      //Scanner scan = new Scanner(test1);
      // Be sure to only have one scanner object at a time

      // Otherwise you can still use the console to enter data
      Scanner scan = new Scanner(test3);

      System.out.println("Enter how many players there are");
      Player[] players = new Player[scan.nextInt()];

      for(int p = 0; p < players.length; p++) 
      {
        System.out.println("How many cards does player " + p + " have?");
        Card[] cards = new Card[scan.nextInt()];
        
        for(int c = 0; c < cards.length; c++) 
        {
          System.out.println("Please enter 25 numbers for card " + c + " of player " + p);
          
          int[][] numbers = new int[5][5];
          for(int i = 0; i < numbers.length; i++) 
          {
             for(int j = 0; j < numbers[i].length; j++) 
             {
            	 numbers[i][j] = scan.nextInt();
             }
           }
          cards[c] = new Card(numbers);
        }
        players[p] = new Player("Player" + p, cards);
      }

      Bingo bingo = new Bingo(players);
      Random random = new Random();
      
      System.out.println("Please enter how many numbers will be called");
      int[] calledNums = new int[scan.nextInt()];
      System.out.println("Please enter " + calledNums.length + " numbers to be called in order");
      
      for(int i = 0; i < calledNums.length; i++) 
      {
        calledNums[i] = scan.nextInt();
      }
      
      String winners = "";
      int idx = 0;
      while(winners.equals(""))
      {
        winners = bingo.play(calledNums[idx]);
        idx++;
      }
      System.out.println(winners);
    }
}