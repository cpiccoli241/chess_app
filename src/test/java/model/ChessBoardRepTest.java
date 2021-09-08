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
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("d2"))==14);
        //test black moving pawn forward 1
        bd.makeMove(app.convertFromPGNToMove("pd7d6"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("d7"))==0);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("d6"))==-14);
        //white moving pawn forward 2
        bd.makeMove(app.convertFromPGNToMove("pe2e4"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e2"))==0);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e4"))==15);
        //black moving pawn forwad 2
        bd.makeMove(app.convertFromPGNToMove("pe7e5"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e7"))==0);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e5"))==15);

        //Now lets try illegal moves

        //Backwards pawn moves
        // test white moving pawn backward 1
        bd.makeMove(app.convertFromPGNToMove("pd3d2"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("d2"))==0);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("d3"))==14);

        //test black moving pawn backward 1
        bd.setTurn(Color.BLACK);
        bd.makeMove(app.convertFromPGNToMove("pd6d7"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("d7"))==0);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("d6"))==-14);
        //white moving pawn backward 2
        bd.setTurn(Color.WHITE);
        bd.makeMove(app.convertFromPGNToMove("pe4e2"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e2"))==0);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e4"))==15);
        //black moving pawn backward 2
        bd.setTurn(Color.BLACK);
        bd.makeMove(app.convertFromPGNToMove("pe5e7"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e7"))==0);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e5"))==15);

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

    }
    @Test
    public void testMoveKnight(){
        ChessBoardRep bd = new ChessBoardRep();
        CMDChessApp app = new CMDChessApp();

        // test white moving knight
        bd.makeMove(app.convertFromPGNToMove("hg1f3"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("g1"))==0);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("f3"))==7);
        //test black moving knight
        bd.makeMove(app.convertFromPGNToMove("hg8f6"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("g8"))==0);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("f6"))==-7);
        //knight again
        bd.makeMove(app.convertFromPGNToMove("he2e4"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("f3"))==0);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e5"))==7);
        //black moving knight again
        bd.makeMove(app.convertFromPGNToMove("he7e5"));
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("f6"))==0);
        assertTrue(bd.getSquare(app.convertFromPGNToSquare("e4"))==-7);
    }
}
