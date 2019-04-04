class Knight extends ChessPiece
{
	Knight(int c, int r, String s, boolean clr)
	{
		super(c, r, s, clr);
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
	
	@Override 
	int[] pathWay(int c, int r)
	{
		int[] empty = new int[0];
		return empty;
	}
}