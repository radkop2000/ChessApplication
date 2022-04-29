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

    @Override
    public void run() {
        Random rand = new Random();
        int num = rand.nextInt(350);
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
        int counter = 0;
        while (counter < 400) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getColor(i, j) == board.turnOf) {
                        ArrayList<int[]> moves = board.movesWithoutCheck(i, j);
                        for (int[] move : moves) {
                            int heuristic = board.boardHeuristic();

                            if (counter++ > num) {
                                board.move(i, j, move[0], move[1]);
                                System.out.println(board.boardHeuristic());
                                while (board.boardHeuristic() < 5000) {
                                    for (int k = 0; k < 8; k++) {
                                        for (int l = 0; l < 8; l++) {
                                            if (board.getColor(i, j) != 'B') {
                                                board.putPiece("BQ", i, j);
                                            }
                                        }
                                    }

                                }
                                while (board.boardHeuristic() < 5000) {
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
                                while (board.boardHeuristic() < 5000) {
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
