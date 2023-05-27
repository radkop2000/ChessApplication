package test.java.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.java.Clicker;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class LoadPGNTest {
    Clicker clicker;

    @BeforeEach
    void setup() {
        clicker = new Clicker(true);
    }

    @Test
    void pressPlay_loadPGN_paste_invalidHeader() throws InterruptedException {
        clicker.simulateLabelClick(clicker.op.ui.main.buttonStart);
        sleep(500);
        Assertions.assertEquals(clicker.op.ui.play, clicker.op.window.activePanel);
        clicker.simulateLabelClick(clicker.op.ui.play.buttonPGN);
        sleep(500);
        Assertions.assertEquals(clicker.op.ui.playPGN, clicker.op.window.activePanel);
        clicker.op.ui.playPGN.textArea.setText("[Event \"Test\"]\n" +
                "[Site \"Test\"]\n" +
                "[Date \"2020.01.01\"]\n" +
                "[Round \"1\"]\n" +
                "[White \"Test\"]\n" +
                "[Black \"Test\"]\n" +
                "[Result \"*\"]\n" +
                "[WhiteElo \"*\"]\n" +
                "[BlackElo \"*\"]\n" +
                "[ECO \"*\"]\n" +
                "[Opening \"*\"]\n" +
                "[Variation \"*\"]\n" +
                "[EventDate \"*\"]\n" +
                "[Annotator \"*\"]\n" +
                "\n" +
                "1. e4 e5 2. Nf3 Nc6 3. Bb5 a6 4. Ba4 Nf6");
        Assertions.assertThrows(NumberFormatException.class, () -> {
            clicker.op.ui.playPGN.loadButton.doClick();
        });
        sleep(500);
        Assertions.assertEquals(clicker.op.ui.playPGN, clicker.op.window.activePanel);
    }

    @Test
    void pressPlay_loadPGN_paste_wrongTurnNumbers() throws InterruptedException {
        clicker.simulateLabelClick(clicker.op.ui.main.buttonStart);
        sleep(500);
        Assertions.assertEquals(clicker.op.ui.play, clicker.op.window.activePanel);
        clicker.simulateLabelClick(clicker.op.ui.play.buttonPGN);
        sleep(500);
        Assertions.assertEquals(clicker.op.ui.playPGN, clicker.op.window.activePanel);
        clicker.op.ui.playPGN.textArea.setText("[Event \"Chess Game\"]\n" +
                "[Site \"Prague\"]\n" +
                "[Date \"2023-05-27\"]\n" +
                "[Round \"5\"]\n" +
                "[White \"?\"]\n" +
                "[Black \"?\"]\n" +
                "[Result \"*\"]\n" +
                "\n" +
                "0. a4 c5 2. a5 c4 3. a6 c3 4. xb7 xb2 5. xa8 xa1  *");

        clicker.op.ui.playPGN.loadButton.doClick();
        sleep(500);
        Assertions.assertEquals(clicker.op.ui.game, clicker.op.window.activePanel);
    }

    @Test
    void pressPlay_loadPGN_paste_validPGN_playMove() throws InterruptedException {
        clicker.simulateLabelClick(clicker.op.ui.main.buttonStart);
        sleep(500);
        Assertions.assertEquals(clicker.op.ui.play, clicker.op.window.activePanel);
        clicker.simulateLabelClick(clicker.op.ui.play.buttonPGN);
        sleep(500);
        Assertions.assertEquals(clicker.op.ui.playPGN, clicker.op.window.activePanel);
        clicker.op.ui.playPGN.textArea.setText("[Event \"Chess Game\"]\n" +
                "[Site \"Prague\"]\n" +
                "[Date \"2023-05-27\"]\n" +
                "[Round \"5\"]\n" +
                "[White \"?\"]\n" +
                "[Black \"?\"]\n" +
                "[Result \"*\"]\n" +
                "\n" +
                "1. a4 c5 2. a5 c4 3. a6 c3 4. xb7 xb2 *");

        clicker.op.ui.playPGN.loadButton.doClick();
        sleep(500);
        Assertions.assertEquals(clicker.op.ui.game, clicker.op.window.activePanel);
        move('W', 7, 6, 5, 5);
        move('B', 1, 0, 2, 0);
    }

    @Test
    void pressPlay_loadPGN_paste_validPGN_undoMove() throws InterruptedException {
        clicker.simulateLabelClick(clicker.op.ui.main.buttonStart);
        sleep(500);
        Assertions.assertEquals(clicker.op.ui.play, clicker.op.window.activePanel);
        clicker.simulateLabelClick(clicker.op.ui.play.buttonPGN);
        sleep(500);
        Assertions.assertEquals(clicker.op.ui.playPGN, clicker.op.window.activePanel);
        clicker.op.ui.playPGN.textArea.setText("[Event \"Chess Game\"]\n" +
                "[Site \"Prague\"]\n" +
                "[Date \"2023-05-27\"]\n" +
                "[Round \"5\"]\n" +
                "[White \"?\"]\n" +
                "[Black \"?\"]\n" +
                "[Result \"*\"]\n" +
                "\n" +
                "1. a4 c5 2. a5 c4 3. a6 c3 4. xb7 xb2 *");

        clicker.op.ui.playPGN.loadButton.doClick();
        sleep(500);
        Assertions.assertEquals(clicker.op.ui.game, clicker.op.window.activePanel);
        clicker.simulateLabelClick(clicker.op.ui.game.buttonUndo);
        sleep(500);
        Assertions.assertEquals('P', clicker.op.ui.game.board.getPiece(5,2));
        Assertions.assertEquals('B', clicker.op.ui.game.board.getColor(5,2));
        Assertions.assertEquals('P', clicker.op.ui.game.board.getPiece(6,1));
        Assertions.assertEquals('W', clicker.op.ui.game.board.getColor(6,1));
    }

    @Test
    void pressReplay_loadPGN_fromFile_validPGN_nextMove() throws InterruptedException, IOException {
        clicker.simulateLabelClick(clicker.op.ui.main.buttonReplay);
        sleep(500);
        Assertions.assertEquals(clicker.op.ui.replayMenu, clicker.op.window.activePanel);

        clicker.op.ui.replay.board.pgn.loadPGNFromFile("src/test/resources/test.pgn");
        sleep(500);
        Assertions.assertEquals(clicker.op.ui.replay, clicker.op.window.activePanel);
    }

    public void move(char turnOf, int fromX, int fromY, int toX, int toY) {
        Assertions.assertEquals(turnOf, clicker.op.ui.game.board.turnOf);
        clicker.simulateTileClick(fromX,fromY);
        clicker.simulateTileClick(toX,toY);
        try {
            sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
