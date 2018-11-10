package main;

import java.util.Scanner;


public class BoardFactory {

    public static Board  readUserSizeInput() {
        int x,y;
        Scanner reader = new Scanner(System.in);
        System.out.println("Infinite? select y for yes:");
        String inf = reader.nextLine();
        if(inf.equals("y"))
            return new infiniteBoard();
        else
        {
          System.out.println("Enter the x dimension: ");
          x = reader.nextInt();     
          System.out.println("Enter the y dimension: ");
          y = reader.nextInt();
        }
        return new boundedBoard(x,y);
    
    }

}
