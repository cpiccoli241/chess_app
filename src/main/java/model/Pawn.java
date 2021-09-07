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

    @Override
    public List<Move> getCandidateMoves() {
        Move[] moves;
        if (hasMoved == false){
            //has the extra space pawn move
            moves = new Move[]{new Move(new int[]{2*direction,0,0}),
                    new Move(new int[]{1*direction,0,0}),
                    new Move(new int[]{1*direction,1,0}),
                    new Move(new int[]{1*direction,-1,0})};
        }else
            // does not have the extra space pawn move
            moves = new Move[]{new Move(new int[]{1*direction,0,0}),
                    new Move(new int[]{1*direction,1,0}),
                    new Move(new int[]{1*direction,-1,0})};;
        return Arrays.asList(moves);
    }
}
