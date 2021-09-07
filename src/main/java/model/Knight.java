package model;

import java.util.Arrays;
import java.util.List;

public class Knight extends Piece {
    public static List candidateMoves = Arrays.asList(new Move[]{
                                                        new Move(new int[]{-1,2,0}),
                                                        new Move(new int[]{1,2,0}),
                                                        new Move(new int[]{2,-1,0}),
                                                        new Move(new int[]{2,1,0}),
                                                        new Move(new int[]{-1,-2,0}),
                                                        new Move(new int[]{1,-2,0}),
                                                        new Move(new int[]{-2,-1,0}),
                                                        new Move(new int[]{-2,1,0})
                                                    });
    public Knight(int id, Color col) {
        super(id, col);
    }

    @Override
    public List getCandidateMoves() {
        return candidateMoves;
    }
    @Override
    public String toString(){
        return "H";
    }
}
