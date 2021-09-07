package model;

import java.util.List;

public class Bishop extends Piece {
    public Bishop(int id, Color col) {
        super(id, col);
    }

    @Override
    public List getCandidateMoves() {
        return null;
    }
    @Override
    public String toString(){
        return "B";
    }
}
