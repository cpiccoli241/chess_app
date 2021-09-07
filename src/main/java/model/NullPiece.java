package model;

import java.util.Collections;
import java.util.List;

public class NullPiece extends Piece {
    private int pieceID = 0;
    private int[] direction = {0,0,0};
    private Color col = Color.BLANK;

    @Override
    public List getCandidateMoves() {
        return Collections.emptyList();
    }
}
