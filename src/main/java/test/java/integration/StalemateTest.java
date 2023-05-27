package test.java.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.java.Clicker;


import static java.lang.Thread.sleep;


class StalemateTest {

    Clicker clicker;

    @BeforeEach
    void setup() {
        clicker = new Clicker();
    }

    @Test
    void testStalemateRepeating() throws InterruptedException {

        move('W', 7,1,5,2,-1);

        move('B', 0,1,2,2,-1);

        move('W', 5,2,7,1,-1);

        move('B', 2,2,0,1,-1);

        move('W', 7,1,5,2,-1);

        move('B', 0,1,2,2,-1);

        move('W', 5,2,7,1,-1);

        move('B', 2,2,0,1,-1);

        move('W', 7,1,5,2,-1);

        move('B', 0,1,2,2,-1);

        move('W', 5,2,7,1,-1);

        move('B', 2,2,0,1,2);

    }

    @Test
    public void testStalemateOnlyKings() throws InterruptedException {
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
        move('B', 7,2,7,5,-1);

        move('W', 6,4,6,3,-1);
        move('B', 7,5,7,6,-1);

        move('W', 0,5,0,6,-1);
        move('B', 7,6,7,7,-1);

        move('W', 0,6,0,7,-1);
        move('B', 7,7,4,7,-1);

        move('W', 0,7,4,7,-1);
        move('B', 1,3,1,4,-1);

        move('W', 4,7,0,7,-1);
        move('B', 1,4,1,5,-1);

        move('W', 0,7,0,5,-1);
        move('B', 1,5,0,5,2);

    }

    @Test
    public void testStalemateOnlyBishop() throws InterruptedException {
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
        move('B', 7,2,3,6,-1);

        move('W', 0,5,0,6,-1);
        move('B', 3,6,7,6,-1);

        move('W', 0,6,0,7,-1);
        move('B', 7,6,7,7,-1);

        move('W', 4,7,7,7,-1);
        move('B', 1,3,1,2,-1);

        move('W', 7,7,1,1,-1);
        move('B', 1,2,1,1,-1);

        move('W', 0,7,0,1,-1);
        move('B', 1,1,0,1,2);

    }

    @Test
    public void testStatelamateOnlyKnight() throws InterruptedException {
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
        move('B', 7,7,4,7,-1);

        move('W', 0,7,4,7,-1);
        move('B', 1,3,2,2,-1);

        move('W', 6,4,7,4,-1);
        move('B', 2,2,3,2,-1);

        move('W', 7,5,3,1,-1);
        move('B', 3,2,3,1,-1);

        move('W', 4,7,4,1,-1);
        move('B', 3,1,4,1,2);

    }

    @Test
    public void testStalemateNoMoves() throws InterruptedException {
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

        move('W', 5,7,3,6,2);

    }

    public void isTurnOf(char expected) {
        Assertions.assertEquals(expected, clicker.op.ui.game.board.turnOf);
    }

    public void move(char turnOf, int fromX, int fromY, int toX, int toY, int gameState) {
        isTurnOf(turnOf);
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
