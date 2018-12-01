package main;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class infiniteBoard extends Board {
    
  int newXMin, newXMax, newYMin, newYMax;

  public infiniteBoard(){
    super();
  }
  public infiniteBoard( int x, int y){
    super(x,y);
  }
  @Override
  public void evolve(){
    newXMin = newYMin = 100000000;// these should probably be larger/ smaller but i dont remember the package.
    newXMax = newYMax = -100000000;
    Map<Location,Cell> nextgen = new HashMap<Location,Cell>();
    Stream<Location>  a;
    Map<Location,Boolean> removedCells = new HashMap<Location, Boolean>();
    Set<Location> b = cells.keySet();
    List<Location> all = new List<>();
    List<Location> s = cells.keySet().stream()
        .reduce(ArrayList::new,l -> l.around().toArray().toList());
    ArrayList<Location> killed = s.stream().distinct()
        .fliter(l -> !aliveOrDead(l)).collect(ArrayList::new,ArrayList::add,ArrayList::addAll);

    for(Location l : b){
        a = l.around();
        for( Location loc : a){
            if( aliveOrDead(loc) ) {
                // this adds the location and only checks for updateing values if it hasnt been added before
                if(nextgen.putIfAbsent(loc,new Cell(true)) == null){
                }
            }
        }
        if( !aliveOrDead(l) ) {
            removedCells.put(l,true);
        }
    }
    for(Location l: removedCells.keySet()){
        cells.remove(l);
    }
    for(Location l: nextgen.keySet()){
        cells.put(l, new Cell(true));
    }
    for(Location l: cells.keySet()){
        updateSize(l);
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
