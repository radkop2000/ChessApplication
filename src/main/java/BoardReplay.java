import java.util.ArrayList;

public class BoardReplay implements Board {

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

    /**
     * It creates a new board with no pieces on it.
     */
    @Override
    public void setup() {

        pieces = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pieces[i][j] = null;
            }
        }
    }

    /**
     * It puts a piece on the board
     *
     * @param piece The piece to be placed.
     * @param x The x coordinate of the piece
     * @param y The y coordinate of the piece.
     */
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

    /**
     * Remove the piece at the given coordinates from the board and the UI.
     *
     * @param x The x coordinate of the piece to be removed.
     * @param y The y coordinate of the piece to be removed.
     */
    @Override
    public void removePiece(int x, int y) {
        ui.removePiece(x, y);
        pieces[x][y] = null;
    }

    /**
     * It sets up the board for a classic game of chess
     */
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


    /**
     * If the piece at the given coordinates is not null, return the first character of the class name of the piece.
     * Otherwise, return 'N'
     *
     * @param x The x coordinate of the piece you want to get.
     * @param y The y coordinate of the piece to get.
     * @return The piece at the given position.
     */
    @Override
    public char getPiece(int x, int y) {

        return pieces[x][y] != null ? pieces[x][y].getClass().getName().charAt(0) : 'N';
    }

    /**
     * If the piece at the given location is not null, return the color of the piece, otherwise return 'N'
     *
     * @param x The x coordinate of the piece you want to get the color of.
     * @param y The y coordinate of the piece to be moved.
     * @return The color of the piece at the given position.
     */
    @Override
    public char getColor(int x, int y) {
        return pieces[x][y] != null ? pieces[x][y].getColor() : 'N';
    }

    /**
     * If the color is white, return black, otherwise return white.
     *
     * @param color The color of the piece to flip
     * @return The opposite color of the color passed in.
     */
    @Override
    public char not(char color) {
        return color == 'W' ? 'B' : 'W';
    }

    /**
     * This function returns the character that represents the player whose turn it is
     *
     * @return The turn of the player.
     */
    @Override
    public char getTurnOf() {
        return turnOf;
    }

    /**
     * If the piece is a king and it's moving two spaces, then castle and
     * move a piece from one square to another
     *
     * @param fromX The x coordinate of the piece you want to move.
     * @param fromY The y coordinate of the piece you want to move
     * @param toX The x coordinate of the piece you want to move to.
     * @param toY The y coordinate of the destination square.
     */
    @Override
    public void move(int fromX, int fromY, int toX, int toY) {
        if (getPiece(fromX, fromY) == 'K' && Math.abs(fromY - toY) == 2) {
            castle(fromX, fromY, toX, toY);
        }

        putPiece(getColor(fromX, fromY) + "" + getPiece(fromX, fromY), toX, toY);
        putPiece("NN", fromX, fromY);
        ui.updateText();
    }


    /**
     * Return a list of all possible moves for a piece, with checking for check.
     *
     * @param fromX The x coordinate of the piece you want to move
     * @param fromY The y coordinate of the piece you want to move
     * @return An ArrayList of int arrays.
     */
    @Override
    public ArrayList<int[]> movesWithoutCheck(int fromX, int fromY) {
        return movesWithoutCheck(pieces[fromX][fromY].possibleMoves(), fromX, fromY);
    }

    /**
     * It takes in a list of moves and a starting position, and returns a list of moves that don't put the king in check
     *
     * @param moves the list of possible moves
     * @param fromX the x coordinate of the piece you want to move
     * @param fromY the y coordinate of the piece you want to move
     * @return The method is returning an ArrayList of int arrays.
     */
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

    /**
     * Find the king of the given color and return its position.
     *
     * @param color The color of the king you want to find.
     * @return The row and column of the king.
     */
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

    /**
     * If the king moves to the left two spaces, move the rook to the left one space. If the king moves to the right two
     * spaces, move the rook to the right one space
     *
     * @param fromX The x coordinate of the piece you're moving
     * @param fromY The y-coordinate of the piece you're moving
     * @param toX The x coordinate of the piece you want to move
     * @param toY The y-coordinate of the piece you want to move.
     */
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

    /**
     * This function returns the current round number
     *
     * @return The turn number
     */
    @Override
    public int getRound() {
        return turn;
    }

    /**
     * This function returns the current clock time.
     *
     * @return The clock time.
     */
    @Override
    public int getClockTime() {
        return ui.ui.op.clockTime;
    }

    /**
     * > This function is called when the game time for a player has ended
     *
     * @param color The color of the player whose time has ended.
     */
    @Override
    public void endGameTime(char color) {
        // TODO
    }
}
