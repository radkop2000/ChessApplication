import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PGN {

    Board board;

    String header;
    String game;
    int end; // -1 == *, 0 == 1-0, 1 == 0-1, 2 == 1/2-1/2
    int moveNum;
    int charsInLine = 0;
    ArrayList<String> moves;

    public PGN(Board board) {
        this.board = board;
        end = -1;
    }

    public String getPGN() {
        String state = "";
        switch (end) {
            case -1 -> state = " *";
            case 0 -> state = " 1-0";
            case 1 -> state = " 0-1";
            case 2 -> state = " 1/2-1/2";
        }
        return header.concat(game).concat(state);
    }

    public void generateHeader(int round, int state) {
        String date = java.time.LocalDate.now().toString();
        String result;
        if (state == -1) {
            result = "*";
        } else if (state == 0) {
            result = "1-0";
        } else if (state == 1) {
            result = "0-1";
        } else {
            result = "1/2-1/2";
        }
        header = String.format("[Event \"Chess Game\"]\n[Site \"Prague\"]\n[Date \"%s\"]\n[Round \"%d\"]\n[White \"?\"]\n[Black \"?\"]\n[Result \"%s\"]\n\n", date, round, result);

    }

    public void updatePGN(int fromX, int fromY, int toX, int toY, boolean takes, char piece) {
        // TODO
    }

    public void copyPGN() { // To clipboard
        String myString = getPGN();
        StringSelection stringSelection = new StringSelection(myString);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    public void savePGN() throws IOException {
        String str = getPGN();
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy-HH:mm");
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/pgnFiles/" + format.format(date) + ".pgn"));
        writer.write(str);
        writer.close();
    }

    public void loadPGNFromFile(String fileName) throws IOException {
        // TODO
    }

    public void loadPGNFromString(String pgn) {
        // TODO
    }

    public void splitToMoves() {
        // TODO
    }

    public int[] nextMove() {

        return new int[]{};
    }






}
