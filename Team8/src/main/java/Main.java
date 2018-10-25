package main.java;

import java.util.ArrayList;
import java.util.Random;

public class Main {

	// list of all cells
	private ArrayList<Cell> cells;
	
	private int size = 100;
	private int dimension = 10;
	
	
	// populates empty list with 100 cells that have a 20% chance of being alive
	public void populate( ) {
		
		Random rand = new Random(System.nanoTime());
		
		for( int i = 0; i < size; i++ ) {
			if( rand.nextInt(5) <= 0 ) {
				cells.add( new Cell(true) );
			} else {
				cells.add( new Cell(false) );
			}
		}
	}
	
	//takes a cell index position and outputs the number of alive cells nearby
	public int detectNearby(int index) {
		int nearbycount = 0;
		
		//tests if above cell
		if(index > dimension) {
			if(cells.get(index-dimension).getAlive()) {
				nearbycount++;
			}
		}
		//tests if left cell
		if(index > 0) {
			if(cells.get(index-1).getAlive()) {
				nearbycount++;
			}
		}
		//tests if right cell
		if(index < size - 1) {
			if(cells.get(index+1).getAlive()) {
				nearbycount++;
			}
		}
		//Tests bottom cell
		if(index < size) {
			if(cells.get(index+dimension).getAlive()) {
				nearbycount++;
			}
		}		
		
		return nearbycount;
	}
	
	public void print() {
		
		int lineCount = 0;
		
		for( Cell c: cells ) {
			if( lineCount >= dimension ) {
				System.out.println();
				lineCount = 0;
			}
			
			lineCount++;
			
			if( c.getAlive() ) {
				System.out.print("O ");
			} else {
				System.out.print("- ");
			}
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		Main main = new Main();
		main.cells = new ArrayList<Cell>();
		
		main.populate( );
		
		main.print();

		System.out.println("Nearby to cell 0: " + main.detectNearby(0));
	}

}
