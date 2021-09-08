package model;

import java.util.Arrays;
import java.util.List;

public class Knight extends Piece {
    public Knight(int id, Color col) {
        super(id, col);
    }

    @Override
    public List<Move> getCandidateMoves(int[] start) {
        return Arrays.asList(new Move(start,new int[]{-1,2}),
                new Move(start,new int[]{1,2}),
                new Move(start,new int[]{2,-1}),
                new Move(start,new int[]{2,1}),
                new Move(start,new int[]{-1,-2}),
                new Move(start,new int[]{1,-2}),
                new Move(start,new int[]{-2,-1}),
                new Move(start,new int[]{-2,1}));
    }
    @Override
    public String toString(){
        return "N";
    }
}
