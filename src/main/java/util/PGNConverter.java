package util;

import model.Move;

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

    public static int[] convertFromPGNToSquare(String input){
        return new int[]{Integer.parseInt(input.substring(1, 2)), PGNConvert.get(input.charAt(0))};
    }

    public static Move convertFromPGNToMove(String input){

        int[] start = convertFromPGNToSquare(input.substring(1,3));
        int[] end = convertFromPGNToSquare(input.substring(3,5));
        return new Move(start,end[0],end[1]);
    }
}
