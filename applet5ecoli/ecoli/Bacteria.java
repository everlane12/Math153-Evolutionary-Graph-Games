package ecoli;

public class Bacteria {
	
	// 0 for cooperator, 1 for defector
	public int type;
	
	// keeps track of total points
	public int points;
	
	// coordinates for Bacteria
	public int x, y;
	
	// constructor for Bacteria
	public Bacteria(int type, int x, int y)
	{
		this.type = type;
		this.points = 0;
		this.x = x;
		this.y = y;
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
