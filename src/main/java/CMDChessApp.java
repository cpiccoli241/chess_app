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
        if(bd.isInCheck()){
            System.out.println("Check");
        }
        if(bd.getTurn() == Color.WHITE)
            System.out.println("White Players Move!");
        else
            System.out.println("Black Players Move!");
        String input = scin.nextLine();
        //@todo support truePGN movement for now require start square id Pa2a3 captures not noted

        bd.makeMove(PGNConverter.convertFromPGNToMove(input));
    }

    public static void main(String[] args) {
        PGNConverter.addLetters();
        ChessBoardRep gameBoard = new ChessBoardRep();
        Scanner sc = new Scanner(System.in);
        while (!gameBoard.endState()) {
            System.out.print(gameBoard.WhitePerspective());
            takeTurn(sc, gameBoard);
            while(gameBoard.isWaitingOnPromotion()){
                System.out.println("Enter Q, B, N, R: ");
                String input = sc.nextLine();
                gameBoard.promotePiece(input);
            }
        }
        System.out.println("Checkmate!!!");
        if(Color.WHITE==gameBoard.getTurn()){
            System.out.println("Black Wins!");
        }else{
            System.out.println("White Wins!");
        }
    }
}
