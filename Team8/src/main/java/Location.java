package main;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.stream.Stream;

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
    public String toString(){
        return X+":"+Y;
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
    public Stream<Location> around() {
        ArrayList<Location> a = new ArrayList<Location>();
        a.add(new Location(X-1,Y-1));
        a.add(new Location(X,Y-1));
        a.add(new Location(X+1,Y-1));
        a.add(new Location(X-1,Y));
        a.add(this);
        a.add(new Location(X+1,Y));
        a.add(new Location(X-1,Y+1));
        a.add(new Location(X,Y+1));
        a.add(new Location(X+1,Y+1));
        return a.stream();
    }
    @Override
    public int hashCode() {
        return Objects.hash(X,Y);
    }
    @Override
    public boolean equals(Object l){
        if ( l instanceof Location){
            Location loc = (Location) l;
            return  (getX() == loc.getX()) && (getY() == loc.getY());
        }
        return false;

         
    }
    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {
        inputStream.defaultReadObject();

    }
    private void writeObject(ObjectOutputStream outputStream) throws  IOException {
        outputStream.defaultWriteObject();

    }





}
