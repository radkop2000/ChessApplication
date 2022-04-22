import java.util.ArrayList;

public class NextMoveRandom implements Runnable {

    BoardGame board;
    char color;

    public NextMoveRandom(BoardGame board, char color) {
        this.board = board;
        this.color = color;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException ignored) {

        }
        int counter = 0;
        while (counter < 400) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getColor(i, j) == board.turnOf) {
                        ArrayList<int[]> moves = board.movesWithoutCheck(i, j);
                        for (int[] move : moves) {
                            if (counter++ > 300) {
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
