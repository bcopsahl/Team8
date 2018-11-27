package main;

import java.util.Scanner;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.io.InputStreamReader;


public class BoardFactory {

    public static Board  getBoard() {
        int x,y;
        x = y = 10;
        Board result;
        Scanner reader = new Scanner(System.in);
        System.out.println("Type of board: [infinite,bounded,wrap-around]");
        String boardType = reader.nextLine();
        System.out.println("Select board size? y/auto ");
        String boardSize = reader.nextLine();
        if(boardSize.equals("y")){
            System.out.println("Enter the x dimension: ");
            x = reader.nextInt();     
            System.out.println("Enter the y dimension: ");
            y = reader.nextInt();
        } else {
            if (!boardType.equals("infinite")){ 
                y = getConsoleDimension("lines")-1;
                x = (getConsoleDimension("cols")-1)/2;
            }
        }
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
    private static int getConsoleDimension(String dimension){
        if( System.getProperty("os.name").indexOf("nux")>=0){
            ProcessBuilder pb = new ProcessBuilder("tput",dimension);
            InputStreamReader i;
            int j;
            char c;
            Character[] d;
            List<Character> l = new ArrayList<Character>();
            try{
                Process process  = pb.start();
                // this next line is confusing, its input as in this process input
                i = new InputStreamReader(process.getInputStream());
                while((j = i.read())!= -1) {
                    l.add(new  Character((char)j));
                }
                process.waitFor();
            } catch( Exception e ){
                e.printStackTrace();
            }
            char[] e = new char[l.size()];
            for(int k =0; k < l.size(); k++){
                e[k] = l.get(k).charValue();
            }
            //this may not be necessary, however somehow parsing charachter outputs is hard
            String result = new String(e).replaceAll("\\r|\\n|\"","");
            int r =  Integer.valueOf(result);
            return r;
        } else {
            return 10;
        }
    }

}
