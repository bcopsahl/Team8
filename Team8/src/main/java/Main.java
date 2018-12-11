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
    
    static boolean isDelete = false;

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
        String action;
        Integer x,y;

        int turns = 0;
        while(turns < 3){
            x = y = null;
            System.out.println("Please select an action now");
            action = reader.nextLine();
            action =action.replaceAll("[\\d[^\\w\\s]]+","");
            if(action.equals("g")){
                while(x == null ||  y == null){    
                    System.out.println("Please input x and y cordinates for the new cell");
                    if(reader.hasNextInt()){
                        x = new Integer(reader.nextInt());
                    }
                    if(reader.hasNextInt()){
                        y = new Integer(reader.nextInt());
                    }
                    if(x==null|| y==null){
                        System.out.println("Your input did not work, please try again.");
                    } 
                        reader.nextLine();

                }
                Location location = new Location(x.intValue(),y.intValue());
                if(board.get(location) == null){
                    board.add(new Location(x,y),new Cell(player));
                    System.out.println(" Congradulations on your new Cell");
                    print();
                    turns++;
                } else {
                    System.out.println("There is already a cell there");
                }
            }else if(action.equals("k")){
                while(x == null ||  y == null){    
                    System.out.println("Please input x and y cordinates for the cell to kill");
                    if(reader.hasNextInt()){
                        x = new Integer(reader.nextInt());
                    }
                    if(reader.hasNextInt()){
                        y = new Integer(reader.nextInt());
                    }
                    if(x==null|| y==null){
                        System.out.println("Your input did not work, please try again.");
                    } 
                        reader.nextLine();
                }
                Location location = new Location(x.intValue(),y.intValue());
                if(board.get(location).getOwner().equals(player)){
                    board.kill(location);
                    turns++;
                    print();
                } else{
                    System.out.println("That cell is not yours");
                }

            } else  if( action.equals("p")){
                System.out.println("You have elected to pass");
                turns+=3;
            } else {
                System.out.println(action+" is not a supported action. Please choose again.");
            }
            
            //if the players cell count drops to 0 delete the player
            if(board.checkPlayerCellCount(player) == 0)
            {
            	player = null;
            	isDelete = true;
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
                   //object is removed as well to shift all other positions to the left
                   if(isDelete)
                   {
                	   players.remove(p);
                       isDelete = false;
                   }
                   main.print();
                   //once player size is 1 the winning player will be at index 0 as each removal implies a shift to the left
                   if(players.size() == 1)
                   {
                	   System.out.println("Game over: " + players.get(0).getName() + " is the winner");
                	   System.exit(0);
                   }
                }
                main.evolve();
                main.print();
            }

            System.out.println("Game over: specified number of turns has elapsed");            
            
        } else {
	    System.out.println("Would you like to load a board? (Y)es/(N)o");
	    String loadBoard = reader.nextLine();
        main  = new Main(new BoardFactory().getSingleBoard());
	    if( loadBoard.equals("Y") ) {
		  System.out.println("Please enter the filename for the board:");
		  String boardToLoad = reader.nextLine();
		  main.board.load( boardToLoad );
	    } else {
		  main.populate();
	    } 
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
            reader.nextLine();
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
                    String shouldSave; // just in case
                    System.out.println("If you would like to save the board, enter 'y'");
                    shouldSave = reader.nextLine();
                    if( shouldSave.equals("y") ) {
                        System.out.println("What should the filename be?");
                        String saveFileName = reader.nextLine();
                        main.board.save( saveFileName );
                    }
                    reader.next();
                }
            }
            main.print();
        }
    }
}
