/* --------------------------------------------------------------------
 * Bryan Lopez
 * blopez24 (1612626)
 * --------------------------------------------------------------------
 * charType.c
 *
 * Takes two command line arguments giving the input and output file
 * names respectively, then classifies the characters on each line of
 * the input file into the following categories: alphabetic characters
 * (upper or lower case), numeric characters (digits 0-9), punctuation,
 * and white space(space, tab, or newline).  Any characters on a given
 * line of the input file that cannot be placed into one of these four
 * categories (such as control or non-printable characters) will be 
 * ignored.
 * --------------------------------------------------------------------
 */

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>
#include<assert.h>

/* Takes the input string s, and copies its characters into the appropriate output character arrays a (alphabetic), d (digits), p (punctuation), or w (whitespace). */
void extract_chars(char* s, char* a, char* d, char* p, char* w)
{
	/* Counters to keep track of location in s, a, d, p, and w */
	int i = 0, j = 0, k = 0, l = 0, m = 0;
	
	/* While s is not done/empty */
	while(s[i] != '\0')
	{
		if( isalpha((int)s[i]) )
		{
			a[j] = s[i];	
			j++;
		}
		else if( isdigit((int)s[i]) )
		{
			d[k] = s[i];
			k++;
		}
		else if( ispunct((int)s[i]) )
		{
			p[l] = s[i];
			l++;
		}
		else if( isspace((int)s[i]) )
		{
			w[m] = s[i];
			m++;
		}
		i++;
	}
	
	/* Assign the end of each a, d, p, w to be '\0' to indicate that the line has ended */
	a[j] = '\0';
	d[k] = '\0';
	p[l] = '\0';
	w[m] = '\0';
}

int main(int argc, char* argv[])
{
	FILE* in; /* file handle for input */
	FILE* out; /* file handle for output */
	
	char* line;
	char* alphabetic;
	char* digits;
	char* punctuation;
	char* whitespace;
	
	int maxLineSize = 256;	/* Max line length */ 
	int count = 1; /* To keep track of current line*/
	
	/* check command line for correct number of arguments */
	if(argc != 3)
	{
		printf("Usage: %s <input file> <output file>\n", argv[0]);
		exit(EXIT_FAILURE);
	}
	
	/* open input file for reading */
	in = fopen(argv[1], "r");
	if(in == NULL)
	{
		printf("Unable to read from file %s\n", argv[1]);
		exit(EXIT_FAILURE);
	}
	
	/* open output file for writing */
	out = fopen(argv[2], "w");
	if(out == NULL)
	{
		printf("Unable to write to file %s\n", argv[2]);
		exit(EXIT_FAILURE);
	}
	
	/* Allocates a block of heap memory sufficient to store and char array of length 256 + 1(to store the null variable '\0') */
	line = calloc(maxLineSize + 1, sizeof(char));
	alphabetic = calloc(maxLineSize + 1, sizeof(char));
	digits = calloc(maxLineSize + 1, sizeof(char));
	punctuation = calloc(maxLineSize + 1, sizeof(char));
	whitespace = calloc(maxLineSize + 1, sizeof(char));
	
	/* It reads a characters from in and  stores them in line until maxLineSize(256). Stops fgets once the end of file is reached */
	/* A terminating null character is automatically appended after the characters copied to line. */
	while(fgets(line, maxLineSize, in) != NULL)
	{
		/* Calls extract_chars to appropriately sort*/
		extract_chars(line, alphabetic, digits, punctuation, whitespace);

		fprintf(out, "line %d contains: \n", count);

		/* Checks whether "character" should be singular or plural */
		/* Prints out the length and line */
		if(strlen(alphabetic) == 1)
			fprintf(out, "%d alphabetic character: %s\n", (int)strlen(alphabetic), alphabetic);
		else
			fprintf(out, "%d alphabetic characters: %s\n", (int)strlen(alphabetic), alphabetic);
		if(strlen(digits) == 1)
			fprintf(out, "%d numeric character: %s\n", (int)strlen(digits), digits);
		else
			fprintf(out, "%d numeric characters: %s\n", (int)strlen(digits), digits);
		if(strlen(punctuation) == 1)
			fprintf(out, "%d punctuation character: %s\n", (int)strlen(punctuation), punctuation);
		else
			fprintf(out, "%d punctuation characters: %s\n", (int)strlen(punctuation), punctuation);
		if(strlen(whitespace) == 1)
			fprintf(out, "%d whitespace character: %s\n", (int)strlen(whitespace), whitespace);
		else
			fprintf(out, "%d whitespace characters: %s\n", (int)strlen(whitespace), whitespace);
		
		count++;
	}
	
	free(line);
	free(alphabetic);
	free(digits);
	free(punctuation);
	free(whitespace);
	
	/* close input and output files */
	fclose(in);
	fclose(out);
	
	return(EXIT_SUCCESS);
}
