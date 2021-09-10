import model.ChessBoardRep;
import model.Color;
import util.PGNConverter;

import java.util.Scanner;

public class CMDChessApp {
    private static boolean setup = false;
    public CMDChessApp(){
        if(!setup) {
            PGNConverter.addLetters();
            setup = true;
        }

    }

    public static void takeTurn(Scanner scin, ChessBoardRep bd){
        if(bd.getTurn() == Color.WHITE)
            System.out.println("White Players Move!");
        else
            System.out.println("Black Players Move!");
        String input = scin.nextLine();
        //@todo support truePGN movement for now require start square id Pa2a3
        //@todo find a good place for converting pgn to ints for now do it here

        bd.makeMove(PGNConverter.convertFromPGNToMove(input));
    }

    public static void main(String[] args) {
        PGNConverter.addLetters();
        ChessBoardRep gameBoard = new ChessBoardRep();
        Scanner sc = new Scanner(System.in);
        while (!gameBoard.endState()) {
            System.out.print(gameBoard.WhitePerspective());
            takeTurn(sc, gameBoard);
        }
    }
}
