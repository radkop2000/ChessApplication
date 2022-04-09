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

    @Override
    public ArrayList<int[]> possibleMoves() {
        ArrayList<int[]> moves = new ArrayList<>();
        int mul = 1;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 || j == 0) {
                    continue;
                }
                while (x+i*mul < 8 && x+i*mul >= 0 && y+j*mul < 8 && y+j*mul >= 0 && board.pieces[x+i*mul][y+j*mul] == null) {
                    moves.add(new int[]{x+i*mul, y+j*mul++});
                }
                if (x+i*mul < 8 && x+i*mul >= 0 && y+j*mul < 8 && y+j*mul >= 0 && board.pieces[x+i*mul][y+j*mul] != null && board.pieces[x+i*mul][y+j*mul].getColor() != color) {
                    moves.add(new int[]{x+i*mul, y+j*mul});
                }
                mul = 1;
            }
        }
        return moves;
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
