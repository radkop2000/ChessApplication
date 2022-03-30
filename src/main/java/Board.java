
public class Board {

    private Piece[][] pieces;
    private Tile[][] tiles;
    private Operator op;

    public Board(Operator op) {
        this.op = op;
        setupTiles();
    }

    public void setupTiles() {
        // TODO
    }

    public void setupClassic() {
        // TODO
    }

    public void setupPGN(int move) {
        // TODO (if move == -1 => setup until end)
    }

    public void drawPieces() {
        // TODO
    }

    public String getPiece(int x, int y) {
        // TODO
        return null;
    }

    public char getColor(int x, int y) {
        // TODO
        return ' ';
    }


    public void tileClicked(int x, int y) {
        // TODO
    }

    public boolean isCheckMate() {
        // TODO
        return false;
    }



}
