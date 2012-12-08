package ecoli;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics2D;

public class ecoliApplet extends Applet {
	
	// dimensions of applet
	int width;
	int height;
	
	public void init()
	{
		this.width = getSize().width;
		this.height = getSize().height;
		
		setBackground(Color.white);
		
	}
	
	public void paint(Graphics g)
	{
		// paint gradient
		Graphics2D g2D = (Graphics2D) g;
		GradientPaint gradient = new GradientPaint(0, 0, Color.lightGray, (height/10), width, Color.white);
		g2D.setPaint (gradient);
		g2D.drawRect(0, 0, width, (height/10));
	}

}
