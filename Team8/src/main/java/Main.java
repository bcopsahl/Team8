package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.List;
import java.io.Writer;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


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
        Main main = new Main(new BoardFactory().getBoard());
        main.populate();
        Scanner reader = new Scanner(System.in);
        System.out.println("How would you like the board to evolve? (interval, onKey, number)");
        String evolveType = reader.nextLine();
        boolean interval,repetition,onKey;
        interval = repetition = onKey = false;
        switch (evolveType) {
            case "interval": interval = true;
                break;
            case "number": repetition = true;
                break;
            case "onKey": onKey = true;
                break;
        }
        System.out.println("How many times should we evolve the field?");
        int x = reader.nextInt();
        long y = 0;
        if( interval){
            System.out.println("How many seconds between interations?");
            y = (long) (reader.nextDouble() * 1000);
        }

        for( int i = 0; i <x; i++){
            if(interval ||  onKey){
                main.print();
            }
            main.evolve();
            if(interval){
                try {
                    Thread.sleep(y);
                } catch (Exception e){}
            }

            if(main.board.isDead()){
                System.out.println("the board is dead");
                break;
            }
            if(onKey){
                reader.next();
            }
        }
        main.print();
    }
}
