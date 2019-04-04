
/*
 * ------------------
 * Bryan Lopez
 * blopez24 (1612626)
 * ------------------
 * fileReverse.c
 * Takes two command line arguments naming the input and output file.
 * Reads each word from the input file, then prints it backwards on a line
 * by itself.
 */

#include<stdio.h>
#include<stdlib.h>
#include<string.h>

/* Reverses the string argument */
void stringReverse(char* s){
	int i;					/* Starting index */
	int j = strlen(s) - 1;	/* Length of s - 1 */
	char temp;				/* Temporary holds a letter */
	
	for(i = 0; i < j; i++){
		temp = s[i];		/* Holds letter at i */
		s[i] = s[j];		/* Swaps letter at i with j */
		s[j] = temp;		/* String at j is assign to temp */
		j--;
	}
}

int main(int argc, char* argv[]){
	FILE* in;		/* file handle for input */
	FILE* out;		/* file handle for output */
	char word[256];	/* char array to store words from input file */
	
	/* check command line for correct number of arguments */
	if( argc != 3){
		printf("Usage: %s <input file> <output file>\n", argv[0]);
		exit(EXIT_FAILURE);
	}
	
	/* open input file for reading */
	in = fopen(argv[1], "r");
	if( in == NULL ){
		printf("Unable to read from file %s\n", argv[1]);
		exit(EXIT_FAILURE);
	}
	
	/* open output file for writing */
	out = fopen(argv[2], "w");
	if( out==NULL ){
		printf("Unable to write to file %s\n", argv[2]);
		exit(EXIT_FAILURE);
	}
	
	/* read words from input file, uses stringReverse(), and prints on separate lines to output file */
	while( fscanf(in, " %s", word) != EOF ){
		stringReverse(word);
		fprintf(out, "%s\n", word);
	}

	/* close input and output files */
	fclose(in);
	fclose(out);
	
	return(EXIT_SUCCESS);
}
