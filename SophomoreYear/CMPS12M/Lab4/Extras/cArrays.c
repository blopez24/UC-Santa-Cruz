/* --------------------------------------------------
 * Bryan Lopez
 * blopez24 (1612626)
 * --------------------------------------------------
 * cArrays.c
 * This gives an alternative method for traversing an
 * array, which is illustrated in the next example. 
 * -------------------------------------------------
 */

#include<stdio.h>
#include<stdlib.h>
#include<string.h>

int main(int argc, char* argv[])
{
	int i, n;
	int* A;
	
	/* check number of arguments on the command line */
	if(argc < 2)
	{
		printf("Usage: %s positive integers\n", argv[0]);
		exit(EXIT_FAILURE);
	}
	
	/* check that the command line argument is an integer */
	/* and if so, assign it to n */
	if( sscanf(argv[1], "%d", &n) < 1 || n < 1)
	{
		printf("Usage: %s positive integer\n", argv[0]);
		exit(EXIT_FAILURE);
	}
	
	/* allocate an array of n ints on the heap */
	A = calloc(n, sizeof(int));
	
	/* initialize the array using the standard subscript notation */
	for(int i = 0; i < n; i++)
		A[i] = 2*i+2;
	
	/* process the array using pointer arithmetic */
	for(i = 0; i < n; i++)
		printf("%d", *(A+i));
	
	free(A);
	A = NULL;
	
	return EXIT_SUCCESS;
}
