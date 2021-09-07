package model;

import java.util.List;

public class Queen extends Piece {
    public Queen(int id, Color col) {
        super(id, col);
    }

    @Override
    public List<Move> getCandidateMoves(int[] start) {
        return null;
    }
    @Override
    public String toString(){
        return "Q";
    }
}
