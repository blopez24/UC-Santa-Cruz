import java.util.Scanner;

public class Ordered {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		int x, y, z;
		boolean ordered;
		
		x = s.nextInt();
		y = s.nextInt();
		z = s.nextInt();
		
		if(x < y && y < z)
			ordered = true;
		else if(z < y && y < x)
			ordered = true;
		else
			ordered = false;
		
		System.out.print(ordered);
		s.close();
	}
	
}