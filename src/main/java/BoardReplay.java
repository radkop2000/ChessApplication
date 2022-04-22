public class BoardReplay implements Board{

    ReplayUI ui;

    public BoardReplay(ReplayUI ui) {
    this.ui = ui;
    }

    @Override
    public void setup() {

    }

    @Override
    public void putPiece(String piece, int x, int y) {

    }

    @Override
    public void removePiece(int x, int y) {

    }

    @Override
    public void setupClassic() {

    }

    @Override
    public char getPiece(int x, int y) {
        return 0;
    }

    @Override
    public char getColor(int x, int y) {
        return 0;
    }

    @Override
    public char not(char color) {
        return 0;
    }
}
