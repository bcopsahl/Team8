package main;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

public class Location {
	private int X;
	private int Y;

	public void setLocation(int x, int y ){
		X = x;
		Y = y;
	}

	public Location() {
		X = 0;
		Y = 0;
	}

	public Location(int x, int y) {
		X = x;
		Y = y;
	}

	public int getX() {
		return X;
	}
	public int getY(){
		return Y;	
	}
	public ArrayList<Location> around() {
		ArrayList<Location> a = new ArrayList<Location>();
		a.add(new Location(X-1,Y-1));
		a.add(new Location(X,Y-1));
		a.add(new Location(X+1,Y-1));
		a.add(new Location(X-1,Y));

		a.add(new Location(X+1,Y));
		a.add(new Location(X-1,Y+1));
		a.add(new Location(X,Y+1));
		a.add(new Location(X+1,Y+1));
		return a;
	}
	@Override
	public int hashCode() {
		return Objects.hash(X,Y);
	}
	@Override
	public boolean equals(Object l){
		return hashCode() == l.hashCode();
	}
}
