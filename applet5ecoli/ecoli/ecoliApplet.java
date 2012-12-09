package ecoli;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

public class ecoliApplet extends Applet implements ActionListener {
	
	// constants
	int DIMNUM = 0;
	int CNUM = 1;
	int REWARDNUM = 2;
	int SUCKERNUM = 3;
	int TEMPTATIONNUM = 4;
	int PUNISHMENTNUM = 5;
	int CONSTSNUM = 6;
	
	// dimensions of applet
	public int width;
	public int height;
	
	Dimension size;
	
	// input panel for applet layout
	Panel inputPanel;
	
	// textfiels for input panel
	TextField dimInput;
	TextField cNumInput;
	TextField rewardInput;
	TextField suckerInput;
	TextField temptationInput;
	TextField punishmentInput;
	
	// button for submit inputs
	Button goButton;
	Button nextButton;
	
	// constants for game
	int gameStatus = -1;
	
	int[] gameNums = {0, 0, 0, 0, 0, 0};	
	
	// grid game
	Grid game;
	
	// info for paint
	Font titleFont = new Font("Arial", Font.PLAIN, 15);
	 
	public void init()
	{
		// set size of applet
		this.width = getSize().width;
		this.height = getSize().height;
		
		// sets size of applet
		size = new Dimension(500,500);
		
		setSize(size);
		
		// create textfields and submit button
		dimInput = new TextField(5);
		dimInput.setText("0");
		
		cNumInput = new TextField(5);
		cNumInput.setText("0");
		
		rewardInput = new TextField(5);
		rewardInput.setText("0");
		
		suckerInput = new TextField(5);
		suckerInput.setText("0");
		
		temptationInput = new TextField(5);
		temptationInput.setText("0");
		
		punishmentInput = new TextField(5);
		punishmentInput.setText("0");
		
		goButton = new Button("Go");
		goButton.addActionListener(this);
		nextButton = new Button("Next");
		nextButton.addActionListener(this);
		nextButton.setEnabled(false);
		
		// creates the input panel (Flow style) and add fields
		inputPanel = new Panel();
		inputPanel.add(dimInput);
		inputPanel.add(cNumInput);
		inputPanel.add(rewardInput);
		inputPanel.add(suckerInput);
		inputPanel.add(temptationInput);
		inputPanel.add(punishmentInput);
		inputPanel.add(goButton);
		inputPanel.add(nextButton);
		
		
		// overall layout is Border style, add main and roadmap
		this.setLayout(new BorderLayout());
		this.add(inputPanel, BorderLayout.SOUTH);
		// set background
		setBackground(Color.white);
		
	}
	
	// implement ActionListener interface
	public void actionPerformed(ActionEvent event)
	{
		// actions for roadmap buttons
		if (event.getSource() == goButton)
		{
			String dimS = dimInput.getText();
			String cNumS = cNumInput.getText();
			String rewardS = rewardInput.getText();
			String suckerS = suckerInput.getText();
			String temptationS = temptationInput.getText();
			String punishmentS = punishmentInput.getText();
			
			String[] submitStr = {dimS, cNumS, rewardS, suckerS, temptationS, punishmentS};
			
			for (int i = 0; i < CONSTSNUM; i++ )
			{
				gameNums[i] = Integer.parseInt(submitStr[i]); 
			}
					
			// shows first page
			gameStatus = 0;
			nextButton.setEnabled(true);
			repaint();
		}
		
		if (event.getSource() == nextButton)
		{
			goButton.setEnabled(false);
			gameStatus = 1;
			gameEcoli();
		}
	}

	// paint function
	public void paint (Graphics g)
	{
		// draw title of applet
		g.setFont (titleFont);
		g.setColor(Color.darkGray);
		g.drawString("Evolutionary Games: E. coli on a grid", 100, 20);
		
		// draws the input labels
		String[] labelStr = {"Dim", "BD", "R", "S", "T", "P"};
		g.setColor(Color.darkGray);
		for (int i = 0; i < CONSTSNUM; i++)
		{
			g.drawString(labelStr[i], 50 + (55 * i), 450);
		}
		
		if (gameStatus == -1)
		{
			// prints instructions
			g.drawString("E.coli on a grid simulation. [insert description]", 100, 100);
			g.drawString("Please enter inputs below and press Go.", 100, 120);
			
			String[] descriptStr = {"Dim = dimesion of the grid",
				"BD = birth/death number",
				"R = reward of payoff matrix",
				"S = sucker of payoff matrix",
				"T = temptation of payoff matrix",
				"P = punishment of payoff matrix"};
			
			g.setColor(Color.darkGray);
			for (int i = 0; i < CONSTSNUM; i++)
			{
				g.drawString(descriptStr[i], 100, 140 + (20 * i));
			}
		}
		
		// draw input values
		if (gameStatus == 0)
		{
			g.setColor(Color.darkGray);
			
			// display inputs
			String dimStr = "dimension: ";
			String cNumStr = "birth-death number: ";
			String rewardStr = "reward: ";
			String suckerStr = "sucker: ";
			String temptationStr = "temptation: ";
			String punishmentStr = "punishment: ";
			String[] nameStr = {dimStr, cNumStr, rewardStr, suckerStr, temptationStr, punishmentStr};
			
			for (int i = 0; i < CONSTSNUM; i++)
			{
				nameStr[i] += Integer.toString(gameNums[i]);
				g.drawString(nameStr[i], 120, 100 + (20 * i));
			}
			
			g.drawString("Re-enter values or", 120, 260);
			g.drawString("Press Next to start the simulation", 120, 280);
		}
		
		// draws game grid
		/*if (gameStatus == 1)
		{
			game.
		}*/
	}
	// run an e.coli game
	public void gameEcoli ()
	{
		// makes the game grid object
		game = new Grid (gameNums[DIMNUM], gameNums[CNUM], gameNums[REWARDNUM], gameNums[SUCKERNUM],
				gameNums[TEMPTATIONNUM], gameNums[PUNISHMENTNUM]);
		
		repaint();
		
	}

}
