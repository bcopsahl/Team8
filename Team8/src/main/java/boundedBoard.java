package main;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;


public class boundedBoard extends Board {
  public boundedBoard(){
    super();
  }
  public boundedBoard( int x, int y){
    super(x,y);
  }
  @Override
  public void evolve(){
    Map<Location,Boolean> removedCells = new HashMap<Location,Boolean>();
    List<Location> g = new ArrayList<Location>();
    Set<Location> b = cells.keySet();
    for(Location l : b){
        g.addAll(l.around().collect(Collectors.toList()));
    }
    List<Location> nextGen = g.stream().distinct().filter(l -> aliveOrDead(l)).filter(l -> inBounds(l)).collect(Collectors.toList());
    List<Location> killed = cells.keySet().stream().filter(l -> !nextGen.contains(l)).collect(Collectors.toList());
    for(Location l : nextGen){
        if( !cells.containsKey(l)){
            cells.put(l,new Cell(true));
        }
    }
    for(Location l:killed){
        cells.remove(l);
    }
  }

  private boolean inBounds(Location loc){
    return (loc.getX() <= xMax) && (loc.getX() >= xMin) && (loc.getY() <= yMax) && (loc.getY() >= yMin);
  }


}
