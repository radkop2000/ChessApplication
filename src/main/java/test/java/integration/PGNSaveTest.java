package test.java.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.java.Clicker;

import static java.lang.Thread.sleep;

public class PGNSaveTest {

    Clicker clicker;

    @BeforeEach
    void setup() {
        clicker = new Clicker();
    }

    @Test
    public void testPGNOutput() {
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

        Assertions.assertEquals("[Event \"Chess Game\"]\n" +
                "[Site \"Prague\"]\n" +
                "[Date \"2023-05-27\"]\n" +
                "[Round \"27\"]\n" +
                "[White \"?\"]\n" +
                "[Black \"?\"]\n" +
                "[Result \"1-0\"]\n" +
                "\n" +
                "1. a4 a5 2. b4 b5 3. c4 c5 4. d4 d5 5. e4 e5 6. f4 f5 7. g4 g5 8. h4 h5 9. xab5 xb4 10. xcd5 xd4 \n" +
                "11. xef5 xf4 12. xgh5 xh4 13. Qxd4 Qxd5 14. Qxb4 Qxb5 15. Qxf4 Qxf5 16. Qxh4 Qxh5 17. Rxa8 Qb5 \n" +
                "18. Rxb8 Qxb1 19. Rxc8 Kd7 20. Rxf8 Qxc1 21. Ke2 Qc6 22. Rxg8 Qxh1 23. Rxh8 Qh3 24. Nxh3 Kc7 25. Qc4 \n" +
                "Kb7 26. Qc6 Ka7 27. Rh7 Kb8 28. Qe8  1-0", clicker.op.ui.game.board.pgn.getPGN());
    }

    @Test
    public void testPGNOutput2() throws InterruptedException {
        move('W', 6,0,4,0,-1);
        move('B', 1,2,3,2,-1);

        move('W', 4,0,3,0,-1);
        move('B', 3,2,4,2,-1);

        move('W', 3,0,2,0,-1);
        move('B', 4,2,5,2,-1);

        move('W', 2,0,1,1,-1);
        move('B', 5,2,6,1,-1);

        move('W', 1,1,0,0,-1);
        clicker.simulateClick(220 + -1 * 94 + 44, 7 + 2 * 94 + 44 + 44);
        sleep(50);

        move('B', 6,1,7,0,-1);
        clicker.simulateClick(220 + -1 * 94 + 44, 7 + 2 * 94 + 44 + 44);
        sleep(50);

        Assertions.assertEquals("[Event \"Chess Game\"]\n" +
                "[Site \"Prague\"]\n" +
                "[Date \"2023-05-27\"]\n" +
                "[Round \"5\"]\n" +
                "[White \"?\"]\n" +
                "[Black \"?\"]\n" +
                "[Result \"*\"]\n" +
                "\n" +
                "1. a4 c5 2. a5 c4 3. a6 c3 4. xb7 xb2 5. xa8 xa1  *", clicker.op.ui.game.board.pgn.getPGN());
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
