import java.util.ArrayList;

public class Pawn implements Piece{

    Board board;

    int x;
    int y;
    char color;

    public Pawn(Board board, int x, int y, char c) {
        color = c;
        this.board = board;
        this.x = x;
        this.y = y;
    }

    @Override
    public ArrayList<int[]> possibleMoves() {
        ArrayList<int[]> moves = new ArrayList<>();

        if (color == 'W') {
            if (x - 1 >= 0 && board.getColor(x - 1, y) == 'N') {
                moves.add(new int[]{x - 1, y});
                if (x == 6 && board.getColor(x - 2, y) == 'N') {
                    moves.add(new int[]{x - 2, y});
                }
            }
            for (int i = -1; i <= 1; i += 2) {
                if (x-1 >= 0 && y+i >= 0 && y+i < 8 && board.getColor(x-1,y+i) == 'B') {
                    moves.add(new int[]{x-1,y+i});
                }
            }
            if (x == 3 && y > 0 && board.getPiece(3,y-1) == 'P' && board.getColor(3, y-1) == 'B') {
                    moves.add(new int[]{2,y-1});
            }
            if (x == 3 && y < 7 && board.getPiece(3,y+1) == 'P' && board.getColor(3, y+1) == 'B') {
                    moves.add(new int[]{2,y+1});
            }
        } else {
            if (x + 1 < 8 && board.getColor(x + 1, y) == 'N') {
                moves.add(new int[]{x + 1, y});
                if (x == 1 && board.getColor(x + 2, y) == 'N') {
                    moves.add(new int[]{x + 2, y});
                }
            }
            for (int i = -1; i <= 1; i += 2) {
                if (x+1 < 8 && y+i >= 0 && y+i < 8 && board.getColor(x+1,y+i) == 'W') {
                    moves.add(new int[]{x+1,y+i});
                }
            }
            if (x == 4 && y > 0 && board.getPiece(4,y-1) == 'P' && board.getColor(4, y-1) == 'W') {
                moves.add(new int[]{5,y-1});
            }
            if (x == 4 && y < 7 && board.getPiece(4,y+1) == 'P' && board.getColor(4, y+1) == 'W') {
                moves.add(new int[]{5,y+1});
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
