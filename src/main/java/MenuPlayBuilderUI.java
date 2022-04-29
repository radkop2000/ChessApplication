import javax.swing.*;

public class MenuPlayBuilderUI extends JPanel implements UI{

    ManagerUI ui;
    Tile[][] tiles = new Tile[8][8];

    public MenuPlayBuilderUI(ManagerUI ui) {
        this.ui = ui;
        setup();
    }


    public void setup() {




        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tiles[i][j] = new Tile(this, i, j);
            }
        }
    }

    @Override
    public void tilePressed(int x, int y) {

    }
}
