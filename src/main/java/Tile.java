import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tile {

    GameUI gameUI;
    ReplayUI replayUI;
    int x;
    int y;
    JLabel tile;
    Color light = new Color(180, 123, 0);
    Color lightDarker = light.darker().darker();
    Color dark = new Color(141, 97, 0);
    Color darkDarker = dark.darker().darker();


    public Tile(GameUI gameUI, int x, int y) {
        this.gameUI = gameUI;
        this.x = x;
        this.y = y;
        tile = new JLabel();
        tile.setBounds(220 + y * 94, 7 + x * 94, 88, 88);

        // region mouseListener
        tile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!gameUI.gm.ch.isTurn) {
                    return;
                }
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (gameUI.gm.ch.turnOf == gameUI.gm.ch.board[x][y].getColor() ) {
                        gameUI.paintMoves(x, y);
                    } else if (gameUI.gm.ch.board[x][y].getYellow()) {
                        gameUI.move(x, y);
                    } else if (gameUI.gm.ch.board[x][y].getRed()) {
                        gameUI.take(x, y);
                    } else {
                        gameUI.unpaintMoves();
                    }
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
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        // endregion

        if ((x+y)%2 == 0) {
            tile.setBackground(light);
        } else {
            tile.setBackground(dark);
        }
        tile.setOpaque(true);
        gameUI.mainPanel.add(tile);
    }

    public Tile(ReplayUI replayUI, int x, int y) {
        this.replayUI = replayUI;
        this.x = x;
        this.y = y;
        tile = new JLabel();
        tile.setBounds(220 + y * 94, 7 + x * 94, 88, 88);

        if ((x+y)%2 == 0) {
            tile.setBackground(light);
        } else {
            tile.setBackground(dark);
        }
        tile.setOpaque(true);
        replayUI.mainPanel.add(tile);
        }

    public void setPiece(String piece) {
        ImageIcon icon = new ImageIcon("src/main/resources/Pieces/" + piece + ".png");
        tile.setIcon(icon);
    }

    public void removePiece() {
        tile.setIcon(null);
    }

    public void setColor(int color) {  //  0=None, 1=Yellow, 2=Red
        switch (color) {
            case 0:
                if ((x+y)%2 == 0) {
                    tile.setBackground(light);
                } else {
                    tile.setBackground(dark);
                }
                break;
            case 1:
            case 2:
                if ((x+y)%2 == 0) {
                    tile.setBackground(lightDarker);
                } else {
                    tile.setBackground(darkDarker);
                }
                break;
        }
    }

}
