package model;

import java.util.List;

public class King extends Piece {
    private boolean hasmoved = false;

    public King(int id, Color col) {
        super(id, col);
    }

    @Override
    public List getCandidateMoves() {
        return null;
    }
    @Override
    public String toString(){
        return "K";
    }
}
