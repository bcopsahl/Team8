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
		int a,x,y;
		if (args.length > 0 && args[0] != null) {
			 a = Integer.parseInt(args[0]);
		} else {
			 a = 10;
		}
		if(args.length > 2 && args[2] != null){
			y = Integer.parseInt(args[2]);
			x = Integer.parseInt(args[1]);
		} else {
			x = 10;
			y = 10;
		}
		Board board = new infiniteBoard(x,y);
		Main main = new Main(board);
		main.populate();
		for( int i = 0; i <a; i++){
			main.print();
			System.out.println();
			main.evolve();
		}

	}

}
