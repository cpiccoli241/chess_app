package model;

import java.util.Arrays;
import java.util.List;

public class King extends Piece {
    private boolean hasmoved = false;

    public King(int id, Color col, int[] position) {
        super(id, col, position);
    }

    @Override
    public List<Move> getCandidateMoves(int[] start) {
        return Arrays.asList(new Move(start,new int[]{-1,2}),
                new Move(start,new int[]{1,0}),
                new Move(start,new int[]{0,1}),
                new Move(start,new int[]{-1,0}),
                new Move(start,new int[]{0,-1}),
                new Move(start,new int[]{1,1}),
                new Move(start,new int[]{-1,1}),
                new Move(start,new int[]{-1,-1}),
                new Move(start,new int[]{1,-1})
        );
    }

    @Override
    public String toString(){
        return "K";
    }
}
