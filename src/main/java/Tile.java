import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tile extends JLabel {

    Board board;
    int x;
    int y;
    boolean isLight;
    boolean isHighlighted;
    Color light = new Color(180, 123, 0);
    Color lightDarker = light.darker().darker();
    Color dark = new Color(141, 97, 0);
    Color darkDarker = dark.darker().darker();

    public Tile(Board board, int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
        setBounds(220 + y * 94, 7 + x * 94, 88, 88);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (board.playable) {
                    board.tilePressed(x, y);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mouseOn();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseOff();
            }
        });
        if ((x+y)%2 == 0) {
            setBackground(light);
            isLight = true;
        } else {
            setBackground(dark);
            isLight = false;
        }
        isHighlighted = false;
        setOpaque(true);
    }

    public void setPiece(String piece) {
        ImageIcon icon = new ImageIcon("src/main/resources/Pieces/" + piece + ".png");
        setIcon(icon);
    }

    public void removePiece() {
        setIcon(null);
    }

    public void mouseOn() {
        if (isLight) {
            setBackground(lightDarker);
        } else {
            setBackground(darkDarker);
        }
    }

    public void mouseOff() {
        if (isHighlighted) {
            return;
        }
        if (isLight) {
            setBackground(light);
        } else {
            setBackground(dark);
        }
    }

    public void setDarker() {
        if (isLight) {
            setBackground(lightDarker);
        } else {
            setBackground(darkDarker);
        }
        isHighlighted = true;
    }

    public void setLigher() {
        if (isLight) {
            setBackground(light);
        } else {
            setBackground(dark);
        }
        isHighlighted = false;
    }

}