import java.util.Scanner;

public class Matrix {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		int rows = s.nextInt();
		int columns = s.nextInt();

		int[][] m = new int[rows][columns];
		
		for(int r = 0; r < m.length; r++)
		{
			for(int c = 0; c < m[0].length; c++)
			{
				m[r][c] = s.nextInt();
			}
		}
		
		String command  = s.next();
		
		while(!command.equals("Q"))
		{
			if(command.equals("R"))
			{
				rowMultiply(m);
				command  = s.next();
				System.out.println();
			}
			else if(command.equals("C"))
			{
				columnMin(m);
				command  = s.next();
				System.out.println();
			}
			else if(command.equals("T"))
			{
				transpose(m);
				command  = s.next();
			}
		}
		s.close();
	}
	
	private static void transpose(int[][] m) {
		int[][] temp = new int[m[0].length][m.length];
		
		for(int j = 0; j < m[0].length; j++)
		{
			for(int i = 0; i < m.length; i++)
			{
				temp[j][i] = m[i][j];
			}
		}
		
		for(int i = 0; i < temp.length; i++)
		{
			for(int j = 0; j < temp[0].length; j++)
			{
				if(j == temp[0].length - 1)
					System.out.print(temp[i][j]);
				else
					System.out.print(temp[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void columnMin(int[][] m) {
		int[] output = new int[m[0].length];
		
		int[] temp = new int[m[0].length];
		
		for(int j = 0; j < m[0].length; j++)
		{
			temp[j] = m[0][j]; 
		}
		
		for(int j = 0; j < m[0].length; j++)
		{
			for(int i = 0; i < m.length; i++)
			{
				if(temp[j] > m[i][j])
					temp[j] = m[i][j];
			}
		}
		
		for(int i = 0; i < m[0].length; i++)
		{
			output[i] = temp[i];
		}
		
		for(int i = 0; i < output.length; i++)
		{
			if(i == output.length - 1)
				System.out.print(output[i]);
			else
				System.out.print(output[i] + " ");
		}
	}

	private static void rowMultiply(int[][] m)
	{
		int[] output = new int[m.length];
		for(int i = 0; i < m.length; i++)
		{
			output[i] = 1;
		}
		
		for(int i = 0; i < m.length; i++)
		{
			for(int j = 0; j < m[0].length; j++)
			{
				output[i] *= m[i][j];
			}
		}
		
		for(int i = 0; i < output.length; i++)
		{
			if(i == output.length - 1)
				System.out.print(output[i]);
			else
				System.out.print(output[i] + " ");
		}
	}

}
