package model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import util.PGNConverter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("Model-tier")
public class ChessBoardRepTest {
    private void helper_test_move(ChessBoardRep bd, String sq1, String sq2, int id1, int id2){
        assertTrue(bd.getSquare(PGNConverter.convertFromPGNToSquare(sq1))==id1);
        assertTrue(bd.getSquare(PGNConverter.convertFromPGNToSquare(sq2))==id2);
    }
    @Test
    public void testMovePawn(){
        ChessBoardRep bd = new ChessBoardRep();
        PGNConverter.setup();

        // test white moving pawn forward 1
        bd.makeMove(PGNConverter.convertFromPGNToMove("pd2d3"));
        helper_test_move(bd, "d2", "d3", 0, 15);
        //test black moving pawn forward 1
        bd.makeMove(PGNConverter.convertFromPGNToMove("pd7d6"));
        helper_test_move(bd, "d7", "d6", 0, -15);

        //white moving pawn forward 2
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe2e4"));
        helper_test_move(bd, "e2", "e4", 0, 14);
        //black moving pawn forwad 2
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe7e5"));
        helper_test_move(bd, "e7", "e5", 0, -14);

        //Now lets try illegal moves

        //Backwards pawn moves
        // test white moving pawn backward 1
        bd.makeMove(PGNConverter.convertFromPGNToMove("pd3d2"));
        helper_test_move(bd, "d3", "d2", 15, 0);

        //test black moving pawn backward 1
        bd.setTurn(Color.BLACK);
        bd.makeMove(PGNConverter.convertFromPGNToMove("pd6d7"));
        helper_test_move(bd, "d7", "d6", 0, -15);
        //white moving pawn backward 2
        bd.setTurn(Color.WHITE);
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe4e2"));
        helper_test_move(bd, "e2", "e4", 0, 14);
        //black moving pawn backward 2
        bd.setTurn(Color.BLACK);
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe5e7"));
        helper_test_move(bd, "e5", "e7", -14, 0);
    }
    @Test
    public void testPawnCapture(){
        ChessBoardRep bd = new ChessBoardRep();
        PGNConverter.setup();
        //white moving pawn forward 2
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe2e4"));
        //black moving pawn forward 2
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe7e5"));

        //Forwards Capture Moves
        bd.setTurn(Color.WHITE);
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe4e5"));
        helper_test_move(bd, "e4", "e5", 14, -14);

        //black captures forwards
        bd.setTurn(Color.BLACK);
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe5e4"));
        helper_test_move(bd, "e4", "e5", 14, -14);

        //Make Sure Pawns can only move diagonally on capture
        bd.setTurn(Color.WHITE);
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe4d5"));
        helper_test_move(bd,   "e4", "d5", 14, 0);

        bd.setTurn(Color.BLACK);
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe5d4"));
        helper_test_move(bd,   "e5", "d4", -14, 0);

        // Test Actual Pawn Capture
        bd.setTurn(Color.WHITE);
        bd.makeMove(PGNConverter.convertFromPGNToMove("pd2d4"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe5d4"));
        helper_test_move(bd,   "e5", "d4", 0, -14);
    }
    @Test
    public void testEnPassant() {
        ChessBoardRep bd = new ChessBoardRep();
        PGNConverter.setup();
        //white moving pawn forward 2
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe2e4"));
        //black moving pawn forward 2
        bd.makeMove(PGNConverter.convertFromPGNToMove("pa7a5"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe4e5"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pd7d5"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe5d6"));
        helper_test_move(bd,   "e5", "d6", 0, 14);
        helper_test_move(bd,   "d5", "d6", 0, 14);
    }
    @Test
    public void testMoveKnight(){
        ChessBoardRep bd = new ChessBoardRep();
         PGNConverter.setup();

        // test white moving knight
        bd.makeMove(PGNConverter.convertFromPGNToMove("hg1f3"));

        helper_test_move(bd,   "g1", "f3", 0, 2);

        //test black moving knight
        bd.makeMove(PGNConverter.convertFromPGNToMove("hg8f6"));
        helper_test_move(bd,   "g8", "f6", 0, -2);

        //knight again
        bd.makeMove(PGNConverter.convertFromPGNToMove("hf3e5"));
        helper_test_move(bd,   "f3", "e5", 0, 2);

        //black moving knight again
        bd.makeMove(PGNConverter.convertFromPGNToMove("hf6e4"));
        helper_test_move(bd,   "f6", "e4", 0, -2);

        //test some illegal moves for knight
        bd.makeMove(PGNConverter.convertFromPGNToMove("he5e6"));
        helper_test_move(bd,   "f3", "e5", 0, 2);

        bd.makeMove(PGNConverter.convertFromPGNToMove("he5e7"));
        helper_test_move(bd,   "f3", "e5", 0, 2);

        bd.makeMove(PGNConverter.convertFromPGNToMove("he5d6"));
        helper_test_move(bd,   "f3", "e5", 0, 2);
    }
    @Test
    public void testBishopMoves() {
        ChessBoardRep bd = new ChessBoardRep();
         PGNConverter.setup();
        //white moving pawn forward 2
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe2e4"));
        //black moving pawn forward 2
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe7e5"));

        //test white moving bishop passively
        bd.makeMove(PGNConverter.convertFromPGNToMove("bf1e2"));
        helper_test_move(bd,   "f1", "e2", 0, 3);
        //test white moving bishop passively
        bd.makeMove(PGNConverter.convertFromPGNToMove("bf8c5"));
        helper_test_move(bd,   "f8", "c5", 0, -3);
        //test white moving bishop passively
        bd.makeMove(PGNConverter.convertFromPGNToMove("be2f1"));
        helper_test_move(bd,   "f1", "e2", 3, 0);
        //test white moving bishop passively
        bd.makeMove(PGNConverter.convertFromPGNToMove("bc5e3"));
        helper_test_move(bd,   "c5", "e3", 0, -3);

        //illegal bishop moves
        //test white moving bishop straight
        bd.makeMove(PGNConverter.convertFromPGNToMove("bf1f6"));
        helper_test_move(bd,   "f6", "f1", 0, 3);

    }
    @Test
    public void testRookMoves() {
        ChessBoardRep bd = new ChessBoardRep();
         PGNConverter.setup();
        //white moving pawn forward 2
        bd.makeMove(PGNConverter.convertFromPGNToMove("pa2a4"));
        //black moving pawn forward 2
        bd.makeMove(PGNConverter.convertFromPGNToMove("pa7a5"));

        //test white moving rook passively
        bd.makeMove(PGNConverter.convertFromPGNToMove("ra1a2"));
        helper_test_move(bd,   "a1", "a2", 0, 8);
        //test white moving rook passively
        bd.makeMove(PGNConverter.convertFromPGNToMove("ra8a7"));
        helper_test_move(bd,   "a8", "a7", 0, -8);
        //test white moving rook passively
        bd.makeMove(PGNConverter.convertFromPGNToMove("ra2a3"));
        helper_test_move(bd,   "a2", "a3", 0, 8);
        //test white moving rook passively
        bd.makeMove(PGNConverter.convertFromPGNToMove("ra7a6"));
        helper_test_move(bd,   "a7", "a6", 0, -8);

        //illegal bishop moves
        //test white moving bishop straight
        bd.makeMove(PGNConverter.convertFromPGNToMove("bf1f6"));
        helper_test_move(bd,   "f6", "f1", 0, 3);

    }
    @Test
    public void testQueenMoves() {
        ChessBoardRep bd = new ChessBoardRep();
         PGNConverter.setup();
        //white moving pawn forward 2
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe2e4"));
        //black moving pawn forward 2
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe7e5"));

        //test white moving bishop passively
        bd.makeMove(PGNConverter.convertFromPGNToMove("qd1e2"));
        helper_test_move(bd,   "d1", "e2", 0, 5);
        //test white moving bishop passively
        bd.makeMove(PGNConverter.convertFromPGNToMove("qd8e7"));
        helper_test_move(bd,   "d8", "e7", 0, -5);
        //test white moving bishop passively
        bd.makeMove(PGNConverter.convertFromPGNToMove("qe2d3"));
        helper_test_move(bd,   "e2", "d3", 0, 5);
        //test white moving bishop passively
        bd.makeMove(PGNConverter.convertFromPGNToMove("qe7d6"));
        helper_test_move(bd,   "e7", "d6", 0, -5);

        //test queen moving like rook
        bd.makeMove(PGNConverter.convertFromPGNToMove("qd3e3"));
        helper_test_move(bd,   "d3", "e3", 0, 5);
        //test white moving bishop passively
        bd.makeMove(PGNConverter.convertFromPGNToMove("qd6e6"));
        helper_test_move(bd,   "d6", "e6", 0, -5);
        //test queen moving like rook
        bd.makeMove(PGNConverter.convertFromPGNToMove("qe3h3"));
        helper_test_move(bd,   "e3", "h3", 0, 5);
        //test white moving bishop passively
        bd.makeMove(PGNConverter.convertFromPGNToMove("qe6h6"));
        helper_test_move(bd,   "e6", "h6", 0, -5);
    }
    @Test
    public void testKingMoves() {
        ChessBoardRep bd = new ChessBoardRep();
         PGNConverter.setup();
        //white moving pawn forward 2
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe2e4"));
        //black moving pawn forward 2
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe7e5"));

        bd.makeMove(PGNConverter.convertFromPGNToMove("ke1e2"));
        helper_test_move(bd,   "e1", "e2", 0, 4);
        bd.makeMove(PGNConverter.convertFromPGNToMove("ke8e7"));
        helper_test_move(bd,   "e8", "e7", 0, -4);

        bd.makeMove(PGNConverter.convertFromPGNToMove("ke2d3"));
        helper_test_move(bd,   "e2", "d3", 0, 4);
        bd.makeMove(PGNConverter.convertFromPGNToMove("ke7d6"));
        helper_test_move(bd,   "e7", "d6", 0, -4);

        bd.makeMove(PGNConverter.convertFromPGNToMove("kd3e3"));
        helper_test_move(bd,   "d3", "e3", 0, 4);
        bd.makeMove(PGNConverter.convertFromPGNToMove("kd6e6"));
        helper_test_move(bd,   "d6", "e6", 0, -4);

    }
    @Test
    public void testEmptyMoves() {
        ChessBoardRep bd = new ChessBoardRep();
         PGNConverter.setup();

        bd.makeMove(PGNConverter.convertFromPGNToMove("ee3e4"));
        helper_test_move(bd,   "e3", "e4", 0, 0);
        assertEquals(bd.getTurn(),Color.WHITE);
    }

    /*
    Now we begin testing other rules like looking for collisions
     */
    @Test
    public void testQueenCollisions() {
        ChessBoardRep bd = new ChessBoardRep();
         PGNConverter.setup();

        //test white moving queen through pawn
        bd.makeMove(PGNConverter.convertFromPGNToMove("qd1e3"));
        helper_test_move(bd,   "d1", "c3", 5, 0);
        //test black moving queen through pawn
        bd.setTurn(Color.BLACK);
        bd.makeMove(PGNConverter.convertFromPGNToMove("qd8c6"));
        helper_test_move(bd,   "d8", "c6", -5, 0);

        bd.setTurn(Color.WHITE);
        //test white moving queen through pawn
        bd.makeMove(PGNConverter.convertFromPGNToMove("qd1d3"));
        helper_test_move(bd,   "d1", "d3", 5, 0);
        //test black moving queen through pawn
        bd.setTurn(Color.BLACK);
        bd.makeMove(PGNConverter.convertFromPGNToMove("qd8d6"));
        helper_test_move(bd,   "d8", "d6", -5, 0);
    }
    @Test
    public void testRookCollision() {
        ChessBoardRep bd = new ChessBoardRep();
         PGNConverter.setup();
        //test white moving queen through pawn
        bd.makeMove(PGNConverter.convertFromPGNToMove("ra1a3"));
        helper_test_move(bd,   "a1", "a3", 8, 0);
        //test black moving queen through pawn
        bd.setTurn(Color.BLACK);
        bd.makeMove(PGNConverter.convertFromPGNToMove("ra8a6"));
        helper_test_move(bd,   "a8", "a6", -8, 0);
    }
    @Test
    public void testBishopCollision(){
        ChessBoardRep bd = new ChessBoardRep();
         PGNConverter.setup();
        //test white moving bishop through pawn
        bd.makeMove(PGNConverter.convertFromPGNToMove("bf1e3"));
        helper_test_move(bd,   "f1", "e3", 3, 0);
        //test white moving bishop through pawn
        bd.setTurn(Color.BLACK);
        bd.makeMove(PGNConverter.convertFromPGNToMove("bf8c5"));
        helper_test_move(bd,   "f8", "c5", -3, 0);
    }
    @Test
    public void testKingSelfCapture(){
        ChessBoardRep bd = new ChessBoardRep();
         PGNConverter.setup();
        bd.makeMove(PGNConverter.convertFromPGNToMove("ke1e2"));
        helper_test_move(bd,   "e1", "e2", 4, 14);
        bd.makeMove(PGNConverter.convertFromPGNToMove("ke8e7"));
        helper_test_move(bd,   "e8", "e7", -4, -14);

    }

    /*
     *
     * The tests for checks
     *
     */
    @Test
    public void testCheckSeen(){
        ChessBoardRep bd = new ChessBoardRep();
        PGNConverter.setup();
        bd.makeMove(PGNConverter.convertFromPGNToMove("pd2d4"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pd7d5"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe2e4"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe7e5"));

        bd.makeMove(PGNConverter.convertFromPGNToMove("bf1b5"));
        assertTrue(bd.isInCheck());
        assertTrue(bd.getTurn()==Color.BLACK);
    }
    @Test
    public void testCheckLimitsMoves(){
        ChessBoardRep bd = new ChessBoardRep();
        PGNConverter.setup();
        bd.makeMove(PGNConverter.convertFromPGNToMove("pd2d4"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pd7d5"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe2e4"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe7e5"));

        bd.makeMove(PGNConverter.convertFromPGNToMove("bf1b5"));
        assertTrue(bd.isInCheck());
        assertTrue(bd.getTurn()==Color.BLACK);
        // this move should be caught as illegal
        bd.makeMove(PGNConverter.convertFromPGNToMove("pa7a6"));
        assertTrue(bd.isInCheck());
        assertTrue(bd.getTurn()==Color.BLACK);
        bd.makeMove(PGNConverter.convertFromPGNToMove("pc7c6"));
        assertFalse(bd.isInCheck());
        assertTrue(bd.getTurn()==Color.WHITE);


    }
    @Test
    public void kingMovesIntoCheck(){
        ChessBoardRep bd = new ChessBoardRep();
        PGNConverter.setup();
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe2e4"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe7e5"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pd2d4"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pc7c6"));

        bd.makeMove(PGNConverter.convertFromPGNToMove("bc1g5"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("ke8e7"));
        helper_test_move(bd,   "e8", "e7", -4, 0);
        assertEquals(bd.getTurn(), Color.BLACK);
        // does not check to see if the piece is removed from checking pieces, or pinning pieces

    }
    /*
     *
     * tests for pins
     *
     */
    @Test
    public void kingMovesPin(){
        ChessBoardRep bd = new ChessBoardRep();
        PGNConverter.setup();
        bd.makeMove(PGNConverter.convertFromPGNToMove("pd2d4"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pd7d5"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe2e4"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe7e5"));

        bd.makeMove(PGNConverter.convertFromPGNToMove("bf1b5"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("ke8e7"));
        helper_test_move(bd,   "e8", "e7", 0, -4);
        // does not check to see if the piece is removed from checking pieces, or pinning pieces

    }
    @Test
    public void testPieceBlocksCheckBecomesPinned(){
        ChessBoardRep bd = new ChessBoardRep();
        PGNConverter.setup();
        bd.makeMove(PGNConverter.convertFromPGNToMove("pd2d4"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pd7d5"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe2e4"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe7e5"));

        bd.makeMove(PGNConverter.convertFromPGNToMove("bf1b5"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pc7c6"));

        bd.makeMove(PGNConverter.convertFromPGNToMove("pa2a3"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pc6c5"));

        helper_test_move(bd,   "c6", "c5", -16, 0);

    }
    @Test
    public void testPieceBecomesPinned(){
        ChessBoardRep bd = new ChessBoardRep();
        PGNConverter.setup();
        bd.makeMove(PGNConverter.convertFromPGNToMove("pd2d4"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pd7d5"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe2e4"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pc7c6"));

        bd.makeMove(PGNConverter.convertFromPGNToMove("bf1b5"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pc6c5"));

        helper_test_move(bd,   "c6", "c5", -16, 0);

    }
    @Test
    public void testPieceBecomesUnPinned(){
        ChessBoardRep bd = new ChessBoardRep();
        PGNConverter.setup();
        bd.makeMove(PGNConverter.convertFromPGNToMove("pd2d4"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pd7d5"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe2e4"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe7e5"));

        bd.makeMove(PGNConverter.convertFromPGNToMove("bf1b5"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pc7c6"));

        bd.makeMove(PGNConverter.convertFromPGNToMove("bb5c4"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pc6c5"));

        helper_test_move(bd,   "c6", "c5", 0, -16);
        // notable cannot and does not test to see if the piece is removed from the pinning pieces arraylist

    }
    @Test
    public void testPieceRemainsPinned(){
        ChessBoardRep bd = new ChessBoardRep();
        PGNConverter.setup();
        bd.makeMove(PGNConverter.convertFromPGNToMove("pd2d4"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pd7d5"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe2e4"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe7e5"));

        bd.makeMove(PGNConverter.convertFromPGNToMove("bf1b5"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pc7c6"));

        bd.makeMove(PGNConverter.convertFromPGNToMove("pa2a3"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pa7a8"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("bb5a4"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pc6c5"));

        helper_test_move(bd,   "c6", "c5", -16, 0);

    }
    /*
     *
     * Checking for Endgame states
     *
     */
    @Test
    public void testFoolsMate(){
        ChessBoardRep bd = new ChessBoardRep();
        PGNConverter.setup();
        bd.makeMove(PGNConverter.convertFromPGNToMove("pf2f3"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe7e5"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pg2g4"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("qd8h4"));
        assertTrue(bd.endState());
        assertEquals(Color.BLACK, bd.getWinner());
    }
    @Test
    public void testScholarsMate(){
        ChessBoardRep bd = new ChessBoardRep();
        PGNConverter.setup();
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe2e4"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pe7e5"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("bf1c4"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("kb8c6"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("qd1f3"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pa7a6"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("qf3f7"));
        assertTrue(bd.endState());
        assertEquals(Color.WHITE, bd.getWinner());
    }
    /*
     *
     * Miscellaneous
     *
     */
    @Test
    public void testMoveOnRightTurn(){
        ChessBoardRep bd = new ChessBoardRep();
         PGNConverter.setup();

        //test black moving pawn forward 1
        bd.makeMove(PGNConverter.convertFromPGNToMove("pd7d6"));
        helper_test_move(bd,   "d7", "d6", -15, 0);
        // test white moving pawn forward 1
        bd.setTurn(Color.BLACK);
        bd.makeMove(PGNConverter.convertFromPGNToMove("pd2d3"));
        helper_test_move(bd,   "d2", "d3", 15, 0);
    }
    @Test
    public void testPawnPromotion(){
        ChessBoardRep bd = new ChessBoardRep();
        PGNConverter.setup();
        bd.makeMove(PGNConverter.convertFromPGNToMove("pa2a4"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pg7g6"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pa4a5"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pg6g5"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pa5a6"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pg5g4"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pa6b7"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pg4g3"));
        bd.makeMove(PGNConverter.convertFromPGNToMove("pb7a8"));
        assertTrue(bd.isWaitingOnPromotion());
        bd.promotePiece("Q");
        assertEquals("Q",bd.getPiece(18).toString());
    }
}
