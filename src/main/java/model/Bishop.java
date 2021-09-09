package model;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.abs;

public class Bishop extends Piece {
    public Bishop(int id, Color col) {
        super(id, col);
    }

    @Override
    /**
     * Not usable rn @todo
     */
    public List<Move> getCandidateMoves(int[] start) {
        List<Move> moves = Arrays.asList(
                new Move(start,2-start[0],2-start[1]),
                new Move(start,  3-start[0],3-start[1]),
                new Move(start,  4-start[0],4-start[1]),
                new Move(start,  5-start[0],5-start[1]),
                new Move(start,  6-start[0],6-start[1]),
                new Move(start,  7-start[0],7-start[1]),
                new Move(start,  8-start[0],8-start[1]),
                new Move(start,  8-start[0],2-start[1]),
                new Move(start,  7-start[0],3-start[1]),
                new Move(start,  6-start[0],4-start[1]),
                new Move(start,  5-start[0],5-start[1]),
                new Move(start,  4-start[0],6-start[1]),
                new Move(start,  3-start[0],7-start[1]),
                new Move(start,  2-start[0],8-start[1])
        );
        return moves;
    }
    @Override
    /**
     * Find if the bishop move is legal in 5 or so operations
     */
    public boolean isValidMove(Move move){
        return abs(move.getStart()[0] - move.getEnd()[0]) == abs(move.getEnd()[1] - move.getStart()[1]);
    }
    @Override
    public String toString(){
        return "B";
    }
}
