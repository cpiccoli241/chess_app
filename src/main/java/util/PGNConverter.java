package util;

import model.Bishop;
import model.Color;
import model.EmptyPiece;
import model.King;
import model.Knight;
import model.Move;
import model.Pawn;
import model.Piece;
import model.Queen;
import model.Rook;

import java.util.HashMap;

public class PGNConverter {
    private static HashMap<Character, Integer> PGNConvert = new HashMap<>();
    public static void setup(){
        addLetters();
    }

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
    public static Piece convertFromStringToPiece(String name, Color cl, int[] position, int id){
        Piece moving;
        switch (name.charAt(0)) {
            case 'P':
                moving = new Pawn(id, cl, position);
                break;
            case 'R':
                moving = new Rook(id, cl, position);
                break;
            case 'N':
                moving = new Knight(id, cl, position);
                break;
            case 'B':
                moving = new Bishop(id, cl, position);
                break;
            case 'Q':
                moving = new Queen(id, cl, position);
                break;
            case 'K':
                moving = new King(id, cl, position);
                break;
            default:
                moving = new EmptyPiece();
        }
        return moving;
    }
    public static int[] convertFromPGNToSquare(String input){
        return new int[]{Integer.parseInt(input.substring(1, 2)), PGNConvert.get(input.charAt(0))};
    }

    public static Move convertFromPGNToMove(String input){

        return convertFromPGNToMove(input, Color.BLACK);
    }
    public static Move convertFromPGNToMove(String input, Color cl){

        int[] start = convertFromPGNToSquare(input.substring(1,3));
        int[] end = convertFromPGNToSquare(input.substring(3,5));
        Piece moving;
        switch (input.charAt(0)) {
            case 'P':
                moving = new Pawn(99, cl, start);
                break;
            case 'R':
                moving = new Rook(99, cl, start);
                break;
            case 'N':
                moving = new Knight(99, cl, start);
                break;
            case 'B':
                moving = new Bishop(99, cl, start);
                break;
            case 'Q':
                moving = new Queen(99, cl, start);
                break;
            case 'K':
                moving = new King(99, cl, start);
                break;
            default:
                moving = new EmptyPiece();

        }
        return Move.MoveEndStart(start,end,moving);
    }
}
