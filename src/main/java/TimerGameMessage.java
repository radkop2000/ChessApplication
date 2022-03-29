import java.util.TimerTask;

public class TimerGameMessage extends TimerTask {

    GameUI ui;

    public TimerGameMessage(GameUI ui) {
        this.ui = ui;
    }

    @Override
    public void run() {
        ui.messageText.setText("");
    }
}
