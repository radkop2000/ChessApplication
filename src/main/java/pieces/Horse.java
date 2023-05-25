package pieces;

import board.Board;

import java.util.ArrayList;

public class Horse implements Piece{

    Board board;

    int x;
    int y;
    char color;

    public Horse(Board board, int x, int y, char c) {
        color = c;
        this.board = board;
        this.x = x;
        this.y = y;
    }

    /**
     * If the piece is moving two spaces in one direction and one space in the other, and the destination is not occupied
     * by a piece of the same color, then it is a valid move
     *
     * @return An ArrayList of valid moves.
     */
    @Override
    public ArrayList<int[]> possibleMoves() {
        ArrayList<int[]> moves = new ArrayList<int[]>();
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if (Math.abs(i) + Math.abs(j) == 3 && x+i < 8 && x+i >= 0 && y+j < 8 &&
                        y+j >= 0 && board.getColor(x+i, y+j) != color ) {
                    moves.add(new int[]{x+i,y+j});
                }
            }
        }
        return moves;
    }

    // Overriding the methods in the pieces.Piece interface.
    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setColor(char c) {
        color = c;
    }

    @Override
    public char getColor() {
        return color;
    }

    @Override
    public char getName() {
        return 'H';
    }
}
