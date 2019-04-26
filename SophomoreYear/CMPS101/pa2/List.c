//-----------------------------------------------------------------------------
// Created by:  Bryan Lopez
//              blopez24
//-----------------------------------------------------------------------------
// File:        List.c
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include "List.h"


// Structs --------------------------------------------------------------------

// private NodeObj type
typedef struct NodeObj
{
	int data;
	struct NodeObj* next;
	struct NodeObj* prev;
} NodeObj;

// private Node type
typedef NodeObj* Node;

// private ListObj type
typedef struct ListObj
{
	Node front;
	Node back;
	Node cursor;
	int length;
	int index;
} ListObj;


// Constructors-Destructors ---------------------------------------------------

// newNode()
// Returns reference to new Node object. Initializes next and data fields.
// Private.
Node newNode(int data)
{
	Node N = malloc(sizeof(NodeObj));
	N->data = data;
	N->next = NULL;
	N->prev = NULL;
	return(N);
}

// freeNode()
// Frees heap memory pointed to by *pN, sets *pN to NULL.
// Private.
void freeNode(Node* pN)
{
	if( pN != NULL && *pN != NULL )
	{
		free(*pN);
		*pN = NULL;
	}
}

// newList()
// Returns reference to new empty List object.
List newList(void)
{
	List L;
	L = malloc(sizeof(ListObj));
	L->front = L->back = L->cursor = NULL;
	L->length = 0;
	L->index = -1;
	return(L);
}

// freeList()
// Frees all heap memory associated with List *pL, and sets *pL to
// NULL.S
void freeList(List* pL)
{
	if( pL != NULL && *pL != NULL )
	{
		while( !isEmpty(*pL) )
		{
			deleteFront(*pL);
		}
		free(*pL);
		*pL = NULL;
	}
}


// Access functions -----------------------------------------------------------

// length()
// Returns the length of L
int length(List L)
{
	if( L == NULL )
	{
		printf("List Error: calling length() on NULL List reference\n");
		exit(1);
	}

	return(L->length);
}

// index()
// If cursor id defined, returns the index of the cursor element, otherwise
// returns -1.
int index(List L)
{
	if( L == NULL )
	{
		printf("List Error: calling index() on NULL List reference\n");
		exit(1);
	}

	if( !isUndefined(L) )
	{
		return(L->index);
	}
	return -1;
}

// front()
// Returns the value at the front of L.
// Pre: !isEmpty.
int front(List L)
{
	if( L == NULL )
	{
		printf("List Error: calling front() on NULL List reference\n");
		exit(1);
	}
	if( isEmpty(L) )
	{
		printf("List Error: calling front() on empty List\n");
		exit(1);
	}

	return(L->front->data);
}

// back()
// Returns the value at the back of L.
// Pre: !isEmpty.
int back(List L)
{
	if( L == NULL )
	{
		printf("List Error: calling back() on NULL List reference\n");
		exit(1);
	}
	if( isEmpty(L) )
	{
		printf("List Error: calling back() on empty List\n");
		exit(1);
	}

	return(L->back->data);
}

// get()
// Returnis cursor element.
// Pre: !isEmpty(L), !isUndefined(L)
int get(List L)
{
	if( L == NULL )
	{
		printf("List Error: calling get() on NULL List reference\n");
                exit(1);
        }
        if( isEmpty(L) )
        {
                printf("List Error: calling get() on empty List\n");
                exit(1);
        }
	if( isUndefined(L) )
	{
		printf("List Error: calling get() on undefined cursor\n");
		exit(1);
	}

        return(L->cursor->data);
}

// equals()
// Returns true (1) if A is identical to B, false (0) otherwise.
int equals(List A, List B)
{
	if( A == NULL || B == NULL )
	{
		printf("List Error: calling equals() on NULL List reference\n");
		exit(1);
	}

	int eq = 0;
	Node N = NULL;
	Node M = NULL;
	
	eq = ( A->length == B->length );
	N = A->front;
	M = B->front;

	while( eq && N != NULL )
	{
		eq = (N->data == M->data);
		N = N->next;
		M = M->next;
	}
	return eq;
}

// isEmpty()
// Returns true (1) if L is empty, otherwise returns false (0)
int isEmpty(List L)
{
	if( L == NULL )
	{
		printf("List Error: calling isEmpty() on NULL List reference\n");
		exit(1);
	}

	return(L->length == 0);
}

