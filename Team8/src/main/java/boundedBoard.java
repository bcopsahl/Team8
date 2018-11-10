package main;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class boundedBoard extends Board {
  public boundedBoard(){
    super();
  }
  public boundedBoard( int x, int y){
    super(x,y);
  }
  @Override
  public void evolve(){
    Map<Location,Cell> nextgen = new HashMap<Location,Cell>();
    List<Location>  a;
    Map<Location,Boolean> removedCells = new HashMap<Location,Boolean>();
    Set<Location> b = cells.keySet();
    for(Location l : b){
        a = l.around();
        for( Location loc : a){
            if(!inBounds(loc)){
                continue;
            }
            if( aliveOrDead(loc)) {
                nextgen.putIfAbsent(loc,new Cell(true));
            }
        }
        if( !aliveOrDead(l)) {
            removedCells.put(l,true);
        }
    }
    for(Location l: removedCells.keySet()){
        cells.remove(l);
    }
    for(Location l: nextgen.keySet()){
        cells.put(l,new Cell(true));
    }
  }

  private boolean inBounds(Location loc){
    return (loc.getX() <= xMax) && (loc.getX() >= xMin) && (loc.getY() <= yMax) && (loc.getY() >= yMin);
  }


}
