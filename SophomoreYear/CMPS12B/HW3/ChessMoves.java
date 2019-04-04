/* -----------------------------------------------------------------
 * Bryan Lopez
 * blopez24 (1612626)
 * -----------------------------------------------------------------
 * ChessMoves.java
 * 
 * Store a chessboard as a linked list of chess pieces, implement 
 * moves, and determine if king is under attack. Assume an 8 x 8 
 * chessboard. Implement a procedure that, given a chessboard, makes
 * a series of moves. Let squares be indexed as (column,row) pairs.
 * Given a source square (x,y) and a destination square (x0,y0),
 * determine if the piece (if any) at (x,y) can legally move to 
 * (x0,y0). Given a sequence of moves (x1,y1) to (x0 1,y1), (x2,y2)
 * to (x0 2,y0 2), etc., implement all these moves to determine the
 * final chessboard.
 * -----------------------------------------------------------------
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ChessMoves 
{
	private static Scanner file;
	private static PrintWriter out;
	
	private static CPLinkedList list = new CPLinkedList();
	private static ChessPiece p = new ChessPiece();;
	
	private static String type;
	private static int column;
	private static int row;
	
	private static int turnTracker = 1;
	
	public static void main(String[] args) throws IOException
	{
		file = new Scanner(new File(args[0]));
		out = new PrintWriter(new FileWriter(args[1]));
		
		while(file.hasNextLine())
		{
			String line = file.nextLine();
			int locationOfColon = line.indexOf(":");
			
			Scanner chessPieces = new Scanner(line.substring(0, locationOfColon));
			
			while(chessPieces.hasNext())
			{
				placePieces(chessPieces);
			}
			chessPieces.close();
			
			//printPieces(); //Prints Chess Pieces
			
			Scanner movements = new Scanner(line.substring(locationOfColon + 2, line.length()));
			
			
			boolean hasNotPrinted = true;
			while(movements.hasNext())
			{
				int sColumn = movements.nextInt();
				int sRow = movements.nextInt();
				
				int dColumn = movements.nextInt();
				int dRow = movements.nextInt();
				
				
				// No Piece at Starting Location
				if(pieceFound(sColumn, sRow) == false)	
				{
					String s = "";
					//s = " No Piece at Starting Location";
					String move = sColumn + " " + sRow + " " + dColumn + " " + dRow;
					out.println(move + " illegal" + s);
					hasNotPrinted = false;
					break;
				}
				// Piece found, but not their turn
				if(pieceFound(sColumn, sRow))
				{
					if(playerTurn() == 1 && colorOfPiece(sColumn, sRow))
					{
						String s = "";
						//s = " Piece found, but not their turn";
						String move = sColumn + " " + sRow + " " + dColumn + " " + dRow;
						out.println(move + " illegal" + s);
						hasNotPrinted = false;
						break;
					}
					if(playerTurn() == 0 && colorOfPiece(sColumn, sRow) == false)
					{
						String s = "";
						//s = " Piece found, but not their turn";
						String move = sColumn + " " + sRow + " " + dColumn + " " + dRow;
						out.println(move + " illegal" + s);
						hasNotPrinted = false;
						break;
					}
				}
				
				// Possible to move there
				// Add this +6 lines 
				ChessPiece dummy = new ChessPiece(dColumn, dRow, "dummy", !chessPieceAt(sColumn,sRow).getColor());
				if(moveThere(chessPieceAt(sColumn, sRow), dummy) == false){
					out.println(sColumn + " " + sRow + " " + dColumn + " " + dRow + " illegal");
					hasNotPrinted = false;
					break;
				}
				// Go to line 283
				
				// Piece found
				if(pieceFound(sColumn, sRow) )
				{
					// Piece at destination is found
					if(pieceFound(dColumn, dRow))
					{
						//  But Piece at destination is the same color
						if(colorOfPiece(sColumn, sRow) == colorOfPiece(dColumn, dRow))
						{
							String s = "";
							//s = " Piece found, but Piece at destination is the same color";
							String move = sColumn + " " + sRow + " " + dColumn + " " + dRow;
							out.println(move + " illegal" + s);
							hasNotPrinted = false;
							break;
						}
						// Piece found, and Piece at destination is a different color
						else
						{
							// Pathway
							int[] pathToCheck = chessPieceAt(sColumn, sRow).pathWay(dColumn, dRow);
							boolean pathIsFree = true;
							
							for(int i = 0; i < pathToCheck.length; i += 2)
							{
								// Path is blocked
								if(chessPieceAt(pathToCheck[i], pathToCheck[i + 1]) != null)
								{
									pathIsFree = false;
									
									String s = "";
									//s =  " Piece found, but way blocked";
									String move = sColumn + " " + sRow + " " + dColumn + " " + dRow;
									out.println(move + " illegal" + s);
									hasNotPrinted = false;
									break;
								}
							}
							if(pathIsFree == false)
								break;	// Breaks from while loop
							
							// Path is not blocked
							if(pathIsFree)
							{
								if(illegalMove(sColumn, sRow, dColumn, dRow))
								{
									String s = "";
									//s =  " Piece found, moves illegal";
									String move = sColumn + " " + sRow + " " + dColumn + " " + dRow;
									out.println(move + " illegal" + s);
									hasNotPrinted = false;
									break;
								}
								
								int indexDes = indexOfChessPieceAt(dColumn, dRow);
								
								list.remove(indexDes);
								
								int indexStart = indexOfChessPieceAt(sColumn, sRow);
								placeSinglePiece(dColumn, dRow, chessPieceAt(sColumn, sRow).getType());
								list.remove(indexStart);
								
								/*
								if(kingEndanger())
								{
									String s = "";
									//s =  " Piece found, moves illegal";
									String move = sColumn + " " + sRow + " " + dColumn + " " + dRow;
									out.println(move + " illegal" + s);
									hasNotPrinted = false;
									break;
								}
								*/
							}
						}	
					}
				}
				// Piece is found
				if(pieceFound(sColumn, sRow))
				{
					// Piece at destination is not found = Destination is empty
					if(pieceFound(dColumn, dRow) == false)
					{
						
						// Pathway
						int[] pathToCheck = chessPieceAt(sColumn, sRow).pathWay(dColumn, dRow);
						boolean pathIsFree = true;
						
						for(int i = 0; i < pathToCheck.length; i += 2)
						{
							// Path is blocked
							if(chessPieceAt(pathToCheck[i], pathToCheck[i + 1]) != null)
							{
								System.out.println(pathToCheck[i] + " " + pathToCheck[i + 1]);
								pathIsFree = false;
								
								String s = "";
								//s =  " Piece found, but way blocked";
								String move = sColumn + " " + sRow + " " + dColumn + " " + dRow;
								out.println(move + " illegal" + s);
								hasNotPrinted = false;
								break;
							}
						}
						
						if(pathIsFree == false)
							break; // Breaks from while loop
						
						// Path is not blocked
						if(pathIsFree)
						{
							
							if(illegalMove(sColumn, sRow, dColumn, dRow))
							{
								String s = "";
								//s =  " Piece found, moves illegal";
								String move = sColumn + " " + sRow + " " + dColumn + " " + dRow;
								out.println(move + " illegal" + s);
								hasNotPrinted = false;
								break;
							}
							
							int indexStart = indexOfChessPieceAt(sColumn, sRow);
							String typeTemp = chessPieceAt(sColumn, sRow).getType();
							list.remove(indexStart);
							placeSinglePiece(dColumn, dRow, typeTemp);
							
							/*
							if(kingEndanger())
							{
								String s = "";
								//s =  " Piece found, moves illegal";
								String move = sColumn + " " + sRow + " " + dColumn + " " + dRow;
								out.println(move + " illegal" + s);
								hasNotPrinted = false;
								break;
							}
							*/
						}
					}
					
				}
				
				turnTracker++;
			}
			movements.close();
			
			if(hasNotPrinted)
				out.println("legal");
			
			list.removeAll();
			turnTracker = 1;
		}
		
		file.close();
		out.close();
	}
	
	private static boolean illegalMove(int sC, int sR, int dC, int dR)
	{
		ChessPiece dummy = new ChessPiece(dC, dR, "d", false);
		
		if(chessPieceAt(sC, sR).isAttacking(dummy) == false)
			return true;
		else
			return false;
	}
	
	// Added this +3 lines
	private static boolean moveThere(ChessPiece s, ChessPiece d){
		return s.isAttacking(d);
	}
	
	private static boolean kingEndanger()
	{
		// Last piece to Move
		// White
		if(playerTurn() == 1)
		{
			for(int i = 0; i < list.size(); i++)
			{
				if( ((ChessPiece) list.get(i)).getColor() == true)
				{
					if( ( (ChessPiece) list.get(i) ).isAttacking( kingInfo() ) )
						return true;
				}
			}
		}
		
		// Black
		if(playerTurn() == 0)
		{
			for(int i = 0; i < list.size(); i++)
			{
				if( ((ChessPiece) list.get(i)).getColor() == false)
				{
					if( ( (ChessPiece) list.get(i) ).isAttacking( kingInfo() ) )
						return true;
				}
			}
		}
		
		// King is not under attack
		return false;
	}
	
	private static ChessPiece kingInfo()
	{
		if(playerTurn() == 1)
		{
			for(int i = 0; i < list.size(); i++)
			{
				if( ((ChessPiece)list.get(i)).getType().equals("k") )
				{
					return (ChessPiece) list.get(i);
				}
			}
		}
		else
		{
			for(int i = 0; i < list.size(); i++)
			{
				if( ((ChessPiece)list.get(i)).getType().equals("K") )
				{
					return (ChessPiece) list.get(i);
				}
			}
		}
		return null;
	}
	
	static boolean colorOfPiece(int c, int r)
	{
		boolean color =  chessPieceAt(c, r).getColor();
		return color;
	}
	
	private static int indexOfChessPieceAt(int c, int r)
	{
		for(int i = 0; i < list.size(); i++)
		{
			if( (((ChessPiece) list.get(i)).getCol() == c) && (((ChessPiece) list.get(i)).getRow() == r) )
			{
				return i;
			}
		}
		return -1;
	}
	
	private static ChessPiece chessPieceAt(int c, int r)
	{
		for(int i = 0; i < list.size(); i++)
		{
			if( (((ChessPiece) list.get(i)).getCol() == c) && (((ChessPiece) list.get(i)).getRow() == r) )
			{
				return (ChessPiece) list.get(i);
			}
		}
		return null;
	}
	
	private static int playerTurn()
	{
		// turnTracker == 1 white turn
		// turnTracker == 0 black turn
		return turnTracker % 2;
	}
	
	private static boolean pieceFound(int c, int r)
	{
		for(int i = 0; i < list.size(); i++)
		{
			
			if( ((ChessPiece) list.get(i)).getCol() == c 
					&& ((ChessPiece) list.get(i)).getRow() == r)
			{
				return true;
			}
		}
		
		return false;
	}
	
	private static void placeSinglePiece(int col, int row, String type)
	{
		if(type.equals("k") || type.equals("K"))
		{
			if(type.equals("k"))
				p = new King(col, row, type, false);
			else
				p = new King(col, row, type, true);
		}
		else if(type.equals("q") || type.equals("Q"))
		{
			if(type.equals("q"))
				p = new Queen(col, row, type, false);
			else
				p = new Queen(col, row, type, true);
		}
		else if(type.equals("r") || type.equals("R"))
		{
			if(type.equals("r"))
				p = new Rook(col, row, type, false);
			else
				p = new Rook(col, row, type, true);
		}
		else if(type.equals("b") || type.equals("B"))
		{
			if(type.equals("b"))
				p = new Bishop(col, row, type, false);
			else
				p = new Bishop(col, row, type, true);
		}
		else if(type.equals("n") || type.equals("N"))
		{
			if(type.equals("n"))
				p = new Knight(col, row, type, false);
			else
				p = new Knight(col, row, type, true);
		}
		else if(type.equals("p"))
		{
			p = new WhitePawn(col, row, type);
		}
		else if(type.equals("P"))
		{
			p = new BlackPawn(col, row, type);
		}
		list.add(list.size(), p);
	}
	
	private static void placePieces(Scanner s) 
	{
		type = s.next();
		column = s.nextInt();
		row = s.nextInt();
		
		if(type.equals("k") || type.equals("K"))
		{
			if(type.equals("k"))
				p = new King(column, row, type, false);
			else
				p = new King(column, row, type, true);
		}
		else if(type.equals("q") || type.equals("Q"))
		{
			if(type.equals("q"))
				p = new Queen(column, row, type, false);
			else
				p = new Queen(column, row, type, true);
		}
		else if(type.equals("r") || type.equals("R"))
		{
			if(type.equals("r"))
				p = new Rook(column, row, type, false);
			else
				p = new Rook(column, row, type, true);
		}
		else if(type.equals("b") || type.equals("B"))
		{
			if(type.equals("b"))
				p = new Bishop(column, row, type, false);
			else
				p = new Bishop(column, row, type, true);
		}
		else if(type.equals("n") || type.equals("N"))
		{
			if(type.equals("n"))
				p = new Knight(column, row, type, false);
			else
				p = new Knight(column, row, type, true);
		}
		else if(type.equals("p"))
		{
			p = new WhitePawn(column, row, type);
		}
		else if(type.equals("P"))
		{
			p = new BlackPawn(column, row, type);
		}
		list.add(list.size(), p);
	}
	
	private static void printPieces()
	{
		for(int i = 0; i < list.size(); i++)
		{
			ChessPiece cp = (ChessPiece) list.get(i);
			System.out.print(cp.getType() + " " + cp.getCol() + " " + cp.getRow() + " " + cp.getColor() + " ---\t");
		}
		System.out.println();
	}
}

