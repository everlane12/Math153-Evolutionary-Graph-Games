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
		this.repNum = (int) (0.25 * (cNum + dNum)); 
		
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
		
		Pair[] bacCoords = new Pair[repNum];
		
		for (int i = 0; i < repNum; i++)
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
		// Pair[] lowestN = lowHighN(true);
		Pair[] highestN = lowHighN(false);
		
		// lowest one in lowest array replaced by lowest one in highest array
		for (int i = 0; i < repNum; i++)
		{
			// extract coords
			// int xL = lowestN[i].x;
			// int yL = lowestN[i].y;
			int xH = highestN[i].x;
			int yH = highestN[i].y;
			
			Random generator = new Random();
			int neighbor;
			int self;
			
			// separate blocks into cases
			
			// corners
			if ((xH == 0) && (yH == 0))
			{
				self = 0;
			}
			
			else if((xH == 0) && (yH == dim - 1))
			{
				self = 1;
			}
			
			else if ((xH == dim - 1) && (yH == 0))
			{
				self = 2;
			}
			
			else if ((xH == dim - 1) && (yH == dim - 1))
			{
				self = 3;
			}
			
			// sides
			else if (xH == 0)
			{
				self = 5;
			}
			
			else if (yH == 0)
			{
				self = 4;
			}
			
			else if (xH == dim -1)
			{
				self = 6;
			}
			
			else if (yH == dim - 1)
			{
				self = 7;
			}
			
			// center		
			else
			{
				self = 8;
			}
			
			if (self == 0 || self == 1 || self == 2 || self == 3)
			{
				neighbor = generator.nextInt(3);
				if (self == 0)
				{
					if (neighbor == 0)
					{
						neighbor = 4;
					}
					else if (neighbor == 1)
					{
						neighbor = 6;
					}
					else
					{
						neighbor = 7;
					}
				}
				
				else if (self == 1)
				{
					if (neighbor == 0)
					{
						neighbor = 1;
					}
					else if (neighbor == 1)
					{
						neighbor = 2;
					}
					else
					{
						neighbor = 4;
					}
				}
				
				else if (self == 2)
				{
					if (neighbor == 0)
					{
						neighbor = 3;
					}
					else if (neighbor == 1)
					{
						neighbor = 5;
					}
					else
					{
						neighbor = 6;
					}
				}
				
				else
				{
					if (neighbor == 0)
					{
						neighbor = 0;
					}
					else if (neighbor == 1)
					{
						neighbor = 1;
					}
					else
					{
						neighbor = 3;
					}
				}
			}
			
			else if (self == 8)
			{
				neighbor = generator.nextInt(8);
			}
			
			else
			{
				neighbor = generator.nextInt(5);
				
				if (self == 4)
				{
					if (neighbor == 0)
					{
						neighbor = 3;
					}
					else if (neighbor == 1)
					{
						neighbor = 4;
					}
					else if (neighbor == 2)
					{
						neighbor = 5;
					}
					else if (neighbor == 3)
					{
						neighbor = 6;
					}
					else
					{
						neighbor = 7;
					}
				}
				
				else if (self == 5)
				{
					if (neighbor == 0)
					{
						neighbor = 1;
					}
					else if (neighbor == 1)
					{
						neighbor = 2;
					}
					else if (neighbor == 2)
					{
						neighbor = 4;
					}
					else if (neighbor == 3)
					{
						neighbor = 6;
					}
					else
					{
						neighbor = 7;
					}
				}
				
				if (self == 6)
				{
					if (neighbor == 0)
					{
						neighbor = 0;
					}
					else if (neighbor == 1)
					{
						neighbor = 1;
					}
					else if (neighbor == 2)
					{
						neighbor = 3;
					}
					else if (neighbor == 3)
					{
						neighbor = 5;
					}
					else
					{
						neighbor = 6;
					}
				}
				
				else
				{
					if (neighbor == 0)
					{
						neighbor = 0;
					}
					else if (neighbor == 1)
					{
						neighbor = 1;
					}
					else if (neighbor == 2)
					{
						neighbor = 2;
					}
					else if (neighbor == 3)
					{
						neighbor = 3;
					}
					else
					{
						neighbor = 4;
					}
				}
			}
			
			int xL;
			int yL;
			
			if (neighbor == 0)
			{
				xL = xH - 1;
				yL = yH - 1;
			}
			
			else if (neighbor == 1)
			{
				xL = xH;
				yL = yH - 1;
			}
			
			else if (neighbor == 2)
			{
				xL = xH + 1;
				yL = yH - 1;
			}
			
			else if (neighbor == 3)
			{
				xL = xH - 1;
				yL = yH;
			}
			
			else if (neighbor == 4)
			{
				xL = xH + 1;
				yL = yH;
			}
			
			else if (neighbor == 5)
			{
				xL = xH - 1;
				yL = yH + 1;
			}
			
			else if (neighbor == 6)
			{
				xL = xH;
				yL = yH + 1;
			}
			
			else 
			{
				xL = xH + 1;
				yL = yH + 1;
			}
			
			// make lower same type as upper and reset points
			int typeNew = bacGrid[xH][yH].type;
			int typeOld = bacGrid[xL][yL].type;
			
			bacGrid[xL][yL].type = bacGrid[xH][yH].type;
			
			
			if ((typeNew == 0) && (typeOld == 1))
			{
				cNum += 1;
				dNum -= 1;
			}
			
			if ((typeNew == 1) && (typeOld == 0))
			{
				dNum += 1;
				cNum -= 1;
			}
			
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
