import javax.swing.*;
import java.awt.*;

public class GameManager {

    Chess ch;
    Window window;
    GameUI gameUI;
    MenuUI menuUI;
    ReplayUI replayUI;

    JPanel currentPanel = null;

    public static void main(String[] args) {
        GameManager gm = new GameManager();
    }

    public GameManager() {
        ch = new Chess(this);
        window = new Window(this);
        gameUI = new GameUI(this);
        menuUI = new MenuUI(this);
        replayUI = new ReplayUI(this);
        menuUI.start();
        gameUI.start();
        replayUI.start();
        menuUI.show();
    }

    public void exit() {

        System.exit(0);
    }
}
