package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.List;

public class Main {

	// list of all cells
	private Map<Location,Cell> cells;
	
	private int Ydimension = 10;
	private int Xdimension = 10;
	

	//constructor sets dimention and calcualtes size of array 
	public Main( int x,int y ){
		Xdimension = x;
		Ydimension = y;
		cells = new HashMap<Location,Cell>();
	}

	//needed a controlable way to make arrays for testing
	public void populateWith( boolean alive ) {
		if(alive){
			for( int x = 0; x < Xdimension; x++){
				for( int y = 0; y < Ydimension; y++ ){
					cells.put(new Location(x,y), new Cell(alive) );
				}
			}
		}	
	}
	
	// populates empty list with 100 cells that have a 20% chance of being alive
	public void populate( ) {
		
		Random rand = new Random(System.nanoTime());
		
		for( int x = 0; x < Xdimension; x++){
			for( int y = 0; y < Ydimension; y++ ){
				if( rand.nextInt(5) <= 0) {
					cells.put(new Location(x,y),new Cell(true));
				}
			}
		}
	}


	
	//takes a cell index position and outputs the number of alive cells nearby
	public int detectNearby(Location location) {
		int X,Y;
		X = location.getX();
		Y = location.getY();
		int nearbycount = 0;
		Location localInstance = new Location();
		for(int x = X-1; x <= X+1 ; x++){
			for(int y = Y-1; y <= Y+1 ;y++){
				localInstance.setLocation(x,y);
				if( cells.containsKey(localInstance)){
					nearbycount++;
				}
			}
		}
		if( cells.containsKey(location) ) {
			nearbycount--;
		}
		return nearbycount;
	}


	//based on the number of alive cells nearby returns if the cell is alive
	public boolean aliveOrDead(Location location) {
		int count = detectNearby(location);
		if( cells.containsKey(location)){
			if ( count == 2 || count == 3) {
				return true;
			}
			return false;
		} else {
			if(count == 3){
				return true;
			}
		}
		return false;
	}

	//print function	
	public void print() {
		Location l = new Location();
		for( int y = 0; y < Ydimension;y++){
			for( int x = 0; x < Xdimension; x++){
				l.setLocation(x,y);
				if(cells.containsKey(l)){
					System.out.print("0 ");
				} else {

					System.out.print("- ");
				}
			}
			System.out.println();

		}		
		System.out.println();
	}

	//makes a full new generation
	public void evolve(){
		Map<Location,Cell> nextgen = new HashMap<Location,Cell>();
		List<Location>  a;
		Set<Location> b = cells.keySet();
		for(Location l : b){
			a = l.around();
			for( Location i : a){
				if( aliveOrDead(i) && !nextgen.containsKey(i)) {
					nextgen.put(i,new Cell(true));
				}
			}
			if( aliveOrDead(l) && !nextgen.containsKey(l)) {
				nextgen.put(l,new Cell(true));
			}
		}
		cells = nextgen;
	}

	
	public static void main(String[] args) {
		int a;
		if (args.length > 0 && args[0] != null) {
			 a = Integer.parseInt(args[0]);
		} else {
			 a = 10;
		}
		Main main = new Main(10,10);
		main.populateWith(false);
		for( int i = 0; i <a; i++){
			main.print();
			System.out.println();
			main.evolve();
		}			

	}

}
