import java.util.Scanner;

public class Bits {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		int n;
		int count = 0;
		String error = "Illegal input";
		
		n = s.nextInt();
		
		if(n < 1)
			System.out.print(error);
		else
		{
			while(n >= 1)
			{
				count++;
				n /= 2;
			}
			System.out.print(count);
		}
		
		s.close();
	}
	
}