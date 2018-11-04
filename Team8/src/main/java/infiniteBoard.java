package main;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;


public class infiniteBoard extends Board {
	
  int newXMin, newXMax, newYMin, newYMax;

  public infiniteBoard(){
	super();
	newXMin = newYMin = 100000000;// these should probably be larger/ smaller but i dont remember the package.
	newXMax = newYMax = -100000000;
  }
  public infiniteBoard( int x, int y){
	super(x,y);
	newXMin = newYMin = 100000000;// these should probably be larger/ smaller but i dont remember the package.
	newXMax = newYMax = -100000000;
  }
  @Override
  public void evolve(){
	Map<Location,Cell> nextgen = new HashMap<Location,Cell>();
	List<Location>  a;
	Map<Location,Boolean> removedCells = new HashMap<Location, Boolean>();
	Set<Location> b = cells.keySet();
	for(Location l : b){
		a = l.around();
		for( Location loc : a){
			if( aliveOrDead(loc) ) {
				// this adds the location and only checks for updateing values if it hasnt been added before
				if(nextgen.putIfAbsent(loc,new Cell(true)) == null){
					updateSize(loc);
				}
			}
		}
		if( !aliveOrDead(l) ) {
			removedCells.put(l,true);
			updateSize(l);
		}
	}
	for(Location l: removedCells.keySet()){
		cells.remove(l);
	}
	for(Location l: nextgen.keySet()){
		cells.put(l, new Cell(true));
	}
	xMax = newXMax;
	yMax = newYMax;
	xMin = newXMin;
	yMin = newYMin;
  }


  private void updateSize(Location l){
	newXMin = (l.getX() < newXMin) ? l.getX() : newXMin;
	newYMin = (l.getY() < newYMin) ? l.getY() : newYMin;
	newXMax = (l.getX() > newXMax) ? l.getX() : newXMax;
	newYMax = (l.getY() > newYMax) ? l.getY() : newYMax;
  }
}
