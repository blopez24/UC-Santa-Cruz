//-----------------------------------------------------------------------------
// Created by:	Bryan Lopez
//				blopez24
//-----------------------------------------------------------------------------
// File:		Lex.c
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include "List.h"

#define MAX_LEN 1024

int main(int argc, char * argv[])
{
	int n = 0, count = 0;
	FILE *in, *out;
	char line[MAX_LEN];

	// check command line for correct number of arguments
	if( argc != 3 )
	{
		printf("Usage: %s <input file> <output file>\n", argv[0]);
		exit(1);
	}

	// opens files for reading and writing
	in = fopen(argv[1], "r");
	out = fopen(argv[2], "w");
	
	if( in == NULL )
	{
		printf("Unable to open file %s for reading\n", argv[1]);
		exit(1);
	}
	if( out == NULL )
	{
		printf("Unable to open file %s for writing\n", argv[2]);
		exit(1);
	}

	/* count each line in input file */
	while( fgets(line, MAX_LEN, in) != NULL )
	{
		count++;
	}
	fclose(in);
	
	in = fopen(argv[1], "r");
	
	char words[count][MAX_LEN];
	
	while( fgets(line, MAX_LEN, in) != NULL )
	{
		strcpy(words[n], line);
		n++;
	}

	List A = newList();
	prepend(A, 0);
	
	for(int i = 1; i < n; i++)
	{
		char* str1 = words[i];
		
		moveBack(A);
		while( index(A) >= 0 )
		{
			char* str2 = words[get(A)];
			int compareValue = strcmp(str1, str2);
			
			if( index(A) == 0 && compareValue < 0 )
			{
				insertBefore(A, i);
				break;
			}
			else if( compareValue < 0 )
			{	
				movePrev(A);
			}
			else
			{	
				insertAfter(A, i);
				break;
			}
		}
	}
	
	for(moveFront(A); index(A) >= 0; moveNext(A))
	{
		fprintf(out, "%s", words[get(A)]);
	}
	
	freeList(&A);
	
	/* close files */
	fclose(in);
	fclose(out);

	return(0);
}
