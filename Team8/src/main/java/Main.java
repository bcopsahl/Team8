package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.List;
import java.io.Writer;
import java.io.PrintWriter;


public class Main {

    public Board board;


    //constructor sets dimention and calcualtes size of array
    public Main(Board b){
        board = b;
    }

    public void evolve(){
        board.evolve();
    }
    public void print(){
        board.print();
    }
    private void populate(){
        board.populate();
    }
    private void populate(boolean alive){
        board.populate(alive);
    }





    public static void main(String[] args) {
        Main main = new Main(new BoardFactory().readUserSizeInput());
        main.populate();
        for( int i = 0; i <10; i++){
            main.print();
            main.evolve();
            if(main.board.isDead()){
                break;
            }
        }
    }
}
