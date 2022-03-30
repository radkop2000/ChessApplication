public class ManagerUI {

    public static final int MENU_MAIN =             0;
    public static final int MENU_PLAY_BUILDER =     1;
    public static final int MENU_PLAY_END_GAME =    2;
    public static final int MENU_PLAY_PGN =         3;
    public static final int MENU_PLAY =             4;
    public static final int MENU_REPLAY =           5;
    public static final int MENU_SETTINGS =         6;


    Operator op;
    Window window;

    MenuMainUI main;
    MenuPlayBuilderUI builder;
    MenuPlayEndGameUI endGame;
    MenuPlayPGNUI playPGN;
    MenuPlayUI play;
    MenuReplayUI replay;
    MenuSettingsUI settings;


    public ManagerUI(Operator op, Window window) {
        this.op = op;
        this.window = window;
        main = new MenuMainUI(this);
        builder = new MenuPlayBuilderUI(this);
        endGame = new MenuPlayEndGameUI(this);
        playPGN = new MenuPlayPGNUI(this);
        play = new MenuPlayUI(this);
        replay = new MenuReplayUI(this);
        settings = new MenuSettingsUI(this);
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
            case 1 -> window.showPanel(builder);
            case 2 -> window.showPanel(endGame);
            case 3 -> window.showPanel(playPGN);
            case 4 -> window.showPanel(play);
            case 5 -> window.showPanel(replay);
            case 6 -> window.showPanel(settings);
        }
    }
}
