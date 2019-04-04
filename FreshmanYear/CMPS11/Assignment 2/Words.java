import java.util.Scanner;

public class Words {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		int num = s.nextInt();
		
		String[] w = new String[num];
		
		for(int i = 0; i < w.length; i++)
		{
			w[i] = s.next();
		}
		
		int count = 0;
		
		for(int i = 0; i < num; i++)
		{
			if(doesNotRepeat(w[i]))
				count++;
		}
		
		System.out.println(count);
		
		s.close();
	}

	private static boolean doesNotRepeat(String word) {
		for(int i = 0; i < word.length(); i++)
		{
			for(int j = i + 1; j < word.length(); j++)
			{
				if(word.charAt(i) == word.charAt(j))
					return false;
			}
		}
		return true;
	}

}
