package model;

import java.util.List;

public abstract class Piece {
    /**
     * Abstract Class for Pieces on a Board
     * Contains Information for Subclasses
     * (duh)
     */
    private int pieceID;
    private int[] direction;
    private Color col;
    public Piece(int id, int[] direction, Color col){
        pieceID = id;
        /**
         * Represents 3 axis of movement
         * 0 forwards/backwards
         * 1 left right
         * 2 diagonal
         */
        this.direction = direction;
        this.col = col;
    }
    /**
     * Gets the Directions the Piece
     * Can Move
     * @return direction
     */
    public int[] getDirection() {
        return direction;
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
    public abstract List<Move> getCandidateMoves();
}
