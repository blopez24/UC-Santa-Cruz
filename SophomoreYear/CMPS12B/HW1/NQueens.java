//-------------------------------------------------------------------------
// Bryan Lopez
// blopez24 1612626
//-------------------------------------------------------------------------
// NQueens.java
// Solve the n queens problems. You have to place n queens on an n x n 
// chessboard such that no two attack each other
//-------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

public class NQueens
{
	private static int boardSize;
	private static Queen[] solution;
	
	public static void main(String[] args) throws IOException
	{
		if(args.length < 2)
		{
			System.out.println("Usage: java -jar NQueens.jar <input file> <output file>");
			System.exit(1);
		}
		
		Scanner file = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new FileWriter(args[1]));
		
		while(file.hasNextLine())
		{
			String line = file.nextLine();
			Scanner input = new Scanner(line);
			
			int column;
			int row;
			
			while(input.hasNext())
			{
				boardSize = input.nextInt();
				column = input.nextInt();
				row = input.nextInt();
				
				solution = new Queen[boardSize];
				solution[0] = new Queen(column, row);;
				
				if(boardSize <= 3)
				{
					out.println("No solution");
					break;
				}
				else
				{
					if(nQueens(boardSize-1))
					{
						for(int col = 1; col < solution.length + 1; col++)
						{
							int queenRow = 0;
							for(int index = 0; index < solution.length; index++)
							{
								if(solution[index].col == col)
									queenRow = solution[index].row;
							}
							
							out.print(col + " " + queenRow + " ");
						}
						out.println();
					}
					else
					{
						out.println("No solution");
					}
				}
			}
			input.close();
		}
		file.close();
		out.close();
	}
	
	private static boolean nQueens(int num_left) 
	{
		int free = -1;
		int queensPlaced= boardSize - num_left;
		
		if(num_left == 0)
			return true;
		
		boolean available = false;
		for(int i = 1; i < boardSize + 1; i++)
		{
			available = true;
			for(int j = 0; j < queensPlaced; j++)
			{
				if(solution[j].col == i)
				{
					available = false;
					break;
				}
			}
			if(available)
			{
				free = i;
				break;
			}
		}
		
		for(int row = 1; row < boardSize + 1; row++)
		{
			Queen next = new Queen(free, row);
			boolean isPossible = true;
			
			for(int i = 0; i < queensPlaced; i++)
			{
				if(solution[i].isAttacking(next))
				{
					isPossible = false;
					break;
				}
			}
			if(isPossible)
			{
				solution[queensPlaced] = next;
				if(nQueens(num_left - 1))
					return true;
			}
		}
		
		return false;
	}
}

class Queen
{
	int col, row;
	
	public Queen()
	{
		this.col = -1;
		this.row = -1;
	}
	
	public Queen(int c, int r)
	{
		this.col = c;
		this.row = r;
	}
	
	boolean isAttacking(Queen q)
	{
		int diff1 = Math.abs(this.col - q.col);
		int diff2 = Math.abs(this.row - q.row);
		
		if(q.col == this.col || q.row == this.row)
			return true;
		else if(diff1 == diff2)
			return true;
		
		return false;
	}
}