import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    JPanel activePanel;


    public Window() {
        setup();
        activePanel = new JPanel();
    }

    private void setup() {
        // TODO
    }

    public void showPanel(JPanel panel) {
        remove(activePanel);
        add(panel);
        activePanel = panel;
    }
}
