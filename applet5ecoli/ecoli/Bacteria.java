package ecoli;

public class Bacteria {
	
	// 0 for cooperator, 1 for defector
	public int type;
	
	// keeps track of total points
	public int points;
	
	// constructor for Bacteria
	public Bacteria(int type)
	{
		this.type = type;
		this.points = 0;
	}
	
	// updating points of bacteria
	public void addPoint(int p)
	{
		this.points += p;
	}
	
	public void minusPoints(int p)
	{
		this.points -= p;
	}	

}
