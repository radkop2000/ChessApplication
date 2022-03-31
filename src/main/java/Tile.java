import javax.swing.*;
import java.awt.*;

public class Tile extends JLabel {

    Board board;
    int x;
    int y;
    boolean isLight;
    Color light = new Color(180, 123, 0);
    Color lightDarker = light.darker().darker();
    Color dark = new Color(141, 97, 0);
    Color darkDarker = dark.darker().darker();

    public Tile(Board board, int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
        setBounds(220 + y * 94, 7 + x * 94, 88, 88);
        // TODO mouse listener
        if ((x+y)%2 == 0) {
            setBackground(light);
            isLight = true;
        } else {
            setBackground(dark);
            isLight = false;
        }
        setOpaque(true);
    }

    public void setPiece(String piece) {
        ImageIcon icon = new ImageIcon("src/main/resources/Pieces/" + piece + ".png");
        setIcon(icon);
    }

    public void removePiece() {
        setIcon(null);
    }

    public void setDarker() {
        if (isLight) {
            setBackground(lightDarker);
        } else {
            setBackground(darkDarker);
        }
    }

    public void removeDarker() {
        if (isLight) {
            setBackground(light);
        } else {
            setBackground(darkDarker);
        }
    }

}
