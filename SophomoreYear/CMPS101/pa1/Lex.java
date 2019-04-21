//-------------------------------------------------------------------
// Created by:	Bryan Lopez
// 		blopez24
//-------------------------------------------------------------------
// File:	Lex.java
//-------------------------------------------------------------------
import java.io.*;
import java.util.Scanner;


class Lex
{
	public static void main(String[] args) throws IOException
	{
		Scanner in = null;
                PrintWriter out = null;
                int n = 0, lines = 0;

                if(args.length < 2)
                {
                        System.err.println("Usage: Lex <input file> <output file>");
                        System.exit(1);
                }

                in = new Scanner(new File(args[0]));
                out = new PrintWriter(new FileWriter(args[1]));

                while( in.hasNextLine() )
                {
                        lines = lines + 1;
                        in.nextLine();
                }
                in.close();

                String[]  array = new String[lines];
                in = new Scanner(new File(args[0]));

                while( in.hasNextLine() )
                {
                        array[n] = in.nextLine();
                        n++;
                }	

		List A = new List();
		
		for(int i = 0; i < array.length; i++)
		{
			if(i == 0)
				A.prepend(i);

			else
			{
				String str1 = array[i];
				
				A.moveBack();
				while( A.index() >= 0 )
				{
					String str2 = array[A.get()];
					int compareValue = str1.compareTo(str2);	

					if(A.index() == 0 && compareValue < 0)
					{
						A.insertBefore(i);
						break;
					}
					else if(compareValue < 0)
					{
						A.movePrev();
					}
					else
					{
						A.insertAfter(i);
						break;
					}
				}
			} 
		}
		System.out.println(A);
		for(A.moveFront(); A.index() >= 0; A.moveNext())
		{
			out.println(array[A.get()]);
		}

		in.close();
		out.close();
	}
}
