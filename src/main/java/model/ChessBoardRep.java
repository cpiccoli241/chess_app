package model;
import util.PGNConverter;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessBoardRep extends Board {
    public static final int BOARD_LENGTH = 8;
    private static final int KingWId = 4;
    private static final int KingBId = -4;
    private boolean incheck = false;
    private boolean waitingOnPromotion = false;
    private Piece pieceToPromote;
    private ArrayList<Piece> piecesCheckingBlack = new ArrayList<>();
    private ArrayList<Piece> piecesPinningBlack = new ArrayList<>();
    private ArrayList<Piece> piecesCheckingWhite = new ArrayList<>();
    private ArrayList<Piece> piecesPinningWhite = new ArrayList<>();
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
        putPiece(new King(4, Color.WHITE, new int[]{1,4}));
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

            int KingId;
            int cl = -1;
            ArrayList<Piece> piecesChecking;
            ArrayList<Piece> piecesPinning;;
            if (Color.WHITE == getTurn()) {
                KingId = KingWId;
                 piecesChecking = piecesCheckingBlack;
                 piecesPinning = piecesPinningBlack;
            }
            else {
                KingId = KingBId;
                cl=1;
                piecesChecking = piecesCheckingWhite;
                piecesPinning = piecesPinningWhite;
            }

            if(getSquare(move.getStart())==KingId) {
                for (int i = 1; i < 9; i++) {
                    if(isValidMove(Move.MoveEndStart(getPiece(i*cl).getPosition(), move.getEnd(),getPiece(i*cl))) ||
                            isValidMove(Move.MoveEndStart(getPiece((i+10)*cl).getPosition(), move.getEnd(),getPiece((i+10)*cl))))
                        // king cant move into check escape
                        return;
                }
                movePiece(move);
                nextTurn();
                piecesPinning.removeAll(piecesPinning);
                // @todo if the king is moved new pieces become pinned need to find those
                return;
            }
            else {
                movePiece(move);

                // stop the king from moving into check

                //check to see if the move prevents the check
                //might be superfluous
                if(inCheck(piecesChecking, KingId, move)){
                    return;
                }

                //check if the piece is pinned
                if(isPinned(piecesPinning, KingId, move)){
                    return;
                }

                // the checking pieces become pinning pieces (unless king moves see below)
                if (getPiece(KingId).equals(getPiece(getSquare(move.getEnd())))) {
                    piecesChecking.removeAll(piecesChecking);

                } else {
                    piecesPinning.addAll(piecesChecking);
                    piecesChecking.removeAll(piecesChecking);
                }


                // Beginnings of castling rights
                // and double pawn advance
                //does nothing unless the piece is a rook, king or pawn
                getPiece(getSquare(move.getEnd())).hasMoved();

                waitingOnPromotion = getPiece(getSquare(move.getEnd())).canPromote();
                if(waitingOnPromotion){
                    pieceToPromote = getPiece(getSquare(move.getEnd()));
                }
                //todo get some input from?


                nextTurn();
                if (incheck)
                    incheck = false;

                // Simple check scenario checks if the piece that has moved checked king
                if (Color.WHITE == getTurn()) {
                    KingId = KingWId;
                    piecesChecking = piecesCheckingBlack;
                    piecesPinning = piecesPinningBlack;
                }
                else {
                    KingId = KingBId;
                    piecesChecking = piecesCheckingWhite;
                    piecesPinning = piecesPinningWhite;
                }
                if (isValidMove(Move.MoveEndStart(move.getEnd(), getPiece(KingId).getPosition(),getPiece(KingId)))) {
                    incheck = true;
                    piecesChecking.add(getPiece(getSquare(move.getEnd())));
                }

                // @todo only checks for 1 piece should be a collection/List
                Piece checkingPiece = checkDiscovery(move.getStart(), KingId);
                if (checkingPiece != null) {
                    incheck = true;
                    piecesChecking.add(checkingPiece);
                }
                // test to see if the piece is pinning without check
                // uses the isValidMove from the piece which does not check the board state just the way the piece can move
                else if (!getPiece(getSquare(move.getEnd())).isKing()
                        && getPiece(getSquare(move.getEnd())).isValidMove(Move.MoveEndStart(move.getEnd(), getPiece(KingId).getPosition(),getPiece(0)))) {
                    piecesPinning.add(getPiece(getSquare(move.getEnd())));
                } else {
                    piecesPinning.remove(getPiece(getSquare(move.getEnd())));
                }
            }
        }else
            //@todo throw an error?
            return;


    }

    /**
     * Helper Function to find Pinned Pieces
     * @param piecesPinning
     * @param KingId
     * @param move
     * @return
     */
    private boolean isPinned(List<Piece> piecesPinning, int KingId, Move move){
        for (Piece checkers : piecesPinning) {
            if (isValidMove(Move.MoveEndStart(checkers.getPosition(), getPiece(KingId).getPosition(), checkers))) {
                movePiece(Move.MoveEndStart(move.getEnd(), move.getStart(),checkers));
                return true;
            }
        }
        return false;
    }

    /**
     * Helper function for Checked Pieces
     * @param piecesChecking
     * @param KingId
     * @param move
     * @return
     */
    private boolean inCheck(List<Piece> piecesChecking, int KingId, Move move){
        for (Piece checkers : piecesChecking) {
            if (isValidMove(Move.MoveEndStart(checkers.getPosition(), getPiece(KingId).getPosition(),checkers))) {
                movePiece(Move.MoveEndStart(move.getEnd(), move.getStart(),checkers));
                return true;
            }
        }
        return false;
    }
    /**
     * Checks to see if a piece can promote
     * then promotes it to the string characterized by promoteTo
     * @param promoteTo the string representation of new piece type
     *
     */
    public void promotePiece(String promoteTo){
        if(pieceToPromote.canPromote()){
            Piece promoted = PGNConverter.convertFromStringToPiece(promoteTo,pieceToPromote.getColor(), pieceToPromote.getPosition(), pieceToPromote.getPieceID());
            getPieces().put(pieceToPromote.getPieceID(), promoted);
        }
    }
    /**
     * Checks the direction the piece is in of the king
     * notable it must be [1,0] [-1,1], [1,1] or [0,1] vector direction of the king
     * @param emptySQ
     * @param KingID
     * @return
     */
    private Piece checkDiscovery(int[] emptySQ, int KingID){
        Move mv = Move.MoveEndStart(emptySQ, getPiece(KingID).getPosition(), getPiece(0));
        int[] dir = mv.getDir();
        int pieceID = checkSQInDir(mv.getStart(),dir);
        // if pieceid isnonempty or is white no need to do anything else return
        if(pieceID==0||getPiece(pieceID).getColor() == getPiece(KingID).getColor())
            return null;
        // ok so what if it is black
        // Check to see if it is valid for the oppenent to take their piece
        if(isValidMove(Move.MoveEndStart(getPiece(pieceID).getPosition(), getPiece(KingID).getPosition(),getPiece(0)))){
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
        if(piece.isKnight())
            return true;
        //pawn capture case
        if(piece.isPawn())
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
        if(isInCheck()){
            return Checkmate();
        }else{
        return Stalemate();
        }
    }
    private boolean Checkmate(){
        int cl = 0;
        if(getTurn()==Color.WHITE)
            cl = 1;
        else
            cl = -1;

        int[] pieceIds = {1,2,3,4,5,6,7,8,11,12,13,14,15,16,17,18};
        for(int id:pieceIds){
            List<Move> moves = getPiece(id*cl).getCandidateMoves();
            for(Move mv:moves){
                if(isValidMove(mv))
                    return false;
            }
        }
        return true;
    }
    private boolean Stalemate(){
        return false;
    }

    /**
     * Returns if the player is in check
     * @return
     */
    public boolean isInCheck() {
        return incheck;
    }

    /**
     * Returns true if the board is waiting on what to promote to
     * false otherwise
     * @return waitingOnPromotion
     */
    public boolean isWaitingOnPromotion() {
        return waitingOnPromotion;
    }
}
