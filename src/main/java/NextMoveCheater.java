import java.util.ArrayList;
import java.util.Random;

public class NextMoveCheater implements Runnable {

    BoardGame board;
    char color;
    boolean flag = false;

    public NextMoveCheater(BoardGame board, char color) {
        this.board = board;
        this.color = color;
    }


    /**
     * It makes a random move, with a loop that will keep adding queens to the board until the heuristic is positive.
     */
    @Override
    public void run() {
        Random rand = new Random();
        try {
            Thread.sleep(rand.nextInt(3000));
        } catch (InterruptedException ignored) {
        }
        int counter = 0;
        int num = rand.nextInt(1000);
        while (counter < num+100) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getColor(i, j) == board.turnOf) {
                        ArrayList<int[]> moves = board.movesWithoutCheck(i, j);
                        for (int[] move : moves) {
                            int heuristic = board.boardHeuristic();

                            if (counter++ > num) {
                                board.move(i, j, move[0], move[1]);
                                while (board.boardHeuristic() < 0) {
                                    for (int k = 0; k < 8; k++) {
                                        for (int l = 0; l < 8; l++) {
                                            if (board.getColor(i, j) != 'B') {
                                                board.putPiece("BQ", i, j);
                                            }
                                        }
                                    }

                                }
                                while (board.boardHeuristic() < 0) {
                                    for (int k = 0; k < 8; k++) {
                                        for (int l = 0; l < 8; l++) {
                                            if (board.getColor(i, j) != 'B') {
                                                board.putPiece("BQ", i, j);
                                            }
                                        }
                                    }

                                }
                                return;
                            }
                            if (board.getColor(move[0], move[1]) == board.not(color) && counter > 10) {
                                board.move(i, j, move[0], move[1]);
                                if (heuristic < 0 && board.getPiece(move[0], move[1]) != 'K') {
                                    board.putPiece("BQ", move[0], move[1]);
                                }
                                while (board.boardHeuristic() < 0) {
                                    for (int k = 0; k < 8; k++) {
                                        for (int l = 0; l < 8; l++) {
                                            if (board.getColor(i, j) != 'B') {
                                                board.putPiece("BQ", i, j);
                                            }
                                        }
                                    }

                                }
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
}
