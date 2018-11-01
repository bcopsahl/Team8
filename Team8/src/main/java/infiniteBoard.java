package main;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;


public class infiniteBoard extends Board {
  public infiniteBoard(){
    super();
    populate();
  }
  public infiniteBoard( int x, int y){
    super(x,y);
    populate();
  }
  @Override
  public void evolve(){
    Map<Location,Cell> nextgen = new HashMap<Location,Cell>();
		List<Location>  a;
		Set<Location> b = cells.keySet();
		for(Location l : b){
			a = l.around();
			for( Location loc : a){
				if( aliveOrDead(loc) && !nextgen.containsKey(loc)) {
					nextgen.put(loc,new Cell(true));
					xMin = (loc.getX() < xMin) ? loc.getX() : xMin;
					yMin = (loc.getY() < yMin) ? loc.getY() : yMin;
					xMax = (loc.getX() > xMax) ?  loc.getX() : xMax;
					yMax = (loc.getY() > yMax) ?  loc.getY() : yMax;
				}
			}
			if( aliveOrDead(l) && !nextgen.containsKey(l)) {
				nextgen.put(l,new Cell(true));
			}
		}
		cells = nextgen;
  }

}
