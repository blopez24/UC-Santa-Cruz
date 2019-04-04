//--------------------------------------------------------------------------
// Bryan Lopez
// blopez24 (1612626)
//--------------------------------------------------------------------------
// FileReverse.java
// Takes two command line arguments. Reads each line of input, parse the tokens, then prints each token backwards to the output file on a line by itself.
//--------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

class FileReverse 
{
	public static void main(String[] args) throws IOException
	{
		//  Check number of command line arguments is at least 2
		if(args.length < 2)
		{
			System.out.println("Usage: java –jar FileReverse.jar <input file> <output file>");
			System.exit(1);
			}

		// Open files
		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new FileWriter(args[1]));
		
		// Read lines from in, extract and print tokens from each line
		while( in.hasNextLine() )
		{
			// Trim leading and trailing spaces, then add one trailing space so split works on blank lines
			String line = in.nextLine().trim() + " "; 
			
			// Split line around white space 
			String[] token = line.split("\\s+");  

			// Print out tokens       
			int n = token.length;
              
			for(int i=0; i<n; i++)
			{
				out.println(stringReverse(token[i]));
			}
		}

            // Close files
            in.close();
            out.close();
         }

	// Returns a string that is the reversal of s
	public static String stringReverse(String s)
	{
		// Checks if s is at least 1 
		if(s.length() <= 1)
			return s;
		// Returns last letter and calls the method again without the last letter
		return s.charAt(s.length()-1) + stringReverse(s.substring(0, s.length()-1));
	}	
}
