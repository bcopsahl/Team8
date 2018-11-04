package main;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class wraparoundBoard extends Board {
  public wraparoundBoard(){
    super();
  }
  public wraparoundBoard( int x, int y){
    super(x,y);
  }
  @Override
  public void evolve(){
 	Map<Location,Cell> nextgen = new HashMap<Location,Cell>();
	List<Location>  a;
	//this does not really need to be a map but it is fast and im lazy
	Map<Location,Boolean> removedCells = new HashMap<Location,Boolean>();
	Set<Location> b = cells.keySet();
	for(Location l : b){
		a = l.around();
		for( Location loc : a){
			wrapAround(loc);
			if( aliveOrDead(loc) ) {
				nextgen.putIfAbsent(loc,new Cell(true));
			}
		}
		if( !aliveOrDead(l) ) {
			removedCells.putIfAbsent(l,true);
		}
	}
	for(Location l: removedCells.keySet()){
		cells.remove(l);
	}
	for(Location l: nextgen.keySet()){
		cells.put(l, new Cell(true));
	}
  }

  @Override
  public int detectNearby(Location location){
		int nearbycount = 0;
		ArrayList<Location> around = location.around();
		for( Location l : around){
			wrapAround(l);
			if (cells.containsKey(l)){
				nearbycount++;
			}
		}

	return nearbycount;
  }

  private void wrapAround(Location loc){
	loc.setX(loc.getX() <= xMax ? loc.getX():xMin);
	loc.setX(loc.getX() >= xMin ? loc.getX():xMax);
	loc.setY(loc.getY() <= yMax ? loc.getY():yMin);
	loc.setY(loc.getY() >= yMin ? loc.getY():yMax);
  }
}
