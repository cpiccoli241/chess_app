package model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import util.CMDChessApp;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("Model-tier")
public class ChessBoardRepTest {
    private void helper_test_move(ChessBoardRep bd, CMDChessApp app, String sq1, String sq2, int id1, int id2){
        assertTrue(bd.getSquare(app.convertFromPGNToSquare(sq1))==id1);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare(sq2))==id2);
    }
    @Test
    public void testMovePawn(){
        ChessBoardRep bd = new ChessBoardRep();
        CMDChessApp app = new CMDChessApp();

        // test white moving pawn forward 1
        bd.makeMove(app.convertFromPGNToMove("pd2d3"));
        helper_test_move(bd, app, "d2", "d3", 0, 15);
        //test black moving pawn forward 1
        bd.makeMove(app.convertFromPGNToMove("pd7d6"));
        helper_test_move(bd, app, "d7", "d6", 0, -15);

        //white moving pawn forward 2
        bd.makeMove(app.convertFromPGNToMove("pe2e4"));
        helper_test_move(bd, app, "e2", "e4", 0, 14);
        //black moving pawn forwad 2
        bd.makeMove(app.convertFromPGNToMove("pe7e5"));
        helper_test_move(bd, app, "e7", "e5", 0, -14);

        //Now lets try illegal moves

        //Backwards pawn moves
        // test white moving pawn backward 1
        bd.makeMove(app.convertFromPGNToMove("pd3d2"));
        helper_test_move(bd, app, "d3", "d2", 15, 0);

        //test black moving pawn backward 1
        bd.setTurn(Color.BLACK);
        bd.makeMove(app.convertFromPGNToMove("pd6d7"));
        helper_test_move(bd, app, "d7", "d6", 0, -15);
        //white moving pawn backward 2
        bd.setTurn(Color.WHITE);
        bd.makeMove(app.convertFromPGNToMove("pe4e2"));
        helper_test_move(bd, app, "e2", "e4", 0, 14);
        //black moving pawn backward 2
        bd.setTurn(Color.BLACK);
        bd.makeMove(app.convertFromPGNToMove("pe5e7"));
        helper_test_move(bd, app, "e5", "e7", -14, 0);
    }
    @Test
    public void testPawnCapture(){
        ChessBoardRep bd = new ChessBoardRep();
        CMDChessApp app = new CMDChessApp();
        //white moving pawn forward 2
        bd.makeMove(app.convertFromPGNToMove("pe2e4"));
        //black moving pawn forward 2
        bd.makeMove(app.convertFromPGNToMove("pe7e5"));

        //Forwards Capture Moves
        bd.setTurn(Color.WHITE);
        bd.makeMove(app.convertFromPGNToMove("pe4e5"));
        helper_test_move(bd, app, "e4", "e5", 14, -14);

        //black captures forwards
        bd.setTurn(Color.BLACK);
        bd.makeMove(app.convertFromPGNToMove("pe5e4"));
        helper_test_move(bd, app, "e4", "e5", 14, -14);

        //Make Sure Pawns can only move diagonally on capture
        bd.setTurn(Color.WHITE);
        bd.makeMove(app.convertFromPGNToMove("pe4d5"));
        helper_test_move(bd, app, "e4", "d5", 14, 0);

        bd.setTurn(Color.BLACK);
        bd.makeMove(app.convertFromPGNToMove("pe5d4"));
        helper_test_move(bd, app, "e5", "d4", -14, 0);

        // Test Actual Pawn Capture
        bd.setTurn(Color.WHITE);
        bd.makeMove(app.convertFromPGNToMove("pd2d4"));
        bd.makeMove(app.convertFromPGNToMove("pe5d4"));
        helper_test_move(bd, app, "e5", "d4", 0, -14);
    }
    @Test
    public void testMoveKnight(){
        ChessBoardRep bd = new ChessBoardRep();
        CMDChessApp app = new CMDChessApp();

        // test white moving knight
        bd.makeMove(app.convertFromPGNToMove("hg1f3"));

        helper_test_move(bd, app, "g1", "f3", 0, 2);

        //test black moving knight
        bd.makeMove(app.convertFromPGNToMove("hg8f6"));
        helper_test_move(bd, app, "g8", "f6", 0, -2);

        //knight again
        bd.makeMove(app.convertFromPGNToMove("hf3e5"));
        helper_test_move(bd, app, "f3", "e5", 0, 2);

        //black moving knight again
        bd.makeMove(app.convertFromPGNToMove("hf6e4"));
        helper_test_move(bd, app, "f6", "e4", 0, -2);

        //test some illegal moves for knight
        bd.makeMove(app.convertFromPGNToMove("he5e6"));
        helper_test_move(bd, app, "f3", "e5", 0, 2);

        bd.makeMove(app.convertFromPGNToMove("he5e7"));
        helper_test_move(bd, app, "f3", "e5", 0, 2);

        bd.makeMove(app.convertFromPGNToMove("he5d6"));
        helper_test_move(bd, app, "f3", "e5", 0, 2);
    }
    @Test
    public void testBishopMoves() {
        ChessBoardRep bd = new ChessBoardRep();
        CMDChessApp app = new CMDChessApp();
        //white moving pawn forward 2
        bd.makeMove(app.convertFromPGNToMove("pe2e4"));
        //black moving pawn forward 2
        bd.makeMove(app.convertFromPGNToMove("pe7e5"));

        //test white moving bishop passively
        bd.makeMove(app.convertFromPGNToMove("bf1e2"));
        helper_test_move(bd, app, "f1", "e2", 0, 3);
        //test white moving bishop passively
        bd.makeMove(app.convertFromPGNToMove("bf8c5"));
        helper_test_move(bd, app, "f8", "c5", 0, -3);
        //test white moving bishop passively
        bd.makeMove(app.convertFromPGNToMove("be2f1"));
        helper_test_move(bd, app, "f1", "e2", 3, 0);
        //test white moving bishop passively
        bd.makeMove(app.convertFromPGNToMove("bc5e3"));
        helper_test_move(bd, app, "c5", "e3", 0, -3);

        //illegal bishop moves
        //test white moving bishop straight
        bd.makeMove(app.convertFromPGNToMove("bf1f6"));
        helper_test_move(bd, app, "f6", "f1", 0, 3);

    }
    @Test
    public void testRookMoves() {
        ChessBoardRep bd = new ChessBoardRep();
        CMDChessApp app = new CMDChessApp();
        //white moving pawn forward 2
        bd.makeMove(app.convertFromPGNToMove("pa2a4"));
        //black moving pawn forward 2
        bd.makeMove(app.convertFromPGNToMove("pa7a5"));

        //test white moving rook passively
        bd.makeMove(app.convertFromPGNToMove("ra1a2"));
        helper_test_move(bd, app, "a1", "a2", 0, 8);
        //test white moving rook passively
        bd.makeMove(app.convertFromPGNToMove("ra8a7"));
        helper_test_move(bd, app, "a8", "a7", 0, -8);
        //test white moving rook passively
        bd.makeMove(app.convertFromPGNToMove("ra2a3"));
        helper_test_move(bd, app, "a2", "a3", 0, 8);
        //test white moving rook passively
        bd.makeMove(app.convertFromPGNToMove("ra7a6"));
        helper_test_move(bd, app, "a7", "a6", 0, -8);

        //illegal bishop moves
        //test white moving bishop straight
        bd.makeMove(app.convertFromPGNToMove("bf1f6"));
        helper_test_move(bd, app, "f6", "f1", 0, 3);

    }
    @Test
    public void testQueenMoves() {
        ChessBoardRep bd = new ChessBoardRep();
        CMDChessApp app = new CMDChessApp();
        //white moving pawn forward 2
        bd.makeMove(app.convertFromPGNToMove("pe2e4"));
        //black moving pawn forward 2
        bd.makeMove(app.convertFromPGNToMove("pe7e5"));

        //test white moving bishop passively
        bd.makeMove(app.convertFromPGNToMove("qd1e2"));
        helper_test_move(bd, app, "d1", "e2", 0, 5);
        //test white moving bishop passively
        bd.makeMove(app.convertFromPGNToMove("qd8e7"));
        helper_test_move(bd, app, "d8", "e7", 0, -5);
        //test white moving bishop passively
        bd.makeMove(app.convertFromPGNToMove("qe2d3"));
        helper_test_move(bd, app, "e2", "d3", 0, 5);
        //test white moving bishop passively
        bd.makeMove(app.convertFromPGNToMove("qe7d6"));
        helper_test_move(bd, app, "e7", "d6", 0, -5);

        //test queen moving like rook
        bd.makeMove(app.convertFromPGNToMove("qd3e3"));
        helper_test_move(bd, app, "d3", "e3", 0, 5);
        //test white moving bishop passively
        bd.makeMove(app.convertFromPGNToMove("qd6e6"));
        helper_test_move(bd, app, "d6", "e6", 0, -5);
        //test queen moving like rook
        bd.makeMove(app.convertFromPGNToMove("qe3h3"));
        helper_test_move(bd, app, "e3", "h3", 0, 5);
        //test white moving bishop passively
        bd.makeMove(app.convertFromPGNToMove("qe6h6"));
        helper_test_move(bd, app, "e6", "h6", 0, -5);
    }
}
