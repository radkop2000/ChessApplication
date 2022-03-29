public class King implements Piece{
    Chess ch;
    int x;
    int y;
    char color;
    boolean isYellow = false;
    boolean isRed = false;
    boolean hasMoved = false;

    public King(Chess ch, int x, int y, char color) {
        this.ch = ch;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public int[][] PossibleMoves() {
        int[][] moves = new int[30][2];
        int idx = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (Math.abs(i) + Math.abs(j) == 0) {
                    continue;
                }
                if (x+i < 8 && x+i >= 0 && y+j < 8 && y+j >= 0 && ch.board[x+i][y+j].getColor() != color) {
                    moves[idx][0] = x+i;
                    moves[idx++][1] = y+j;
                }
            }
        }
        if (!hasMoved && ((x == 7 && y == 4) || (x == 0 && y == 4))) {
            if (ch.board[x][y + 1].getColor() == 'N' && ch.board[x][y + 2].getColor() == 'N' &&
                    ch.board[x][y+3].getColor() == color && ch.board[x][y+3].getClass().getName().equals("Rook")) {
                moves[idx][0] = x;
                moves[idx++][1] = y+2;
            }
            if (ch.board[x][y - 1].getColor() == 'N' && ch.board[x][y - 2].getColor() == 'N' && ch.board[x][y - 3].getColor() == 'N' &&
                    ch.board[x][y-4].getColor() == color && ch.board[x][y-4].getClass().getName().equals("Rook")) {
                moves[idx][0] = x;
                moves[idx++][1] = y-2;
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
        hasMoved = true;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
        hasMoved = true;
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
