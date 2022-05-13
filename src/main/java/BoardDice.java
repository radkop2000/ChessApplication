import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BoardDice implements Board{

    Piece[][] pieces;
    DiceGameUI ui;

    int[] clickedOn = new int[2];
    char turnOf = 'W';
    int turn;

    int[] nextMoveXY = new int[2];
    char nextMoveC;
    int mode;

    GameModeAnimation anim;


    public BoardDice(DiceGameUI ui) {
        this.ui = ui;
        pieces = new Piece[8][8];
        turn = 0;
        anim = new GameModeAnimation(ui, this);
        setup();
    }

    public void setup() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pieces[i][j] = null;
            }
        }
    }

    public int boardHeuristic() {
        int heuristic = 0;
        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 8; l++) {
                if (getColor(k, l) == 'W') {
                    heuristic -= heuristic(getPiece(k, l));
                } else if (getColor(k, l) == 'B') {
                    heuristic += heuristic(getPiece(k, l));
                }
            }
        }
        return heuristic;
    }

    public void putPiece(String piece, int x, int y) {
        ui.putPiece(piece, x, y);
        switch (piece.charAt(1)) {
            case 'P' -> pieces[x][y] = new Pawn(this, x, y, piece.charAt(0));
            case 'R' -> pieces[x][y] = new Rook(this, x, y, piece.charAt(0));
            case 'H' -> pieces[x][y] = new Horse(this, x, y, piece.charAt(0));
            case 'B' -> pieces[x][y] = new Bishop(this, x, y, piece.charAt(0));
            case 'Q' -> pieces[x][y] = new Queen(this, x, y, piece.charAt(0));
            case 'K' -> pieces[x][y] = new King(this, x, y, piece.charAt(0));
            case 'N' -> pieces[x][y] = null;
        }
    }

    public void removePiece(int x, int y) {
        ui.removePiece(x, y);
        pieces[x][y] = null;
    }

    public void setupClassic() {
        char[] pieces = {'R', 'H', 'B', 'Q', 'K', 'B', 'H', 'R'};
        for (int i = 0; i < 8; i++) {
            putPiece('B' + "" + pieces[i], 0, i);
            putPiece("BP", 1, i);
            for (int j = 2; j <= 5; j++) {
                putPiece("NN", j, i);
            }
            putPiece("WP", 6, i);
            putPiece('W' + "" + pieces[i], 7, i);
        }
        turnOf = 'W';
        turn = 0;
        ui.updateText();
        ui.resetHighlight();
        generateNextMove();
    }

    public int heuristic(char piece) {
        switch (piece) {
            case 'P' -> {
                return 1;
            }
            case 'R' -> {
                return 5;
            }
            case 'H', 'B' -> {
                return 3;
            }
            case 'Q' -> {
                return 7;
            }
            case 'K' -> {
                return 1000;
            }
        }
        return 0;
    }

    public void move(int x, int y) {

        if (getPiece(clickedOn[0], clickedOn[1]) == 'K' && Math.abs(clickedOn[1]-y) == 2) {
            castle(y);
        }

        if (getPiece(clickedOn[0], clickedOn[1]) == 'P' && x != clickedOn[0] && y != clickedOn[1] && getColor(x,y) == 'N') {
            enPassant(y);
        }

        putPiece(getColor(clickedOn[0], clickedOn[1]) + "" + getPiece(clickedOn[0], clickedOn[1]), x, y);
        removePiece(clickedOn[0], clickedOn[1]);


        if (getPiece(x, y) == 'P' && (x == 0 || x == 7)) {
            ui.showPromotion(turnOf);
            turnOf = smallMove(turnOf);
        } else {
            endTurn();
        }
    }

    private void enPassant(int y) {
        putPiece("NN", clickedOn[0], clickedOn[1] - (clickedOn[1] - y));
    }

    private void castle(int y) {
        System.out.println("CASTLING");
        if (clickedOn[0] == 0 && y == 2) {
            putPiece("BR", 0, 3);
            removePiece(0,0);
        } else if (clickedOn[0] == 0 && y == 6) {
            putPiece("BR", 0, 5);
            removePiece(0,7);
        } else if (clickedOn[0] == 7 && y == 2) {
            putPiece("WR", 7, 3);
            removePiece(7,0);
        }else if (clickedOn[0] == 7 && y == 6) {
            putPiece("WR", 7, 5);
            removePiece(7,7);
        } else {
            System.out.println("OOF");
        }
    }

    private char smallMove(char move) {
        if (move == 'W') {
            return 'w';
        } else if (move == 'B') {
            return 'b';
        } else if (move == 'w') {
            return 'W';
        } else {
            return 'B';
        }
    }

    public void promote(char toPiece) {
        for (int i = 0; i < 8; i++) {
            if (getPiece(0, i) == 'P') {
                putPiece("W" + toPiece, 0, i);
            }
            if (getPiece(7, i) == 'P') {
                putPiece("B" + toPiece, 7, i);
            }
        }
        endTurn();
    }

    public void endTurn() {

        turnOf = not(turnOf);

        if (isCheckMate()) {
            endGame();
            return;
        }

        generateNextMove();


        turn++;
        ui.updateText();

    }

    public void generateNextMove() {

        Random rand = new Random();

        if (ui.ui.op.gameMode == 2) {
            mode = rand.nextInt(1,3);
        } else {
            mode = ui.ui.op.gameMode;
        }

        if (mode == 1) {
            ArrayList<int[]> possibleMoves = new ArrayList<>();

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (getColor(i, j) == turnOf && movesWithoutCheck(i,j).size() > 0) {
                        possibleMoves.add(new int[]{i,j});
                    }
                }
            }

            nextMoveXY = possibleMoves.get(rand.nextInt(0, possibleMoves.size()));

            anim.NextAnimation(nextMoveXY);
