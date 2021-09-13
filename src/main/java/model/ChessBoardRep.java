package model;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Arrays;

public class ChessBoardRep extends Board {
    public static final int BOARD_LENGTH = 8;
    private static final int KingWId = 4;
    private static final int KingBId = -4;
    private boolean incheck = false;
    private ArrayList<Piece> piecesChecking = new ArrayList<>();
    private ArrayList<Piece> piecesPinning = new ArrayList<>();
    public ChessBoardRep() {
        setTurn(Color.WHITE);
        setBoardRep(makeBoardInit());
        // adds the default emptyPiece
        putPiece(new EmptyPiece());
        int[] pawnPosition = new int[2];
        for(int i = 1; i < 9; i++) {
            pawnPosition[1] = i;
            pawnPosition[0] = 2;
            putPiece(new Pawn(10 + i, Color.WHITE, pawnPosition));
            pawnPosition[0] = 7;
            putPiece(new Pawn(-10 - i, Color.BLACK, pawnPosition));
        }
        //add whites pieces
        putPiece(new Rook(1, Color.WHITE, new int[]{1,1}));
        putPiece(new Knight(2, Color.WHITE,new int[]{1,2}));
        putPiece(new Bishop(3, Color.WHITE, new int[]{1,3}));
        putPiece(new King(4, Color.WHITE, new int[]{8,4}));
        putPiece(new Queen(5, Color.WHITE, new int[]{1,5}));
        putPiece(new Bishop(6, Color.WHITE,new int[]{1,6}));
        putPiece(new Knight(7, Color.WHITE,new int[]{1,7}));
        putPiece(new Rook(8, Color.WHITE, new int[]{1,8}));
        //add whites pieces
        putPiece(new Rook(-1, Color.BLACK, new int[]{8,1}));
        putPiece(new Knight(-2, Color.BLACK,new int[]{8,2}));
        putPiece(new Bishop(-3, Color.BLACK,new int[]{8,3}));
        putPiece(new King(-4, Color.BLACK, new int[]{8,4}));
        putPiece(new Queen(-5, Color.BLACK, new int[]{8,5}));
        putPiece(new Bishop(-6, Color.BLACK,new int[]{8,6}));
        putPiece(new Knight(-7, Color.BLACK,new int[]{8,7}));
        putPiece(new Rook(-8, Color.BLACK, new int[]{8,8}));
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
        if(getTurn()!= getPiece(getSquare(move.getStart())).getColor())
            return;

        if(isValidMove(move)) {
            movePiece(move);
            //check to see if the move prevents the check
            int KingId;
            if (Color.WHITE == getTurn())
                KingId = KingWId;
            else
                KingId = KingBId;
            for(Piece checkers:piecesChecking){
                if(isValidMove(Move.MoveEndStart(checkers.getPosition(), getPiece(KingId).getPosition()))) {
                    movePiece(Move.MoveEndStart(move.getEnd(), move.getStart()));
                    return;
                }
            }
            for(Piece checkers:piecesChecking) {
                piecesPinning.add(checkers);
                piecesChecking.remove(checkers);
            }
            // Beginings of castling rights
            // and double pawn advance
            //does nothing unless the piece is a rook, king or pawn
            getPiece(getSquare(move.getEnd())).hasMoved();

            nextTurn();
            if(incheck)
                incheck=false;
            //@Todo check here

            // Simple check scenario checks if the piece that has moved checked king
            //@Todo discovery Check only possible with rooks, queen and bishop
            if (Color.WHITE == getTurn())
                KingId = KingWId;
            else
                KingId = KingBId;

            if (isValidMove(Move.MoveEndStart(move.getEnd(), getPiece(KingId).getPosition()))){
                incheck = true;
                piecesChecking.add(getPiece(getSquare(move.getEnd())));
            }
            Piece checkingPiece = checkDiscovery(move.getStart(), KingId);
            if(checkingPiece != null) {
                incheck = true;
                piecesChecking.add(checkingPiece);
            }

        }else
            //@todo throw an error?
            return;


    }

    /**
     * Checks the direction the piece is in of the king
     * notable it must be [1,0] [-1,1], [1,1] or [0,1] vector direction of the king
     * @param emptySQ
     * @param KingID
     * @return
     */
    private Piece checkDiscovery(int[] emptySQ, int KingID){
        Move mv = Move.MoveEndStart(emptySQ, getPiece(KingID).getPosition());
        int[] dir = mv.getDir();
        int pieceID = checkSQInDir(mv.getStart(),dir);
        // if pieceid isnonempty or is white no need to do anything else return
        if(pieceID==0||getPiece(pieceID).getColor() == getPiece(KingID).getColor())
            return null;
        // ok so what if it is black
        // Check to see if it is valid for the oppenent to take their piece
        if(isValidMove(Move.MoveEndStart(getPiece(pieceID).getPosition(), getPiece(KingID).getPosition()))){
            return getPiece(pieceID);
        }
        return null;
    }

    /**
     * Helper function
     * Goes in a direction until it finds a piece
     * or goes off the board
     * @param start the starting square
     * @param dir the direction to go in
     * @return the pieceid of the first nonempty piece
     */
    private int checkSQInDir(int[] start, int[] dir){
        int[] ours = Arrays.copyOf(start,2);
        ours[0] = ours[0] + dir[0];
        ours[1] = ours[1] + dir[1];
        while(OnBoard(ours)){
            if(getSquare(ours)!=0)
                return getSquare(ours);
            ours[0] = ours[0] + dir[0];
            ours[1] = ours[1] + dir[1];
        }
        return 0;
    }
    /**
     * Checks to see if a move is valid
     * @param move being checking
     * @return whether to allow the move or not
     */
    public boolean isValidMove(Move move){
        if (!OnBoard(move.getStart()) || !OnBoard(move.getEnd()))
            return false;
        //can't move a piece that isn't yours
        Piece piece = getPiece(getSquare(move.getStart()));
        //make sure you aren't capturing your own piece
        if(piece.getColor()==getPiece(getSquare(move.getEnd())).getColor())
            return false;
        //make sure the piece can move in this way
        if(!piece.isValidMove(move))
            return false;
        //@todo implement pins, etc
        //return true here cause a knight can jump over pieces
        if(piece.toString().charAt(0) == 'N')
            return true;
        //pawn capture case
        if(piece.toString().charAt(0)=='P')
            if(move.getDir()[1] != 0) {
                if (getSquare(move.getEnd()) == 0)
                    return false;
            }else if(getSquare(move.getEnd()) != 0)
                return false;

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
        String output = "| H | G | F | E | D | C | B | A |     \n" +
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

    /**
     * Returns if the player is in check
     * @return
     */
    public boolean isInCheck() {
        return incheck;
    }

}
