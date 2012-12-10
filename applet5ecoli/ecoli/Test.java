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
		
		Grid testGame = new Grid(2, 2, 5, 1, 10, 0);
		testGame.printType();
		System.out.println(testGame.repNum);
		
		testGame.playPD();
		testGame.printType();
		
		Pair[] lowPairs = testGame.lowHighN(true);
		Pair[] highPairs = testGame.lowHighN(false);
		
		for (int i = 0; i < testGame.repNum; i++)
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
		}
		
		/*testGame.dieGrow();
		testGame.printType();
		testGame.dieGrow();
		testGame.printType();*/
		
	}

}
