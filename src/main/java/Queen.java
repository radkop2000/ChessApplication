import java.util.ArrayList;

public class Queen implements Piece{

    Board board;

    int x;
    int y;
    char color;

    public Queen(Board board, int x, int y, char c) {
        color = c;
        this.board = board;
        this.x = x;
        this.y = y;
    }

    @Override
    public ArrayList<int[]> possibleMoves() {
        // TODO
        return null;
    }

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
}
