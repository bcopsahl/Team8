package main;
import java.lang.Exception;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.lang.ClassNotFoundException;
import java.util.Set;
import java.util.ArrayList;
import java.util.Scanner;

public class Board implements Serializable  {
    protected Map<Location,Cell> cells;
    protected int xMin,yMin,xMax,yMax;
    protected boolean isInfinite;

    public Board(){
        xMin = yMin =   0;
        xMax = yMax = 10;
        cells = new HashMap<Location,Cell>();
    }

    public Board(int x, int y){
        xMin = yMin =   0;
        xMax = x;
        yMax = y;
        cells = new HashMap<Location,Cell>();
    }

    protected boolean isDead(){
       return cells.isEmpty();  
    }

    protected void populate(){
        Random rand;
        try{
            for( int x = xMin; x <= xMax; x++){
                rand =  new Random(System.nanoTime());
                Thread.sleep(rand.nextInt(10));
                for( int y = yMin; y <= yMax; y++){
                    if(rand.nextInt(5) <= 0){
                        cells.put(new Location(x,y), new Cell(true));
                    }
                }
            }
           } catch(Exception e){}
    }

    protected void populate(boolean alive){
        if(alive){
            for( int x = xMin; x <= xMax; x++){
                for( int y = yMin; y <= yMax; y++){
                        cells.put(new Location(x,y), new Cell(alive));
                }
            }
        }
    }
    public void add(Location loc, Cell cell){
        cells.put(loc,cell);
    }
    public void kill(Location loc ){
        cells.remove(loc);
    }
    public Cell get(Location loc){
        return cells.get(loc);
    }

    protected int detectNearby(Location location) {    
        int nearbycount = 0;
        ArrayList<Location> around = location.around();
        for( Location l : around){
            if(cells.containsKey(l)){
                nearbycount++;      
            }
        }
        return nearbycount;
    }
    protected boolean aliveOrDead(Location location) {
        int count = detectNearby(location);
        if( cells.containsKey(location)){
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

    protected void clear(){
        ProcessBuilder pb = new ProcessBuilder("clear");
        pb.inheritIO();
        try {
            Process process = pb.start();
            process.waitFor();
        } catch ( Exception e) {
            e.printStackTrace();
        }

    }

    protected void print() {
        Location l = new Location();
        StringBuilder string = new StringBuilder();
        Writer writer = new PrintWriter(System.out);
        StringBuilder horizontalLine = new StringBuilder();
        clear();
        for( int y = yMin; y <= yMax;y++){
            for( int x = xMin; x <= xMax; x++){
                l.setLocation(x,y);
                if(cells.containsKey(l)){
                    string.append(" ");
                    string.append(cells.get(l).toString());
                } else {
                    string.append(" -");
                }
            }
            string.append("\n");
        }
        string.append("\n");
        try{
            writer.write(string.toString());
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected void save(String saveFile){
        try {
            FileOutputStream fileout = new FileOutputStream(saveFile);
            ObjectOutputStream out = new ObjectOutputStream(fileout);
            out.writeObject(this);
            out.close();
            fileout.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    protected Board load(String loadFile){
        Board b = null;
        try {
            FileInputStream infile = new FileInputStream(loadFile);
            ObjectInputStream in = new ObjectInputStream(infile);
            b = (Board) in.readObject();
            in.close();
            infile.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        } catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return b;
    }

    protected void evolve(){
        System.out.println("implement this yourself damn it");
    }
    
}
