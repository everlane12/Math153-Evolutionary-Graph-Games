package ecoli;

public class PDPayoff {
	
	// I cooperate, you cooperate
	public int reward;
	
	// I cooperate, you defect
	public int sucker;
	
	// I defect, you cooperate
	public int temptation;
	
	// I defect, you defect
	public int punishment;
	
	public PDPayoff (int reward, int sucker, int temptation, int punishment)
	{
		this.reward = reward;
		this.sucker = sucker;
		this.temptation = temptation;
		this.punishment = punishment;
	}

}
