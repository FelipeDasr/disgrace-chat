package src.screens.JoinServer;

import java.awt.FontFormatException;
import java.io.IOException;

import src.screens.SecondStepGeneric.SecondStepGenericScreen;

public class JoinServerScreen extends SecondStepGenericScreen {
    private final ScreenHandler screenHandler;

    public JoinServerScreen() throws FontFormatException, IOException {
        super("Entrar em um servidor");
        this.screenHandler = new ScreenHandler(this);

        this.setFirstFormInputLabel("Host:              ");
        this.setSecondFormInputLabel("Porta:             ");
        this.setButtonLabel("Entrar");

        this.getMainButton().addActionListener(this.screenHandler.joinServerOnClick());
    }

    public static void main(String[] args) throws FontFormatException, IOException {
        JoinServerScreen screen = new JoinServerScreen();
        screen.show();
    }
}
