package model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import util.CMDChessApp;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("Model-tier")
public class ChessBoardRepTest {
    @Test
    public void testMovePawn(){
        ChessBoardRep bd = new ChessBoardRep();
        CMDChessApp app = new CMDChessApp();

        // test white moving pawn forward 1
        bd.makeMove(app.convertFromPGNToMove("pd2d3"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("d2"))==0);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("d3"))==15);
        //test black moving pawn forward 1
        bd.makeMove(app.convertFromPGNToMove("pd7d6"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("d7"))==0);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("d6"))==-15);
        //white moving pawn forward 2
        bd.makeMove(app.convertFromPGNToMove("pe2e4"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e2"))==0);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e4"))==14);
        //black moving pawn forwad 2
        bd.makeMove(app.convertFromPGNToMove("pe7e5"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e7"))==0);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e5"))==-14);

        //Now lets try illegal moves

        //Backwards pawn moves
        // test white moving pawn backward 1
        bd.makeMove(app.convertFromPGNToMove("pd3d2"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("d2"))==0);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("d3"))==15);

        //test black moving pawn backward 1
        bd.setTurn(Color.BLACK);
        bd.makeMove(app.convertFromPGNToMove("pd6d7"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("d7"))==0);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("d6"))==-15);
        //white moving pawn backward 2
        bd.setTurn(Color.WHITE);
        bd.makeMove(app.convertFromPGNToMove("pe4e2"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e2"))==0);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e4"))==14);
        //black moving pawn backward 2
        bd.setTurn(Color.BLACK);
        bd.makeMove(app.convertFromPGNToMove("pe5e7"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e7"))==0);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e5"))==-14);
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
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e4"))==14);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e5"))==-14);

        //black captures forwards
        bd.setTurn(Color.BLACK);
        bd.makeMove(app.convertFromPGNToMove("pe5e4"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e4"))==14);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e5"))==-14);

        //Make Sure Pawns can only move diagonally on capture
        bd.setTurn(Color.WHITE);
        bd.makeMove(app.convertFromPGNToMove("pe4d5"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e4"))==14);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("d5"))==0);
        bd.setTurn(Color.BLACK);
        bd.makeMove(app.convertFromPGNToMove("pe5d4"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e5"))==-14);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("d4"))==0);

        // Test Actual Pawn Capture
        bd.setTurn(Color.WHITE);
        bd.makeMove(app.convertFromPGNToMove("pd2d4"));
        bd.makeMove(app.convertFromPGNToMove("pe5d4"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e5"))==0);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("d4"))==-14);
    }
    @Test
    public void testMoveKnight(){
        ChessBoardRep bd = new ChessBoardRep();
        CMDChessApp app = new CMDChessApp();

        // test white moving knight
        bd.makeMove(app.convertFromPGNToMove("hg1f3"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("g1"))==0);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("f3"))==2);
        //test black moving knight
        bd.makeMove(app.convertFromPGNToMove("hg8f6"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("g8"))==0);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("f6"))==-2);
        //knight again
        bd.makeMove(app.convertFromPGNToMove("hf3e5"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("f3"))==0);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e5"))==2);
        //black moving knight again
        bd.makeMove(app.convertFromPGNToMove("hf6e4"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("f6"))==0);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e4"))==-2);

        //test some illegal moves for knight
        bd.makeMove(app.convertFromPGNToMove("he5e6"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("f3"))==0);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e5"))==2);
        bd.makeMove(app.convertFromPGNToMove("he5e7"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("f3"))==0);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e5"))==2);
        bd.makeMove(app.convertFromPGNToMove("he5d6"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("f3"))==0);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e5"))==2);
    }
}
