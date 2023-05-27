package board;

import java.util.ArrayList;
import java.util.Random;

public class NextMoveRandom implements Runnable {

    BoardGame board;
    char color;

    public NextMoveRandom(BoardGame board, char color) {
        this.board = board;
        this.color = color;
    }

    /**
     * It makes a random move
     */
    @Override
    public void run() {
        Random rand = new Random();
        try {
            Thread.sleep(300);
        } catch (InterruptedException ignored) {
        }
        int counter = 0;
        int limit = rand.nextInt(1000);
        while (counter < limit+100) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getColor(i, j) == board.turnOf) {
                        ArrayList<int[]> moves = board.movesWithoutCheck(i, j);
                        for (int[] move : moves) {
                            if (counter++ > limit) {
                                board.move(i, j, move[0], move[1]);
                                return;
                            }

                        }
                    }
                }
            }
        }
    }
}
