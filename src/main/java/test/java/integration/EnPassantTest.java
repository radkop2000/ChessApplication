package test.java.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.java.Clicker;

import static java.lang.Thread.sleep;

public class EnPassantTest {

    Clicker clicker;

    @BeforeEach
    void setup() {
        clicker = new Clicker();
    }

    @Test
    public void testEnPassant() {

        move('W', 6,3,4,3,-1);
        move('B', 0,6,2,7,-1);

        move('W', 4,3,3,3,-1);
        move('B', 1,2,3,2,-1);

        move('W', 3,3,2,2,-1);

        Assertions.assertEquals('W', clicker.op.ui.game.board.getColor(2,2));
        Assertions.assertEquals('N', clicker.op.ui.game.board.getColor(3,3));
        Assertions.assertEquals('N', clicker.op.ui.game.board.getColor(3,2));

    }



    public void move(char turnOf, int fromX, int fromY, int toX, int toY, int gameState) {
        Assertions.assertEquals(turnOf, clicker.op.ui.game.board.turnOf);
        clicker.simulateTileClick(fromX,fromY);
        clicker.simulateTileClick(toX,toY);
        try {
            sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(gameState, clicker.op.ui.game.board.pgn.end);
    }
}
