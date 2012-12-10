package ecoli;
import java.util.*;

public class Grid {
	
	// number of opponents each cell has (8 in a 2D grid)
	public static final int NEIGHBORS = 8;
	
	// int values for cooperators and defects
	public static final int COOPERATOR = 0;
	public static final int DEFECTOR = 1;
	
	// dimension of the grid
	public int dim;
	
	// cooperator and defector number
	public int cNum;
	public int dNum;
	
	// number of bacteria replaced each round
	public int repNum;
	
	// timesteps before 
	public int timesteps = 0;
	
	// prisoner's dilemma payoff
	public PDPayoff payoff;
	
	// 2x2 array to hold Bacteria
	public Bacteria[][] bacGrid;
	
	public Grid (int dim, int cNum, int reward, int sucker, int temptation, int punishment)
	{
		// initialized Grid
		this.dim = dim;
		this.cNum = cNum;
		this.dNum = (dim * dim) - cNum;
		
		// sets replication numbers
		this.repNum = (int) (0.4 * (cNum + dNum)); 
		
		// keep track of cooperators and defectors created
		int c = cNum;
		int d = dNum;
		double t = (double) cNum + dNum; 
		
		// create payoff matrix
		payoff = new PDPayoff(reward, sucker, temptation, punishment);
		
		// initialize Grid
		bacGrid = new Bacteria[dim][dim];
		
		// populate the initial bacGrid
		for (int i = 0; i < dim; i ++)
		{
			for (int j = 0; j < dim; j++)
			{				
				// create random generator
				Random generator = new Random();
				
				// generate random number between 0 and 1
				double randomNum = generator.nextDouble();
				
				// if no more cooperators or defectors to be created, create the other kind
				if (c == 0)
				{
					bacGrid[i][j] = new Bacteria(DEFECTOR);
					d -= 1;
				}
				
				else if (d == 0)
				{
					bacGrid[i][j] = new Bacteria(COOPERATOR);
					c -= 1;
				}
				
				// sort randomNum using the c / (c+d) proportion
				else if (randomNum < (double)( cNum / t))
				{
					bacGrid[i][j] = new Bacteria(COOPERATOR);
					c -= 1;
				}
				
				else
				{
					bacGrid[i][j]= new Bacteria(DEFECTOR);
					d -= 1;
				}
			}
		}
	}
	
	// helper function to find lowest or highest n bacteria in Grid
	public Pair[] lowHighN (boolean lower)
	{	
		// stores when array is first filled
		int counter = 0;
		
		// store bacteria coords, corresponding to bacPoints
		LinkedList<PointCoords> bacPointCoords = new LinkedList<PointCoords>();
				
		// iterate over grid
		for (int i = 0; i < dim; i++)
		{
			for (int j = 0; j < dim; j++)
			{
				// fill empty array for first X numbers
				
				if (counter == 0)
				{
					Pair currPair = new Pair(i, j);
					PointCoords currPointCoords = new PointCoords(currPair, bacGrid[i][j].points);
					bacPointCoords.add(currPointCoords);
					counter += 1;
				}
				
				else if (counter < repNum)
				{
					for (int k = 0; k < counter; k++)
					{
						Pair compare = new Pair(bacGrid[i][j].points, bacPointCoords.get(k).points);
						
						if ((compare.lessThan() && lower) || (compare.greaterThan() && !lower))
						{
							Pair currPair = new Pair(i, j);
							PointCoords currPointCoords = new PointCoords(currPair, bacGrid[i][j].points);
							bacPointCoords.add(k, currPointCoords);
							counter += 1;
							break;
						}
						
						if (k == (counter - 1))
						{
							Pair currPair = new Pair(i, j);
							PointCoords currPointCoords = new PointCoords(currPair, bacGrid[i][j].points);
							bacPointCoords.addLast(currPointCoords);
							counter += 1;
							break;
						}
					}
				}	
				
				// update array with other points if needed
				else
				{
					for (int k = 0; k < counter; k++)
					{
						// make an Pair to compare either > or <
						Pair compare = new Pair(bacGrid[i][j].points, bacPointCoords.get(k).points);
						
						// updates arrays if lower than and want lowest or greater than and want highest
						if ((compare.lessThan() && lower) || (compare.greaterThan() && !lower))
						{
							Pair currPair = new Pair(i, j);
							PointCoords currPointCoords = new PointCoords(currPair, bacGrid[i][j].points);
							bacPointCoords.add(k, currPointCoords);
							bacPointCoords.removeLast();
							break;
						}
						
					}
				}
			}
		}
		
		Pair[] bacCoords = new Pair[counter];
		
		for (int i = 0; i < counter; i++)
		{
			bacCoords[i] = bacPointCoords.get(i).coords;						
		}
		
		return bacCoords;
	}
	
