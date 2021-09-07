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
     * in a list form
     * @return List
     */
    public abstract List getCandidateMoves();
}
