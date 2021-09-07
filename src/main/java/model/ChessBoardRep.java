package model;

public class ChessBoardRep extends Board {
    public ChessBoardRep() {
        setBoardRep(makeBoardInit());
        // adds the default emptyPiece
        putPiece(new EmptyPiece());
        for(int i = 1; i < 9; i++) {
            putPiece(new Pawn(10 + i, Color.WHITE));
            putPiece(new Pawn(-10 - i, Color.BLACK));
        }
        //add whites pieces
        putPiece(new Rook(1, Color.WHITE));
        putPiece(new Knight(2, Color.WHITE));
        putPiece(new Bishop(3, Color.WHITE));
        putPiece(new King(4, Color.WHITE));
        putPiece(new Queen(5, Color.WHITE));
        putPiece(new Bishop(6, Color.WHITE));
        putPiece(new Knight(7, Color.WHITE));
        putPiece(new Rook(8, Color.WHITE));
        //add whites pieces
        putPiece(new Rook(-1, Color.BLACK));
        putPiece(new Knight(-2, Color.BLACK));
        putPiece(new Bishop(-3, Color.BLACK));
        putPiece(new King(-4, Color.BLACK));
        putPiece(new Queen(-5, Color.BLACK));
        putPiece(new Bishop(-6, Color.BLACK));
        putPiece(new Knight(-7, Color.BLACK));
        putPiece(new Rook(-8, Color.BLACK));
    }
    private int[][] makeBoardInit(){
        // note that according to oracle
        // all the values are defaulted to 0
        int[][] bdrep = new int[8][8];
        // add the values of the piece IDS
        bdrep[0] = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        bdrep[1] = new int[]{11, 12, 13, 14, 15, 16, 17, 18};
        bdrep[7] = new int[]{-1, -2, -3, -4, -5, -6, -7, -8};
        bdrep[6] = new int[]{-11, -12, -13, -14, -15, -16, -17, -18};

        return bdrep;
    }
    @Override
    public void makeMove() {

    }

    /**
     * Returns a string representation of the object
     * There is no Guarantee that 2 boards with the same
     * toString are equal
     * @return A string representing the locations of all the pieces from blacks perspective
     */
    @Override
    public String toString(){
        String output = "- - - - - - - - - - - - - - - - -\n";
        for (int[] row: getBoardRep()) {
            for (int square: row) {
                output = output + "| " + getPiece(square).toString() + " ";
            }
            output = output +"|\n";

        }
        output = output+"- - - - - - - - - - - - - - - - -\n";
        return output;
    }
}
