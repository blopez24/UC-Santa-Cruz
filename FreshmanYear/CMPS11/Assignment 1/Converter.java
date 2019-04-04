import java.util.Scanner;

public class Converter {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		String letter;
		double num1;
		double num2;
		
		letter = s.next();
		
		num1 = s.nextDouble();
		
		if(letter.equals("k"))
		{
			num2 = num1 - 273.15;
			System.out.printf("%.2f",  num2);
		}
		if(letter.equals("f"))
		{
			num2 = (num1-32) * 5/9;
			System.out.printf("%.2f",num2 );
		}
		
		s.close();
	}

}