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
