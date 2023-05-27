package test.java.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.java.Clicker;

public class LoadPGNTest {
    Clicker clicker;

    @BeforeEach
    void setup() {
        clicker = new Clicker(true);
    }

    @Test
    void pressPlay_loadPGN_paste_invalidHeader() {
        clicker.simulateLabelClick(clicker.op.ui.main.buttonStart);
        clicker.simulateLabelClick(clicker.op.ui.play.buttonPGN);
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

    }

}
