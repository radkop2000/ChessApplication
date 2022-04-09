import java.util.ArrayList;

public class ComputerRandom implements Computer, Runnable {

    Board board;
    char color;

    public ComputerRandom(Board board, char color) {
        this.board = board;
        this.color = color;
    }

    @Override
    public void nextTurn() {
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

    @Override
    public char getColor() {
        return color;
    }

    @Override
    public void run() {
    }
}
