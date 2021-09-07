package model;

import java.util.HashMap;

public abstract class Board {
    private int[][] boardRep;
    private HashMap<Integer, Piece> pieces = new HashMap<>();
    private Color turn;

    public abstract void makeMove();
    public Color getTurn(){
        return turn;
    }
    protected void setTurn(Color nextTurn){
        turn = nextTurn;
    }
    protected void setBoardRep(int[][] bdrep){
        boardRep = bdrep;
    }
    protected void putPiece(Piece piece){
        pieces.put(piece.getPieceID(), piece);
    }
    protected HashMap getPieces(){
        return pieces;
    }

}
