import java.util.ArrayList;

public class King implements Piece{

    Board board;

    int x;
    int y;
    char color;
    boolean hasMoved;

    public King(Board board, int x, int y, char c) {
        color = c;
        this.board = board;
        this.x = x;
        this.y = y;
//        hasMoved = false;
    }

    @Override
    public ArrayList<int[]> possibleMoves() {

        ArrayList<int[]> moves = new ArrayList<int[]>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (Math.abs(i) + Math.abs(j) == 0) {
                    continue;
                }
                if (x+i < 8 && x+i >= 0 && y+j < 8 && y+j >= 0 && board.getColor(x+i,y+j) != color) {
                    moves.add(new int[]{x+i,y+j});
                }
            }
        }

        if (color == 'W' && x == 7 && y == 4 && board.getPiece(7,5) == 'N' && board.getPiece(7,6) == 'N'
                && board.getPiece(7,7) == 'R' && board.getColor(7,7) == 'W') {
            moves.add(new int[]{7,6});
        }
        if (color == 'W' && x == 7 && y == 4 && board.getPiece(7, 3) == 'N' && board.getPiece(7, 2) == 'N'
                && board.getPiece(7, 1) == 'N' && board.getPiece(7,0) == 'R' && board.getColor(7,0) == 'W') {
            moves.add(new int[]{7,2});
        }

        if (color == 'B' && x == 0 && y == 4 && board.getPiece(0,5) == 'N' && board.getPiece(0,6) == 'N'
                && board.getPiece(0,7) == 'R' && board.getColor(0,7) == 'B') {
            moves.add(new int[]{0,6});
        }
        if (color == 'B' && x == 0 && y == 4 && board.getPiece(0, 3) == 'N' && board.getPiece(0, 2) == 'N'
                && board.getPiece(0, 1) == 'N' && board.getPiece(0,0) == 'R' && board.getColor(0,0) == 'B') {
            moves.add(new int[]{0,2});
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
