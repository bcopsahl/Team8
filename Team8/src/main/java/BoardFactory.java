package main;

import java.util.Scanner;


public class BoardFactory {

    public static Board  getBoard() {
        int x,y;
        x = y = 10;
        Scanner reader = new Scanner(System.in);
        System.out.println("Type of board: [infinite,bounded,wrap-around]");
        String boardType = reader.nextLine();
        System.out.println("Select board size? y/n ");
        String boardSize = reader.nextLine();
        if(boardSize.equals("y")){
            System.out.println("Enter the x dimension: ");
            x = reader.nextInt();     
            System.out.println("Enter the y dimension: ");
            y = reader.nextInt();
        }
        Board result;
        switch (boardType) {
            case "infinite": result = new infiniteBoard(x,y);
                            break;
            case "bounded": result = new boundedBoard(x,y);
                            break;
            case "wrap-around": result = new wraparoundBoard(x,y);
                            break;
            default: result = new boundedBoard(x,y);
                            break;
        }
        return result;
    
    }

}
