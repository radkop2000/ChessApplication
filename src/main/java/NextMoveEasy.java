import java.util.ArrayList;
import java.util.Random;

public class NextMoveEasy implements Runnable {

    BoardGame board;
    char color;

    public NextMoveEasy(BoardGame board, char color) {
        this.board = board;
        this.color = color;
    }

    /**
     * It makes a random move, with hecking if the move is a capture move and if it is, it will make that move.
     */
    @Override
    public void run() {
        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException ignored) {
        }
        int counter = 0;
        int limit = random.nextInt(1000);
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
                            if (board.getColor(move[0], move[1]) == board.not(color) && counter > 10) {
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
