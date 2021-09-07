package model;
import java.lang.StringBuilder;

public class ChessBoardRep extends Board {
    private final int BOARD_LENGTH = 8;
    public ChessBoardRep() {
        setTurn(Color.WHITE);
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
        int[][] bdrep = new int[BOARD_LENGTH][BOARD_LENGTH];
        // add the values of the piece IDS
        bdrep[0] = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        bdrep[1] = new int[]{11, 12, 13, 14, 15, 16, 17, 18};
        bdrep[7] = new int[]{-1, -2, -3, -4, -5, -6, -7, -8};
        bdrep[6] = new int[]{-11, -12, -13, -14, -15, -16, -17, -18};

        return bdrep;
    }
    @Override
    public void makeMove(Move move) {
        if(isValid(move)) {
            movePiece(move);
            nextTurn();
        }else
            //@todo throw an error?
            return;


    }
    public boolean isValid(Move move){
        if (!OnBoard(move.getStart()) || !OnBoard(move.getEnd()))
            return false;
        //@todo implement pins, pawn capture, etc
        return getPiece(getSquare(move.getStart())).isValidMove(move);
    }
    private void nextTurn(){
        if (getTurn()==Color.WHITE)
            setTurn(Color.BLACK);
        else
            setTurn(Color.WHITE);
    }
    /**
     * Checks is the space is on the board
     * @param start :: the square to check
     * @return true if on board false otherwise
     */
    private boolean OnBoard(int[] start){
            return start[0] > 0 && start[1] > 0 && start[0] <= BOARD_LENGTH && start[1] <= BOARD_LENGTH ;

    }

    /**
     * Returns a string representation of the object
     * There is no Guarantee that 2 boards with the same
     * perspective are equal
     * @return A string representing the locations of all the pieces from blacks perspective
     */
    public String BlackPerspective(){
        String output = "| H | G | F | E | D | C | B | A |\n" +
                        "- - - - - - - - - - - - - - - - - - -\n";
        Integer counter = 1;
        for (int[] row: getBoardRep()) {
            for (int square: row) {
                output = output + "| " + getPiece(square).toString() + " ";
            }
            output = output + "|| " + counter.toString() +" |\n";
            counter++;
        }
        output = output+"- - - - - - - - - - - - - - - - - - -\n";
        return output;
    }

    /**
     * Returns a string representation of the object
     * There is no Guarantee that 2 boards with the same
     * perspective are equal
     * @return A string representing the locations of all the pieces from blacks perspective
     */
    public String WhitePerspective(){
        return new StringBuilder().append(BlackPerspective()).reverse().toString()+"\n";
    }

    /**
     *
     * @return if the game is over
     */
    public boolean endState(){
        return false;
    }
}
