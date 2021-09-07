package model;

import java.util.List;

public class Rook extends Piece {
    private boolean hasmoved = false;

    public Rook(int id, Color col) {
        super(id, col);
    }

    @Override
    public List<Move> getCandidateMoves(int[] start) {
        return null;
    }
    @Override
    public String toString(){
        return "R";
    }
}
