package model;
import java.lang.StringBuilder;
import java.util.Arrays;

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

    /**
     * Helper function for constructor
     * @return makes a default chess board
     */
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

    /**
     * moves a piece on the board
     * @param move the move made
     */
    @Override
    public void makeMove(Move move) {
        if(isValidMove(move)) {
            movePiece(move);
            // Beginings of castling rights
            // and double pawn advance
            Piece piece = getPiece(getSquare(move.getEnd()));
            //does nothing unless the piece is a rook, king or pawn
            piece.hasMoved();

            nextTurn();
        }else
            //@todo throw an error?
            return;


    }

    /**
     * Checks to see if a move is valid
     * @param move being checking
     * @return whether to allow the move or not
     */
    public boolean isValidMove(Move move){
        if (!OnBoard(move.getStart()) || !OnBoard(move.getEnd()))
            return false;
        Piece piece = getPiece(getSquare(move.getStart()));
        //make sure you aren't capturing your own piece
        if(piece.getColor()==getPiece(getSquare(move.getEnd())).getColor())
            return false;
        //make sure the piece can move in this way
        if(!piece.isValidMove(move))
            return false;

        //@todo implement pins, pawn capture, etc
        //return true here cause a knight can jump over pieces
        if(piece.toString().charAt(0) == 'N')
            return true;
        if (findCollision(move))
            return false;
        return true;
    }
    /**
     * changes the current players turn
     */
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
     * Tries to find a collision in the line of the move
     * @param move being tested
     * @return whether a piece was on the line
     */
    private boolean findCollision(Move move){
        int[] dir = move.getDir();
        int[] spot = Arrays.copyOf(move.getStart(),2);
        spot[0] = spot[0] + dir[0];
        spot[1] = spot[1] + dir[1];

        while(move.getEnd()[0] != spot[0] || move.getEnd()[1] != spot[1]){
            if(getSquare(spot)!=0){
                return true;
            }
            spot[0] = spot[0] + dir[0];
            spot[1] = spot[1] + dir[1];
        }
        return false;
    }
    /**
     *
     * @return if the game is over
     */
    public boolean endState(){
        return false;
    }
}
