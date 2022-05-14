import java.util.ArrayList;

public class Bishop implements Piece{

    Board board;

    int x;
    int y;
    char color;

    public Bishop(Board board, int x, int y, char c) {
        color = c;
        this.board = board;
        this.x = x;
        this.y = y;
    }

    /**
     * For each of the 4 directions, check if the piece is in the board and if it's empty or not. If it's empty, add it to
     * the list of possible moves. If it's not empty, check if it's an enemy piece. If it is, add it to the list of
     * possible moves
     *
     * @return An ArrayList of int arrays.
     */
    @Override
    public ArrayList<int[]> possibleMoves() {
        ArrayList<int[]> moves = new ArrayList<>();
        int mul = 1;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 || j == 0) {
                    continue;
                }
                while (x+i*mul < 8 && x+i*mul >= 0 && y+j*mul < 8 && y+j*mul >= 0 && board.getPiece(x+i*mul, y+j*mul) == 'N') {
                    moves.add(new int[]{x+i*mul, y+j*mul++});
                }
                if (x+i*mul < 8 && x+i*mul >= 0 && y+j*mul < 8 && y+j*mul >= 0 && board.getPiece(x+i*mul, y+j*mul) != 'N' && board.getColor(x+i*mul, y+j*mul) != color) {
                    moves.add(new int[]{x+i*mul, y+j*mul});
                }
                mul = 1;
            }
        }
        return moves;
    }

    // Overriding the methods in the Piece interface.
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