//            highlight(1);
//            ui.tiles[nextMoveXY[0]][nextMoveXY[1]].highlight();
        } else {

            ArrayList<Character> possibleMoves = new ArrayList<>();

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (getColor(i, j) == turnOf && movesWithoutCheck(i,j).size() > 0) {
                        possibleMoves.add(getPiece(i,j));
                    }
                }
            }

            nextMoveC = possibleMoves.get(rand.nextInt(0, possibleMoves.size()));

            anim.NextAnimation(nextMoveC);

//            highlight(2);
//            for (int i = 0; i < 8; i++) {
//                for (int j = 0; j < 8; j++) {
//                    if (getPiece(i, j) == nextMoveC && getColor(i, j) == turnOf) {
//                        if (movesWithoutCheck(i,j).size() > 0) {
//                            ui.tiles[i][j].highlight();
//                        }
//                    }
//                }
//            }
        }
    }

    public void endGame() {

        ui.ui.endGame(ui, isPat() ? 'N' : not(turnOf));
    }

    private boolean isPat() {
        int[] king = findKing(turnOf);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (getColor(i,j) == not(turnOf)) {
                    ArrayList<int[]> moves = pieces[i][j].possibleMoves();
                    for (int[] move: moves) {
                        if (move[0] == king[0] && move[1] == king[1]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public void highlight(int mode) {
        System.out.println("mode: " + mode);
        if (mode == 1) {
            System.out.println("mode1 executing");
            ui.tiles[nextMoveXY[0]][nextMoveXY[1]].highlight();
        } else {
            System.out.println("modeElse executing");
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (getPiece(i, j) == nextMoveC && getColor(i, j) == turnOf) {
                        if (movesWithoutCheck(i,j).size() > 0) {
                            ui.tiles[i][j].highlight();
                        }
                    }
                }
            }
        }
    }

    public void move(int fromX, int fromY, int toX, int toY) {
        clickedOn[0] = fromX;
        clickedOn[1] = fromY;
        move(toX, toY);
    }

    public void setupPGN(int move) {
        // TODO (if move == -1 => setup until end)
    }

    public char getPiece(int x, int y) {
        if (pieces[x][y] == null) {
            return 'N';
        }
        return pieces[x][y].getClass().getName().charAt(0);
    }

    public char getColor(int x, int y) {
        if (pieces[x][y] == null) {
            return 'N';
        }
        return pieces[x][y].getColor();
    }


    public boolean isCheckMate() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (getColor(i,j) == turnOf && movesWithoutCheck(pieces[i][j].possibleMoves(), i, j).size() > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public ArrayList<int[]> movesWithoutCheck(int fromX, int fromY) {
        return movesWithoutCheck(pieces[fromX][fromY].possibleMoves(), fromX, fromY);
    }

    @Override
    public int getRound() {
        return 0;
    }

    @Override
    public int getClockTime() {
        return ui.ui.op.clockTime;
    }

    @Override
    public void endGameTime(char color) {
        // TODO
    }

    public ArrayList<int[]> movesWithoutCheck(ArrayList<int[]> moves, int fromX, int fromY) {


        ArrayList<int[]> newMoves = new ArrayList<>();

        if (moves.size() == 0) {
            return newMoves;
        }

        char color = getColor(fromX, fromY);

        for (int[] move : moves) {
            boolean isCheck = false;
            Piece temp1 = pieces[fromX][fromY];
            Piece temp2 = pieces[move[0]][move[1]];
            pieces[move[0]][move[1]] = pieces[fromX][fromY];
            pieces[fromX][fromY] = null;
            int[] king = findKing(color);
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (getColor(i,j) == not(color)) {
                        ArrayList<int[]> opMoves = pieces[i][j].possibleMoves();
                        if (opMoves.size() == 0) {
                            break;
                        }
                        for (int[] opMove: opMoves) {
                            if (opMove[0] == king[0] && opMove[1] == king[1]) {
                                isCheck = true;
                                break;
                            }
                        }
                    }
                }
            }
            if (!isCheck) {
                newMoves.add(move);
            }
            pieces[fromX][fromY] = temp1;
            pieces[move[0]][move[1]] = temp2;
        }



        return newMoves;
    }

    public int[] findKing(char color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null && getColor(i, j) == color && getPiece(i,j) == 'K') {
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{-1,-1};
    }



    public char getColor(Piece piece) {
        return piece.getColor();
    }

    public char getPiece(Piece piece) {
        return piece.getClass().getName().charAt(0);
    }

    public char not(char color) {
        if (color == 'W' || color == 'w') {
            return 'B';
        } else {
            return 'W';
        }
    }

    @Override
    public char getTurnOf() {
        return turnOf;
    }


}
