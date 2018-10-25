package main.java;

import java.util.ArrayList;
import java.util.Random;

public class Main {

	// list of all cells
	private ArrayList<Cell> cells;
	
	// populates empty list with 100 cells that have a 20% chance of being alive
	public void populate( ) {
		
		Random rand = new Random(System.nanoTime());
		
		for( int i = 0; i < 100; i++ ) {
			if( rand.nextInt(5) <= 0 ) {
				cells.add( new Cell(true) );
			} else {
				cells.add( new Cell(false) );
			}
		}
	}
	
	public void print() {
		
		int lineCount = 0;
		
		for( Cell c: cells ) {
			if( lineCount >= 10 ) {
				System.out.println();
				lineCount = 0;
			}
			
			lineCount++;
			
			if( c.getAlive() ) {
				System.out.print("O");
			} else {
				System.out.print("X");
			}
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		Main main = new Main();
		main.cells = new ArrayList<Cell>();
		
		main.populate( );
		
		main.print();

	}

}