// isUndefined()
// Returns true (1) if cursor is undefined otherwise returns false (0)
int isUndefined(List L)
{
	if( L == NULL )
	{
		printf("List Error: calling isUndefined() on NULL List reference\n");
		exit(1);
	}

	return(L->index == -1);
}

// Manipulation procedures ----------------------------------------------------

// clear()
// Resets this List to its original empty state.
void clear(List L)
{
	if( L == NULL )
	{
		printf("List Error: calling clear() on NULL List reference\n");
		exit(1);
	}

	while( !isEmpty(L) )
	{
		deleteFront(L);
	}
}

// moveFront()
// If List is non-empty, places the cursor under the front element, otherwise
// does nothing.
void moveFront(List L)
{
	if( L == NULL )
	{
		printf("List Error: calling moveFront() on NULL List reference\n");
		exit(1);
	}

	if( !isEmpty(L) )
	{
		L->cursor = L->front;
		L->index = 0;
	}
}

// moveBack()
// If List is non-empty, places the cursor under the back element, otherwise
// does nothing.
void moveBack(List L)
{
	if( L == NULL )
	{
		printf("List Error: calling moveBack() on NULL List reference\n");
		exit(1);
	}

	if( !isEmpty(L) )
	{
		L->cursor = L->back;
		L->index = (L->length - 1);
	}
}

// movePrev()
// If cursor is defined and not at front, moves cursor one step toward front of
// this List, if cursor is defined and at front, cursor becomes undefined, if
// cursor is undefined does nothing.
void movePrev(List L)
{
	if( L == NULL )
	{
		printf("List Error: calling movePrev() on NULL List reference\n");
		exit(1);
	}

	if( !isUndefined(L) && L->index != 0 )
	{
		L->cursor = (L->cursor->prev);	
		L->index = (L->index - 1);	
	}
	else if( !isUndefined(L) && L->index == 0 )
	{
		L->cursor = NULL;
		L->index = -1;
	}
}

// moveNext()
// If cursor is defined and not at back, move cursor one step toward back of
// this List, if cursor is defined and at back, cursor becomes undefined, if
// cursor is undefined does nothing.
void moveNext(List L)
{
	if( L == NULL )
	{
		printf("List Error: calling moveBack() on NULL List reference\n");
		exit(1);
	}

	if( !isUndefined(L) && L->index != (L->length - 1) )
	{
		L->cursor = L->cursor->next;
		L->index = (L->index + 1);
	}
	else if( !isUndefined(L) && L->index == (L->length - 1) )
	{
		L->cursor = NULL;
		L->index = -1;
	}
}

// prepend()
// Insert new element into this List. If List is non-empty, insertion takes
// place before front element. 
void prepend(List L, int data)
{
	if( L == NULL )
	{
		printf("List Error: calling prepend() on NULL List reference\n");
		exit(1);
	}
	
	Node N = newNode(data);
	if( isEmpty(L) )
	{
		L->back = N;
	}
	else
	{
		if( !isUndefined(L) )
		{
			L->index = (L->index + 1);
		}
		N->next = L->front;
		L->front->prev = N;
	}
	L->front = N;
	L->length = (L->length + 1);
}

// append()
// Insert new element into this List. If List is non-empty, insertion takes
// place after back element.
void append(List L, int data)
{
	if( L == NULL )
	{
		printf("List Error: calling append() on NULL List reference\n");
		exit(1);
	}
	
	Node N = newNode(data);
	if( isEmpty(L) )
	{
		L->front = N;
	}
	else
	{
		N->prev = L->back;
		L->back->next = N;
	}
	L->back = N;
	L->length = (L->length + 1);
}	

// insertBefore()
// Insert new element before cursor.
// Pre: !isEmpty(L), !isUndefined(L)
void insertBefore(List L, int data)
{
	if( L == NULL )
	{
		printf("List Error: calling insertBefore() on NULL List reference\n");
		exit(1);
	}
	if( isEmpty(L) )
	{
		printf("List Error: calling insertBefore() on empty List\n");
		exit(1);
	}
	if( isUndefined(L) )
	{
		printf("List Error: calling insertBefore() on undefined cursor\n");
		exit(1);
	}
	
	if( L->index == 0 )
	{
		prepend(L, data);
	}
	else
	{
		Node N = newNode(data);
		N->next = L->cursor;
		N->prev = L->cursor->prev;
		L->cursor->prev->next = N;
		L->cursor->prev = N;

		L->index = (L->index + 1);
		L->length = (L->length + 1);
	}
}

