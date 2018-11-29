package main;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.IOException;

class Player implements Serializable{
    private static final long serialversionUID = 123562994272828L;
    private char id;
    private String name;
    public char getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    //for default instatiation/single player;
    public Player(){
        name = "Default Player";
        id = 'O';
    }
    //for multipayer;
    public Player(String Name, char Id){
        id = Id;
        name = Name;
    }
    public String toString(){
        char[] d = new char[1];
        d[0] = id;
        return new String(d);
    }
    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {
        inputStream.defaultReadObject();
        //maybe do some validation here? idk
    }
    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.defaultWriteObject();
    }
}
