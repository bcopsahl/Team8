package main;

class Player{
    private char id;
    private String name;
    public char getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    //for default instatiation/single player;
    public Player(){
        name = "Default Player";
        id = 'O';
    }
    //for multipayer;
    public Player(String Name, char Id){
        id = Id;
        name = Name;
    }
    public String toString(){
        char[] d = new char[1];
        d[0] = id;
        return new String(d);
    }
}
