package main;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class wraparoundBoard extends Board {
  public wraparoundBoard(){
    super();
  }
  public wraparoundBoard( int x, int y){
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
    List<Location> nextGen = g.stream().distinct().filter(l -> aliveOrDead(l)).collect(Collectors.toList());
    List<Location> killed = cells.keySet().stream().filter(l -> !nextGen.contains(l)).collect(Collectors.toList());
    for(Location l : nextGen){
            cells.put(l,new Cell(true));
    }
    for(Location l:killed){
        cells.remove(l);
    }
  }

  @Override
  public int detectNearby(Location location){
      return (int) location.around().filter(l -> cells.containsKey(wrapAround(l))).filter(l -> !l.equals(location)).count();
  }

  private Location wrapAround(Location loc){
    loc.setX(loc.getX() <= xMax ? loc.getX():xMin);
    loc.setX(loc.getX() >= xMin ? loc.getX():xMax);
    loc.setY(loc.getY() <= yMax ? loc.getY():yMin);
    loc.setY(loc.getY() >= yMin ? loc.getY():yMax);
    return loc;
  }
}
