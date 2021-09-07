package model;

import java.util.HashMap;

public abstract class Board {
    /**
     * A base representation of a game board
     * for some game
     */
    private int[][] boardRep;
    private HashMap<Integer, Piece> pieces = new HashMap<>();
    private Color turn;

    /**
     * makes a move on the board
     */
    public abstract void makeMove(Move move);

    /**
     * Returns whose turn it is
     * via color
     * @return turn
     */
    public Color getTurn(){
        return turn;
    }

    /**
     * Sets it to the next players turn
     * @param nextTurn :: the next turn
     */
    protected void setTurn(Color nextTurn){
        turn = nextTurn;
    }

    /**
     * Sets the board representation
     * Used by subclasses to initialize
     * the board state
     * @param bdrep:: the state to set the board
     */
    protected void setBoardRep(int[][] bdrep){
        boardRep = bdrep;
    }

    /**
     * Puts a piece in the piece hashmap
     * @param piece :: piece to put in hashmap
     */
    protected void putPiece(Piece piece){
        pieces.put(piece.getPieceID(), piece);
    }

    /**
     * Get the HashMap of Pieces
     * @return pieces
     */
    protected HashMap<Integer, Piece> getPieces(){
        return pieces;
    }
    protected int getSquare(int[] index){
        return boardRep[index[0]-1][index[1]-1];
    }
    private void setSquare(int[] index, int value){
        boardRep[index[0]-1][index[1]-1] = value;
    }
    protected void movePiece(Move move){
        int pieceID = getSquare(move.getStart());
        setSquare(move.getStart(), 0);
        setSquare(move.getEnd(), pieceID);

    }
    public int[][] getBoardRep() {
        return boardRep;
    }
    protected Piece getPiece(int id){
        return pieces.get(id);
    }
}
