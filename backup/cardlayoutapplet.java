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
	
	// keeps track of current page
	public int currPage;
	
	// layout for applet
	// main Panel
	CardLayout mainLayout;
	Panel mainPanel;
	Panel page0, page1;
	
	// bottom map
	Panel roadMap;
	
	// buttons for bottom map
	Button button0, button1;
	
	
	TextField dimInput;
	
	public void init()
	{
		// set size of applet
		this.width = getSize().width;
		this.height = getSize().height;
		
		size = new Dimension(500,300);
		
		setSize(size);
		
		this.currPage = 0;
		
		// make holder for main slides
		mainPanel = new Panel();
		mainLayout = new CardLayout();
		mainPanel.setLayout(mainLayout);
		
		// make slides
		page0 = new Panel();
		page1 = new Panel();
		
		dimInput = new TextField(20);
		dimInput.setText("0");
		page1.add(dimInput);
		
		// add slides to main holder
		mainPanel.add(page0, "page 0");
		mainPanel.add(page1, "page 1");
		
		// make road maps
		roadMap = new Panel();
		
		// add buttons to road map
		button0 = new Button("1");
		button0.addActionListener(this);
		button1 = new Button("2");
		button1.addActionListener(this);
		
		roadMap.add(button0);
		roadMap.add(button1);
		
		// overall layout is Border style, add main and roadmap
		this.setLayout(new BorderLayout());
		this.add(roadMap, BorderLayout.SOUTH);
		this.add(mainPanel, BorderLayout.CENTER);
		
		setBackground(Color.white);
		
	}
	
	// implement ActionListener interface
	public void actionPerformed(ActionEvent event)
	{
		// actions for roadmap buttons
		if (event.getSource() == button0)
		{
			currPage = 0;
			mainLayout.show(mainPanel, "page 0");
			repaint();
		}
		
		if (event.getSource() == button1)
		{
			currPage = 1;
			mainLayout.show(mainPanel, "page 1");
		}
	}
	
	// paint function
	public void paint (Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		if (currPage == 0)
		{
			Font f = new Font("Monospaced", Font.BOLD, 20);
			g2.setFont (f);
			g2.setColor(Color.black);
			//g.drawRect(10, 10, 10, 10);
			g2.drawString("Evolutionary Games on Graphs with Examples", 10, 10);
			
		}
	}

}

