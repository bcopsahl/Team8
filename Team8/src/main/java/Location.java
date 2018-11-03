package main;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.Serializable;

public class Location implements Serializable{
	private int X;
	private int Y;
	private static final long serialversionUID = 234566123459L;
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
	public void setX(int x){
		X = x;
	}
	public void setY(int y){
		Y = y;
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
	private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {
		inputStream.defaultReadObject();

	}
	private void writeObject(ObjectOutputStream outputStream) throws  IOException {
		outputStream.defaultWriteObject();

	}





}
