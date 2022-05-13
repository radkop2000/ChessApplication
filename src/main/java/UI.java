import java.awt.*;
import java.awt.event.MouseEvent;

public interface UI {

    public void tilePressed(int x, int y);

    Component getComp();

    boolean getDev();
}
