class Queen extends ChessPiece
{
	Queen(int c, int r, String s, boolean clr)
	{
		super(c, r, s, clr);
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
	
	@Override 
	int[] pathWay(int c, int r)
	{
		return mergePathWay(c, r);
	}
	
	int[] mergePathWay(int column, int row)
	{
		int[] pathway1 = returnPathRookHelper(column, row);
		
		int[] pathway2 = returnPathBishopHelper(column, row);
		
		int[] path = new int[pathway1.length + pathway2.length];
		
		int index = 0;
		for(int i = 0; i < pathway1.length; i++)
		{
			path[index] = pathway1[i];
			index++;
		}
		for(int i = 0; i < pathway2.length; i++)
		{
			path[index] = pathway2[i];
			index++;
		}
		
		return path;
	}
	
	// Forgot to recopy and paste from bishop class after updating bishop returnPath method
	int[] returnPathBishopHelper(int c, int r)
	{
		int[] path  = new int[16];	// Max 8 x 8 Board
		int index = 0;
		
		int columnCP1 = this.getCol();
		int rowCP1 = this.getRow();
		
		int columnCP2 = c;
		int rowCP2 = r;
		
		
		if( (columnCP1 >  columnCP2) && (rowCP1 < rowCP2) )
		{
			while(columnCP1 != columnCP2+1 && rowCP1 != rowCP2-1)
			{
				columnCP2++;
				rowCP2--;
				
				path[index] = columnCP2;
				path[index+1] = rowCP2;
				index += 2;
			}
		}
		else if( (columnCP1 >  columnCP2) && (rowCP1 > rowCP2) )
		{
			while(columnCP1 != columnCP2+1 && rowCP1 != rowCP2+1)
			{
				columnCP2++;
				rowCP2++;
				
				path[index] = columnCP2;
				path[index+1] = rowCP2;
				index += 2;
			}
		}
		else if( (columnCP1 <  columnCP2) && (rowCP1 < rowCP2) )
		{
			while(columnCP1 != columnCP2-1 && rowCP1 != rowCP2-1)
			{
				columnCP2--;
				rowCP2--;
				
				path[index] = columnCP2;
				path[index+1] = rowCP2;
				index += 2;
			}
		}
		else if( (columnCP1 <  columnCP2) && (rowCP1 > rowCP2) )
		{
			while(columnCP1 != columnCP2-1 && rowCP1 != rowCP2+1)
			{
				columnCP2--;
				rowCP2++;
				
				path[index] = columnCP2;
				path[index+1] = rowCP2;
				index += 2;
			}
		}
		
		path = correctArray(path);
		return path;
	}
	
	int[] returnPathRookHelper(int c, int r)
	{
		int[] path  = new int[16];	// Max 8 x 8 Board
		int index = 0;
		
		int columnCP1 = this.getCol();
		int rowCP1 = this.getRow();
		
		int columnCP2 = c;
		int rowCP2 = r;
		
		if( (columnCP1 == columnCP2) && (rowCP1 > rowCP2) )
		{
			while(rowCP1 != rowCP2+1)
			{
				rowCP2++;
				
				path[index] = columnCP2;
				path[index+1] = rowCP2;
				index += 2;
			}
		}
		else if( (columnCP1 == columnCP2) && (rowCP1 < rowCP2) )
		{
			while(rowCP1 != rowCP2-1)
			{
				rowCP2--;
				
				path[index] = columnCP2;
				path[index+1] = rowCP2;
				index += 2;
			}
		}
		else if( (columnCP1 < columnCP2) && (rowCP1 == rowCP2) )
		{
			while(columnCP1 != columnCP2-1)
			{
				columnCP2--;
				
				path[index] = columnCP2;
				path[index+1] = rowCP2;
				index += 2;
			}
		}
		else if( (columnCP1 > columnCP2) && (rowCP1 == rowCP2) )
		{
			while(columnCP1 != columnCP2+1)
			{
				columnCP2++;
				
				path[index] = columnCP2;
				path[index+1] = rowCP2;
				index += 2;
			}
		}
		
		path = correctArray(path);
		return path;
	}
	
	int[] correctArray(int[] arrayOfPath)
	{
		int length = 0;
		for(int i = 0; i < arrayOfPath.length; i++)
		{
			if(arrayOfPath[i] != 0)
				length++;
		}
		
		int cArray[] = new int[length];
		for(int i = 0; i < cArray.length; i++)
		{
			cArray[i] = arrayOfPath[i];
		}
		return cArray;
	}
}