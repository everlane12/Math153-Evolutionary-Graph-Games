package ecology;

import java.util.Random;

public class Ecology_game_1{


	//To create the multiple rounds, have a loop that goes through the 
	//first iteration section for the desired amount of times. Have a sleep
	//command as well that will allow the old graphic to be displayed before
	//the new one comes up
	public int no_species; 
	public int no_rounds;
	public int[] species_rank;
	public int[] graphics_matrix;
	//creates random numbers for ordering
	Random generator= new Random();

	public Ecology_game_1 (int number, int rounds){
		no_species=number;
		no_rounds=rounds;
		species_rank=new int[number]; 
		graphics_matrix=new int[number];
	}


	public int first_run(){	
		double[] rand_vals=new double[no_species];
		double prob=0;
		int deathcount=0;
		//finds highest score
		int highest=0;
		int[] rankings= new int[no_species];
		int[] winner= new int[no_species];//winner matrix, stores wins
		int[][] fight=new int[no_species][no_species];//stores the fights.
		int[] graphics=new int[no_species];//array which stores the remaining

		for(int r=0;r<no_species;r++){
			rand_vals[r]=generator.nextDouble();
		}


		for(int a=0; a<no_species;a++)
	        	rankings[a]=a;

	        //ranking the species

	        int smallest=0;
	        for(int a=0;a<no_species;a++){
	        	smallest=a;
	        	for(int b=0;b<no_species;b++){
	        		if(rand_vals[smallest]>rand_vals[b])
	        			smallest=b;
	        	}
	        	rankings[smallest]=(a+1);//so no one will just always die
	        	rand_vals[smallest]=2.0;
	        }


			//competition stage     
	        	//each one fights once
	        	for(int first=0; first<no_species;first++){
	        		for(int second=first+1;second<no_species;second++){
	        			if(((prob=generator.nextDouble())<((double)rankings[first]/(rankings[first]+rankings[second])))){
	        				fight[first][second]=first;
	        				winner[first]=winner[first]+1;

	        			}
	        			else{

	        				fight[first][second]=second;
	        				winner[second]=winner[second]+1;

	        			}

	        		}
	        	}



	        	for(int b=0;b<no_species;b++){
	        		if(winner[highest]<winner[b])	
	        			highest=b;
	        	}


	        	for(int copy=0;copy<no_species;copy++)
	        		graphics[copy]=rankings[copy];

	        	//this section is different for the different games
	        	for(int first=0; first<no_species;first++){
	        		if(winner[first]==0){
	        			System.out.println(first+" died");
	        			deathcount++;
	        			//dead replaced with individual with most wins
	        			rankings[first]=rankings[highest];
	        			graphics[first]=highest;
	        		}	
	        			System.out.println("winner["+first+"]="+winner[first]);
	        	}


	        	//copies graphic matrix!
	        	for(int copy=0;copy<no_species;copy++){
	        		graphics_matrix[copy]=graphics[copy];
	        		species_rank[copy]=rankings[copy];
	        	}








		/*
		*Note to self. Things don't really die in this scenario
		*so it would be worth creating like 3 different death
		*criterion which the user can change.
		*
		*
		*
		*/

		return deathcount;

	}

	public int next_run(){
		int highest=0, deathcount=0;
		int[] rankings= new int[no_species];
		int[] winner= new int[no_species];//winner matrix, stores wins
		int[][] fight=new int[no_species][no_species];//stores the fights.
		int[] graphics=new int[no_species];//array which stores the remaining

		for(int copy=0; copy<no_species;copy++)
			rankings[copy]=species_rank[copy];

		//competition stage     
        	//each one fights once
        	for(int first=0; first<no_species;first++){
	       		for(int second=first+1;second<no_species;second++){
	       			if(((generator.nextDouble())<((double)rankings[first]/(rankings[first]+rankings[second])))){
	       				fight[first][second]=first;
	       				winner[first]=winner[first]+1;

	       			}
	       			else{

	       				fight[first][second]=second;
        				winner[second]=winner[second]+1;	        			
	        		}

	        	}
	        }



	       	for(int b=0;b<no_species;b++){
	       		if(winner[highest]<winner[b])	
	       			highest=b;
	       	}


	       	for(int copy=0;copy<no_species;copy++)
	       		graphics[copy]=rankings[copy];

	       	//this section is different for the different games
        	for(int first=0; first<no_species;first++){
        		if(winner[first]==0){
	        		System.out.println(first+" died");
	       			deathcount++;
	       			//dead replaced with individual with most wins
	       			rankings[first]=rankings[highest];
	       			graphics[first]=highest;
	       		}	
	       			System.out.println("winner["+first+"]="+winner[first]);
	       	}


	       	//copies graphic matrix!
        	for(int copy=0;copy<no_species;copy++){
        		graphics_matrix[copy]=graphics[copy];
	        	species_rank[copy]=rankings[copy];
	       	}

	       	return deathcount;
	}
	public void showRank(){
		for(int show=0;show<no_species;show++)
			System.out.println(species_rank[show]);

	}





	}
