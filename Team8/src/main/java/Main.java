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
	// checks a three wide row if things are alive 
	public int leftRight(int index){
		int count = 0;
		if((index % dimension) <= (dimension - 1)) {
			if(index>0 && cells.get(index-1).getAlive()){
				count++;
			}
		}
		if((index % dimension) >= 0 ){
			if(index+1 < size && cells.get(index+1).getAlive()){
				count++;
			}
		}
		if( index < size && cells.get(index).getAlive()){
			count++;
		}
		return count;
	} 
	
	//takes a cell index position and outputs the number of alive cells nearby
	public int detectNearby(int index) {
		int nearbycount = 0;
		
		//top three cells
		if(index > dimension) {
			nearbycount += leftRight(index-dimension);
		}
		//middle three cells
		nearbycount += leftRight(index);
		if(cells.get(index).getAlive()){
			nearbycount--;
		}
		//Bottom three cells
		if(index < size && (index + dimension) < size) {
			nearbycount += leftRight(index + dimension);
		}
		return nearbycount;
	}
	//based on the number of alive cells nearby returns if the cell is alive
	public boolean aliveOrDead(int index) {
		int count = detectNearby(index);
		if( cells.get(index).getAlive()){
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

	//makes a full new generation
	public void generation(){
		ArrayList<Cell> nextgen = new ArrayList<Cell>();

		for(int i =0; i <size;i++) {
			nextgen.add(i,new Cell(aliveOrDead(i)));
		}
		cells = nextgen;
	}
	
	public static void main(String[] args) {
		
		Main main = new Main();
		main.cells = new ArrayList<Cell>();
		
		main.populate( );
		for( int i = 0; i <10; i++){
			main.print();
			System.out.println();
			main.generation();
		}			

		System.out.println("Nearby to cell 0: " + main.detectNearby(0));
	}

}
