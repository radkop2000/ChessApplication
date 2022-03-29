

public class Horse implements Piece{

    Chess ch;
    int x;
    int y;
    char color;
    boolean isYellow = false;
    boolean isRed = false;

    public Horse(Chess ch, int x, int y, char color) {
        this.ch = ch;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public int[][] PossibleMoves() {
        int[][] moves = new int[30][2];
        int idx = 0;
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if (Math.abs(i) + Math.abs(j) == 3 && x+i < 8 && x+i >= 0 && y+j < 8 &&
                        y+j >= 0 && ch.board[x+i][y+j].getColor() != color ) {
                    moves[idx][0] = x+i;
                    moves[idx++][1] = y+j;
                }
            }
        }
        while (idx < 30) {
            moves[idx][0] = -1;
            moves[idx++][1] = -1;
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

    @Override
    public int getMoved2Turn() {
        return 0;
    }
}
