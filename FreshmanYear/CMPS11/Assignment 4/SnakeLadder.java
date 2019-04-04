import java.util.Random;

class Game
{
	Board board;
	Player[] players;
	int count = 1;
	int divisor;
	
	public Game(Board board, Player[] players)	//Clear
	{
		this.board = board;
		this.players = players;
		this.divisor = players.length;
	}
	
	public Game(Player[] players)
	{
		board = new Board(10);
		this.players = players;
		this.divisor = players.length;
	}
	
	public Player currentPlayer()
	{
		int indexOfCurrentPlayer = count % divisor;
		
		if(indexOfCurrentPlayer == 0)
			return players[players.length - 1];
		else if(indexOfCurrentPlayer == 1)
			return players[0];
		else if(indexOfCurrentPlayer == 2)
			return players[1];
		else 
			return players[2];
	}
	
	public void addPlayer(Player p)	//Clear
	{
		divisor++;
		Player[] temp = new Player[players.length + 1];
		for(int i = 0; i < players.length; i++)
		{
			temp[i] = players[i];
		}
		temp[temp.length - 1] = new Player(p.getName());
		p.setPosition(0);
		
		players = temp;
	}
	
	public boolean winner()		//Not Clear
	{
		if(currentPlayer().getPosition() == getBoard().board.length - 1)
			return true;
		else 
			return false;
	}
	
	public void movePlayer(int n)	//Not clear
	{
		currentPlayer().setPosition(currentPlayer().getPosition() + n);
		if(currentPlayer().getPosition() > getBoard().board.length - 1)
			currentPlayer().setPosition(0);
		count++;
	}
	
	public boolean play(int moveCount)	//Not clear
	{
		movePlayer(moveCount);
		return winner();
	}
	
	public Board getBoard()	//Not clear
	{
		return board;
	}
}

class Player
{
	String name;
	Cell cell = new Cell(0);
	
	public Player(String name)
	{
		this.name = name;
	}
	
	public void setPosition(int position)
	{
		this.cell = new Cell(position);
	}
	
	public int getPosition()
	{
		return cell.getNumber();
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String toString()
	{
		return this.name + " @ " + cell.getNumber();
	}
}

class Cell
{
	int cellNumber;
	boolean cellOccupied;
	Ladder ladder = null;
	Snake snake = null;
	
	public Cell(int number)
	{
		this.cellNumber = number;
	}
	
	public void setOccupied(boolean occupied)
	{
		this.cellOccupied = occupied;
	}
	
	public boolean isOccupied()
	{
		return this.cellOccupied;
	}
	
	public Ladder getLadder()
	{
		return this.ladder;
	}
	
	public Snake getSnake()	
	{
		return this.snake;
	}
	
	public void setLadder(Ladder ladder)
	{
		this.ladder = new Ladder(cellNumber, ladder.getTop());
	}
	
	public void setSnake(Snake snake)
	{
		this.snake = new Snake(cellNumber, snake.getTail());
	}
	
	public int getNumber()
	{
		return this.cellNumber;
	}
}

class Board	
{
	Cell[] board;
	
	public Board(int n)
	{
		board = new Cell[n *n];
		for(int i = 0; i < board.length; i++)
			board[i] = new Cell(i);
	}
	
	public void setCellToLadder(int startPosition, int endPosition)
	{	
		board[startPosition].setLadder(new Ladder(startPosition, endPosition));
	}
	
	public void setCellToSnake(int headPosition, int tailPosition)
	{
		board[headPosition].setSnake(new Snake(headPosition, tailPosition));
	}
	
	public Cell[] getCells()
	{
		return board;
	}
}

class Snake
{
	int headPosition;
	int tailPosition;
	
	public Snake(int headPosition, int tailPosition)
	{
		this.headPosition = headPosition;
		this.tailPosition = tailPosition;
	}
	
	public int getTail()
	{
		return this.tailPosition;
	}
	
	public String toString()
	{
		return this.headPosition + " - " + this.tailPosition;
	}
}

class Ladder
{
	int startPosition;
	int endPosition;
	
	public Ladder(int startPosition, int endPosition) 
	{
		this.startPosition = startPosition;
		this.endPosition = endPosition;
	}
	
	public int getTop()
	{
		return this.endPosition;
	}
	
	public String toString()
	{
		return this.startPosition + " - " + this.endPosition;
	}
}

public class SnakeLadder 
{
	public static void main(String[] args)
	{  
		Player[] players = new Player[3];   
		players [0] = new Player("abc");   
		players [1] = new Player("def");   
		players [2] = new Player("ghi");   
		Board myBoard = new Board(5);   
		myBoard.setCellToLadder(7, 17);   
		myBoard.setCellToLadder(5, 14);   
		myBoard.setCellToSnake(19, 8);   
		myBoard.setCellToSnake(16, 4);
		Game game = new Game(myBoard, players);  
		Player p = new Player("jkl");
		game.addPlayer(p );   
		boolean b = false;  
		Random random = new Random();   
		int n;  while (!b) 
		{    
			n = random.nextInt(6) + 1;    
			b = game.play(n); 
			System.out.println(players[0].getName() + "\t" + players[0].getPosition());	//Erase Later
			System.out.println(players[1].getName() + "\t" + players[1].getPosition());	//Erase Later
			System.out.println(players[2].getName() + "\t" + players[2].getPosition());	//Erase Later
			System.out.println(p.getName() + "\t" + p.getPosition());
		}   
		System. out.println(game.currentPlayer().getPosition() + "\t" + game.currentPlayer().getName());	//Erase Part of it Later
	}  
}
