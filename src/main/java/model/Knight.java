package model;

import java.util.List;

public class Knight extends Piece {
    public Knight(int id, Color col) {
        super(id, col);
    }

    @Override
    public List getCandidateMoves() {
        return null;
    }
}
