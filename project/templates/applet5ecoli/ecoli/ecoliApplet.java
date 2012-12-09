package ecoli;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

public class ecoliApplet extends Applet implements ActionListener {
	
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
	Button submitButton;
	
	// constants for game
	boolean gameOn = false;
	
	int dimNum = 0;
	int cNum = 0;
	int rewardNum = 0;
	int suckerNum = 0;
	int temptationNum = 0;
	int punishmentNum = 0;
	 
	public void init()
	{
		// set size of applet
		this.width = getSize().width;
		this.height = getSize().height;
		
		// sets size of applet
		size = new Dimension(500,300);
		
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
		
		submitButton = new Button("Go");
		submitButton.addActionListener(this);
		
		// creates the input panel (Flow style) and add fields
		inputPanel = new Panel();
		
		inputPanel.add(dimInput);
		inputPanel.add(cNumInput);
		inputPanel.add(rewardInput);
		inputPanel.add(suckerInput);
		inputPanel.add(temptationInput);
		inputPanel.add(punishmentInput);
		inputPanel.add(submitButton);
		
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
		if (event.getSource() == submitButton)
		{
			String dimS = dimInput.getText();
			String cNumS = cNumInput.getText();
			String rewardS = rewardInput.getText();
			String suckerS = suckerInput.getText();
			String temptationS = temptationInput.getText();
			String punishmentS = punishmentInput.getText();
			
			dimNum = Integer.parseInt(dimS);
			cNum = Integer.parseInt(cNumS);
			rewardNum = Integer.parseInt(rewardS);
			suckerNum = Integer.parseInt(suckerS);
			temptationNum = Integer.parseInt(temptationS);
			punishmentNum = Integer.parseInt(punishmentS);
			
			gameOn = true;
			repaint();
		}
	}

	// paint function
	public void paint (Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		if (!gameOn)
		{
			Font f = new Font("Monospaced", Font.BOLD, 20);
			g2.setFont (f);
			g2.setColor(Color.black);
			g2.drawString("Evolutionary Games on Graphs with Examples", 20, 20);
	
		}
	}
	// run an e.coli game
	/*public void gameEcoli ()
	{
		Grid game = new Grid()
	}*/

}
