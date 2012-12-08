package rps;
import java.util.*;

public class RpsGame {
	
	// constants for type
	public static final int ROCK = 0;
	public static final int PAPER = 1;
	public static final int SCISSORS = 2;
	
	// numbers 
	public int size;
	public int[] rpsSize = new int[3];
	public int timesteps;
	
	// constructor
	public RpsGame (int rocks, int papers, int scissors, int timesteps)
	{
		// set values
		this.rpsSize[0] = rocks;
		this.rpsSize[1] = papers;
		this.rpsSize[2] = scissors;
		this.size = rocks + papers + scissors;
		this.timesteps = timesteps;
	}
	
	// random number comparison function
	public int randCompare(double randNum)
	{
		if (randNum < ((double) rpsSize[0]/size))
		{
			return ROCK;
		}
		
		else if (randNum < ((double) (rpsSize[0] + rpsSize[1])/size))
		{
			return PAPER;
		}
		
		else
		{
			return SCISSORS;
		}
	}
	
	// plays a round, returns integer for type that won or -1 if tie
	public void play ()
	{
		// generates random numbers
		Random generator = new Random();
		double randNum1 = generator.nextDouble();
		double randNum2 = generator.nextDouble();
		
		int type1 = randCompare(randNum1);
		int type2 = randCompare(randNum2);
		
		if (type1 == type2)
		{
			return;
		}
		
		// type1 wins
		else if ((type1 == ROCK && type2 == SCISSORS) || (type1 == PAPER && type2 == ROCK) || (type1 == SCISSORS && type2 == PAPER))
		{
			rpsSize[type2] = rpsSize[type2] - 1;
			size = size -1;
			return;
		}
		
		else
		{
			size = size -1;
			rpsSize[type1] = rpsSize[type1] - 1;
			return;
		}
	}
	

}
