import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BoardDice implements Board {

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

    /**
     * It sets all the pieces on the board to null
     */
    public void setup() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pieces[i][j] = null;
            }
        }
    }

    /**
     * The heuristic function is a function that takes in a board and returns a number that represents how good the board
     * is for the player
     *
     * @return The heuristic value of the board.
     */
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

    /**
     * It puts a piece on the board
     *
     * @param piece The piece to be placed.
     * @param x The x coordinate of the piece
     * @param y The y coordinate of the piece.
     */
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
     * Remove the piece at the given coordinates from the board and from the UI.
     *
     * @param x The x coordinate of the piece to be removed.
     * @param y The y coordinate of the piece to remove.
     */
    public void removePiece(int x, int y) {
        ui.removePiece(x, y);
        pieces[x][y] = null;
    }

    /**
     * It sets up the board for a classic game of chess
     */
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

    /**
     *
     * The heuristic function is a function that takes a piece and returns a number that represents how valuable that piece
     * is
     *
     * @param piece The piece that is being evaluated.
     * @return The heuristic value of the piece.
     */
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

    /**
     * It moves a piece from one square to another
     *
     * @param x The x coordinate of the square the piece is being moved to.
     * @param y the y coordinate of the square the piece is being moved to
     */
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

    /**
     * If the pawn is on the fifth rank, and the pawn is moving two spaces, then the pawn is allowed to capture the enemy
     * pawn that is directly besides it
     *
     * @param y the y coordinate of the piece that was captured
     */
    private void enPassant(int y) {
        putPiece("NN", clickedOn[0], clickedOn[1] - (clickedOn[1] - y));
    }

    /**
     * If the king is in the right place, move the rook to the right place
     *
     * @param y the y coordinate of the square the piece is moving to
     */
    private void castle(int y) {
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
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, "COULDNT CASTLE");
        }
    }

    /**
     * If the move is a capital letter, make it lowercase. If the move is a lowercase letter, make it uppercase
     *
     * @param move The move that the player wants to make.
     * @return the opposite of the input.
     */
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

    /**
     * If a pawn is at the end of the board, replace it with the piece specified by the parameter
     *
     * @param toPiece The piece to promote to.
     */
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

    /**
     * It checks if the game is over, if it is, it ends the game. If it isn't, it generates the next move
     */
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

    /**
     * It generates a random move for the player to make
     */
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

        }
    }

    /**
     * If the game is over, then the game is ended
     */
    public void endGame() {
        ui.ui.endGame(ui, isPat() ? 'N' : not(turnOf));
    }

    /**
     * If the king can't be captured, then it's a pat.
     *
     * @return A boolean value.
     */
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

    /**
     * If the mode is 1, highlight the tile that the piece will move to. If the mode is 2, highlight all the pieces of
     * that type that can move
     *
     *
     * @param mode 1 = highlight one piece, 0 = highlight all possible pieces of the same type
     */
    public void highlight(int mode) {
        if (mode == 1) {
            ui.tiles[nextMoveXY[0]][nextMoveXY[1]].highlight();
        } else {
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

    /**
     * This function sets the clickedOn array to the fromX and fromY values, and then calls the move function with the toX
     * and toY values.
     *
     * @param fromX The x coordinate of the piece you want to move
     * @param fromY The y coordinate of the piece you want to move
     * @param toX The x coordinate of the square you want to move to.
     * @param toY The y coordinate of the square to move to.
     */
    public void move(int fromX, int fromY, int toX, int toY) {
        clickedOn[0] = fromX;
        clickedOn[1] = fromY;
        move(toX, toY);
    }

    /**
     * If the piece at the given coordinates is null, return 'N', otherwise return the first character of the class name of
     * the piece
     *
     * @param x The x coordinate of the piece you're trying to reference
     * @param y The y coordinate of the piece you want to get.
     * @return The first letter of the class name of the piece at the given position.
     */
    public char getPiece(int x, int y) {
        if (pieces[x][y] == null) {
            return 'N';
        }
        return pieces[x][y].getClass().getName().charAt(0);
    }

    /**
     * If there is no piece at the given location, return 'N', otherwise return the color of the piece at the given
     * location.
     *
     * @param x the x-coordinate of the piece
     * @param y the y coordinate of the piece
     * @return The color of the piece at the given position.
     */
    public char getColor(int x, int y) {
        if (pieces[x][y] == null) {
            return 'N';
        }
        return pieces[x][y].getColor();
    }


    /**
     * If there are no possible moves for any piece of the current player, then the game is over
     *
     * @return true if the game is over
     */
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

    /**
     * Return a list of all possible moves for a piece at a given location, with checking for check.
     *
     * @param fromX The x coordinate of the piece you want to move
     * @param fromY The y coordinate of the piece you want to move
     * @return An ArrayList of int arrays.
     */
    public ArrayList<int[]> movesWithoutCheck(int fromX, int fromY) {
        return movesWithoutCheck(pieces[fromX][fromY].possibleMoves(), fromX, fromY);
    }

    // Overriding the methods in the interface.
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

    /**
     * It takes in a list of moves and a starting position, and returns a list of moves that don't put the king in check
     *
     * @param moves the list of possible moves
     * @param fromX the x coordinate of the piece you want to move
     * @param fromY the y coordinate of the piece you want to move
     * @return An arraylist of moves that do not put the king in check.
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

    /**
     * Find the king of the given color and return its position.
     *
     * @param color The color of the king you want to find.
     * @return The row and column of the king of the given color.
     */
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

    /**
     * If the color is white, return black, otherwise return white.
     *
     * @param color The color of the piece you want to flip.
     * @return The opposite color of the color that is passed in.
     */
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
