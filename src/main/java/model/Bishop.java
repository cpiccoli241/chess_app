package model;

import java.util.Arrays;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(int id, Color col) {
        super(id, col);
    }

    @Override
    public List<Move> getCandidateMoves(int[] start) {
        return Arrays.asList(
                new Move(start,new int[]{2-start[0],2-start[1]}),
                new Move(start,new int[]{3-start[0],3-start[1]}),
                new Move(start,new int[]{4-start[0],4-start[1]}),
                new Move(start,new int[]{5-start[0],5-start[1]}),
                new Move(start,new int[]{6-start[0],6-start[1]}),
                new Move(start,new int[]{7-start[0],7-start[1]}),
                new Move(start,new int[]{8-start[0],8-start[1]}),
                new Move(start,new int[]{8-start[1],2-start[1]}),
                new Move(start,new int[]{7-start[1],3-start[1]}),
                new Move(start,new int[]{6-start[1],4-start[1]}),
                new Move(start,new int[]{5-start[1],5-start[1]}),
                new Move(start,new int[]{4-start[1],6-start[1]}),
                new Move(start,new int[]{3-start[1],7-start[1]}),
                new Move(start,new int[]{2-start[1],8-start[1]})
        );
    }
    @Override
    public String toString(){
        return "B";
    }
}
