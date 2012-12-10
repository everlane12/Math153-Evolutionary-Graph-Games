package ecoli;

// stores a pair of integers along with the function to compare them
public class Pair {
	
	public int x, y;
	
	public Pair (int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	
	// < function
	public boolean lessThan ()
	{
		if (x < y)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	// > function
	public boolean greaterThan()
	{
		if (x > y)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	// print function for debugging
	public void print()
	{
		String printPair = "(" + Integer.toString(x) + "," + Integer.toString(y) + ")";	
		System.out.println(printPair);
	}

}
