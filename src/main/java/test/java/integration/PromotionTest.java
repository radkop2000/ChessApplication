package test.java.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.java.Clicker;

import static java.lang.Thread.sleep;

public class PromotionTest {

    Clicker clicker;

    @BeforeEach
    void setup() {
        clicker = new Clicker();
    }

    @Test
    public void testPromotionQueen() throws InterruptedException {

        move('W', 6,0,4,0,-1);
        move('B', 1,2,3,2,-1);

        move('W', 4,0,3,0,-1);
        move('B', 3,2,4,2,-1);

        move('W', 3,0,2,0,-1);
        move('B', 4,2,5,2,-1);

        move('W', 2,0,1,1,-1);
        move('B', 5,2,6,1,-1);

        move('W', 1,1,0,0,-1);
        clicker.simulatePromotionClick(0);
        sleep(50);
        Assertions.assertEquals('W', clicker.op.ui.game.board.getColor(0,0));
        Assertions.assertEquals('Q', clicker.op.ui.game.board.getPiece(0,0));

        move('B', 6,1,7,0,-1);
        clicker.simulatePromotionClick(0);
        sleep(50);
        Assertions.assertEquals('B', clicker.op.ui.game.board.getColor(7,0));
        Assertions.assertEquals('Q', clicker.op.ui.game.board.getPiece(7,0));
    }

    @Test
    public void testPromotionRook() throws InterruptedException {

        move('W', 6,0,4,0,-1);
        move('B', 1,2,3,2,-1);

        move('W', 4,0,3,0,-1);
        move('B', 3,2,4,2,-1);

        move('W', 3,0,2,0,-1);
        move('B', 4,2,5,2,-1);

        move('W', 2,0,1,1,-1);
        move('B', 5,2,6,1,-1);

        move('W', 1,1,0,0,-1);
        clicker.simulatePromotionClick(1);
        sleep(50);
        Assertions.assertEquals('W', clicker.op.ui.game.board.getColor(0,0));
        Assertions.assertEquals('R', clicker.op.ui.game.board.getPiece(0,0));

        move('B', 6,1,7,0,-1);
        clicker.simulatePromotionClick(1);
        sleep(50);
        Assertions.assertEquals('B', clicker.op.ui.game.board.getColor(7,0));
        Assertions.assertEquals('R', clicker.op.ui.game.board.getPiece(7,0));
    }

    @Test
    public void testPromotionBishop() throws InterruptedException {

        move('W', 6,0,4,0,-1);
        move('B', 1,2,3,2,-1);

        move('W', 4,0,3,0,-1);
        move('B', 3,2,4,2,-1);

        move('W', 3,0,2,0,-1);
        move('B', 4,2,5,2,-1);

        move('W', 2,0,1,1,-1);
        move('B', 5,2,6,1,-1);

        move('W', 1,1,0,0,-1);
        clicker.simulatePromotionClick(2);
        sleep(50);
        Assertions.assertEquals('W', clicker.op.ui.game.board.getColor(0,0));
        Assertions.assertEquals('B', clicker.op.ui.game.board.getPiece(0,0));

        move('B', 6,1,7,0,-1);
        clicker.simulatePromotionClick(2);
        sleep(50);
        Assertions.assertEquals('B', clicker.op.ui.game.board.getColor(7,0));
        Assertions.assertEquals('B', clicker.op.ui.game.board.getPiece(7,0));
    }

    @Test
    public void testPromotionKnight() throws InterruptedException {

        move('W', 6,0,4,0,-1);
        move('B', 1,2,3,2,-1);

        move('W', 4,0,3,0,-1);
        move('B', 3,2,4,2,-1);

        move('W', 3,0,2,0,-1);
        move('B', 4,2,5,2,-1);

        move('W', 2,0,1,1,-1);
        move('B', 5,2,6,1,-1);

        move('W', 1,1,0,0,-1);
        clicker.simulatePromotionClick(3);
        sleep(50);
        Assertions.assertEquals('W', clicker.op.ui.game.board.getColor(0,0));
        Assertions.assertEquals('H', clicker.op.ui.game.board.getPiece(0,0));

        move('B', 6,1,7,0,-1);
        clicker.simulatePromotionClick(3);
        sleep(50);
        Assertions.assertEquals('B', clicker.op.ui.game.board.getColor(7,0));
        Assertions.assertEquals('H', clicker.op.ui.game.board.getPiece(7,0));
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
