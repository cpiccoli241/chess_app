import model.ChessBoardRep;
import model.Color;
import model.Move;

import java.util.HashMap;
import java.util.Scanner;

public class CMDChessApp {
    public static HashMap<Character, Integer> PGNConvert = new HashMap<>();
    public static void addLetters(){
        PGNConvert.put('a',8);
        PGNConvert.put('b',7);
        PGNConvert.put('c',6);
        PGNConvert.put('d',5);
        PGNConvert.put('e',4);
        PGNConvert.put('f',3);
        PGNConvert.put('g',2);
        PGNConvert.put('h',1);
    }
    public static void takeTurn(Scanner scin, ChessBoardRep bd){
        if(bd.getTurn() == Color.WHITE)
            System.out.println("White Players Move!");
        else
            System.out.println("Black Players Move!");
        String input = scin.nextLine();
        //@todo support truePGN movement for now require start square id Pa2a3
        //@todo find a good place for converting pgn to ints for now do it here

        int[] start = {Integer.parseInt(input.substring(2,3)),PGNConvert.get(input.charAt(1))};
        int[] end = {Integer.parseInt(input.substring(4,5)),PGNConvert.get(input.charAt(3))};
        bd.makeMove(new Move(start,end[0],end[1]));
    }
    public static void main(String[] args) {
        addLetters();
        ChessBoardRep gameBoard = new ChessBoardRep();
        Scanner sc = new Scanner(System.in);
        while (!gameBoard.endState()) {
            System.out.print(gameBoard.WhitePerspective());
            takeTurn(sc, gameBoard);
        }
    }
}
