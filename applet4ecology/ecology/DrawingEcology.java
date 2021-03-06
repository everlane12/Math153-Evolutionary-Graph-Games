package ecology;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

public class DrawingEcology extends Applet implements ActionListener{

	//GUI elements
	TextField Species_input=new TextField(5);
	TextField Round_input=new TextField(5);
	TextField Death_input=new TextField(5);
	TextField Resource_input=new TextField(5);

	Button goButton=new Button("Go");	

	//program variables
	int Species_num=0, Round_num=0,Death_num=0,Resource_num=0;

	public void init(){

		makeGUI();
	}




	public void actionPerformed (ActionEvent e){
		String cmd = e.getActionCommand();

		//deals with the action
		//reads the numbers inputted

		if(cmd.equals("Go")){
			processInput();
			play_game();
			repaint();
		}
	}//action performed



	public void processInput(){

		String s;
		Integer i;

		//Get the text in the first field
		s=Species_input.getText();

		//Convert it to Integer object
		i= new Integer(s);

		//get its int value
		Species_num=i.intValue();



		//Get the text in the second field
		s=Round_input.getText();

		//Convert it to Integer object
		i= new Integer(s);

		//get its int value
		Round_num=i.intValue();

		//Get the text in the third field
		s=Death_input.getText();

		//Convert it to Integer object
		i= new Integer(s);

		//get its int value
		Death_num=i.intValue();

		//Get the text in the fourth field
		s=Death_input.getText();

		//Convert it to Integer object
		i= new Integer(s);

		//get its int value
		Resource_num=i.intValue();

	}//process input

	public void play_game(){

		//Set up how many number of games based on resources
		Ecology_game_1[] game1=new Ecology_game_1[Round_num];

		for(int setup=0;setup<Round_num;setup++)
			game1[setup]=new Ecology_game_1(Species_num,Round_num);

		//runs games. Not on a time delay though... Will only then 
		//output the final game. Can change later
		for(int howmany=0; howmany<Species_num;howmany++){

			game1[howmany].first_run();

			for(int remainder=1; remainder<Resource_num;remainder++)
				game1[howmany].next_run();

		}

	}



	public void paint(Graphics g) {

		//Set up how many number of games based on resources
		Ecology_game_1[] game1=new Ecology_game_1[Round_num];

		for(int setup=0;setup<Round_num;setup++)
			game1[setup]=new Ecology_game_1(Species_num,Round_num);

		//runs games. Not on a time delay though... Will only then 
		//output the final game. Can change later
		for(int howmany=0; howmany<Species_num;howmany++){

			game1[howmany].first_run();

			for(int remainder=1; remainder<Resource_num;remainder++)
				game1[howmany].next_run();

		}


		int display=0,x=20,y=20;

		for(int no_species=0;no_species<Species_num;no_species++){

			 for(int no_species2=0;no_species2<Species_num;
			 	 				no_species2++)
			//transfers over the data
			display=game1[no_species].graphics_matrix[no_species2];

			g.drawString(display+", ",x,y);

			y=y+15;



		}

	}


	private void makeGUI(){
		Label SpeciesLabel= new Label("number species:", Label.RIGHT);
		Label RoundLabel= new Label("number species:", Label.RIGHT);
		Label DeathLabel= new Label("input 1,2, or 3:", Label.RIGHT);
		Label ResourceLabel= new Label("number resources:", Label.RIGHT);

		//adding panels
		Panel p1 = new Panel();
		Panel p2 = new Panel();
		Panel p3 = new Panel();
		Panel p4 = new Panel();
		Panel p5 = new Panel();
		Panel p11 = new Panel();
		Panel p21 = new Panel();
		Panel p31 = new Panel();
		Panel p41 = new Panel();
		Panel p = new Panel();

		p1.setLayout(new GridLayout(1,2));
		p1.setBackground(Color.cyan);
		p1.add(SpeciesLabel);

		p11.setLayout(new FlowLayout(FlowLayout.LEFT));
		p11.add(Species_input);
		p1.add(p11);

		p2.setLayout(new GridLayout(1, 2));
		p2.setBackground(Color.cyan);
		p2.add(RoundLabel);

		p21.setLayout(new FlowLayout(FlowLayout.LEFT));
		p21.add(Round_input);
		p2.add(p21);


		p3.setLayout(new GridLayout(1, 2));
		p3.setBackground(Color.cyan);
		p3.add(DeathLabel);

		p31.setLayout(new FlowLayout(FlowLayout.LEFT));
		p31.add(Death_input);
		p3.add(p31);

		p4.setLayout(new GridLayout(1, 2));
		p4.setBackground(Color.cyan);
		p4.add(ResourceLabel);

		p41.setLayout(new FlowLayout(FlowLayout.LEFT));
		p41.add(Resource_input);
		p4.add(p41);

		p5.setBackground(Color.black);
		p5.add(goButton);

		p.setLayout(new GridLayout(5, 1));
		p.add(p1);
		p.add(p2);
		p.add(p3);
		p.add(p4);
		p.add(p5);

		// add the panel to the applet
		this.setLayout(new BorderLayout());
		this.setBackground(Color.yellow);
		add(p, "South");

		// register listener(s), if any
		goButton.addActionListener(this);

	}//makes GUI

}
