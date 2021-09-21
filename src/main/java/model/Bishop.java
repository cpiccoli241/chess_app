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
        int[] temp = Arrays.copyOf(getPosition(),2);
        int[] diagonal1 = getStartingdiag(getPosition()[0], getPosition()[1]);
        int[] diagonal2 = getStartingdiag(getPosition()[0], 8-getPosition()[1]);
        while(diagonal1[0] <= 8 && diagonal1[1] <=8){
            moves.add(Move.MoveEndStart(getPosition(), diagonal1, this));
            diagonal1[0]++;
            diagonal1[1]++;
        }
        while(diagonal2[0] <= 8 && diagonal2[1] <=8){
            moves.add(Move.MoveEndStart(getPosition(), diagonal2, this));
            diagonal2[0]--;
            diagonal2[1]++;
        }
        return moves;
    }
    private int[] getStartingdiag(int pos1, int pos2){
        // one of these will be negative or they will both be 0
        int border1 = pos1 - pos2;
        int border2 = pos2-pos1;
        int[] diag = new int[2];
        // finds the border tile
        if(border2 >= border1) {
            diag[0] = 1;
            diag[1] = border2+1;
        }else{
            diag[1] = 1;
            diag[0] = border1+1;
        }
        return diag;
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
