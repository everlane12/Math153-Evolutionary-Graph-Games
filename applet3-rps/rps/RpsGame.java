package rps;
import java.util.*;

public class RpsGame {
	
	// constants for type
	public static final int ROCK = 0;
	public static final int PAPER = 1;
	public static final int SCISSORS = 2;
	
	// numbers 
	public int size;
	public int rockSize;
	public int paperSize;
	public int scissorsSize;
	public int timesteps;
	
	// constructor
	public RpsGame (int rocks, int papers, int scissors, int timesteps)
	{
		// set values
		this.rockSize = rocks;
		this.paperSize = papers;
		this.scissorsSize = scissors;
		this.size = rocks + papers + scissors;
		this.timesteps = timesteps;
	}
	
	// random number comparison function
	public int randCompare(double randNum)
	{
		if (randNum < ((double) rockSize/size))
		{
			return ROCK;
		}
		
		else if (randNum < ((double) (rockSize + paperSize)/size))
		{
			return PAPER;
		}
		
		else
		{
			return SCISSORS;
		}
	}
	
	// plays a round, returns integer for type that won or -1 if tie
	public int play ()
	{
		// generates random numbers
		Random generator = new Random();
		double randNum1 = generator.nextDouble();
		double randNum2 = generator.nextDouble();
		
		int type1 = randCompare(randNum1);
		int type2 = randCompare(randNum2);
		
		if (type1 == type2)
		{
			return -1;
		}
		
		// type1 wins
		else if ((type1 == ROCK && type2 == SCISSORS) || (type1 == PAPER && type2 == ROCK) || (type1 == SCISSORS && type2 == PAPER))
		{
			return type1;
		}
		
		else
		{
			return type2;
		}
	}
	
	public boolean playGame()
	{
		
	}

}
