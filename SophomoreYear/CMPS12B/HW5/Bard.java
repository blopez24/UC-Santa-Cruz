/*
 * Bryan Lopez
 * blopez24 (1612626)
 * Word analysis of all the compositions of William Shakespeare. On running make, it 
 * should create Bard.jar. Should run the jar with two command line arguments: 
 * the first is an input file, the second is the output file. Each line of the input file 
 * corresponds to a new query. The query is a pair of numbers: LENGTH RANK. The first 
 * number is the length of the word, the second number is the rank, which starts from 0.
 * The ranking is done in decreasing order of frequency, and increasing lexicographic 
 * order. Thus, if two words have the same frequency, then the word that is earlier 
 * lexicographically has the smaller (earlier) rank.
 */


import java.io.*;
import java.util.*;

public class Bard 
{	
	static Hashtable<String, Integer> frequency = new Hashtable<String, Integer>();
	static Hashtable<String, Integer> length = new Hashtable<String, Integer>();
	static ArrayList<String> words = new ArrayList<String>();
	
	public static void main(String[] args) throws IOException
	{
		shakespeare();
		
		if(args.length < 2)
		{
			System.out.println("Usage: java -jar Bard.jar <input file> <output file>");
			System.exit(1);
		}
		
		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new FileWriter(args[1]));
		
		while(in.hasNextLine())
		{
			
			String line = in.nextLine();
			Scanner input = new Scanner(line);
			
			while(input.hasNext())
			{
				int wordLength = in.nextInt();
				int rank = in.nextInt();
				
				ArrayList<String> qWords = new ArrayList<String>();
				Set<String> keys = length.keySet();
				
				for(String key: keys)
				{
					if(length.get(key) == wordLength)
					{
						qWords.add(key);
					}
				}
				
				if(rank >= qWords.size())
	        	{
	        		out.println("~");
	        		
	        		break;
	        	}
	        	
	        	if(qWords.isEmpty())
	        	{
	        		out.println("~");
	        		break;
	        	}
				
	        	
	        	
	        	for (int i = 0; i < rank+1; i++) 
	        	{
	        		String mFrequent = qWords.get(0);
	        		for (int j = 0; j < qWords.size(); j++) 
	        		{
	        			if (frequency.get(qWords.get(j)) > frequency.get(mFrequent))
	        				mFrequent = qWords.get(j);
	        		}
	        		int index = qWords.indexOf(mFrequent);
	        		
	        		if(i == rank)
	        			out.print(mFrequent);
	        		
	        		
	        		qWords.remove(index);
	        	}
	        	out.println();
				
			}
			input.close();
		}
		in.close();
		out.close();
	}
	
	private static void shakespeare() throws IOException
	{
		Scanner shakespeareFile = new Scanner(new File("shakespeare.txt"));
		
		while(shakespeareFile.hasNextLine())
		{
			String line = shakespeareFile.nextLine().trim() + " ";
			String splitters = "[\\s\\?\\,\\.\\!\\:\\;\\[\\]]+";
			String[] w = line.split(splitters);
			
			for(int i = 0; i < w.length; i++)
			{
				words.add(w[i].toLowerCase());
			}
			
		}
		
		for(int i = 0; i < words.size(); i++)
		{
			String word = words.get(i);
			if(frequency.containsKey(word))
			{
				frequency.put(word, frequency.get(word) + 1);
				length.put(word, word.length());
			}
			else
			{
				frequency.put(word, 1);
				length.put(word, word.length());
			}
		}
		shakespeareFile.close();
	}
}
