package src.screens.JoinServer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// private class (only to be used by JoinServerScreen.java)
class ScreenHandler {
    private final JoinServerScreen frame;

    public ScreenHandler(JoinServerScreen frame) {
        this.frame = frame;
    }

    public ActionListener joinServerOnClick() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
    }
}
