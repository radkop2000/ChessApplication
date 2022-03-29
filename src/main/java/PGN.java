import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Character.isDigit;
import static java.lang.Character.isUpperCase;

public class PGN {

    Chess ch;

    String currentGame = "";
    int charsInLine = 0;
    char turnOf = 'W';

    ArrayList<String> moves = new ArrayList<String>();
    int moveIDX = 0;

    public PGN(Chess ch) {
        this.ch = ch;
    }

    public void startNewPGN() {
        currentGame = "";
    }

    public String getPGN() {
        String date = java.time.LocalDate.now().toString();
        int round = ch.turn;
        String result;
        if (ch.state == -1) {
            result = "*";
        } else if (ch.state == 0) {
            result = "1-0";
        } else if (ch.state == 1) {
            result = "0-1";
        } else {
            result = "1/2-1/2";
        }


        String header = String.format("[Event \"Chess Game\"]\n[Site \"Prague\"]\n[Date \"%s\"]\n[Round \"%d\"]\n[White \"?\"]\n[Black \"?\"]\n[Result \"%s\"]\n\n", date, round, result);


        return header.concat(currentGame).concat(result);

    }

    public void updatePGN(int fromX, int fromY, int toX, int toY, boolean takes, char piece) {
        String add = "";
        boolean addPiece = takes;


        if (ch.turnOf == 'W') {
            add = add.concat(ch.turn + ". ");
        }

        if (piece == 'K' && Math.abs(fromY - toY) == 2) {
            add = add.concat("O-O ");
            charsInLine += add.length();
            if (charsInLine > 70) {
                charsInLine = 0;
                add = add.concat("\n");
            }

            currentGame = currentGame.concat(add);
            return;
        }
        if (piece == 'K' && Math.abs(fromY - toY) == 3) {
            add = add.concat("O-O-O ");
            charsInLine += add.length();
            if (charsInLine > 70) {
                charsInLine = 0;
                add = add.concat("\n");
            }

            currentGame = currentGame.concat(add);
            return;
        }

        for (int i = 0; i < 8; i++) {
            if (addPiece) break;
            for (int j = 0; j < 8; j++) {
                if (ch.board[i][j].getColor() == ch.turnOf && ch.board[i][j].getClass().getName().charAt(0) == piece && i != fromX && j != fromY) {
                    int[][] moves = ch.board[i][j].PossibleMoves();
                    int idx = 0;
                    while (moves[idx][0] != -1) {
                        if (moves[idx][0] == toX && moves[idx][1] == toY) {
                            addPiece = true;
                            break;
                        }
                        idx++;
                    }
                }
            }
        }

        if (piece == 'H') {
            piece = 'N';
        }

        if (piece != 'P') {
            add = add.concat(piece + "");
        }

        if (addPiece) {
            add = add.concat(String.valueOf((char) (Math.abs(fromY) + 97)));
        }
        if (takes) {
            add = add.concat("x");
        }

        add = add.concat(String.valueOf((char) (Math.abs(toY) + 97)));
        add = add.concat(Math.abs(toX - 7) + 1 + "");

        if (piece == 'P' && (toX == 7 || toX == 0)) {
            add = add.concat("=Q");
        }


        Piece temp1 = ch.board[fromX][fromY];
        Piece temp2 = ch.board[toX][toY];
        ch.board[toX][toY] = ch.board[fromX][fromY];
        ch.board[fromX][fromY] = new Empty(ch, fromX, fromY, 'N');
        ch.board[toX][toY].setX(toX);
        ch.board[toX][toY].setY(toY);
        int[] king;

        if (ch.turnOf == 'W') {
            king = ch.findKing('B');
        } else {
            king = ch.findKing('W');
        }

        int[][] moves = ch.board[toX][toY].PossibleMoves();
        int idx = 0;
        while (moves[idx][0] != -1) {
            if (moves[idx][0] == king[0] && moves[idx][1] == king[1]) {
                add = add.concat("+");
                break;
            }
            idx++;
        }
        ch.board[fromX][fromY] = temp1;
        ch.board[toX][toY] = temp2;
        ch.board[toX][toY].setX(fromX);
        ch.board[toX][toY].setY(fromY);

        charsInLine += add.length();
        if (charsInLine > 70) {
            charsInLine = 0;
            add = add.concat("\n");
        } else {
            add = add.concat(" ");
        }

        currentGame = currentGame.concat(add);
    }

