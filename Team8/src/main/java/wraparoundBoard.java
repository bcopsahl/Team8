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
		Set<Location> b = cells.keySet();
		for(Location l : b){
			a = l.around();
			for( Location loc : a){
				wrapAround(loc);
				if( aliveOrDead(loc) && !nextgen.containsKey(loc)) {
						nextgen.put(loc,new Cell(true));
				}
			}
			if( aliveOrDead(l) && !nextgen.containsKey(l)) {
				nextgen.put(l,new Cell(true));
			}
		}
		cells = nextgen;
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
	loc.setX(loc.getX() > xMax ? loc.getX():xMax);
	loc.setX(loc.getX() < xMin ? loc.getX():xMin);
	loc.setY(loc.getY() > yMax ? loc.getY():yMax);
	loc.setY(loc.getY() < yMin ? loc.getY():yMin);
  }


}
