package model;

import java.util.Arrays;
import java.util.List;

public class Pawn extends Piece {
    private boolean hasMoved = false;

    public Pawn(int id,Color col) {
        super(id, new int[]{1,0,0}, col);
    }

    @Override
    public List<Move> getCandidateMoves() {
        Move[] moves;
        if (hasMoved == false){
            //has the extra space pawn move
            moves = new Move[]{new Move(new int[]{2,0,0}),
                    new Move(new int[]{1,0,0}),
                    new Move(new int[]{0,1,0}),
                    new Move(new int[]{0,-1,0})};
        }else
            // does not have the extra space pawn move
            moves = new Move[]{new Move(new int[]{1,0,0}),
                    new Move(new int[]{0,1,0}),
                    new Move(new int[]{0,-1,0})};;
        return Arrays.asList(moves);
    }
}
