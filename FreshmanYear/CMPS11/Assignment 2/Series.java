import java.util.Scanner;

public class Series {

	public static void main(String[] args) 
	{
		
		Scanner s = new Scanner(System.in);
		
		double num1 = s.nextInt();
		double num2 = s.nextInt();
		
		
		
		if(num2 == 1)
			System.out.printf("%.2f", 1.0);
		else if(num2 == 2)
			System.out.printf("%.2f", (1.0 +num1));
		else
		{
			double result = 1.0;
			for(int i = 1; i < num2; i++)
			{
				double pow = Math.pow(num1, i);
				double fac = factorial(i);
				
				result += pow/fac;
			}
			System.out.printf("%.2f", result);
		}
		s.close();
	}
	
	private static double factorial(double num)
	{
		if(num == 0)
			return 1;
		
		double result = 0;
		
		double temp  = factorial(num - 1);
		result = temp * num;
		
		return result;
	}
}