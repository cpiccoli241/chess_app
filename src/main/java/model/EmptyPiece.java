package model;

import java.util.Collections;
import java.util.List;

public class EmptyPiece extends Piece {
    public EmptyPiece() {
        super(0, Color.BLANK);
    }

    @Override
    public List getCandidateMoves() {
        return Collections.emptyList();
    }
    @Override
    public String toString(){
        return " ";
    }
}
