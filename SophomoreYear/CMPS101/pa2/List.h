//-----------------------------------------------------------------------------
// Created by:  Bryan Lopez
//              blopez24
//-----------------------------------------------------------------------------
// File:        List.h
//		Header file for List ADT
//-----------------------------------------------------------------------------

#ifndef _LIST_H_INCLUDE_
#define _LIST_H_INCLUDE_

// Exported type --------------------------------------------------------------
typedef struct ListObj* List;


// Constructors-Destructors ---------------------------------------------------

// newList()
// Returns reference to new empty List object.
List newList(void);

// freeList()
// Frees all heap memory associated with List *pL, and sets *pL to NULL.
void freeList(List* pL);


// Access functions -----------------------------------------------------------

// length()
// Returns the length of L
// Pre: !isEmpty(L)
int length(List L);

// index()
// If cursor id defined, returns the index of the cursor element, otherwise
// returns -1.
int index(List L);

// front()
// Returns the value at the front of L.
// Pre: !isEmpty.
int front(List L);

// back()
// Returns the value at the back of L.
// Pre: !isEmpty.
int back(List L);

// get()
// Returns cursor element.
// Pre: !isEmpty(L), index() >= 0
int get(List L);

// equals()
// Returns true (1) if A is identical to B, false (0) otherwise.
int equals(List A, List B);

// isEmpty()
// Returns true (1) if L is empty, otherwise returns false (0)
int isEmpty(List L);

// isUndefined()
// Returns true (1) if cursor is undefined otherwise returns false (0)
int isUndefined(List L);

// Manipulation procedures ----------------------------------------------------

// clear()
// Resets this List to its original empty state.
void clear(List L);

// moveFront()
// If List is non-empty, places the cursor under the front element, otherwise
// does nothing.
void moveFront(List L);

// moveBack()
// If List is non-empty, places the cursor under the back element, otherwise
// does nothing.
void moveBack(List L);

// movePrev()
// If cursor is defined and not at front, moves cursor one step toward front of
// this List, if cursor is defined and at front, cursor becomes undefined, if
// cursor is undefined does nothing.
void movePrev(List L);

// moveNext()
// If cursor is defined and not at back, move cursor one step toward back of
// this List, if cursor is defined and at back, cursor becomes undefined, if
// cursor is undefined does nothing.
void moveNext(List L);

// prepend()
// Insert new element into this List. If List is non-empty, insertion takes
// place before front element. 
void prepend(List L, int data);

// append()
// Insert new element into this List. If List is non-empty, insertion takes
// place after back element.
void append(List L, int data);

// insertBefore()
// Insert new element before cursor.
// Pre: !isEmpty(L), index() >= 0
void insertBefore(List L, int data);

// insertAfter()
// Insert new element after cursor.
// Pre: !isEmpty(L), index() >= 0
void insertAfter(List L, int data);

// deleteFront()
// Deletes the front element.
// Pre: !isEmpty(L)
void deleteFront(List L);

// deleteBack()
// Deletes the back element.
// Pre: !isEmpty(L)
void deleteBack(List L);

// delete()
// Deletes cursor element, making cursor undefined.
// Pre: !isEmpty(L), index() >= 0
void delete(List L);


// Other operations -----------------------------------------------------------

// printList()
// Prints the values in List L with front on left to the file pointed to by
// out, consisting of a space separated sequence of integers.
void printList(FILE* out, List L);

// copyList()
// Returns a new List which is the concatenation of this list followed by L.
// The cursor in the new List is undefined, regardless of the states of the
// cursors in this List and L. The states of this List and L are unchanged.
List copyList(List L);

#endif
