//-------------------------------------------------------------------
// Created by:  Bryan Lopez
//              blopez24
//-------------------------------------------------------------------
// File:        List.java
//-------------------------------------------------------------------

class List
{
	private class Node
	{
		// Fields
		private int data;
		private Node prev;
		private Node next;

		// Constructor
		Node(int data)
		{
			this.data = data;
			this.prev = null;
			this.next = null;
		}

		// toString()
		// Overrides Object's toString() method.
		public String toString()
		{
			return String.valueOf(data);
		}

		// equals()
		// Overrides Object's equals() method.
		public boolean equals(Object x)
		{
			boolean eq = false;
			Node that;

			if(x instanceof Node)
			{
				that = (Node)x;
				eq = (this.data == that.data);
			}
			return eq;
		}
	}

	// Fields
	private Node front;
	private Node back;
	private Node cursor;
	private int length;
	private int index;

	// Constructor
	// Creates a new empty list.
	List()
	{
		front = back = cursor = null;
		this.length = 0;
		this.index = -1;
	}

	/* Access functions */

	// Returns the number of elements in this List
	int length()
	{
		return this.length;
	}
	
	// If cursor is defined, returns the index of the cursor element, 
	// otherwise returns -1
	int index()
	{
		if(this.cursor == null)
			return -1;
		return this.index;
	}

	// Returns front element.
	// Pre: length() > 0
	int front()
	{
		if(this.length == 0)
			throw new RuntimeException("List Error: front() called on empty List.");
		
		return this.front.data;
	}

	// Returns back element.
	// Pre: length() > 0
	int back()
	{
		if(this.length == 0)
			throw new RuntimeException("List Error: back() called on empty List.");

		return this.back.data;
	}

	// Returns cursor element.
	// Pre: length() > 0, index() >= 0
	int get()
	{
		//System.out.println("Debug: L:" + this.length + " I:" + this.index);
		
		if(this.length == 0)
			throw new RuntimeException("List Error: get() called on empty List.");
		if(this.index < 0)
			throw new RuntimeException("List Error: get() called on null cursor.");

		return this.cursor.data;
	}

	// Return true if and only if this List and L are the same integer
	// squence. The states of the cursors in the two Lists are not used in
	// determing equality.
	boolean equals(List L)
	{
		boolean eq = false;
		Node M, N;

		M = this.front;
		N = L.front;
		
		eq = (this.length == L.length);
		
		while( eq && M != null )
		{
			eq = M.equals(N);
			M = M.next;
			N = N.next;
		}		

		return eq;
	}

	/* Manipulation procedures */
	
	// Resets this List to its orignal empty state.
	void clear()
	{
		this.front = this.back = this.cursor = null;
		this.length = 0;
		this.index = -1;
	}

	// If List is non-empty, places the cursor under the front element,
	// otherwise does nothing.
	void moveFront()
	{
		if(this.length > 0)
		{
			this.cursor = this.front;
			this.index = 0;
		}
	}

	// If List is non-empty, places the cursor under the back element,
	// otherwise does nothing.
	void moveBack()
	{
		if(this.length > 0)
		{
			this.cursor = this.back;
			this.index = length - 1;
		}
	}
	
	// If cursor is defined and not at front, moves cursor one step toward
	// front of this List, if cursor is defined and at front, cursor
	// becomes undefined, if cursor is undefined does nothing.
	void movePrev()
	{
		if(this.cursor != null && this.index != 0)
		{
			this.cursor = cursor.prev;
			this.index = index - 1;
		}
		else if(this.cursor != null && index == 0)
		{
			this.cursor = null;
			this.index = -1;
		}
	}

	// If cursor is defined and not at back, moves cursor one step toward
	// back of this List, if cursor is defined and at back, cursor becomes
	// undefined, if cursor is undefined does nothing.
	void moveNext()
	{
		if(this.cursor != null && this.index != length - 1)
		{
			this.cursor = cursor.next;
			this.index = index + 1;
		}
		else if(this.cursor != null && index == length - 1)
		{
			this.cursor = null;
			this.index = -1;
		}
		else{}
	}

	// Insert new element into this List. If List is non-empty, insertion
	// takes place before front element.
	void prepend(int data)
	{
		Node N = new Node(data);
		
		if(this.length == 0)
		{
			back = N;
		}
		else if(this.length != 0)
		{
			N.next = front;
			front.prev = N;
		}

		this.front = N;
		this.length = length + 1;
		this.index = index + 1;
	}


