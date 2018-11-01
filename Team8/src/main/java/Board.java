package main;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.io.Writer;
import java.io.PrintWriter;
import java.util.Set;

public class Board {
	protected Map<Location,Cell> cells;
	protected int xMin,yMin,xMax,yMax;
	private Writer writer;

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

	public void populate(){
		Random rand = new Random(System.nanoTime());
		for( int x = xMin; x <= xMax; x++){
			for( int y = yMin; y <= yMax; y++){
				if(rand.nextInt(5) <= 0){
					cells.put(new Location(x,y), new Cell(true));
				}
			}
		}
	}

	public void populate(boolean alive){
		if(alive){
			for( int x = xMin; x <= xMax; x++){
				for( int y = yMin; y <= yMax; y++){
						cells.put(new Location(x,y), new Cell(alive));
				}
			}
		}
	}

	public int detectNearby(Location location) {
		int X,Y;
		X = location.getX();
		Y = location.getY();
		int nearbycount = 0;
		Location localInstance = new Location();
		for(int x = X-1; x <= X+1 ; x++){
			for(int y = Y-1; y <= Y+1 ;y++){
				localInstance.setLocation(x,y);
				if( cells.containsKey(localInstance)){
					nearbycount++;
				}
			}
		}
		if( cells.containsKey(location) ) {
			nearbycount--;
		}
		return nearbycount;
	}
	public boolean aliveOrDead(Location location) {
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

	public void print() {
		Location l = new Location();
		StringBuilder string = new StringBuilder();
		for( int y = yMin; y <= yMax;y++){
			for( int x = xMin; x <= xMax; x++){
				l.setLocation(x,y);
				if(cells.containsKey(l)){
					string.append("0 ");
				} else {

					string.append("- ");
				}
			}
			string.append("\n");
		}
		try{
			writer.write(string.toString());
			writer.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void evolve(){
		System.out.println("implement this yourself damn it");
	}

}
