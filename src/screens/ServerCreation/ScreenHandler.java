package src.screens.ServerCreation;

import java.awt.event.ActionListener;

// private class (only to be used by ServerCreationScreen.java)
class ScreenHandler {
    private final ServerCreationScreen frame;

    public ScreenHandler(ServerCreationScreen frame) {
        this.frame = frame;
    }

    public ActionListener confirmServerCreationOnClick() {
        return new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent event) {
                System.out.println("Server creation confirmed");
            }
        };
    };
}
