import java.util.ArrayList;

public class PGN {

    Operator op;

    String header;
    String game;
    int move;
    ArrayList<String> moves;

    public PGN(Operator op) {
        this.op = op;
    }

    public String getPGN() {
        // TODO
        return null;
    }

    private void resetPGN() {
        // TODO
    }

    public void updatePGN(int fromX, int fromY, int toX, int toY, boolean takes, char piece) {
        // TODO
    }

    public void addCheckMate() {
        // TODO
    }

    public void copyPGN() { // To clipboard
        // TODO
    }

    public void savePGN() {
        // TODO
    }

    public void loadPGNFromFile(String fileName) {
        // TODO
    }

    public void loadPGNFromString(String pgn) {
        // TODO
    }






}
