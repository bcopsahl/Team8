package main.java;

public class Cell {

	private boolean alive;
	
	public Cell( boolean isAlive ) {
		alive = isAlive;	
	}
	
	public boolean getAlive() {
		return alive;
	}
	
	public void setAlive( boolean isAlive ) {
		alive = isAlive;
	}
}
