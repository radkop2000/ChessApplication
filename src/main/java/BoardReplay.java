import java.util.ArrayList;

public class BoardReplay implements Board{

    ReplayUI ui;
    PGN pgn;
    Piece[][] pieces;
    int turn;
    char turnOf;


    public BoardReplay(ReplayUI ui) {
    this.ui = ui;
    pgn = new PGN(this);
    turn = 0;
    turnOf = 'W';
    setup();
    }

    @Override
    public void setup() {

        pieces = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pieces[i][j] = null;
            }
        }
    }

    @Override
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

    @Override
    public void removePiece(int x, int y) {
        ui.removePiece(x, y);
        pieces[x][y] = null;
    }

    @Override
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
    }


    @Override
    public char getPiece(int x, int y) {

        return pieces[x][y] != null ? pieces[x][y].getClass().getName().charAt(0) : 'N';
    }

    @Override
    public char getColor(int x, int y) {
        return pieces[x][y] != null ? pieces[x][y].getColor() : 'N';
    }

    @Override
    public char not(char color) {
        return color == 'W' ? 'B' : 'W';
    }

    @Override
    public char getTurnOf() {
        return turnOf;
    }

    @Override
    public void move(int fromX, int fromY, int toX, int toY) {
        if (getPiece(fromX, fromY) == 'K' && Math.abs(fromY - toY) == 2) {
            castle(fromX, fromY, toX, toY);
        }

        putPiece(getColor(fromX, fromY) + "" + getPiece(fromX, fromY), toX, toY);
        putPiece("NN", fromX, fromY);
        ui.updateText();
    }


    @Override
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
                    if (getColor(i, j) == not(color)) {
                        ArrayList<int[]> opMoves = pieces[i][j].possibleMoves();
                        if (opMoves.size() == 0) {
                            break;
                        }
                        for (int[] opMove : opMoves) {
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

    private int[] findKing(char color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null && getColor(i, j) == color && getPiece(i, j) == 'K') {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public void castle(int fromX, int fromY, int toX, int toY) {
        if (fromX == 0 && toY == 2) {
            putPiece("BR", 0, 3);
            removePiece(0,0);
        } else if (fromX == 0 && toY == 6) {
            putPiece("BR", 0,5);
            removePiece(0,7);
        } else if (fromX == 7 && toY == 2) {
            putPiece("WR", 7, 3);
            removePiece(7,0);
        } else if (fromX == 7 && toY == 6) {
            putPiece("WR", 7, 5);
            removePiece(7,7);
        }
    }

    @Override
    public int getRound() {
        return turn;
    }
}
