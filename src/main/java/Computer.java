

public class Computer {

    Chess ch;
    char color;

    public Computer(Chess ch, char color) {
        this.ch = ch;
        this.color = color;
    }

    public void nextTurn() {
        int counter = 0;
        while (counter < 10000) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (ch.board[i][j].getColor() == color) {
                        int[][] moves = ch.board[i][j].PossibleMoves();
                        ch.gm.gameUI.clickedOn[0] = i;
                        ch.gm.gameUI.clickedOn[1] = j;
                        int k = 0;
                        while (moves[k][0] != -1) {
                            counter++;
                            if (!ch.isKingChecked(moves[k][0], moves[k][1])) {
                                if (counter++ >= 5000 || (ch.board[moves[k][0]][moves[k][1]].getColor() != color &&
                                        ch.board[moves[k][0]][moves[k][1]].getColor() != 'N')) {
                                    if (ch.board[moves[k][0]][moves[k][1]].getColor() == 'N') {
                                        ch.gm.gameUI.move(moves[k][0], moves[k][1]);
                                    } else {
                                        ch.gm.gameUI.take(moves[k][0], moves[k][1]);
                                    }
                                    return;
                                }
                            }
                            k++;
                        }
                    }
                }
            }
        }
    }
}
