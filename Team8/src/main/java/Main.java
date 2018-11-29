package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
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
    protected void populate(){
        board.populate();
    }
    protected void populate(boolean alive){
        board.populate(alive);
    }

    private void turn(Player player){
        Scanner reader = new  Scanner(System.in);
        System.out.printf("Hello %s \n",player.getName());
        System.out.println("You get three actions a turn.");
        System.out.println("You can [g]enerate a new cell,[k]ill one of your current cells,[p]ass your turn");
        for( int i = 0; i < 3; i++){
            System.out.println("Please select an action now");
            String action = reader.nextLine();
            if(action.equals("g")){
                System.out.println("Please input x and y cordinates for the new cell");
                int x = reader.nextInt();
                int y = reader.nextInt();
                reader.nextLine();
                Location location = new Location(x,y);
                if(board.get(location) == null){
                    board.add(new Location(x,y),new Cell(player));
                    System.out.println(" Congradulations on your new Cell");
                } else {
                    System.out.println("There is already a cell there");
                }
            }else if(action.equals("k")){
                System.out.println("Please input x and y cordinates for the cell to kill");
                int x = reader.nextInt();
                int y = reader.nextInt();
                reader.nextLine();
                Location location = new Location(x,y);
                if(board.get(location).equals(player)){
                    board.kill(location);
                    System.out.println("The cell has been brutally murdered you monster");
                } else{
                    System.out.println("That cell is not yours");
                }

            } else{
                System.out.println("You have elected to pass");
                return;
            }
        }
    }





    public static void main(String[] args) {
        
        Main main;
        Scanner reader = new Scanner(System.in);
        System.out.println("Single or multiplayer? (S)ingle/(M)ultiplayer");
        String isMulti = reader.nextLine();
        if( isMulti.equals("M")){
            main =new Main(new  BoardFactory().getMultiBoard());
            System.out.println("How many players?(2-4)");
            String numPlayers = reader.nextLine();
            int intPlayers = Integer.parseInt(numPlayers);
            ArrayList<Player> players = new ArrayList<Player>(intPlayers);
            for(int i = 0;i< intPlayers; i++) {
                System.out.printf("Player %d\n",i+1);
                System.out.println("What is your name?");
                String name = reader.nextLine();
                System.out.println("What character would you like to use as an identifier?");
                char id = reader.nextLine().charAt(0);
                players.add(new Player(name,id));
            }
            System.out.println("How many turns should there be in the game?");
            String numTurns = reader.nextLine();
            int  intTurns = Integer.parseInt(numTurns);
            main.print();
            int t;
            for (int turn = 0; turn < intTurns; turn++){
                for(Player p: players){
                   main.turn(p);
                   main.print();
                }
                main.evolve();
                main.print();
            }


        } else {
            main  = new Main(new BoardFactory().getSingleBoard());
            main.populate(); 
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
}
