package main;
import java.util.Objects;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.IOException;


public class Cell implements Serializable{
    private static final long serialversionUID = 12394994272828L;
    private boolean alive;
    private Player owner;
     
    public Cell( boolean isAlive ) {
        alive = isAlive;
        owner = new Player();    
    }
    public Cell(Player p){
        alive =true;
        owner = p;
    }
    public String toString(){
        return owner.toString();
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
    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {
        inputStream.defaultReadObject();
        //maybe do some validation here? idk
    }
    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.defaultWriteObject();
    }
}
