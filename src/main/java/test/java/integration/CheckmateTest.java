package test.java.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.java.Clicker;

import static java.lang.Thread.sleep;

public class CheckmateTest {

    Clicker clicker;

    @BeforeEach
    void setup() {
        clicker = new Clicker();
    }


    @Test
    public void testCheckmate() {
        for (int i = 0; i < 8; i-=-1) {
            move('W', 6, i, 4, i, -1);
            move('B', 1, i, 3, i, -1);
        }
        for (int i = 0; i < 8; i+=2) {
            move('W', 4, i, 3, i+1, -1);
            move('B', 3, i, 4, i+1, -1);
        }

        move('W', 7,3,4,3,-1);
        move('B', 0,3,3,3,-1);

        move('W', 4,3,4,1, -1);
        move('B', 3,3,3,1, -1);

        move('W', 4,1,4,5,-1);
        move('B', 3,1,3,5,-1);

        move('W', 4,5,4,7, -1);
        move('B', 3,5,3,7,-1);

        move('W', 7,0,0,0,-1);
        move('B', 3,7,3,1,-1);

        move('W', 0,0,0,1,-1);
        move('B', 3,1,7,1,-1);

        move('W', 0,1,0,2,-1);
        move('B', 0,4,1,3,-1);

        move('W', 0,2,0,5,-1);
        move('B', 7,1,7,2,-1);

        move('W', 7,4,6,4,-1);
        move('B', 7,2,2,2,-1);

        move('W', 0,5,0,6,-1);
        move('B', 2,2,7,7,-1);

        move('W', 0,6,0,7,-1);
        move('B', 7,7,5,7,-1);

        move('W', 7,6,5,7,-1);
        move('B', 1,3,1,2,-1);

        move('W', 4,7,4,2,-1);
        move('B', 1,2,1,1,-1);

        move('W', 4,2,2,2,-1);
        move('B', 1,1,1,0,-1);

        move('W', 0,7,1,7,-1);
        move('B', 1,0,0,1,-1);

        move('W', 2,2,0,4,0);
    }

    @Test
    public void moveOutOfCheck() throws InterruptedException {

        move('W', 6,3,4,3,-1);
        move('B', 1,3,3,3,-1);

        move('W', 6,4,4,4,-1);
        move('B', 1,4,3,4,-1);

        move('W', 4,4,3,3,-1);
        move('B', 3,4,4,3,-1);

        move('W', 7,3,6,4,-1);
        move('B', 0,3,1,4,-1);

        move('W', 6,4,3,1,-1);

        sleep(1000000);
        Assertions.assertEquals('W', clicker.op.ui.game.board.getColor(6,4));
        Assertions.assertEquals('Q', clicker.op.ui.game.board.getPiece(6,4));



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
