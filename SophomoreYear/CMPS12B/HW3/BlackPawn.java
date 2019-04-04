class BlackPawn extends ChessPiece
{
	BlackPawn(int c, int r, String s)
	{
		super(c, r, s, true);
	}
	
	@Override
	boolean isAttacking(ChessPiece p)
	{
		int x = this.col;
		int y = this.row;
		
		if( (y+1 == p.row) && ( (x-1 == p.col) || (x == p.col) || (x+1 == p.col) ) )
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