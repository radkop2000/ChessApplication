public interface Board {


    void setup();

    void putPiece(String piece, int x, int y);

    void removePiece(int x, int y);

    void setupClassic();

    char getPiece(int x, int y);

    char getColor(int x, int y);

    char not(char color);

}
