package model;

import java.util.List;

public abstract class Piece {
    /**
     * Abstract Class for Pieces on a Board
     * Contains Information for Subclasses
     * (duh)
     */
    private int pieceID;
    private Color col;
    public Piece(int id, Color col){
        pieceID = id;
        this.col = col;
    }

    /**
     * Gets the Color of the Piece
     * @return col
     */
    public Color getColor() {
        return col;
    }
    /**
     * Gets the ID of the Piece
     * @return pieceID
     */
    public int getPieceID() {
        return pieceID;
    }
    /**
     * Gets the possible moves for the piece
     * in a array form
     * @return array
     */
    public abstract List<Move> getCandidateMoves(int[] start);
    public boolean isValidMove(Move move){
        return getCandidateMoves(move.getStart()).contains(move);
    }
}
