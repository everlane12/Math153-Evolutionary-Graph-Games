package ecoli;
import helpers.*;

public class Grid {
	
	// dimension of the grid
	public int dim;
	
	// cooperator and defector number
	public int cNum;
	public int dNum;
	
	// num replaced each round;
	public int repNum;
	
	public Grid (int dim, int cNum)
	{
		this.dim = dim;
		this.cNum = cNum;
		this.dNum = (dim * dim) - cNum;
	}
	
	// 2x2 array to hold Bacteria
	public Bacteria[][] bacGrid = new Bacteria[dim][dim];
	
	// helper function to find lowest or highest n bacteria in Grid
	public Pair[] lowHighN (boolean lower)
	{	
		// stores when array is first filled
		int counter = 0;
		
		// store bacteria points
		int[] bacPoints = new int[repNum];
		
		// store bacteria coords, corresponding to bacPoints
		Pair[] bacCoords = new Pair[repNum];
		
		// iterate over grid
		for (int i = 0; i < dim; i++)
		{
			for (int j = 0; j < dim; j++)
			{
				// fill empty array for first X numbers
				if (counter < repNum)
				{
					bacPoints[counter] = bacGrid[i][j].points;
					bacCoords[counter] = new Pair(i, j);
				}
				
				// update array with other points if needed
				else
				{
					for (int k = 0; k < counter; k++)
					{
						// make an Pair to compare either > or <
						Pair compare = new Pair(bacGrid[i][j].points, bacPoints[k]);
						
						// updates arrays if lower than and want lowest or greater than and want highest
						if ((compare.lessThan() && lower) || (compare.greaterThan() && !lower))
						{
							bacPoints[k] = bacGrid[i][j].points;
							bacCoords[k] = new Pair(i, j);
						}
					}
				}
			}
		}
		
		return bacCoords;
	}
	
	// updates bacGrid with death and reproduction, returns false if bacGrid too small
	public boolean dieGrow ()
	{
		// the lowest bac is replaced by the highest bac in order
		if (repNum < (dim * dim))
		{
			// find the lowest and highest bacteria
			Pair[] lowestN = lowHighN(true);
			Pair[] highestN = lowHighN(false);
			
			// check that lowestN and highestN have same length
			if (lowestN.length != highestN.length)
			{
				return false;
			}
			
			// lowest one in lowest array replaced by lowest one in highest array
			for (int i = 0; i < lowestN.length; i++)
			{
				// extract coords
				int xL = lowestN[i].x;
				int yL = lowestN[i].y;
				int xH = highestN[i].x;
				int yH = highestN[i].y;
				
				// make lower same type as upper and reset points
				bacGrid[xL][yL].type = bacGrid[xH][yH].type;
				bacGrid[xL][yL].points = 0;
			}
			
			return true;
		}
		
		else
		{
			return false;
		}
	}
}
