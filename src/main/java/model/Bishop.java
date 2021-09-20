package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.abs;

public class Bishop extends Piece {
    public Bishop(int id, Color col) {
        super(id, col);
    }

    public Bishop(int id, Color col, int[] position) {
        super(id, col, position);
    }

    @Override
    public boolean isBishop() {
        return true;
    }

    @Override
    /**
     * Not usable rn @todo
     */
    public List<Move> getCandidateMoves() {
        List<Move> moves = new ArrayList<>();
        int[] end = Arrays.copyOf(getPosition(), 2);
        // very grossly find all the bishop moves
        while (end[0] <= ChessBoardRep.BOARD_LENGTH && end[1] <= ChessBoardRep.BOARD_LENGTH) {
            end[0] = end[0] + 1;
            end[1] = end[1] + 1;
            moves.add(new Move(getPosition(), end));
        }

        end = Arrays.copyOf(getPosition(), 2);
        while (end[0] > 0 && end[1] > 0) {
            end[0] = end[0] - 1;
            end[1] = end[1] - 1;
            moves.add(new Move(getPosition(), end));
        }

        end = Arrays.copyOf(getPosition(), 2);
        while (end[0] <= ChessBoardRep.BOARD_LENGTH && end[1] > 0) {
            end[0] = end[0] + 1;
            end[1] = end[1] - 1;
            moves.add(new Move(getPosition(), end));
        }

        end = Arrays.copyOf(getPosition(), 2);
        while (end[0] > 0 && end[1] <= ChessBoardRep.BOARD_LENGTH) {
            end[0] = end[0] + 1;
            end[1] = end[1] - 1;
            moves.add(new Move(getPosition(), end));
        }
        return moves;
    }

    @Override
    /**
     * Find if the bishop move is legal in 5 or so operations
     */
    public boolean isValidMove(Move move) {
        return abs(move.getStart()[0] - move.getEnd()[0]) == abs(move.getEnd()[1] - move.getStart()[1]);
    }

    @Override
    public String toString() {
        return "B";
    }
}
