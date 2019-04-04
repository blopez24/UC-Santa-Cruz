package winter18.assignment3;

public class Path 
{
	int count = 0;
	int[][] path;
	
	Path()
	{
		path = new int[0][2];
	}
	
	Path addPoint(int x, int y)
	{
		count++;
		int[][] newPath = new int[count][2];
		
		for(int r = 0; r < path.length; r++)
		{
			for(int c = 0; c < path[0].length; c++)
			{
				newPath[r][c] = path[r][c];
			}
		}
		
		newPath[count - 1][0] = x;
		newPath[count - 1][1] = y;
		
		path = newPath;
		
		return this;
	}
	
	String getPoint(int i)
	{
		return "(" + path[i][0] + ", " + path[i][1] + ")";
	}
	
	int numOfPoints()
	{
		return path.length;
	}
	
	boolean removePoint(int i)
	{
		
		if(i < path.length)
		{
			count--;
			int[][] newPath = new int[count][2];
			for(int r = 0; r < i; r++)
			{
				for(int c = 0; c < path[0].length; c++)
				{
					newPath[r][c] = path[r][c];
				}
			}
			for(int r = i + 1; r < path.length; r++)
			{
				for(int c = 0; c < path[0].length; c++)
				{
					newPath[r][c] = path[r][c];
				}
			}
			path = newPath;
			
			return true;
		}
		else
			return false;	
	}
	
	int getX(int i)
	{
		return path[i][0];
	}
	
	int getY(int i)
	{
		return path[i][1];
	}
	
	void addPath(Path p)
	{
		count += p.numOfPoints();
		
		int[][] newPath = new int[count][2];
		
		for(int r = 0; r < path.length; r++)
		{
			for(int c = 0; c < path[0].length; c++)
			{
				newPath[r][c] = path[r][c];
			}
		}
		
		int i = 0;
		for(int r = path.length; r < count; r++)
		{
			newPath[r][0] = p.getX(i);
			i++;
		}
		
		int j = 0;
		for(int r = path.length; r < count; r++)
		{
			newPath[r][1] = p.getY(j);
			j++;
		}
		
		path = newPath;
	}
	
	double getLength()
	{		
		double length = 0;
		
		int[] x = new int[path.length];
		int[] y = new int[path.length];
		
		for(int r = 0; r < path.length; r++)
		{
			x[r] = path[r][0];
		}
		for(int r = 0; r < path.length; r++)
		{
			y[r] = path[r][1];
		}
		
		for(int i = 0; i < path.length - 1; i++)
		{
			double x1 = x[i];
			double y1 = y[i];
			
			double x2 = x[i + 1];
			double y2 = y[i + 1];
			
			double x2minusx1Squared = Math.pow((x2 - x1), 2);
			double y2minusy1Squared = Math.pow((y2 - y1), 2);
			
			length += Math.sqrt(x2minusx1Squared + y2minusy1Squared);
		}
		
		return length;
	}
	
	double getDistance()
	{
		
		double x1 = path[0][0];
		double y1 = path[0][1];
		double x2 = path[count - 1][0];
		double y2 = path[count - 1][1];
		
		double x2minusx1Squared = Math.pow((x2 - x1), 2);
		double y2minusy1Squared = Math.pow((y2 - y1), 2);
		
		return Math.sqrt(x2minusx1Squared + y2minusy1Squared);
	}
	
	boolean isLonger(Path p)
	{
		if(this.getLength() > p.getLength())
			return true;
		else
			return false;
	}
}
