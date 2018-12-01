package main;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;

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
        Set<Location> b = cells.keySet();
        List<Location> g = new ArrayList<Location>();
        for(Location l : b){
            g.addAll(l.around().collect(Collectors.toList()));
        }
        List<Location> nextGen = g.parallelStream().distinct().filter(l -> aliveOrDead(l)).collect(Collectors.toList());
        List<Location> killed = cells.keySet().parallelStream().filter(l -> !nextGen.contains(l)).collect(Collectors.toList());
        for(Location l: nextGen){
            if( !cells.containsKey(l)){
                cells.put(l, new Cell(true));
            }
        }
        for(Location l: killed){
            cells.remove(l);
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
