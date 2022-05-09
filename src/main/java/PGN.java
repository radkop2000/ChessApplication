import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class PGN {

    Board board;

    String header;
    String game;
    int end; // -1 == *, 0 == 1-0, 1 == 0-1, 2 == 1/2-1/2
    int moveNum = 0;
    int charsInLine = 0;
    ArrayList<String> moves = new ArrayList<>();
    boolean pgnMoving = false;

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
        generateHeader(board.getRound(), end);
        generateGame();
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
        header = String.format("[Event \"Chess Game\"]\n[Site \"Prague\"]\n[Date \"%s\"]\n[Round \"%d\"]\n[White \"?\"]\n[Black \"?\"]\n[Result \"%s\"]\n\n", date, round / 2, result);
    }

    public void generateGame() {
        game = "";
        int idx = 2;
        int chars = 0;
        for (String m : moves) {
            if (idx % 2 == 0) {
                game = game.concat(idx / 2 + ". ");
            }
            game = game.concat(m + " ");
            chars += m.length() + 1;
            if (chars > 65) {
                game = game.concat("\n");
                chars = 0;
            }
            idx++;
        }
    }

    public void updatePGN(int fromX, int fromY, int toX, int toY, boolean takes, char piece) {
        if (pgnMoving) {
            return;
        }

        String move = "";

        if (piece == 'H') {
            piece = 'N';
        }
        if (piece != 'P') {
            move = move.concat(piece + "");
        }

        if (piece == 'N') {
            piece = 'H';
        }

        if (takes) {
            move = move.concat("x");
        }

        boolean flag = false;

        board.putPiece(board.getTurnOf() + "" + piece, fromX, fromY);
        board.putPiece("NN", toX, toY);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == fromX && j == fromY) {
                    continue;
                }
                if (board.getPiece(i, j) == piece && board.getTurnOf() == board.getColor(i, j)) {
                    for (int[] m : board.movesWithoutCheck(i, j)) {
                        if (m[0] == toX && m[1] == toY) {
                            flag = true;
                        }
                    }
                }
            }
        }

        if (flag) {
            move = move.concat(String.valueOf((char) (Math.abs(fromY) + 97)));
        }
        board.putPiece(board.getTurnOf() + "" + piece, toX, toY);
        board.putPiece("NN", fromX, fromY);

        move = move.concat(String.valueOf((char) (Math.abs(toY) + 97)));
        move = move.concat(Math.abs(toX - 7) + 1 + "");


        moves.add(move);
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
        new File("src/opinarium3/media/presentaciones/"+format.format(date)+"/comments/");
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/pgnFiles/" + format.format(date) + ".pgn"));
        writer.write(str);
        writer.close();
    }

    public void loadPGNFromFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        loadPGNFromString(Files.readString(path));
        System.out.println(Files.readString(path));
    }

    public void loadPGNFromString(String pgn) {
        char c = pgn.charAt(0);
        int idx = 0;
        int counter = 0;
        header = "";
        String result = "";
        while (counter < 7) {
            header = header.concat(String.valueOf(c));
            if (c == '\n') counter++;
            if (counter == 6) {
                result = result.concat(String.valueOf(c));
            }
            c = pgn.charAt(++idx);
        }

        result = result.substring(10, result.length() - 2);

        switch (result) {
            case "*":
                end = -1;
                game = pgn.substring(idx, pgn.length() - 1);
                break;
            case "1-0":
                end = 0;
                game = pgn.substring(idx, pgn.length() - 3);
                break;
            case "0-1":
                end = 1;
                game = pgn.substring(idx, pgn.length() - 3);
                break;
            default:
                end = 2;
                game = pgn.substring(idx, pgn.length() - 7);

        }
        splitToMoves();
        moveNum = 0;
    }

    public void splitToMoves() {

        boolean flag = true;
        String gameWithoutComments = "";
        for (char c : game.toCharArray()) {
            if (c == '{') {
                flag = false;
            } else if (c == '}') {
                flag = true;
            } else if (flag) {
                gameWithoutComments = gameWithoutComments.concat(String.valueOf(c));
            }

        }

        String[] split = gameWithoutComments.split("[ \n]");
        String gameWithoutTurns = "";
        for (String move : split) {
            if (move.length() < 2) {
                continue;
            }
            if (move.charAt(1) == '.') {
                if (move.length() > 2) {
                    gameWithoutTurns = gameWithoutTurns.concat(move.substring(2) + " ");
                }
            } else if (move.length() > 2 && move.charAt(2) == '.') {
                if (move.length() > 3) {
                    gameWithoutTurns = gameWithoutTurns.concat(move.substring(3) + " ");
                }
            } else {
                gameWithoutTurns = gameWithoutTurns.concat(move + " ");
            }
        }

        moves.addAll(Arrays.asList(gameWithoutTurns.split(" ")));
        System.out.println(moves);
    }

    public boolean nextMove() {

        if (moveNum >= moves.size()) {
            return false;
        }
        char turnOf;
        if ((moveNum % 2) == 0) {
            turnOf = 'W';
        } else {
            turnOf = 'B';
        }

        String move = moves.get(moveNum);
        move = move.replace("x", "");
        move = move.replace("+", "");
        if (move.equals("O-O-O") || move.equals("O-O")) {
            nextMoveCastle();
            return true;
        }

        boolean promote = false;
        int fromX, fromY, toX, toY;

        char piece = 'P';
        if (Character.isUpperCase(move.charAt(0))) {
            piece = move.charAt(0);
            move = move.substring(1);
        }
        if (piece == 'N') {
            piece = 'H';
        }

        if (move.indexOf('=') >= 0) {
            promote = true;
            move = move.substring(0, move.length() - 2);
        }
//          move = move.concat(String.valueOf((char) (Math.abs(toY) + 97)));
//          move = move.concat(Math.abs(toX - 7) + 1 + "");
        System.out.println(piece + " " + move);
        if (move.length() == 2) {
            toX = Math.abs(Integer.parseInt(move.charAt(1) + "") - 8);
            toY = move.charAt(0) - 97;

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getPiece(i, j) == piece && board.getColor(i, j) == turnOf) {
                        for (int[] m : board.movesWithoutCheck(i, j)) {
                            if (m[0] == toX && m[1] == toY) {
                                moveNum++;
                                fromX = i;
                                fromY = j;
                                board.move(fromX, fromY, toX, toY);
                                System.out.println("moving " + piece + " from " + fromX + " " + fromY + " to " + toX + " " + toY);
                                return true;
                            }
                        }
                    }
                }
            }

        } else {
            toX = Math.abs(Integer.parseInt(move.charAt(2) + "") - 8);
            toY = move.charAt(1) - 97;
            fromY = move.charAt(0) - 97;
            for (int i = 0; i < 8; i++) {
                if (board.getPiece(i, fromY) == piece && board.getColor(i, fromY) == turnOf) {
                    for (int[] m : board.movesWithoutCheck(i, fromY)) {
                        if (m[0] == toX && m[1] == toY) {
                            moveNum++;
                            fromX = i;
                            board.move(fromX, fromY, toX, toY);
                            return true;
                        }
                    }
                }
            }
        }
        System.out.println("UHH OHH NIECO SA POKAZILO");
        return false;
    }

    public void nextMoveCastle() {

        String move = moves.get(moveNum);
        char turnOf = moveNum % 2 == 0 ? 'W' : 'B';
        int fromX, fromY, toX, toY;

        fromX = turnOf == 'W' ? 7 : 0;
        fromY = 4;
        toX = fromX;
        toY = move.length() == 3 ? 6 : 2;

        board.move(fromX, fromY, toX, toY);
        moveNum++;

    }

    public void prevMove() {
        moveTo(moveNum-1);
    }

    public void prevMoveDelete() {
        moveToDelete(moveNum-1);
    }

    public void moveToEnd() {
        board.setupClassic();
        moveNum = 0;
        pgnMoving = true;
        while (nextMove()) {
            System.out.println("MOVING");
        }
        pgnMoving = false;
    }

    public void moveTo(int n) {
        board.setupClassic();
        moveNum = 0;
        pgnMoving = true;
        for (int i = 0; i < n; i++) {
            if (!nextMove()) break;
        }
        pgnMoving = false;
    }

    public void moveToDelete(int n) {
        ArrayList<String> newMoves = new ArrayList<>();
        board.setupClassic();
        moveNum = 0;
        pgnMoving = true;
        for (int i = 0; i < n; i++) {
            newMoves.add(moves.get(i));
            if (!nextMove()) break;
        }
        moves = newMoves;
        pgnMoving = false;
    }
}
