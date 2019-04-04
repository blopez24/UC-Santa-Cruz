class PrintSolutionSnippet {

  private static int board_size;
  // A cool method that actually prints a chessboard with chesspieces onto the console. Uses some UNICODE awesomeness
  // Input: 2D character array isFilled, where isFilled[i][j] is K, Q, R, B or N for black king, queen, rook, bishop or knight chesspieces and
  // k, q, r, b or n if there are white king, queen, rook, bishop or knight respectively at row i, col j.
  // Assumes that isFilled is board_size X board_size.
  // Output: void, basically prints the chessboard with the chesspiece onto console
  private static void printSolution(char[][] isFilled){
      String line = "  "; // starting strings to be printed. line is the dividing horizontal strip along the board
      String col_nums = "  ", col_str = ""; // these strings are for printing the column numbers below

      for (int i=1; i < board_size+1; i++) { // loop over all the columns to create the line and col_nums strings
          line = line + "+--"; // each iteratin add "+--" to line
          if (i < 10) // have to break into cases, since i < 10 is one symbol, but i >= 10 is two
              col_str = " " + Integer.toString(i) + " ";  // add string i to col_str with spacing
          else
              col_str = " " + Integer.toString(i);  // add string i to col_str with less spacing, since i >= 10
          col_nums = col_nums + col_str ; // append col_str to col_nums
      }
      line = line + "+";  // complete line string

      for (int i=board_size; i > 0; i--) { // loop over all the rows in decreasing order. each iteration will print a col
          System.out.println(line); // start by printing a line
          String pieces, background, chesspiece; // pieces string will actually put the chesspiece symbols into string
          if (i < 10)   // pieces begins with col number, again break into cases if i has 1 vs 2 symbols
              pieces = " "+Integer.toString(i);
          else
              pieces = Integer.toString(i);

          for (int j=1; j < board_size+1; j++) { // now loop over cols to create individual squares
              if ((i+j)%2 == 1) // place alternating black or red background for squares
                  background = "\u001B[40m";  // ANSI escape code for black
              else
                  background = "\u001B[41m";  // ANSI escape code for red

              switch(isFilled[i][j]) {
                  case 'k':
                      chesspiece = "\u2654 "; // put UNICODE symbol for white king
                      break;
                  case 'q':
                      chesspiece = "\u2655 "; // put UNICODE symbol for white queen
                      break;
                  case 'r':
                      chesspiece = "\u2656 "; // put UNICODE symbol for white rook
                      break;
                  case 'b':
                      chesspiece = "\u2657 "; // put UNICODE symbol for white bishop
                      break;
                  case 'n':
                      chesspiece = "\u2658 "; // put UNICODE symbol for white knight
                      break;
                  case 'p':
                      chesspiece = "\u2659 "; // put UNICODE symbol for white pawn
                      break;
                 case 'K':
                      chesspiece = "\u265A "; // put UNICODE symbol for black king
                      break;
                  case 'Q':
                      chesspiece = "\u265B "; // put UNICODE symbol for black queen
                      break;
                  case 'R':
                      chesspiece = "\u265C "; // put UNICODE symbol for black rook
                      break;
                  case 'B':
                      chesspiece = "\u265D "; // put UNICODE symbol for black bishop
                      break;
                  case 'N':
                      chesspiece = "\u265E "; // put UNICODE symbol for black knight
                      break;
                  case 'P':
                      chesspiece = "\u265F "; // put UNICODE symbol for black pawn
                      break;
                  default:
                      chesspiece = "  ";
              }

              pieces = pieces + "|"+background+chesspiece+"\u001B[0m"; // put UNICODE symbol for queen with a line. also set background, and then apply ANSI reset code
          }
          System.out.println(pieces+"|");
      }

      System.out.println(line); // print out the final line
      System.out.println(col_nums); // print out the columns below
  }

  // Method to initialize isFilled array with '-'
  // Input: 2D char array and its integer size
  // Output: Updated char array
  public static char[][] initializeBoard(char[][] isFilled, int size) {
    for(int i = 0; i < size+1; i++) {
      for(int j = 0; j < size+1; j++) {
        isFilled[i][j] = '-';
      }
    }
    return isFilled;
  }

  public static void main(String[] args) {

    // If your solution placed the chesspieces as shown in isFilledMatrix1 and isFilledMatrix2, the printSolution
    // will print out the board.
    // Example 1:
    System.out.println("Example 1:");
    board_size = 4;
    char[][] isFilledMatrix1 = new char[board_size+1][board_size+1];
    isFilledMatrix1 = initializeBoard(isFilledMatrix1, board_size);
    isFilledMatrix1[4][1] = 'K'; // Place black king in row 4, col 1
    isFilledMatrix1[3][3] = 'b'; // Place white bishop in row 3, col 3
    isFilledMatrix1[3][4] = 'k'; // Place white king in row 3, col 4
    isFilledMatrix1[1][2] = 'r'; // Place white rook in row 1, col 2
    isFilledMatrix1[1][4] = 'B'; // Place black bishop in row 1, col 4
    printSolution(isFilledMatrix1);

    // Example 2:
    System.out.println("Example 2:");
    board_size = 8;
    char[][] isFilledMatrix2 = new char[board_size+1][board_size+1];
    isFilledMatrix2 = initializeBoard(isFilledMatrix2, board_size);
    isFilledMatrix2[1][1] = 'b'; // Place white bishop in row 1, col 1
    isFilledMatrix2[4][8] = 'K'; // Place black king in row 4, col 8
    isFilledMatrix2[3][4] = 'q'; // Place white queen in row 3, col 4
    isFilledMatrix2[4][4] = 'k'; // Place white king in row 4, col 4
    isFilledMatrix2[7][7] = 'N'; // Place black knight in row 7, col 7
    isFilledMatrix2[2][8] = 'r'; // Place white rook in row 2, col 8
    isFilledMatrix2[8][8] = 'r'; // Place white rook in row 8, col 8
    printSolution(isFilledMatrix2);
  }

}