	// Insert new element into this List. If List is non-empty, insertion
	// takes place after back element.
	void append(int data)
	{
		Node N = new Node(data);

		if(this.length == 0)
		{
			front = N;
		}
		else if(this.length != 0)
		{
			N.prev = back;
			back.next = N;
		}

		this.back = N;
		this.length = length + 1;
	}

	// Insert new element before cursor.
	// Pre: length() > 0, index() >= 0
	void insertBefore(int data)
	{
		if(this.length == 0)
			throw new RuntimeException("List Error: insertBefore() called on empty List.");
		if(this.index < 0)
                        throw new RuntimeException("List Error: get() called on null cursor.");		

		if(this.index == 0)
		{
			prepend(data);
		}
		else
		{
			Node N = new Node(data);

			N.next = cursor;
			N.prev = cursor.prev;
			this.cursor.prev.next = N;
			this.cursor.prev = N;
			
			this.index = index + 1;
			this.length = length + 1;
		}
	}

	// Inserts new element after cursor.
	// Pre: length() > 0, index() >= 0
	void insertAfter(int data)
	{
		if(this.length == 0)
			throw new RuntimeException("List Error: insertAfter() called on empty List.");
		if(this.index < 0)
                        throw new RuntimeException("List Error: get() called on null cursor.");

		Node N = new Node(data);

		if(this.index == length - 1)
		{
			append(data);
		}
		else
		{
			N.next = cursor.next;
			N.prev = cursor;
			this.cursor.next.prev = N;
			this.cursor.next = N;

			this.length = length + 1;
		}
	}

	// Deletes the front element.
	// Pre: length() > 0
	void deleteFront()
	{
		if(this.length == 0)
			throw new RuntimeException("List Error: deleteFront() called on empty List.");

		if(this.index == 0 || this.index == -1)
		{
			this.cursor = null;
			index = -1;
		}

		if(this.length == 1)
		{
			this.front = null;
			this.back = null;
			this.length = length - 1;
		}
		else
		{
			this.index = index - 1;
			this.front = front.next;
			this.front.prev = null;
			this.length = length - 1;
		}
	}

	// Deletes the back element.
	// Pre: length > 0
	void deleteBack()
	{
		if(this.length == 0)
			throw new RuntimeException("List Error: deleteBack() called on empty List.");
		

		if(this.index == length - 1 || this.index == -1)
		{
			this.cursor = null;
			index = -1;
		}
	
		if(this.length == 1)
		{
			this.front = null;
			this.back = null;
			this.length = length - 1;
		}		
		else
		{
			this.back = back.prev;
			this.back.next = null;
			this.length = length - 1;
		}
	}

	// Deletes cursor element, making cursor undefined.
	// Pre: length() > 0, index() >= 0
	void delete()
	{
		if(this.length == 0)
			throw new RuntimeException("List Error: delete() called on empty List.");
		if(this.index < 0)
                        throw new RuntimeException("List Error: get() called on null cursor.");

		if(this.index == 0)
		{
			deleteFront();
			
		}
		else if(this.index == length - 1)
		{
			deleteBack();	
		}
		else
		{
			this.cursor.prev.next = cursor.next;
			this.cursor.next.prev = cursor.prev;

			this.cursor.next = null;
			this.cursor.prev = null;
			this.cursor = null;
			
			this.index = -1;
			this.length = length - 1;
		}
	}

	/* Other methods */
	
	// Overrides Object's toString method. Returns a String representation
	// of this List consisting of a space separated sequence of integers,
	// with front on left.
	public String toString()
	{
		if(this.length == 0)
			return "";
		
		String str = "";
		Node N = this.front;

		while( N != null )
		{
			str += N.toString() + " ";
			N = N.next;
		}
		return str;
	}

	// Returns a new List representing the same integer sequence as this
	// List. The cursor in the new list is undefined, regardless of the
	// state of the cursor in this List. This List is unchanged.
	List copy()
	{
		List newList = new List();
		Node N = this.front;

		if(this.length != 0)
		{
			while( N != null)
			{
				newList.append(N.data);
				N = N.next;
			}
		}
		return newList;
	}
}
