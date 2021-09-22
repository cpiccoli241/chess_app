package model;

import java.util.Arrays;
import java.util.List;

public class Rook extends Piece {
    private final boolean hasmoved = false;

    public Rook(int id, Color col) {
        super(id, col);
    }

    public Rook(int id, Color col, int[] position) {
        super(id, col, position);
    }

    @Override
    public boolean isRook() {
        return true;
    }

    @Override
    public List<Move> getCandidateMoves() {
        return Arrays.asList(
                new Move(getPosition(), new int[]{2 - getPosition()[0], 0}),
                new Move(getPosition(), new int[]{3 - getPosition()[0], 0}),
                new Move(getPosition(), new int[]{4 - getPosition()[0], 0}),
                new Move(getPosition(), new int[]{5 - getPosition()[0], 0}),
                new Move(getPosition(), new int[]{6 - getPosition()[0], 0}),
                new Move(getPosition(), new int[]{7 - getPosition()[0], 0}),
                new Move(getPosition(), new int[]{8 - getPosition()[0], 0}),
                new Move(getPosition(), new int[]{0, 2 - getPosition()[1]}),
                new Move(getPosition(), new int[]{0, 3 - getPosition()[1]}),
                new Move(getPosition(), new int[]{0, 4 - getPosition()[1]}),
                new Move(getPosition(), new int[]{0, 5 - getPosition()[1]}),
                new Move(getPosition(), new int[]{0, 6 - getPosition()[1]}),
                new Move(getPosition(), new int[]{0, 7 - getPosition()[1]}),
                new Move(getPosition(), new int[]{0, 1 - getPosition()[1]})
        );
    }

    @Override
    public boolean isValidMove(Move move) {
        return (move.getStart()[0] == move.getEnd()[0] && move.getStart()[1] != move.getEnd()[1])
                || (move.getStart()[1] == move.getEnd()[1] && move.getStart()[0] != move.getEnd()[0]);
    }

    @Override
    public String toString() {
        return "R";
    }
}