// insertAfter()
// Insert new element after cursor.
// Pre: !isEmpty(L), index() >= 0
void insertAfter(List L, int data)
{
	if( L == NULL )
        {
                printf("List Error: calling insertAfter() on NULL List reference\n");
                exit(1);
        }
        if( isEmpty(L) )
        {
                printf("List Error: calling insertAfter() on empty List\n");
                exit(1);
        }
        if( isUndefined(L) )
        {
                printf("List Error: calling insertAfter() on undefined cursor\n");
                exit(1);
        }

        if( L->index == (L->length - 1)  )
        {
                append(L, data);
        }
        else
        {
                Node N = newNode(data);
                N->prev = L->cursor;
		N->next = L->cursor->next;
                L->cursor->next->prev = N;
                L->cursor->next = N;
		
                L->length = (L->length + 1);
        }	
}

// deleteFront()
// Deletes the front element.
// Pre: !isEmpty(L)
void deleteFront(List L)
{
	if( L == NULL )
	{
		printf("List Error: calling deleteFront() on NULL List reference\n");
		exit(1);
	}
	if( isEmpty(L) )
	{
		printf("List Error: calling deleteFront() on empty List\n");
		exit(1);
	}
	
	if( L->index == 0 )
	{
		L->cursor = NULL;
		L->index = -1;
	}

	Node N = L->front;
	if( L->length == 1)
	{
		L->front = NULL;
		L->back = NULL;
		L->length = 0;
		freeNode(&N);
	}
	else
	{
		if( !isUndefined(L) )
		{
			L->index = (L->index - 1);
		}
		L->front = L->front->next;
		L->front->prev = NULL;
		L->length = (L->length - 1);
		freeNode(&N);
	}
}

// deleteBack()
// Deletes the back element.
// Pre: !isEmpty(L)
void deleteBack(List L)
{
	if( L == NULL )
        {
                printf("List Error: calling deleteBack() on NULL List reference\n");
                exit(1);
        }
        if( isEmpty(L) )
        {
                printf("List Error: calling deleteBack() on empty List\n");
                exit(1);
        }

        if( L->index == (L->length - 1)  )
        {
                L->cursor = NULL;
                L->index = -1;
	}

	Node N = L->back;
	if( L->length == 1 )
	{
		L->front = NULL;
		L->back = NULL;
		L->length = 0;
		freeNode(&N);
	}
	else
	{
		L->back= L->back->prev;
		L->back->next = NULL;
		L->length = (L->length - 1);
		freeNode(&N);
	}
}

// delete()
// Deletes cursor element, making cursor undefined.
// Pre: !isEmpty(L), index() >= 0
void delete(List L)
{
	if( L == NULL )
        {
                printf("List Error: calling delete() on NULL List reference\n");
                exit(1);
        }
        if( isEmpty(L) )
        {
                printf("List Error: calling delete() on empty List\n");
                exit(1);
        }
	if( isUndefined(L) )
	{
		printf("List Error: calling delete() on undefined cursor\n");
		exit(1);
	}	
	
	if( L->index == 0 )
	{
		deleteFront(L);
	}
	else if( L->index == (L->length - 1) )
	{
		deleteBack(L);
	}
	else
	{
		Node N = L->cursor;

		L->cursor->prev->next = L->cursor->next;
		L->cursor->next->prev = L->cursor->prev;

		L->cursor->next = NULL;
		L->cursor->prev = NULL;
		L->cursor = NULL;

		L->index = -1;
		L->length = (L->length - 1);
		
		freeNode(&N);
	}
}


// Other operations -----------------------------------------------------------

// printList()
// Prints the values in List L with front on left to the file pointed to by
// out, consisting of a space separated sequence of integers.
void printList(FILE* out, List L)
{
	if( L == NULL )
	{
		printf("List Error: calling printList() on NULL List reference\n");
		exit(1);
	}
	Node N = L->front;

	while( N != NULL )
	{
		fprintf(out, "%d ", N->data);
		N = N->next;
	}
}

// copyList()
// Returns a new List which is the concatenation of this list followed by L.
// The cursor in the new List is undefined, regardless of the states of the
// cursors in this List and L. The states of this List and L are unchanged.
List copyList(List L)
{
	if( L == NULL )
	{
		printf("List Error: calling copyList() on NULL List reference\n");
		exit(1);
	}
	
	List nL = newList();

	Node N = L->front;

	if( !isEmpty(L) )
	{
		while( N != NULL )
		{
			append(nL, N->data);
			N = N->next;
		}
	}

	nL->cursor = NULL;
	nL->index = -1;

	return nL;
}
