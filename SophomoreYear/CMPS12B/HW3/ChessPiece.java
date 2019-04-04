class ChessPiece
{
	int col, row;
	String type;
	boolean color = true;	// White is false (Lower case)
					// Black is true (Upper case)
	
	ChessPiece()
	{
		col = 0;
		row = 0;
		type = "";
		color = false;
	}

	ChessPiece(int c, int r, String chessPieceType, boolean chessPieceColor)
	{
		this.col = c;
		this.row = r;
		this.type = chessPieceType;
		this.color = chessPieceColor;
	}
	
	boolean isAttacking(ChessPiece p) 
	{
		return false;
	}
	
	int[] pathWay(int c, int r)
	{
		return null;
	}
	
	public String toString()
	{
		String str = col + " " + row + " " + type + " " + color;
		return str;
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
	
	boolean getColor()
	{
		return this.color;
	}
}