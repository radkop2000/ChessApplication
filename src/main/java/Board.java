import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    Piece[][] pieces;
    Tile[][] tiles;
    Operator op;

    boolean playable;
    int[] clickedOn = new int[2];
    char turnOf = 'W';
    boolean againstComputer;
    int turn;

    Computer computer = new Computer(this, 'B', 2);





    public Board(Operator op) {
        this.op = op;
        tiles = new Tile[8][8];
        pieces = new Piece[8][8];
        turn = 0;
        setup();
    }

    private void setup() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tiles[i][j] = new Tile(this, i, j);
                pieces[i][j] = null;
            }
        }
    }

    public void drawTiles() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                op.ui.window.activePanel.add(tiles[i][j]);
            }
        }
    }

    public void undrawTiles() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                op.ui.window.activePanel.remove(tiles[i][j]);
            }
        }
    }

    public void putPiece(String piece, int x, int y) {
        tiles[x][y].setPiece(piece);
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
        tiles[x][y].removePiece();
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
        drawTiles();
        turnOf = 'W';
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

    public void tilePressed(int x, int y) {

        if (!tiles[x][y].isHighlighted && getColor(x,y) != turnOf) {   // SWAP FOR TESTING
            return;
        }
//        if (!tiles[x][y].isHighlighted && getColor(x,y) == 'N') {
//            return;
//        }

        if (tiles[x][y].isHighlighted) {
            resetColors();
            move(x,y);
            return;
        }

        resetColors();

        ArrayList<int[]> moves = pieces[x][y].possibleMoves();
        moves = movesWithoutCheck(moves, x, y);
        clickedOn[0] = x;
        clickedOn[1] = y;

        for (int[] move : moves) {
            tiles[move[0]][move[1]].setDarker();
        }
    }

    public void move(int x, int y) {

        resetColors();

        tiles[x][y].mouseOn();
        tiles[clickedOn[0]][clickedOn[1]].mouseOn();

        if (getPiece(clickedOn[0], clickedOn[1]) == 'K' && Math.abs(clickedOn[1]-y) == 2) {
            castle(y);
        }

        putPiece(getColor(clickedOn[0], clickedOn[1]) + "" + getPiece(clickedOn[0], clickedOn[1]), x, y);
        removePiece(clickedOn[0], clickedOn[1]);


        if (getPiece(x, y) == 'P' && (x == 0 || x == 7)) {
            op.ui.game.showPromotion(turnOf);
            turnOf = smallMove(turnOf);
        } else {
            endTurn();
        }
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
        } else {
            return 'b';
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
    }

    public void endTurn() {
        turnOf = not(turnOf);

        if (isCheckMate()) {
            System.out.println("KONIEC HRY SOM NASIEL");
        }

//        if (againstComputer && computer.getColor() == turnOf) {
            computer.nextTurn();
//        }
        turn++;
    }

    public void move(int fromX, int fromY, int toX, int toY) {
        clickedOn[0] = fromX;
        clickedOn[1] = fromY;
        move(toX, toY);
    }

    public void resetColors() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tiles[i][j].setLigher();
            }
        }
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


}
