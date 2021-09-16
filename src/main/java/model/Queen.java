package model;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.abs;

public class Queen extends Piece {
    public Queen(int id, Color col) {
        super(id, col);
    }
    public Queen(int id, Color col, int[] position) {
        super(id, col, position);
    }
    @Override
    public boolean isQueen(){
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
                new Move(start,new int[]{0,1-start[1]}),
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
    /**
     * Find if the Queen move is legal in 15 or so operations
     */
    public boolean isValidMove(Move move){
        return (abs(move.getStart()[0] - move.getEnd()[0]) == abs(move.getEnd()[1] - move.getStart()[1]))
                ||
                (move.getStart()[0] == move.getEnd()[0] && move.getStart()[1] != move.getEnd()[1])
                || (move.getStart()[1] == move.getEnd()[1] && move.getStart()[0] != move.getEnd()[0]);
    }
    @Override
    public String toString(){
        return "Q";
    }
}
