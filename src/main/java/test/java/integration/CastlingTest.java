package test.java.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.java.Clicker;


import static java.lang.Thread.sleep;


class CastlingTest {

    Clicker clicker;

    @BeforeEach
    void setup() {
        clicker = new Clicker();
    }

    @Test
    void testIllegalMove() {
        move('W', 7,0,6,0);
        Assertions.assertEquals('W', clicker.op.ui.game.board.turnOf);
        Assertions.assertEquals('R', clicker.op.ui.game.board.getPiece(7,0));
    }

    @Test
    void testLegalCastlingKingside() {
        move('W', 6,6,5,6);
        move('B', 1,5,2,5);

        move('W', 7,6,5,5);
        move('B', 1,6,2,6);

        move('W', 7,5,6,6);
        move('B', 1,7,2,7);

        move('W', 7,4,7,6);

        Assertions.assertEquals('B', clicker.op.ui.game.board.turnOf);
        Assertions.assertEquals('R', clicker.op.ui.game.board.getPiece(7,5));
        Assertions.assertEquals('K', clicker.op.ui.game.board.getPiece(7,6));
    }

    @Test
    void testLegalCastlingQueenside() {
        move('W', 6,1,5,1);
        move('B', 1,4,2,4);

        move('W', 7,1,5,2);
        move('B', 1,3,2,3);

        move('W', 7,2,6,1);
        move('B', 1,2,2,2);

        move('W', 6,3,5,3);
        move('B', 1,1,2,1);

        move('W', 7,3,6,3);
        move('B', 1,0,2,0);

        move('W', 7,4,7,2);

        Assertions.assertEquals('B', clicker.op.ui.game.board.turnOf);
        Assertions.assertEquals('R', clicker.op.ui.game.board.getPiece(7,3));
        Assertions.assertEquals('K', clicker.op.ui.game.board.getPiece(7,2));
    }

    @Test
    void testCastlingPiecesBetweenKingAndRook() {
        move('W', 7,4,7,6);

        Assertions.assertEquals('W', clicker.op.ui.game.board.turnOf);
        Assertions.assertEquals('R', clicker.op.ui.game.board.getPiece(7,7));
        Assertions.assertEquals('K', clicker.op.ui.game.board.getPiece(7,4));
    }

    @Test
    void testCastlingKingInCheck() {
        move('W', 6,6,5,6);
        move('B', 1,4,2,4);

        move('W', 7,6,5,5);
        move('B', 0,3,2,5);

        move('W', 7,5,6,6);
        move('B', 2,5,3,4);

        move('W', 6,4,4,4);
        move('B', 3,4,4,4);

        move('W', 7,4,7,6);

        Assertions.assertEquals('W', clicker.op.ui.game.board.turnOf);
        Assertions.assertEquals('R', clicker.op.ui.game.board.getPiece(7,7));
        Assertions.assertEquals('K', clicker.op.ui.game.board.getPiece(7,4));
    }

    @Test
    void testCastlingKingPassingThroughCheck() {
        move('W', 6,6,5,6);
        move('B', 1,4,2,4);

        move('W', 7,6,5,7);
        move('B', 0,3,2,5);

        move('W', 6,5,4,5);
        move('B', 2,5,4,5);

        move('W', 7,5,6,6);
        move('B', 1,0,2,0);

        move('W', 7,4,7,6);

        Assertions.assertEquals('W', clicker.op.ui.game.board.turnOf);
        Assertions.assertEquals('R', clicker.op.ui.game.board.getPiece(7,7));
        Assertions.assertEquals('K', clicker.op.ui.game.board.getPiece(7,4));
    }

    @Test
    void testCastlingKingMoved() {
        move('W', 6,6,5,6);
        move('B', 1,5,2,5);

        move('W', 7,6,5,5);
        move('B', 1,6,2,6);

        move('W', 7,5,6,6);
        move('B', 1,7,2,7);

        move('W', 7,4,7,5);
        move('B', 1,0,2,0);

        move('W', 7,5,7,6);

        Assertions.assertEquals('B', clicker.op.ui.game.board.turnOf);
        Assertions.assertEquals('R', clicker.op.ui.game.board.getPiece(7,7));
        Assertions.assertEquals('K', clicker.op.ui.game.board.getPiece(7,6));
    }

    @Test
    void testCastlingKingMovedAndReturned() {
        move('W', 6,6,5,6);
        move('B', 1,5,2,5);

        move('W', 7,6,5,5);
        move('B', 1,6,2,6);

        move('W', 7,5,6,6);
        move('B', 1,7,2,7);

        move('W', 7,4,7,5);
        move('B', 1,0,2,0);

        move('W', 7,5,7,4);
        move('B', 1,1,2,1);

        move('W', 7,4,7,6);

        Assertions.assertEquals('W', clicker.op.ui.game.board.turnOf);
        Assertions.assertEquals('R', clicker.op.ui.game.board.getPiece(7,7));
        Assertions.assertEquals('K', clicker.op.ui.game.board.getPiece(7,4));
    }

    @Test
    void testCastlingRookMoved() {
        move('W', 6,1,5,1);
        move('B', 1,4,2,4);

        move('W', 7,1,5,2);
        move('B', 1,3,2,3);

        move('W', 7,2,6,1);
        move('B', 1,2,2,2);

        move('W', 6,3,5,3);
        move('B', 1,1,2,1);

        move('W', 7,3,6,3);
        move('B', 1,0,2,0);

        move('W', 7,0,7,1);
        move('B', 1,5,2,5);

        move('W', 7,4,7,2);

        Assertions.assertEquals('W', clicker.op.ui.game.board.turnOf);
        Assertions.assertEquals('R', clicker.op.ui.game.board.getPiece(7,1));
        Assertions.assertEquals('K', clicker.op.ui.game.board.getPiece(7,4));
    }

    @Test
    void testCastlingRookMovedAndReturned() {
        move('W', 6,1,5,1);
        move('B', 1,4,2,4);

        move('W', 7,1,5,2);
        move('B', 1,3,2,3);

        move('W', 7,2,6,1);
        move('B', 1,2,2,2);

        move('W', 6,3,5,3);
        move('B', 1,1,2,1);

        move('W', 7,3,6,3);
        move('B', 1,0,2,0);

        move('W', 7,0,7,1);
        move('B', 1,5,2,5);

        move('W', 7,1,7,0);
        move('B', 1,6,2,6);

        move('W', 7,4,7,2);

        Assertions.assertEquals('W', clicker.op.ui.game.board.turnOf);
        Assertions.assertEquals('R', clicker.op.ui.game.board.getPiece(7,0));
        Assertions.assertEquals('K', clicker.op.ui.game.board.getPiece(7,4));
    }

    public void move(char turnOf, int fromX, int fromY, int toX, int toY) {
        Assertions.assertEquals(turnOf, clicker.op.ui.game.board.turnOf);
        clicker.simulateTileClick(fromX,fromY);
        clicker.simulateTileClick(toX,toY);
        try {
            sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
