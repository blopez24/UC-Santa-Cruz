package winter18.assignment3;

public class Bingo 
{
	Player[] players = new Player[2];
	
	public Bingo(Player[] players)
	{
		this.players = players;
	}
	
	public String play(int number)
	{
		String winner = "";
		
		for(int i = 0; i < players.length; i++)
		{
			players[i].markNumber(number);
			
			if(players[i].isWinner() && i != players.length - 1)
				winner += players[i].getName() + " ";
			
			else if(players[i].isWinner() && i == players.length - 1)
				winner += players[i].getName();
		}
		return winner;
	}
}

class Player
{
	String name = "";
	Card[] cards = new Card[2];
	
	public Player(String name, Card[] cards)
	{
		this.name = name;
		this.cards = cards;
	}
	
	public String getName()
	{
		return name;
	}
	
	public boolean isWinner()
	{
		int counter = 0;
		
		for(int i = 0; i < cards.length; i++)
		{
			for(int r = 0; r < 5; r++)
			{
				for(int c = 0; c < 5; c++)
				{
					if(cards[i].isMarked(r, c))
						counter++;
					else
						counter = 0;
				}
				if(counter >= 5)
					return true;
			}
		}
		return false;
	}
	
	public void markNumber(int number)
	{
		for(int i = 0; i < cards.length; i++)
		{
			cards[i].markNumber(number);
		}
	}
}

class Card
{
	int[][] numbers = new int[5][5];
	
	public Card(int[][] numbers)
	{
		this.numbers = numbers;
	}
	
	public int getNumber(int Row, int Column)
	{
		return numbers[Row][Column];
	}
	
	public boolean isMarked(int row, int column)
	{
		if(numbers[row][column] == 0)
			return true;
		else
			return false;
	}
	
	public void markNumber(int number)
	{
		for(int r = 0; r < numbers.length; r++)
		{
			for(int c = 0; c < numbers[0].length; c++)
			{
				if(number == getNumber(r, c))
					numbers[r][c] = 0;
			}
		}
	}
}