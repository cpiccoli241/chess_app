package model;

import java.util.Arrays;
import java.util.List;

public abstract class Piece {
    /**
     * Abstract Class for Pieces on a Board
     * Contains Information for Subclasses
     * (duh)
     */
    private final int pieceID;
    private final Color col;
    private int[] position;

    public Piece(int id, Color col) {
        pieceID = id;
        this.col = col;
    }

    public Piece(int id, Color col, int[] position) {
        pieceID = id;
        this.col = col;
        this.position = Arrays.copyOf(position, 2);
    }

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isQueen() {
        return false;
    }

    public boolean isKnight() {
        return false;
    }

    public boolean isBishop() {
        return false;
    }

    public boolean isRook() {
        return false;
    }

    public boolean isEmpty() {
        return false;
    }

    /**
     * Gets the Color of the Piece
     *
     * @return col
     */
    public Color getColor() {
        return col;
    }

    /**
     * Gets the ID of the Piece
     *
     * @return pieceID
     */
    public int getPieceID() {
        return pieceID;
    }

    /**
     * Gets the possible moves for the piece
     * in a array form
     *
     * @return array
     */
    public abstract List<Move> getCandidateMoves();

    public boolean isValidMove(Move move) {
        return getCandidateMoves().contains(move);
    }

    /**
     * Dummy function because it is used by
     * King, Pawn, and Rook to assure castling rights and
     * double pawn advance
     */
    public void hasMoved() {
    }

    public void move(int[] end) {
        position = Arrays.copyOf(end, 2);
    }

    public int[] getPosition() {
        return position;
    }

    public boolean canPromote() {
        return false;
    }
}
