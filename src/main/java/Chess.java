import java.util.concurrent.TimeUnit;

public class Chess {

    GameManager gm;
    Computer computer;
    PGN pgn;

    Piece[][] board = new Piece[8][8];

    int turn = 1;
    char turnOf = 'W';
    int state = -1;

    boolean againstComputer = false;
    boolean isTurn = true;



    public Chess(GameManager gm) {
        this.gm = gm;
        computer = new Computer(this, 'B');
        pgn = new PGN(this);
    }

    public void setupStandard() {
        board[0][0] = new Rook(this, 0, 0, 'B');
        board[0][1] = new Horse(this, 0, 1, 'B');
        board[0][2] = new Bishop(this, 0, 2, 'B');
        board[0][3] = new Queen(this, 0, 3, 'B');
        board[0][4] = new King(this, 0, 4, 'B');
        board[0][5] = new Bishop(this, 0, 5, 'B');
        board[0][6] = new Horse(this, 0, 6, 'B');
        board[0][7] = new Rook(this, 0, 7, 'B');
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(this, 1, i, 'B');
        }
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Empty(this, i, j, 'N');
            }
        }
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(this, 6, i, 'W');
        }
        board[7][0] = new Rook(this, 7, 0, 'W');
        board[7][1] = new Horse(this, 7, 1, 'W');
        board[7][2] = new Bishop(this, 7, 2, 'W');
        board[7][3] = new Queen(this, 7, 3, 'W');
        board[7][4] = new King(this, 7, 4, 'W');
        board[7][5] = new Bishop(this, 7, 5, 'W');
        board[7][6] = new Horse(this, 7, 6, 'W');
        board[7][7] = new Rook(this, 7, 7, 'W');
        turn = 1;
        turnOf = 'W';
    }

    public void setupPGN(int round) {
        board[0][0] = new Rook(this, 0, 0, 'B');
        board[0][1] = new Horse(this, 0, 1, 'B');
        board[0][2] = new Bishop(this, 0, 2, 'B');
        board[0][3] = new Queen(this, 0, 3, 'B');
        board[0][4] = new King(this, 0, 4, 'B');
        board[0][5] = new Bishop(this, 0, 5, 'B');
        board[0][6] = new Horse(this, 0, 6, 'B');
        board[0][7] = new Rook(this, 0, 7, 'B');
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(this, 1, i, 'B');
        }
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Empty(this, i, j, 'N');
            }
        }
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(this, 6, i, 'W');
        }
        board[7][0] = new Rook(this, 7, 0, 'W');
        board[7][1] = new Horse(this, 7, 1, 'W');
        board[7][2] = new Bishop(this, 7, 2, 'W');
        board[7][3] = new Queen(this, 7, 3, 'W');
        board[7][4] = new King(this, 7, 4, 'W');
        board[7][5] = new Bishop(this, 7, 5, 'W');
        board[7][6] = new Horse(this, 7, 6, 'W');
        board[7][7] = new Rook(this, 7, 7, 'W');
        turnOf = 'W';

        gm.ch.pgn.splitToMoves();

        gm.ch.pgn.moveIDX = 0;
        if (round == -1) {
            while (gm.ch.pgn.moveIDX < gm.ch.pgn.moves.size()) {
                gm.ch.pgn.nextTurnGame();
            }

            turn = gm.ch.pgn.moveIDX/2;
        } else {
            while (gm.ch.pgn.moveIDX < round) {
                // TODO clear pgn to this move
                gm.ch.pgn.nextTurnGame();
            }
            turn = round;
        }


        turnOf = gm.ch.pgn.turnOf;

    }

    public void setupTesting() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                putPiece(i,j,"NN");
            }
        }
        putPiece(0,0,"BK");
        putPiece(0,7,"WQ");
        putPiece(7,6,"WQ");
        putPiece(7,5,"WK");
        turn = 1;
        turnOf = 'W';
    }

    public void putPiece(int x, int y, String piece) {
        if (piece.equals("NN")) {
            board[x][y] = new Empty(this, x, y, 'N');
        } else if (piece.charAt(1) == 'P') {
            board[x][y] = new Pawn(this, x, y, piece.charAt(0));
        } else if (piece.charAt(1) == 'H') {
            board[x][y] = new Horse(this, x, y, piece.charAt(0));
        } else if (piece.charAt(1) == 'B') {
            board[x][y] = new Bishop(this, x, y, piece.charAt(0));
        } else if (piece.charAt(1) == 'R') {
            board[x][y] = new Rook(this, x, y, piece.charAt(0));
        } else if (piece.charAt(1) == 'Q') {
            board[x][y] = new Queen(this, x, y, piece.charAt(0));
        } else if (piece.charAt(1) == 'K') {
            board[x][y] = new King(this, x, y, piece.charAt(0));
        }
    }

    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.printf("%s, ",board[i][j].getClass().getName());
            }
            System.out.println();
        }
    }

    public void startEndTurn() {
        isTurn = false;

        if (turnOf == 'W') {
            turnOf = 'B';
        } else {
            turnOf = 'W';
            turn++;
        }
        gm.gameUI.updateTurnCounter();
        gm.gameUI.updateActivePlayer();
        if (isCheckMate()) {
            endGame(0);
        }
        boolean upgrade = false;
        for (int i = 0; i < 8; i++) {
            if (board[0][i].getClass().getName().equals("Pawn")) {
                upgrade(0, i, 'W');
                upgrade = true;
                break;
            } else if (board[7][i].getClass().getName().equals("Pawn")) {
                upgrade(7, i, 'B');
                upgrade = true;
                break;
            }
        }

        if (!upgrade) {
            endEndTurn();
        }

    }

    public void endEndTurn() {
        if (againstComputer && computer.color == turnOf) {
            computer.nextTurn();
        }
        System.out.println(pgn.getPGN());
        isTurn = true;
    }

    public void move(int x, int y) {
        Piece temp = board[gm.gameUI.clickedOn[0]][gm.gameUI.clickedOn[1]];
        board[gm.gameUI.clickedOn[0]][gm.gameUI.clickedOn[1]] = board[x][y];
        board[x][y] = temp;
        board[x][y].setX(x);
        board[x][y].setY(y);
        board[gm.gameUI.clickedOn[0]][gm.gameUI.clickedOn[1]].setX(gm.gameUI.clickedOn[0]);
        board[gm.gameUI.clickedOn[0]][gm.gameUI.clickedOn[1]].setY(gm.gameUI.clickedOn[1]);
        startEndTurn();
    }

    public void take(int x, int y) {
        board[x][y] = board[gm.gameUI.clickedOn[0]][gm.gameUI.clickedOn[1]];
        board[gm.gameUI.clickedOn[0]][gm.gameUI.clickedOn[1]] = new Empty(this, gm.gameUI.clickedOn[0], gm.gameUI.clickedOn[1], 'N');
        board[x][y].setX(x);
        board[x][y].setY(y);
        startEndTurn();
    }

    public void upgrade(int x, int y, char color) {
        gm.gameUI.showUpgrade(color);
    }

    public void enPassant(int x, int y) {
        board[x][y] = new Empty(this, x, y, 'N');
    }

    public boolean isKingChecked(int x, int y) {
        Piece temp1 = board[x][y];
        Piece temp2 = board[gm.gameUI.clickedOn[0]][gm.gameUI.clickedOn[1]];
        board[x][y] = board[gm.gameUI.clickedOn[0]][gm.gameUI.clickedOn[1]];
        board[gm.gameUI.clickedOn[0]][gm.gameUI.clickedOn[1]] = new Empty(this, gm.gameUI.clickedOn[0], gm.gameUI.clickedOn[1], 'N');
        int[] king = findKing(turnOf);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getColor() != turnOf) {
                    int[][] moves = board[i][j].PossibleMoves();
                    int k = 0;
                    while(moves[k][0] != -1) {
                        if (moves[k][0] == king[0] && moves[k][1] == king[1]) {
                            board[x][y] = temp1;
                            board[gm.gameUI.clickedOn[0]][gm.gameUI.clickedOn[1]] = temp2;
                            return true;
                        }
                        k++;
                    }
                }
            }
        }
        board[x][y] = temp1;
        board[gm.gameUI.clickedOn[0]][gm.gameUI.clickedOn[1]] = temp2;
        return false;
    }

    public int[] findKing(char c) {
        int[] ret = new int[2];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getColor() == c && board[i][j].getClass().getName().equals("King")) {
                    ret[0] = i;
                    ret[1] = j;
                    return ret;
                }
            }
        }
        return new int[2];
    }

    public boolean isCheckMate() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getColor() == turnOf) {
                    int[][] moves = board[i][j].PossibleMoves();
                    gm.gameUI.clickedOn[0] = i;
                    gm.gameUI.clickedOn[1] = j;
                    int k = 0;
                    while (moves[k][0] != -1) {
                        if (!isKingChecked(moves[k][0], moves[k][1])) {
                            return false;
                        }
                        k++;
                    }
                }
            }
        }
        gm.gameUI.clickedOn[0] = 0;
        gm.gameUI.clickedOn[1] = 0;
        if (!isKingChecked(0,0)) {
            endGame(1);
            return false;
        }
        return true;
    }

    public void endGame(int option) {
        if (option == 0) {
            if (turnOf == 'W') {
                state = 1;
            } else {
                state = 0;
            }
        } else {
            state = 2;
        }
        pgn.addCheckMate();
        System.out.println(pgn.getPGN());
        gm.menuUI.endGameUI.show(state);

    }
}
