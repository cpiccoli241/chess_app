package model;

import java.util.List;

public class Queen extends Piece {
    public Queen(int id, Color col) {
        super(id, col);
    }

    @Override
    public List getCandidateMoves() {
        return null;
    }
}