    public void addCheckMate() {
        currentGame = currentGame.substring(0, currentGame.length() - 2);
        currentGame = currentGame.concat("# ");
    }

    public void copyPGN() {
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

    public void loadPGNFile(String fileName) throws IOException {
        String actual = Files.readString(Path.of(fileName));
        currentGame = actual;
        splitToMoves();
    }

    public void loadPGNString(String pgn) {
        currentGame = pgn;
        splitToMoves();
    }

    public void nextTurnReplay() {
        if (moveIDX == moves.size() - 1) {
            ch.gm.replayUI.showEndMessage();
        } else if (moveIDX > moves.size() - 1) {
            ch.gm.replayUI.showEndMessage();
            return;
        }
        StringBuilder move = new StringBuilder();
        move.append(moves.get(moveIDX++));
        if (String.valueOf(move).equals("O-O")) {
            if (turnOf == 'W') {
                ch.gm.replayUI.move(turnOf, 'K', 7, 6);

                ch.gm.replayUI.putPiece(7, 5, turnOf, 'R');
                ch.gm.replayUI.tiles[7][5].setPiece(turnOf + "" + 'R');

                ch.gm.replayUI.tiles[7][7].removePiece();
                ch.gm.replayUI.putPiece(7, 7, 'N', 'N');
            } else {
                ch.gm.replayUI.move(turnOf, 'K', 0, 6);

                ch.gm.replayUI.putPiece(0, 5, turnOf, 'R');
                ch.gm.replayUI.tiles[0][5].setPiece(turnOf + "" + 'R');

                ch.gm.replayUI.tiles[0][7].removePiece();
                ch.gm.replayUI.putPiece(0, 7, 'N', 'N');
            }
            changeTurnOf();
            return;
        }
        if (String.valueOf(move).equals("O-O-O")) {
            if (turnOf == 'W') {
                ch.gm.replayUI.move(turnOf, 'K', 7, 2);
                ch.gm.replayUI.putPiece(7, 3, turnOf, 'R');
                ch.gm.replayUI.tiles[7][3].setPiece(turnOf + "" + 'R');

                ch.gm.replayUI.tiles[7][0].removePiece();
                ch.gm.replayUI.putPiece(7, 0, 'N', 'N');
            } else {
                ch.gm.replayUI.move(turnOf, 'K', 0, 2);
                ch.gm.replayUI.putPiece(0, 3, turnOf, 'R');
                ch.gm.replayUI.tiles[0][3].setPiece(turnOf + "" + 'R');

                ch.gm.replayUI.tiles[0][0].removePiece();
                ch.gm.replayUI.putPiece(0, 0, 'N', 'N');
            }
            changeTurnOf();
            return;
        }
        if (move.indexOf("N") != -1) {
            move.setCharAt(move.indexOf("N"), 'H');
        }
        if (move.indexOf("*") != -1) {
            move.deleteCharAt(move.indexOf("*"));
        }
        if (move.indexOf("+") != -1) {
            move.deleteCharAt(move.indexOf("+"));
        }
        if (move.indexOf("x") != -1) {
            move.deleteCharAt(move.indexOf("x"));
        }
        if (move.indexOf("=") != -1) {
            move.deleteCharAt(move.indexOf("="));
            ch.gm.replayUI.move(turnOf, 'P', Math.abs(move.charAt(1) - 56), (int) move.charAt(0) - 97);
            ch.gm.replayUI.putPiece(Math.abs(move.charAt(1) - 56), (int) move.charAt(0) - 97, turnOf, move.charAt(2));
            ch.gm.replayUI.tiles[Math.abs(move.charAt(1) - 56)][(int) move.charAt(0) - 97].setPiece(turnOf + "" + move.charAt(2));
            changeTurnOf();
            return;
        }
        if (move.length() == 2) {
            System.out.println(move);
            ch.gm.replayUI.move(turnOf, 'P', Math.abs(move.charAt(1) - 56), (int) move.charAt(0) - 97);
        } else if (!isUpperCase(move.charAt(0)) && move.length() == 3) {
            ch.gm.replayUI.move(turnOf, 'P', move.charAt(0)-97, Math.abs(move.charAt(2) - 56), (int) move.charAt(1) - 97);
        } else if (move.length() == 3) {
            System.out.println(move);
            ch.gm.replayUI.move(turnOf, move.charAt(0) , Math.abs(move.charAt(2) - 56), (int) move.charAt(1) - 97);
        } else if ((move.length() == 4)) {
            System.out.println(move);
            ch.gm.replayUI.move(turnOf, move.charAt(0), move.charAt(1)-97, Math.abs(move.charAt(3) - 56), (int) move.charAt(2) - 97);
        } else {
            System.out.println("NOT A VALID MOVE UHH OHH");
        }

        changeTurnOf();
    }

    public void nextTurnGame() {
        if (moveIDX == moves.size() - 1) {
            // TODO
        } else if (moveIDX > moves.size() - 1) {
            // TODO
//            return;
        }
        StringBuilder move = new StringBuilder();
        move.append(moves.get(moveIDX++));
        if (String.valueOf(move).equals("O-O")) {
            if (turnOf == 'W') {
                // TOOD
            } else {
                // TODO
            }
            changeTurnOf();
            return;
        }
        if (String.valueOf(move).equals("O-O-O")) {
            if (turnOf == 'W') {
                // TODO
            } else {
                // TODO
            }
            changeTurnOf();
            return;
        }
        if (move.indexOf("N") != -1) {
            move.setCharAt(move.indexOf("N"), 'H');
        }
        if (move.indexOf("*") != -1) {
            move.deleteCharAt(move.indexOf("*"));
        }
        if (move.indexOf("+") != -1) {
            move.deleteCharAt(move.indexOf("+"));
        }
        if (move.indexOf("x") != -1) {
            move.deleteCharAt(move.indexOf("x"));
        }
        if (move.indexOf("=") != -1) {
            move.deleteCharAt(move.indexOf("="));
            // TODO
            changeTurnOf();
            return;
        }
        if (move.length() == 2) {
            ch.gm.gameUI.movePGN(turnOf, 'P', Math.abs(move.charAt(1) - 56), (int) move.charAt(0) - 97);
//            ch.gm.replayUI.move(turnOf, 'P', Math.abs(move.charAt(1) - 56), (int) move.charAt(0) - 97);
        } else if (!isUpperCase(move.charAt(0)) && move.length() == 3) {
            ch.gm.gameUI.movePGN(turnOf, 'P', move.charAt(0)-97, Math.abs(move.charAt(2) - 56), (int) move.charAt(1) - 97);
//            ch.gm.replayUI.move(turnOf, 'P', move.charAt(0)-97, Math.abs(move.charAt(2) - 56), (int) move.charAt(1) - 97);
        } else if (move.length() == 3) {
            ch.gm.gameUI.movePGN(turnOf, move.charAt(0) , Math.abs(move.charAt(2) - 56), (int) move.charAt(1) - 97);
//            ch.gm.replayUI.move(turnOf, move.charAt(0) , Math.abs(move.charAt(2) - 56), (int) move.charAt(1) - 97);
        } else if ((move.length() == 4)) {
            ch.gm.gameUI.movePGN(turnOf, move.charAt(0), move.charAt(1)-97, Math.abs(move.charAt(3) - 56), (int) move.charAt(2) - 97);
//            ch.gm.replayUI.move(turnOf, move.charAt(0), move.charAt(1)-97, Math.abs(move.charAt(3) - 56), (int) move.charAt(2) - 97);
        } else {
            System.out.println("NOT A VALID MOVE UHH OHH");
        }

        changeTurnOf();
    }

    public void changeTurnOf() {
        if (turnOf == 'W') {
            turnOf = 'B';
        } else {
            turnOf = 'W';
        }
    }

    public void splitToMoves() {
        moves.clear();
        int counter = 0;
        int idx = 0;
        currentGame = getPGN();
        while (counter < 8) {
            if (currentGame.charAt(idx++) == '\n') {
                System.out.println("FOUND ENTER");
                counter++;
            }
        }
        StringBuilder sb = new StringBuilder();
        while (idx < currentGame.length() - 1) {
            while (idx < currentGame.length() - 1 && (currentGame.charAt(idx) != ' ' && currentGame.charAt(idx) != '\n')) {
                if (currentGame.charAt(idx) == '{') {
                    while (idx < currentGame.length() - 1 && currentGame.charAt(idx++) != '}') {
                    }
                    break;
                }
                sb.append(currentGame.charAt(idx++));
            }
            if (sb.length() < 2) {
                sb.setLength(0);
                idx++;
                continue;
            }
            if (isDigit(sb.charAt(0))) {
                idx++;
                sb.setLength(0);
                continue;
            }
            moves.add(String.valueOf(sb));
            idx++;
            sb.setLength(0);
        }
        System.out.println("STRING SPLIT SUCCESSFULLY");
    }
}

