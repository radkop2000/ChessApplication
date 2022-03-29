

interface Piece {

    public int[][] PossibleMoves();

    public void setX(int x);
    public int getX();

    public void setY(int y);
    public int getY();

    public void setColor(char c);
    public char getColor();

    public void setYellow(boolean yellow);
    public boolean getYellow();

    public void setRed(boolean red);
    public boolean getRed();

    public int getMoved2Turn();
}
