package pieces;

import java.util.ArrayList;

public interface Piece {

    public ArrayList<int[]> possibleMoves();

    public void setX(int x);
    public int getX();

    public void setY(int y);
    public int getY();

    public void setColor(char c);
    public char getColor();

    public char getName();

}
