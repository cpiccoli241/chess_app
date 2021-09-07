package model;

public class ChessBoardRep extends Board {
    public ChessBoardRep() {
        setBoardRep(makeBoardInit());
        putPiece(new EmptyPiece());
        //@TODO requires at least 1 real piece to be instantiated
        //for(int i = 1; i < 9; i++)
        //    putPiece(new Pawn());
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
}
