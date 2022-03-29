import java.lang.Math;

public class Pawn implements Piece {

    Chess ch;
    int x;
    int y;
    char color;
    int moved2Turn;
    boolean isYellow = false;
    boolean isRed = false;

    public Pawn(Chess ch, int x, int y, char c) {
        this.ch = ch;
        this.x = x;
        this.y = y;
        color = c;
    }


    @Override
    public int[][] PossibleMoves() {
        int[][] moves = new int[30][2];
        int idx = 0;
        if (color == 'W') {
            if (x-1 >= 0 && ch.board[x-1][y].getColor() == 'N') {
                moves[idx][0] = x-1;
                moves[idx++][1] = y;
                if (x == 6 && ch.board[x-2][y].getColor() == 'N') {
                    moves[idx][0] = x-2;
                    moves[idx++][1] = y;
                }
            }
            if (x-1 >= 0 && y-1 >= 0 && ch.board[x-1][y-1].getColor() == 'B') {
                moves[idx][0] = x-1;
                moves[idx++][1] = y-1;
            }
            if (x-1 >= 0 && y+1 < 8 && ch.board[x-1][y+1].getColor() == 'B') {
                moves[idx][0] = x-1;
                moves[idx++][1] = y+1;
            }
            if (x - 1 >= 0 && y + 1 < 8 && ch.board[x][y + 1].getClass().getName().equals("Pawn") && ch.board[x][y + 1].getColor() != color
                    && ch.board[x-1][y+1].getColor() == 'N' && ch.board[x][y+1].getMoved2Turn() == ch.turn - 1) {
                moves[idx][0] = x-1;
                moves[idx++][1] = y+1;
            }
            if (x - 1 >= 0 && y - 1 >= 0 && ch.board[x][y - 1].getClass().getName().equals("Pawn") && ch.board[x][y - 1].getColor() != color
                    && ch.board[x-1][y-1].getColor() == 'N' && ch.board[x][y-1].getMoved2Turn() == ch.turn - 1) {
                moves[idx][0] = x-1;
                moves[idx++][1] = y-1;
            }

            while (idx < 30) {
                moves[idx][0] = -1;
                moves[idx++][1] = -1;
            }
        } else if (color == 'B') {
            if (x+1 < 8 && ch.board[x+1][y].getColor() == 'N') {
                moves[idx][0] = x+1;
                moves[idx++][1] = y;
                if (x == 1 && ch.board[x+2][y].getColor() == 'N') {
                    moves[idx][0] = x+2;
                    moves[idx++][1] = y;
                }
            }
            if (x+1 < 8 && y-1 >= 0 && ch.board[x+1][y-1].getColor() == 'W') {
                moves[idx][0] = x+1;
                moves[idx++][1] = y-1;
            }
            if (x+1 < 8 && y+1 < 8 && ch.board[x+1][y+1].getColor() == 'W') {
                moves[idx][0] = x+1;
                moves[idx++][1] = y+1;
            }
            if (x + 1 >= 0 && y + 1 < 8 && ch.board[x][y + 1].getClass().getName().equals("Pawn") && ch.board[x][y + 1].getColor() != color
                    && ch.board[x+1][y+1].getColor() == 'N' && ch.board[x][y+1].getMoved2Turn() == ch.turn - 1) {
                moves[idx][0] = x+1;
                moves[idx++][1] = y+1;
            }
            if (x + 1 >= 0 && y - 1 >= 0 && ch.board[x][y - 1].getClass().getName().equals("Pawn") && ch.board[x][y - 1].getColor() != color
                    && ch.board[x+1][y-1].getColor() == 'N' && ch.board[x][y-1].getMoved2Turn() == ch.turn - 1) {
                moves[idx][0] = x+1;
                moves[idx++][1] = y-1;
            }
            while (idx < 21) {
                moves[idx][0] = -1;
                moves[idx++][1] = -1;
            }
        }
        return moves;
    }

    @Override
    public void setX(int x) {
        if (Math.abs(x-this.x) == 2) {
            moved2Turn = ch.turn;
        }
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
    public void setYellow(boolean yellow) {
        isYellow = yellow;
        isRed = false;
    }

    @Override
    public boolean getYellow() {
        return isYellow;
    }

    @Override
    public void setRed(boolean red) {
        isRed = red;
        isYellow = false;
    }

    @Override
    public boolean getRed() {
        return isRed;
    }

    public int getMoved2Turn() {
        return moved2Turn;
    }
}
