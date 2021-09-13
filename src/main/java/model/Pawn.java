package model;

import java.util.Arrays;
import java.util.List;

public class Pawn extends Piece {
    private boolean hasMoved = false;
    private int direction;
    public Pawn(int id,Color col) {
        super(id, col);
        if (col == Color.BLACK)
            direction = -1;
        else
            direction =1;
    }
    public Pawn(int id,Color col, int[] position) {
        super(id, col, position);
        if (col == Color.BLACK)
            direction = -1;
        else
            direction =1;
    }

    @Override
    public List<Move> getCandidateMoves(int[] start) {
        Move[] moves;
        if (hasMoved == false){
            //has the extra space pawn move
            moves = new Move[]{new Move(start,new int[]{2*direction,0}),
                    new Move(start,new int[]{1*direction,0}),
                    new Move(start,new int[]{1*direction,1}),
                    new Move(start,new int[]{1*direction,-1})};
        }else
            // does not have the extra space pawn move
            moves = new Move[]{new Move(start, new int[]{1*direction,0}),
                    new Move(start,new int[]{1*direction,1}),
                    new Move(start,new int[]{1*direction,-1})};;
        return Arrays.asList(moves);
    }
    @Override
    public String toString(){
        return "P";
    }
    @Override
    public void hasMoved(){
        hasMoved = true;
    }
}
