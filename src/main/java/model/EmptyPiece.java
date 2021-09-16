package model;

import java.util.Collections;
import java.util.List;

public class EmptyPiece extends Piece {
    public EmptyPiece() {
        super(0, Color.BLANK);
    }
    @Override
    public boolean isEmpty(){
        return true;
    }
    @Override
    public List<Move> getCandidateMoves(int[] start) {
        return Collections.emptyList();
    }
    @Override
    public String toString(){
        return " ";
    }
}
