import java.util.ArrayList;

public interface Board {


    void setup();

    void putPiece(String piece, int x, int y);

    void removePiece(int x, int y);

    void setupClassic();

    char getPiece(int x, int y);

    char getColor(int x, int y);

    char not(char color);

    char getTurnOf();

    void move(int fromX, int fromY, int toX, int toY);

    public ArrayList<int[]> movesWithoutCheck(int fromX, int fromY);

    int getRound();

}
