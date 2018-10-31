package main;
import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(alive);
	}
	@Override
	public boolean equals(Object c){
		return hashCode() == c.hashCode();

	}
}
