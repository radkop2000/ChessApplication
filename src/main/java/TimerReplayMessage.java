import java.util.TimerTask;

public class TimerReplayMessage extends TimerTask {

    ReplayUI ui;

    public TimerReplayMessage(ReplayUI ui) {
        this.ui = ui;
    }

    @Override
    public void run() {
        ui.messageLabel.setText("");
    }

}
