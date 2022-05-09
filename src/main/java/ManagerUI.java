public class ManagerUI {

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

    Operator op;
    Window window;

    MenuMainUI main;
    MenuSituationUI builder;
    MenuPlayEndGameUI endGame;
    MenuPlayPGNUI playPGN;
    MenuPlayUI play;
    MenuReplayUI replayMenu;
    MenuSettingsUI settings;
    GameUI game;
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

    public void showPanel(int panel) {
/*
0   MenuMainUI
1   MenuPlayBuilderUI
2   MenuPlayEndGameUI
3   MenuPlayPGNUI
4   MenuPlayUI
5   MenuReplayUI
6   MenuSettingsUI
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
            case 7 -> window.showPanel(game);
            case 8 -> window.showPanel(replay);
            case 9 -> {
                window.showPanel(dice);
                dice.board.setupClassic();
                dice.redrawBackground();
                op.window.repaint();
            }
        }
    }
}
