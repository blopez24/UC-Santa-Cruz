import java.util.Scanner;

public class ConnectFour
{
	static int rows;
	static int columns;
	static int[][] board;
	static int[] counter;
	
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		int m = s.nextInt();
		int n = s.nextInt();
		
		rows = m;
		columns = n;
		board = new int[rows][columns];
		counter = new int[columns];
		createEmptyTable(board);
		
		boolean noWinner = true;
		int turnTracker = 0;
		int choosenColumn;
		
		while(noWinner)
		{
			choosenColumn = s.nextInt();
			userInput(board, choosenColumn, counter[choosenColumn], turnTracker);
			if(checkPlayer(turnTracker))
			{
				noWinner = false;
				s.close();
				break;
			}
			turnTracker++;
		}
		s.close();
	}
	
	private static void userInput(int[][] b, int index, int row, int tt)
	{
		int r = rows - 1 - row;				
		counter[index]++;
		
		if(player(tt) == 0)
			b[r][index] = 0;
		else
			b[r][index] = 1;
	}
	
	private static int player(int tt)
	{
		if(tt % 2 == 0)
			return 0;
		else
			return 1;
	}
	
	private static boolean checkPlayer(int tt)
	{
		int i = player(tt);
		boolean gameOver = false;
		
		if(checkVertical(i)	||	checkHorizontal(i)	||	checkFowardSlash(i)	||	checkBackwardSlash(i) || itIsATie(board))
			gameOver = true;
		
		return gameOver;
	}
	
	private static boolean checkFowardSlash(int i) 
	{
		boolean winner = false;
		int checkAgainst;
		int counter = 0;
		
		if(player(i) == 0)
			checkAgainst  = 0;
		else
			checkAgainst = 1;
		
		while(!winner && enoughToCheckDiagonals())
		{
			for(int r = 3; r < rows; r++)
			{
				for(int c = 0; c < columns -3; c++)
				{
					for(int j = 0; j < 4; j++)
					{
						if(board[r-j][c+j] == checkAgainst)
							counter++;
						else
							counter = 0;
					}
					if(counter >= 4 && checkAgainst == 0)
					{
						System.out.println("Lise Wins!");
						winner = true;
					}
					if(counter >= 4 && checkAgainst == 1)
					{
						System.out.println("John Wins!");
						winner = true;
					}
				}
				counter = 0;
			}
			break;
		}
		return winner;
	}

	private static boolean checkBackwardSlash(int i) 
	{
		boolean winner = false;
		int checkAgainst;
		int counter = 0;
		
		if(player(i) == 0)
			checkAgainst  = 0;
		else
			checkAgainst = 1;
		
		while(!winner && enoughToCheckDiagonals())
		{
			for(int r = 3; r < rows; r++)
			{
				for(int c = 3; c < columns; c++)
				{
					for(int j = 0; j < 4; j++)
					{
						if(board[r-j][c-j] == checkAgainst)
							counter++;
						else
							counter = 0;
					}
					if(counter >= 4 && checkAgainst == 0)
					{
						System.out.println("Lise Wins!");
						winner = true;
					}
					if(counter >= 4 && checkAgainst == 1)
					{
						System.out.println("John Wins!");
						winner = true;
					}
				}
				counter = 0;
			}
			break;
		}
		return winner;
	}

	private static boolean checkHorizontal(int i) 
	{
		boolean winner = false;
		int checkAgainst;
		int counter = 0;
		
		if(player(i) == 0)
			checkAgainst  = 0;
		else
			checkAgainst = 1;
		
		while(!winner)
		{
			for(int r = 0; r < rows; r++)
			{
				for(int c = 0; c < columns; c++)
				{
					if(board[r][c] == checkAgainst)
						counter++;
					else
						counter = 0;
					
					if(counter >= 4 && checkAgainst == 0)
					{
						System.out.println("Lise Wins!");
						winner = true;
					}
					if(counter >= 4 && checkAgainst == 1)
					{
						System.out.println("John Wins!");
						winner = true;
					}
				}
				counter = 0;
			}
			break;
		}
		return winner;
	}

	private static boolean checkVertical(int i) 
	{	
		boolean winner = false;
		int checkAgainst;
		int counter = 0;
		
		if(player(i) == 0)
			checkAgainst  = 0;
		else
			checkAgainst = 1;
		
		while(!winner)
		{
			for(int c = 0; c < columns; c++)
			{
				for(int r = 0; r < rows; r++)
				{
					if(board[r][c] == checkAgainst)
						counter++;
					else
						counter = 0;
					
					if(counter >= 4 && checkAgainst == 0)
					{
						System.out.println("Lise Wins!");
						winner = true;
					}
					if(counter >= 4 && checkAgainst == 1)
					{
						System.out.println("John Wins!");
						winner = true;
					}
				}
				counter = 0;
			}
			break;
		}
		return winner;
	}
	
	private static boolean enoughToCheckDiagonals()
	{
		if( rows >= 4 && columns >= 4)
			return true;
		else
			return false;
	}
	
	private static boolean itIsATie(int[][] b)
	{	
		for(int r = 0; r < rows; r++)
		{
			for(int c = 0; c < columns; c++)
			{
				if(board[r][c] == -1)
					return false;
			}
		}
		
		System.out.println("It's a tie.");
		return true;
	}
	
	private static void createEmptyTable(int[][] b) 
	{
		for(int r = 0; r < b.length; r++)
		{
			for(int c = 0; c < b[0].length; c++)
			{
				b[r][c] = -1;
			}
		}
	}
}