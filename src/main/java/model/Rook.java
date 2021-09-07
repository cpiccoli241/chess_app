package model;

import java.util.List;

public class Rook extends Piece {
    private boolean hasmoved = false;

    public Rook(int id, Color col) {
        super(id, col);
    }

    @Override
    public List getCandidateMoves() {
        return null;
    }
}
