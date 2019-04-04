class CPLinkedList
{
	Node head;
	int length;
	
	CPLinkedList()
	{
		this.head = null;
		this.length = 0;
	}
	
	int size()
	{
		return length;
	}
	
	Node find(int index)
	{
		Node current = head;
		
		for(int i = 0; i < index; i++)
		{
			current = current.next;
		}
		return current;
	}
	
	void add(int index, ChessPiece cp)
	{
		if(index >= 0 && index < length + 1)
		{
			if(index == 0)
			{
				Node newNode = new Node(cp, head);
				head = newNode;
			}
			else
			{
				Node previous = find(index-1);
				
				Node newNode = new Node(cp, previous.next);
				previous.next = newNode;
			}
			length++;
		}
	}
	
	public void remove(int index)
	{
		if(index == 0)
			head = head.next;
		else
		{
			Node prev = find(index-1);
			Node curr = prev.next;
			prev.next = curr.next;
		}
		length--;
	}
	
	public void removeAll()
	{
		head = null;
		length = 0;
	}
	
	Object get(int index)
	{
		if(index >= 0 && index < length)
		{
			Node current = find(index);
			ChessPiece cpData = current.data;
			return cpData;
		}
		
		return null;
	}
}