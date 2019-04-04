/* Bryan Lopez
 * blopez24 (1612626)
 *
 * ChessBoard.java
 * Stores a chessboard as a linked list of chess pieces.
 * Determine validity: verify that two pieces do not occupy the same square.
 * Find piece: given a square, determine (if any) the chess piece at that square.
 * Determine attack (from that piece)
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ChessBoard 
{	
	static int theSquareColumn;
	static int theSquareRow;
	
	static String type;
	static int column;
	static int row;
	
	static CPLinkedList list = new CPLinkedList();
	static ChessPiece p = new ChessPiece();
	
	public static void main(String[] args) throws IOException
	{
		if(args.length < 2)
		{
			System.out.println("Usage: java -jar ChessBoard.jar <input file> <output file>");
			System.exit(1);
		}

		// Open files
		Scanner file = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new FileWriter(args[1]));

		// Read lines from in 
		while(file.hasNextLine())
		{
			String line = file.nextLine();
			Scanner in = new Scanner(line);
			
			theSquareColumn = in.nextInt();
			String rowAndColon = in.next();
			theSquareRow = Integer.parseInt(rowAndColon.substring(0,1));
			
			while( in.hasNext() )
			{
				type = in.next();
				column = in.nextInt();
				row = in.nextInt();
				
				if(type.equals("k") || type.equals("K"))
				{
					p = new King(column, row, type);
				}
				else if(type.equals("q") || type.equals("Q"))
				{
					p = new Queen(column, row, type);
				}
				else if(type.equals("r") || type.equals("R"))
				{
					p = new Rook(column, row, type);
				}
				else if(type.equals("b") || type.equals("B"))
				{
					p = new Bishop(column, row, type);
				}
				else if(type.equals("n") || type.equals("N"))
				{
					p = new Knight(column, row, type);
				}
				else if(type.equals("p") || type.equals("P"))
				{
					p = new Pawn(column, row, type);
				}
				list.add(0, p);
			}
			
			if(!isValid())
				out.println("Invalid");
			
			else if(!isPieceAt(theSquareColumn, theSquareRow))
			{
				out.println("-");
			}
			else if(isPieceAt(theSquareColumn, theSquareRow))
			{
				String type = findPiece(theSquareColumn, theSquareRow).getType();
				out.print(type + " ");
				
				CPLinkedList blackCP = new CPLinkedList();
				CPLinkedList whiteCP = new CPLinkedList();
				
				for(int i = 0; i < list.size(); i++)
				{
					String lowerOrUpper = ((ChessPiece) list.get(i)).getType();
					
					boolean hasUpperCase = !lowerOrUpper.equals(lowerOrUpper.toLowerCase());
					boolean hasLowerCase = !lowerOrUpper.equals(lowerOrUpper.toUpperCase());
					
					if(hasUpperCase)
						blackCP.add(0, (ChessPiece)list.get(i));
					if(hasLowerCase)
						whiteCP.add(0, (ChessPiece)list.get(i));
				}
				
				boolean typeHasUpperCase = !type.equals(type.toLowerCase());
				
				boolean attacks = false;
				if(typeHasUpperCase)
				{
					for(int i = 0; i < whiteCP.size(); i++)
					{
						if(findPiece(theSquareColumn, theSquareRow).isAttacking( (ChessPiece)whiteCP.get(i) ))
							attacks = true;
					}
				}
				else
				{
					for(int i = 0; i < blackCP.size(); i++)
					{
						if(findPiece(theSquareColumn, theSquareRow).isAttacking( (ChessPiece)blackCP.get(i) ))
							attacks = true;
					}
				}
				if(attacks)
					out.println("y");
				else
					out.println("n");
			}
			
			list = new CPLinkedList();
			
			in.close();
		}
		
		file.close();
		out.close();
	}
	
	static boolean isValid()
	{
		for(int i = 0; i < list.size(); i++)
		{
			for(int j = i+1; j < list.size(); j++)
			{
				if( ((ChessPiece) list.get(i)).getRow() == ((ChessPiece) list.get(j)).getRow()
						&& ((ChessPiece) list.get(i)).getCol() == ((ChessPiece) list.get(j)).getCol() )
				{
					return false;
				}
			}
		}
		return true;
	}
	
	static boolean isPieceAt(int c, int r)
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
	
	static ChessPiece findPiece(int c, int r)
	{
		for(int i = 0; i < list.size(); i++)
		{
			if( ((ChessPiece) list.get(i)).getCol() == c 
					&& ((ChessPiece) list.get(i)).getRow() == r)
				return (ChessPiece) list.get(i);
		}
		return null;
	}
}

class CPLinkedList
{
	Node head;
	int length;
	
	CPLinkedList()
	{
		this.head = null;
		this.length = 0;
	}
	
	int size()
	{
		return length;
	}
	
	Node find(int index)
	{
		Node current = head;
		
		for(int i = 0; i < index; i++)
		{
			current = current.next;
		}
		
		
		return current;
	}
	
	void add(int index, ChessPiece cp)
	{
		if(index >= 0 && index < length + 1)
		{
			if(index == 0)
			{
				Node newNode = new Node(cp, head);
				head = newNode;
			}
			else
			{
				Node previous = find(index-1);
				
				Node newNode = new Node(cp, previous.next);
				previous.next = newNode;
			}
			length++;
		}
	}
	
	Object get(int index)
	{
		if(index >= 0 && index < length)
		{
			Node current = find(index);
			ChessPiece cpData = current.data;
			return cpData;
		}
		
		return null;
	}
}

class Node
{
	ChessPiece data;
	Node next;
	
	Node(ChessPiece cp)
	{
		this.data = cp;
		this.next = null;
	}
	
	Node(ChessPiece cp, Node nextNode)
	{
		this.data = cp;
		this.next = nextNode;
	}
}

class ChessPiece
{
	int col, row;
	String type;
	
	ChessPiece()
	{
		col = 0;
		row = 0;
		type = "";
	}

	ChessPiece(int c, int r, String chessPieceType)
	{
		this.col = c;
		this.row = r;
		this.type = chessPieceType;
	}
	
	boolean isAttacking(ChessPiece p) 
	{
		return false;
	}
	
	int getRow()
	{
		return this.row;
	}
	
	int getCol()
	{
		return this.col;
	}
	
	String getType()
	{
		return this.type;
	}
}

class King extends ChessPiece
{
	King(int c, int r, String s)
	{
		super(c, r, s);
	}
	
	@Override
	boolean isAttacking(ChessPiece p)
	{
		int x = this.col;
		int y = this.row;
		
		if( (y-1 == p.row) && ( (x-1 == p.col) || (x == p.col) || (x+1 == p.col) ) )
			return true;
		if( (y == p.row) && ( (x-1 == p.col) || (x+1 == p.col) ) )
			return true;
		if( (y+1 == p.row) && ( (x-1 == p.col) || (x == p.col) || (x+1 == p.col) ) )
			return true;
		return false;
	}
}

class Queen extends ChessPiece
{
	Queen(int c, int r, String s)
	{
		super(c, r, s);
	}
	
	@Override
	boolean isAttacking(ChessPiece p)
	{
		if(p.col == this.col || p.row == this.row)
			return true;
			
		int diff = Math.abs(p.col - this.col);
		
		if( (p.col-diff == this.col && p.row-diff == this.row)
				|| (p.col+diff == this.col && p.row+diff == this.row) )
			return true;
		
		if( (p.col+diff == this.col && p.row-diff == this.row)
				|| (p.col-diff == this.col && p.row+diff == this.row) )
			return true;
		
		
		return false;
	}
}

class Bishop extends ChessPiece
{
	Bishop(int c, int r, String s)
	{
		super(c, r, s);
	}
	
	@Override
	boolean isAttacking(ChessPiece p)
	{
		int diff = Math.abs(p.col - this.col);
		
		if( (p.col-diff == this.col && p.row-diff == this.row)
				|| (p.col+diff == this.col && p.row+diff == this.row) )
			return true;
		
		if( (p.col+diff == this.col && p.row-diff == this.row)
				|| (p.col-diff == this.col && p.row+diff == this.row) )
			return true;
		return false;
	}
}

class Knight extends ChessPiece
{
	Knight(int c, int r, String s)
	{
		super(c, r, s);
	}
	
	@Override
	boolean isAttacking(ChessPiece p)
	{
		int x = this.col;
		int y = this.row;
		
		if( (x-1 == p.col && y-2 == p.row) || (x-2 == p.col && y-1 == p.row) )
			return true;
		if( (x-2 == p.col && y+1 == p.row) || (x-1 == p.col && y+2 == p.row) )
			return true;
		if( (x+1 == p.col && y+2 == p.row) || (x+2 == p.col && y+1 == p.row) )
			return true;
		if( (x+2 == p.col && y-1 == p.row) || (x+1 == p.col && y-2 == p.row) )
			return true;
		
		return false;
	}
}


class Rook extends ChessPiece
{
	Rook(int c, int r, String s)
	{
		super(c, r, s);
	}
	
	@Override
	boolean isAttacking(ChessPiece p)
	{
		if(p.col == this.col || p.row == this.row)
			return true;
		return false;
	}
}


class Pawn extends ChessPiece
{
	Pawn(int r, int c, String s)
	{
		super(r,c, s);
	}
	
	@Override
	boolean isAttacking(ChessPiece p)
	{
		int c = this.getCol();
		int r = this.getRow();
		String color = p.getType();
		
		boolean black = !color.equals(color.toLowerCase());
		boolean white = !color.equals(color.toUpperCase());
		
		if(black)
		{
			if(c-1 == p.col && r+1 == p.row)
				return true;
			if(c+1 == p.col && r+1 == p.row)
				return true;
		}
		if(white)
		{
			if(c-1 == p.col && r-1 == p.row)
				return true;
			if(c+1 == p.col && r-1 == p.row)
				return true;
		}
		return false;
	}
}
