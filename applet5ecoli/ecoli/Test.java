package ecoli;
import java.util.*;

public class Test {
	
	public static void main(String[] args)
	{
		
		/*
		Bacteria[] bac = new Bacteria[5];
		int c = 4;
		int d = 1;
		
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				Random generator = new Random();
				double num = generator.nextDouble();
				if (c == 0)
				{
					bac[j] = new Bacteria(1);
					System.out.println("no c's, made a d");
					System.out.println(bac[j].type);
					d -= 1;
				}
				else if (d == 0)
				{
					bac[j] = new Bacteria(c);
					System.out.println("no d's, made a c");
					System.out.println(bac[j].type);
					c -= 1;
				}
				else if (num < (double) 4/5)
				{
					bac[j] = new Bacteria(0);
					System.out.println((double) 4/5);
					System.out.println(num);
					System.out.println("randomly a c");
					System.out.println(bac[j].type);
					c -= 1;
				}
				else
				{
					bac[j] = new Bacteria(1);
					System.out.println("randomly a d");
					System.out.println(bac[j].type);
					d -= 1;
				}
		
			}
		}*/
		
		
		//Grid testGame = new Grid(3, 4, 5, 1, 10, 0);
		
		int[][] testGrid = new int[3][3];
		for (int i = 0; i < 3; i ++)
		{
			for (int j = 0; j < 3; j++)
			{
				testGrid[i][j] = i * j; 
			}
		}
		
		LinkedList<PointCoords> testPairs = new LinkedList<PointCoords>();
		int counter = 0;
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (counter == 0)
				{
					Pair currPair = new Pair(i,j);
					PointCoords currPC = new PointCoords(currPair, i*j);
					testPairs.add(0, currPC);
					counter += 1;
				}
				else if (counter < 4)
				{
					for (int k = 0; k < counter; k ++)
					{
						if (testPairs.get(k).points > (i*j))
						{
							Pair currPair = new Pair(i,j);
							PointCoords currPC = new PointCoords(currPair, i*j);
							testPairs.add(k, currPC);
							counter += 1;
							break;
						}
						
						if (k == (counter - 1))
						{
							Pair currPair = new Pair(i,j);
							PointCoords currPC = new PointCoords(currPair, i*j);
							testPairs.addLast(currPC);
							counter += 1;
							break;
						}
					}
				}
				
				else
				{
					for(int k = 0; k < counter; k++)
					{
						if (testPairs.get(k).points > (i*j))
						{
							Pair currPair = new Pair(i,j);
							PointCoords currPC = new PointCoords(currPair, i*j);
							testPairs.add(k, currPC);
							testPairs.removeLast();
							break;
						}
					}
				}
			}
		}
		
		for (int i = 0; i < counter; i++)
		{
			System.out.println(testPairs.get(i).coords.x);
			System.out.println(testPairs.get(i).coords.y);
		}

		LinkedList<PointCoords> testPairsH = new LinkedList<PointCoords>();
		int counterH = 0;
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (counterH == 0)
				{
					Pair currPair = new Pair(i,j);
					PointCoords currPC = new PointCoords(currPair, i*j);
					testPairsH.add(currPC);
					counterH += 1;
				}
				else if (counterH < 4)
				{
					for (int k = 0; k < counterH; k ++)
					{
						if (testPairsH.get(k).points < (i*j))
						{
							Pair currPair = new Pair(i,j);
							PointCoords currPC = new PointCoords(currPair, i*j);
							testPairsH.add(k, currPC);
							counterH += 1;
							break;
						}
						

						if (k == (counter - 1))
						{
							Pair currPair = new Pair(i,j);
							PointCoords currPC = new PointCoords(currPair, i*j);
							testPairs.addLast(currPC);
							counter += 1;
							break;
						}
					}
				}
				
				else
				{
					for(int k = 0; k < counterH; k++)
					{
						if (testPairsH.get(k).points < (i*j))
						{
							Pair currPair = new Pair(i,j);
							PointCoords currPC = new PointCoords(currPair, i*j);
							testPairsH.add(k, currPC);
							testPairsH.removeLast();
							break;
						}
					}
				}
			}
		}
		
		for (int i = 0; i < counterH; i++)
		{
			System.out.println(testPairsH.get(i).coords.x);
			System.out.println(testPairsH.get(i).coords.y);
		}
		
		
		/*
		testGame.printType();
		System.out.println(testGame.repNum);
		
		testGame.playPD();
		testGame.printType();
		
		Pair[] lowPairs = testGame.lowHighN(true);
		Pair[] highPairs = testGame.lowHighN(false);
		
		for (int i = 0; i < lowPairs.length; i ++)
		{
			System.out.println(lowPairs[i].x);
			System.out.println(lowPairs[i].y);
		}
		for (int i = 0; i < highPairs.length; i ++)
		{
			System.out.println(highPairs[i].x);
			System.out.println(highPairs[i].y);
		}*/
		
		/*for (int i = 0; i < testGame.repNum; i++)
		{
			// extract coords
			int xL = lowPairs[i].x;
			int yL = lowPairs[i].y;
			int xH = highPairs[i].x;
			int yH = highPairs[i].y;
			
			// make lower same type as upper and reset points
			testGame.bacGrid[xL][yL].type = testGame.bacGrid[xH][yH].type;
			
			// give the "babies" a fourth of the points of the parents
			//bacGrid[xL][yL].points = (int) ((1/4) * bacGrid[xH][yH].points);
			
			testGame.printType();
		}*/
		
		/*testGame.dieGrow();
		testGame.printType();
		testGame.dieGrow();
		testGame.printType();*/
		/*
		for (int k = 0; k < 5; k++)
		{
			System.out.println(k);
			if (k == 2)
			{
				break;
			}
		}*/
		
	}

}
