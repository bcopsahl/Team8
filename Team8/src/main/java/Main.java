package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.List;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.IOException;

public class Main {

	// list of all cells
	private Map<Location,Cell> cells;
	
	private Writer writer;
	private int xMin,yMin,xMax,yMax;
	

	//constructor sets dimention and calcualtes size of array 
	public Main( int x,int y ){
		writer = new PrintWriter(System.out);
		yMin = 0;
		xMin = 0;
		xMax = x;
		yMax = y;
		cells = new HashMap<Location,Cell>();
	}

	//needed a controlable way to make arrays for testing
	public void populate( boolean alive ) {
		if(alive){
			for( int x = xMin; x <= xMax; x++){
				for( int y = yMin; y <= yMax; y++ ){
					cells.put(new Location(x,y), new Cell(alive) );
				}
			}
		}	
	}
	
	// populates empty list with 100 cells that have a 20% chance of being alive
	public void populate( ) {
		
		Random rand = new Random(System.nanoTime());
		
		for( int x = xMin; x < xMax; x++){
			for( int y = yMin; y < yMax; y++ ){
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
		StringBuilder string = new StringBuilder();
		for( int y = yMin; y <= yMax;y++){
			for( int x = xMin; x <= xMax; x++){
				l.setLocation(x,y);
				if(cells.containsKey(l)){
					string.append("0 ");
				} else {

					string.append("- ");
				}
			}
			string.append("\n");
		}		
		try{
			writer.write(string.toString());
			writer.flush();
		} catch (IOException ex) {
			ex.printStackTrace();	
		}
	}

	//makes a full new generation
	public void evolve(){
		Map<Location,Cell> nextgen = new HashMap<Location,Cell>();
		List<Location>  a;
		Set<Location> b = cells.keySet();
		for(Location l : b){
			a = l.around();
			for( Location loc : a){
				if( aliveOrDead(loc) && !nextgen.containsKey(loc)) {
					nextgen.put(loc,new Cell(true));
					xMin = (loc.getX() < xMin) ? loc.getX() : xMin;
					yMin = (loc.getY() < yMin) ? loc.getY() : yMin;
					xMax = (loc.getX() > xMax) ?  loc.getX() : xMax;
					yMax = (loc.getY() > yMax) ?  loc.getY() : yMax;
				}
			}
			if( aliveOrDead(l) && !nextgen.containsKey(l)) {
				nextgen.put(l,new Cell(true));
			}
		}
		cells = nextgen;
	}

	
	public static void main(String[] args) {
		int a,x,y;
		if (args.length > 0 && args[0] != null) {
			 a = Integer.parseInt(args[0]);
		} else {
			 a = 10;
		}
		if (args.length > 1 && args[1] != null) {
			x = Integer.parseInt(args[1]);

		} else {
			x = 10;
		}
		if(args.length > 2 && args[2] != null){
			y = Integer.parseInt(args[2]);
		} else {
			y = 10;
		} 

		Main main = new Main(x-1,y-1);
		main.populate();
		for( int i = 0; i <a; i++){
			main.print();
			System.out.println();
			main.evolve();
		}			

	}

}