	// updates bacGrid with death and reproduction, returns false if bacGrid too small
	public void dieGrow ()
	{
		// the lowest bac is replaced by the highest bac in order
	
		// find the lowest and highest bacteria
		Pair[] lowestN = lowHighN(true);
		Pair[] highestN = lowHighN(false);
		
		// lowest one in lowest array replaced by lowest one in highest array
		for (int i = 0; i < repNum; i++)
		{
			// extract coords
			int xL = lowestN[i].x;
			int yL = lowestN[i].y;
			int xH = highestN[i].x;
			int yH = highestN[i].y;
			
			// make lower same type as upper and reset points
			bacGrid[xL][yL].type = bacGrid[xH][yH].type;
			
			// give the "babies" a fourth of the points of the parents
			//bacGrid[xL][yL].points = 0;
			bacGrid[xL][yL].points = (int) ((0.25) * bacGrid[xH][yH].points);
			bacGrid[xH][yH].points -= bacGrid[xL][yL].points;
		}
	}

	// plays prisoner's dilemmma with each of the 8 neighbors for every cell, returns false if failed
	public void playPD ()
	{
		timesteps += 1;
		for (int i = 0; i < dim; i++)
		{
			for (int j = 0; j < dim; j++)
			{
				// store self type
				int typeSelf = bacGrid[i][j].type;
				
				// make Pair array of coords for opponents
				Pair[] op = new Pair[NEIGHBORS];
				op[0] = new Pair(i-1, j-1);
				op[1] = new Pair(i-1, j);
				op[2] = new Pair(i-1, j+1);
				op[3] = new Pair(i, j-1);
				op[4] = new Pair(i, j+1);
				op[5] = new Pair(i+1, j-1);
				op[6] = new Pair(i+1, j);
				op[7] = new Pair(i+1, j+1);		
				
				for (int k = 0; k < NEIGHBORS; k++)
				{
					// remember coords
					int x = op[k].x;
					int y = op[k].y;
					
					// if not off the grid
					if (x >= 0 && y >= 0 && x < dim && y < dim)
					{
						// stores opponent type
						int typeOpponent = bacGrid[x][y].type;
						
						// play Prisoner's Dilemma
						if (typeSelf == COOPERATOR && typeOpponent == COOPERATOR)
						{
							bacGrid[i][j].addPoint(payoff.reward);
							bacGrid[x][y].addPoint(payoff.reward);
						}
						
						else if (typeSelf == COOPERATOR && typeOpponent == DEFECTOR)
						{
							bacGrid[i][j].addPoint(payoff.sucker);
							bacGrid[x][y].addPoint(payoff.temptation);
						}
						
						else if (typeSelf == DEFECTOR && typeOpponent == COOPERATOR)
						{
							bacGrid[i][j].addPoint(payoff.temptation);
							bacGrid[x][y].addPoint(payoff.sucker);
						}
						
						else
						{
							if (typeSelf == DEFECTOR && typeOpponent == DEFECTOR)
							{
								// something didn't work out right
							}
							
							bacGrid[i][j].addPoint(payoff.punishment);
							bacGrid[x][y].addPoint(payoff.punishment);
						}
					}
				}
			}
		}
	}
	
	// print function for debugging 
	public void printPoints()
	{
		for (int i = 0; i < dim; i++)
		{
			for (int j = 0; j < dim; j++)
			{
				String coord = "(";
				coord += Integer.toString(i);
				coord += ",";
				coord += Integer.toString(j);
				coord += "): ";
				coord += Integer.toString(bacGrid[i][j].points);
				
				System.out.println(coord);
			}
		}	
	}
	
	// print type function for debugging 
		public void printType()
		{
			for (int i = 0; i < dim; i++)
			{
				for (int j = 0; j < dim; j++)
				{
					String coord = "(";
					coord += Integer.toString(i);
					coord += ",";
					coord += Integer.toString(j);
					coord += "): ";
					coord += Integer.toString(bacGrid[i][j].type);
					
					System.out.println(coord);
				}
			}	
		}
}
