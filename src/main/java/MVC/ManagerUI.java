package MVC;

import javax.swing.*;

public class ManagerUI {

    // A list of constants that are used to identify the different panels.
    public static final int MENU_MAIN =             0;
    public static final int MENU_PLAY_BUILDER =     1;
    public static final int MENU_PLAY_END_GAME =    2;
    public static final int MENU_PLAY_PGN =         3;
    public static final int MENU_PLAY =             4;
    public static final int MENU_REPLAY =           5;
    public static final int MENU_SETTINGS =         6;
    public static final int GAME =                  7;
    public static final int REPLAY =                8;
    public static final int DICE =                  9;

    public Operator op;
    Window window;

    public MenuMainUI main;
    MenuSituationUI builder;
    MenuPlayEndGameUI endGame;
    public MenuPlayPGNUI playPGN;
    public MenuPlayUI play;
    MenuReplayUI replayMenu;
    MenuSettingsUI settings;
    public GameUI game;
    ReplayUI replay;
    DiceGameUI dice;



    public ManagerUI(Operator op, Window window) {
        this.op = op;
        this.window = window;
        main = new MenuMainUI(this);
        builder = new MenuSituationUI(this);
        endGame = new MenuPlayEndGameUI(this);
        playPGN = new MenuPlayPGNUI(this);
        play = new MenuPlayUI(this);
        replayMenu = new MenuReplayUI(this);
        settings = new MenuSettingsUI(this);
        game = new GameUI(this);
        replay = new ReplayUI(this);
        dice = new DiceGameUI(this);

        showPanel(MENU_MAIN);
    }

    /**
     * It shows a panel
     *
     * @param panel the panel to show
     */
    public void showPanel(int panel) {
/*
0   GUI.MenuMainUI
1   MenuPlayBuilderUI
2   GUI.MenuPlayEndGameUI
3   GUI.MenuPlayPGNUI
4   GUI.MenuPlayUI
5   GUI.MenuReplayUI
6   GUI.MenuSettingsUI
 */
        switch (panel) {
            case 0 -> window.showPanel(main);
            case 1 -> {
                window.showPanel(builder);
                builder.resetBoard();
            }
            case 2 -> window.showPanel(endGame);
            case 3 -> window.showPanel(playPGN);
            case 4 -> {
                window.showPanel(play);
            }
            case 5 -> window.showPanel(replayMenu);
            case 6 -> window.showPanel(settings);
            case 7 -> {
                window.showPanel(game);
                game.board.startClock();
            }
            case 8 -> window.showPanel(replay);
            case 9 -> {
                window.showPanel(dice);
                dice.board.setupClassic();
                dice.redrawBackground();
                op.window.repaint();
            }
        }
    }

    /**
     * This function is called when the game ends, and it shows the end game panel.
     *
     * @param panel The panel that the game is being played on.
     * @param winner The winner of the game.
     */
    public void endGame(JPanel panel, char winner) {
        showPanel(ManagerUI.MENU_PLAY_END_GAME);
        endGame.endGame(panel, winner);
    }
}
