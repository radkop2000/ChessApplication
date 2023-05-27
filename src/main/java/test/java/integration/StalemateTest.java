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

        isTurnOf('W');
        clicker.simulateTileClick(7,1);
        clicker.simulateTileClick(5,2);
        Assertions.assertEquals(-1, clicker.op.ui.game.board.pgn.end);

        isTurnOf('B');
        clicker.simulateTileClick(0,1);
        clicker.simulateTileClick(2,2);
        Assertions.assertEquals(-1, clicker.op.ui.game.board.pgn.end);

        isTurnOf('W');
        clicker.simulateTileClick(5,2);
        clicker.simulateTileClick(7,1);
        Assertions.assertEquals(-1, clicker.op.ui.game.board.pgn.end);

        isTurnOf('B');
        clicker.simulateTileClick(2,2);
        clicker.simulateTileClick(0,1);
        Assertions.assertEquals(-1, clicker.op.ui.game.board.pgn.end);

        isTurnOf('W');
        clicker.simulateTileClick(7,1);
        clicker.simulateTileClick(5,2);
        Assertions.assertEquals(-1, clicker.op.ui.game.board.pgn.end);

        isTurnOf('B');
        clicker.simulateTileClick(0,1);
        clicker.simulateTileClick(2,2);
        Assertions.assertEquals(-1, clicker.op.ui.game.board.pgn.end);

        isTurnOf('W');
        clicker.simulateTileClick(5,2);
        clicker.simulateTileClick(7,1);
        Assertions.assertEquals(-1, clicker.op.ui.game.board.pgn.end);

        isTurnOf('B');
        clicker.simulateTileClick(2,2);
        clicker.simulateTileClick(0,1);
        Assertions.assertEquals(-1, clicker.op.ui.game.board.pgn.end);

        isTurnOf('W');
        clicker.simulateTileClick(7,1);
        clicker.simulateTileClick(5,2);
        Assertions.assertEquals(-1, clicker.op.ui.game.board.pgn.end);

        isTurnOf('B');
        clicker.simulateTileClick(0,1);
        clicker.simulateTileClick(2,2);
        Assertions.assertEquals(-1, clicker.op.ui.game.board.pgn.end);

        isTurnOf('W');
        clicker.simulateTileClick(5,2);
        clicker.simulateTileClick(7,1);
        Assertions.assertEquals(-1, clicker.op.ui.game.board.pgn.end);

        isTurnOf('B');
        clicker.simulateTileClick(2,2);
        clicker.simulateTileClick(0,1);
        Assertions.assertEquals(2, clicker.op.ui.game.board.pgn.end);

        sleep(5000);
        System.out.println(clicker.op.ui.game.board.isPat());
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



        sleep(1000000);

    }

    public void isTurnOf(char expected) {
        try {
            sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(expected, clicker.op.ui.game.board.turnOf);
    }

    public void move(char turnOf, int fromX, int fromY, int toX, int toY, int gameState) {
        isTurnOf(turnOf);
//        try {
//            sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        clicker.simulateTileClick(fromX,fromY);
        clicker.simulateTileClick(toX,toY);
        Assertions.assertEquals(gameState, clicker.op.ui.game.board.pgn.end);
    }

}
