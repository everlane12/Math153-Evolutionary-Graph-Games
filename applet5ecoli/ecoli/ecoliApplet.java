package ecoli;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

public class ecoliApplet extends Applet implements ActionListener {
	
	// dimensions of applet
	int width;
	int height;
	
	// layout for applet
	// main Panel
	CardLayout mainLayout;
	Panel mainPanel;
	Panel page1, page2;
	
	// bottom map
	Panel roadMap;
	
	// buttons for bottom map
	Button button1, button2;
	
	
	TextField dimInput;
	
	public void init()
	{
		// set size of applet
		this.width = getSize().width;
		this.height = getSize().height;
		
		// make holder for main slides
		mainPanel = new Panel();
		mainLayout = new CardLayout();
		mainPanel.setLayout(mainLayout);
		
		// make slides
		page1 = new Panel();
		page2 = new Panel();
		
		dimInput = new TextField(20);
		dimInput.setText("0");
		page1.add(dimInput);
		
		// add slides to main holder
		mainPanel.add(page1, "page 1");
		mainPanel.add(page2, "page 2");
		
		// make road maps
		roadMap = new Panel();
		
		// add buttons to road map
		button1 = new Button("1");
		button1.addActionListener(this);
		button2 = new Button("2");
		button2.addActionListener(this);
		
		roadMap.add(button1);
		roadMap.add(button2);
		
		// overall layout is Border style, add main and roadmap
		this.setLayout(new BorderLayout());
		this.add(roadMap, BorderLayout.SOUTH);
		this.add(mainPanel, BorderLayout.CENTER);
		
		setBackground(Color.white);
		
	}
	
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == button1)
		{
			mainLayout.show(mainPanel, "page 1");
		}
		
		if (event.getSource() == button2)
		{
			mainLayout.show(mainPanel, "page 2");
		}
	}

}
