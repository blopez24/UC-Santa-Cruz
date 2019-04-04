import java.io.*;
import java.util.*;

public class NQueens
{
	private static int boardSize;
	private static ArrayList<Queen> sol;
	//private static Queen[] solution;
	private static Stack<Queen> queenStack;
	
	//private static int counter;
	
	public static void main(String[] args) throws IOException
	{
		if(args.length < 2)
		{
			System.out.println("Usage: java -jar NQueens.jar <input file> <output file>");
			System.exit(1);
		}
		
		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new FileWriter(args[1]));
		
		while(in.hasNext())
		{
			String line = in.nextLine();
			Scanner input = new Scanner(line);
			
			boardSize = input.nextInt();
			//solution = new Queen[boardSize];
			sol = new ArrayList<Queen>();
			//counter = 0;
			
			while(input.hasNext())
			{
				int column = input.nextInt();
				int row = input.nextInt();
				
				//solution[counter] = new Queen(column, row);
				sol.add(new Queen(column, row));
				//counter++;
			}
			
			queenStack = new Stack<Queen>();
			
			if(boardSize < 4)
			{
				out.println("No solution");
				continue;
			}
			else
			{
				solve(boardSize - sol.size());
				if(sol.size() == boardSize)
				{
					if(attacksEachOther())
					{
						out.println("No solution");
						continue;
					}
					
					//for(int col = 1; col < solution.length + 1; col++)
					for(int col = 1; col < sol.size() + 1; col++)
					{
						int queenRow = 0;
						//for(int index = 0; index < solution.length; index++)
						for(int index = 0; index < sol.size(); index++)
						{
							//if(solution[index].col == col)
								//queenRow = solution[index].row;
							if(sol.get(index).col == col)
								queenRow = sol.get(index).row;
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
			
			input.close();
		}
		in.close();
		out.close();
		
	}
	
	private static void solve(int queensToPlace) 
	{	
		ArrayList<Integer> availableColumns = freeColumns(queensToPlace);
		int row = 1;
		int index = 0;
		
		while(true)
		{
			while(row < boardSize + 1  && index < availableColumns.size())
			{
				int column = availableColumns.get(index);
				Queen next = new Queen(column, row);
				if(isSafeToPush(next))
				{
					queenStack.push(next);
					//System.out.println("Push: " + queenStack.peek());
					sol.add(next);
					index++;
					row = 1;
				}
				else
				{
					row++;
				}
			}	
			
			if(queenStack.size() == queensToPlace)
			{
				break;
			}
			if(queenStack.isEmpty())
			{
				break;
			}
			int lastRow = queenStack.peek().row;
			//System.out.println("lastRow: " + queenStack.peek().row);
			//System.out.println("Pop: " + queenStack.peek());
			queenStack.pop();
			
			sol.remove(sol.size()-1);
			row = lastRow+2;
			index--;
			
			if(row == 5)
			{
				break;
			}
		}
	}
	
	
	private static boolean isSafeToPush(Queen q) 
	{
		for(int i = 0; i < sol.size(); i++)
		{
			if(sol.get(i).isAttacking(q))
				return false;
		}
		return true;
	}
	
	private static ArrayList<Integer> freeColumns(int numLeft)
	{
		ArrayList<Integer> columns = new  ArrayList<Integer>();
		int queensPlaced = boardSize - numLeft;
		
		for(int i = 1; i < boardSize + 1; i++)
		{
			boolean free = true;
			for(int j = 0; j < queensPlaced; j++)
			{
				//if(i == solution[j].col)
				if(i == sol.get(j).col)
				{
					free = false;
					break;
				}
			}
			if(free)
				columns.add(i);
		}
		
		return columns;
	}
	
	private static boolean attacksEachOther()
	{
		//for(int i = 0; i < solution.length - 1; i++)
		for(int i = 0; i < sol.size() - 1; i++)
		{
			//for(int j = i + 1; j < solution.length; j++)
			for(int j = i + 1; j < sol.size(); j++)
			{
				//if(solution[i].isAttacking(solution[j]))
				if(sol.get(i).isAttacking(sol.get(j)))
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
	
	public String toString()
	{
		return this.col + " " + this.row;
	}
}
