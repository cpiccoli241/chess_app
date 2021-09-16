package model;

import java.util.Arrays;
import java.util.List;

public class Rook extends Piece {
    private boolean hasmoved = false;

    public Rook(int id, Color col) {
        super(id, col);
    }
    public Rook(int id, Color col, int[] position) {
        super(id, col, position);
    }
    @Override
    public boolean isRook(){
        return true;
    }
    @Override
    public List<Move> getCandidateMoves(int[] start) {
        return Arrays.asList(
                new Move(start,new int[]{2-start[0],0}),
                new Move(start,new int[]{3-start[0],0}),
                new Move(start,new int[]{4-start[0],0}),
                new Move(start,new int[]{5-start[0],0}),
                new Move(start,new int[]{6-start[0],0}),
                new Move(start,new int[]{7-start[0],0}),
                new Move(start,new int[]{8-start[0],0}),
                new Move(start,new int[]{0,2-start[1]}),
                new Move(start,new int[]{0,3-start[1]}),
                new Move(start,new int[]{0,4-start[1]}),
                new Move(start,new int[]{0,5-start[1]}),
                new Move(start,new int[]{0,6-start[1]}),
                new Move(start,new int[]{0,7-start[1]}),
                new Move(start,new int[]{0,1-start[1]})
                );
    }
    @Override
    public boolean isValidMove(Move move){
        return (move.getStart()[0] == move.getEnd()[0] && move.getStart()[1] != move.getEnd()[1])
                || (move.getStart()[1] == move.getEnd()[1] && move.getStart()[0] != move.getEnd()[0]) ;
    }
    @Override
    public String toString(){
        return "R";
    }
}
