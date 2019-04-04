class Node
{
	ChessPiece data;
	Node next;
	
	Node(ChessPiece cp)
	{
		this.data = cp;
		this.next = null;
	}
	
	Node(ChessPiece cp, Node nextNode)
	{
		this.data = cp;
		this.next = nextNode;
	}
}